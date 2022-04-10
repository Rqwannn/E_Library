package com.example.e_library.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.R;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), EditProfile.this);
    }

    public void BackToProfile(View view) {
        finish();
    }
}