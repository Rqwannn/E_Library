package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.JWTOptions.JWTAuth;
import com.example.e_library.Pinjam_buku.PinjamanSaya;

public class Konfirmasi extends AppCompatActivity {
    SharedPreferences SessionStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), Konfirmasi.this);
//        new JWTAuth().CheckTokens(Konfirmasi.this, SessionStorage.getString("Tokens", ""));
    }

    public void Back(View view) {
        finish();
    }
}