package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class VerifikasiOTP extends AppCompatActivity {
    MaterialButton btn_verifikasi_otp;
    EditText input_code_1, input_code_2, input_code_3, input_code_4, input_code_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_otp);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_verifikasi_otp = findViewById(R.id.btn_verifikasi_otp);
    }
}