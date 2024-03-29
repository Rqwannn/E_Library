package com.example.e_library.Notifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.JWTOptions.JWTAuth;
import com.example.e_library.R;

import java.util.Timer;
import java.util.TimerTask;

public class Notifikasi extends AppCompatActivity {
    int ForceClose = 0;
    SharedPreferences SessionStorage;
    SharedPreferences.Editor SessionEdit;
    ImageView BackImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        new TranslucentOptions().withBGStatusBar(this.getWindow(), Notifikasi.this);

        SessionStorage = getSharedPreferences("SESSION", MODE_PRIVATE);
//        new JWTAuth().CheckTokens(Notifikasi.this, SessionStorage.getString("Tokens", ""));

        BackImg = findViewById(R.id.back_notifikasi);
        BackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notifikasi.this, Beranda.class);
                startActivity(intent);

                SessionEdit = SessionStorage.edit();
                SessionEdit.putString("Activity", "Home");
                SessionEdit.apply();

                finish();
                overridePendingTransition(R.anim.enter_bottom_to_top, R.anim.exit_bottom_to_top);
            }
        });

    }

    @Override
    public boolean onKeyDown(int key_code, KeyEvent key_event) {

        if (key_code == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(key_code, key_event);

            if (ForceClose == 0){
                Toast.makeText(Notifikasi.this, "Tekan lagi untuk keluar", Toast.LENGTH_LONG).show();
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

            if (!SessionStorage.getString("Tokens", "").equals("")){
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

}