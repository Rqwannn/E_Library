package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Welcome.Welcome;

public class SplashSceen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sceen);
        new TranslucentOptions().TransparentStatusAndNavigation(this.getWindow(), SplashSceen.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashSceen.this, Welcome.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.exit_right_to_left);
            }
        }, 1500);
    }
}