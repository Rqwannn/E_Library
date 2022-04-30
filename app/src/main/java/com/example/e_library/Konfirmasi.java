package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Pinjam_buku.PinjamanSaya;

public class Konfirmasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), Konfirmasi.this);
//        new JWTAuth().CheckTokens(Konfirmasi.this, SessionStorage.getString("Tokens", ""));
    }

    public void Back(View view) {
        finish();
    }
}