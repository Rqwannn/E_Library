package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ForgotPassword extends AppCompatActivity {

    MaterialButton btn_submit_otp;
    EditText input_send_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_submit_otp = findViewById(R.id.btn_submit_otp);
        input_send_otp = findViewById(R.id.input_send_otp);

        btn_submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_send_otp.getText().toString().trim().equals("")) {
                    Toast.makeText(ForgotPassword.this, "Mohon Untuk Isi Input Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else if(!input_send_otp.getText().toString().contains("@") || (!input_send_otp.getText().toString().contains("gmail.com") && !input_send_otp.getText().toString().contains("yahoo.co.id"))){
                    Toast.makeText(ForgotPassword.this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ForgotPassword.this, VerifikasiOTP.class);
                    intent.putExtra("Email", input_send_otp.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.exit_right_to_left);
                }
            }
        });

    }
}