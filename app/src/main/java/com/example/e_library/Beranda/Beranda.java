package com.example.e_library.Beranda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.e_library.JWTOptions.JWTAuth;
import com.example.e_library.Kategori_buku.DetailKategoriBuku;
import com.example.e_library.Login;
import com.example.e_library.Notifikasi.Notifikasi;
import com.example.e_library.Profile.ProfileFragment;
import com.example.e_library.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class Beranda extends AppCompatActivity {
    int ForceClose = 0;
    SharedPreferences SessionStorage;
    SharedPreferences.Editor SessionEdit;
    BottomNavigationView BtnView;
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
        BtnView = findViewById(R.id.bottom_navigation_view);
        search = findViewById(R.id.search_buku);

//        new JWTAuth().CheckTokens(Beranda.this, SessionStorage.getString("Tokens", ""));

        if (!SessionStorage.getString("FragmentS", "").equals("")){
            String Name = SessionStorage.getString("FragmentS", "");

            if (Name.equals("Profil")){
                fragment = new ProfileFragment();
                loadFragment(fragment);
                BtnView.setSelectedItemId(R.id.btn_profile);
            } else {
                fragment = new HomeFragment();
                loadFragment(fragment);
                BtnView.setSelectedItemId(R.id.btn_home);
            }
        } else {
            fragment = new HomeFragment();
            loadFragment(fragment);
        }

        BtnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                if (item.getItemId() == R.id.btn_profile){
                    if (!SessionStorage.getString("FragmentS", "").equals("Profil")){
                        fragment = new ProfileFragment();
                        animFragment(fragment, "Profil");
                        return true;
                    }
                } else if (item.getItemId() == R.id.btn_home){
                    if (!SessionStorage.getString("FragmentS", "").equals("Home")){
                        fragment = new HomeFragment();
                        animFragment(fragment, "Home");
                        return true;
                    }
                }

                return false;
            }
        });


    }

    public void animFragment(Fragment fragment, String Text){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_rigth_to_left, R.anim.exit_right_to_left);
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

            if (SessionStorage.getBoolean("Submit", false)){
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public void Notifikasi(View view) {
        Intent intent = new Intent(Beranda.this, Notifikasi.class);
        startActivity(intent);

        SessionEdit = SessionStorage.edit();
        SessionEdit.putString("Activity", "Notifikasi");
        SessionEdit.apply();

        finish();
        overridePendingTransition(R.anim.enter_bottom_to_top, R.anim.stay_position);
    }

    public void DetailBuku(View view) {
        String Params = view.getTag().toString();

        Intent intent = new Intent(Beranda.this, DetailKategoriBuku.class);
        intent.putExtra("Kategori", Params);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
    }
}