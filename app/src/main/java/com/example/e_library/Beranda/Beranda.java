package com.example.e_library.Beranda;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.e_library.JWTOptions.JWTAuth;
import com.example.e_library.Login;
import com.example.e_library.R;

import java.util.Timer;
import java.util.TimerTask;

public class Beranda extends AppCompatActivity {
    int ForceClose = 0;
    SharedPreferences SessionStorage;
    SharedPreferences.Editor SessionEdit;
    Fragment fragment;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SessionStorage = getSharedPreferences("SESSION", MODE_PRIVATE);
        search = findViewById(R.id.search_buku);

//        new JWTAuth().CheckTokens(Beranda.this, SessionStorage.getString("Tokens", ""));

        fragment = new HomeFragment();
        loadFragment(fragment);

        if (SessionStorage.getString("FragmentS", null) != null){
            String Name = SessionStorage.getString("FragmentS", null);

//            if (Name.equals("Kapal")){
//                fragment = new Ship();
//                loadFragment(fragment);
//                BtnView.setSelectedItemId(R.id.btn_ship);
//            }

        }
    }

    public void animFragment(Fragment fragment, String Text){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        transaction.replace(R.id.parent_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        SessionEdit = SessionStorage.edit();
        SessionEdit.putString("FragmentS", Text);
        SessionEdit.apply();
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.parent_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int key_code, KeyEvent key_event) {

        if (key_code == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(key_code, key_event);

            if (ForceClose == 0){
                Toast.makeText(Beranda.this, "Tekan lagi untuk keluar", Toast.LENGTH_LONG).show();
                ForceClose++;

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ForceClose = 0;
                    }
                }, 3600);

            } else {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }

            if (SessionStorage.getInt("Submit", 0) == 1){
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

}