package com.example.e_library.Pinjam_buku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Profile.EditProfile;
import com.example.e_library.R;

public class PinjamanSaya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjaman_saya);
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), PinjamanSaya.this);
    }

    public void BackToProfile(View view) {
        finish();
    }
}