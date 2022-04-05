package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiOTP extends AppCompatActivity {
    MaterialButton btn_verifikasi_otp;
    EditText input_code_1, input_code_2, input_code_3, input_code_4, input_code_5;
    TextView textEmail;
    String otp_1, otp_2, otp_3, otp_4, otp_5, otp;
    Bundle extra;
    int Limit_sending = 1, timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_otp);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_verifikasi_otp = findViewById(R.id.btn_verifikasi_otp);
        input_code_1 = findViewById(R.id.input_code_1);
        input_code_2 = findViewById(R.id.input_code_2);
        input_code_3 = findViewById(R.id.input_code_3);
        input_code_4 = findViewById(R.id.input_code_4);
        input_code_5 = findViewById(R.id.input_code_5);
        textEmail = findViewById(R.id.textEmail);

        extra = getIntent().getExtras();
        textEmail.setText(extra.getString("Email"));
        otp = String.valueOf(extra.getInt("OTP"));

        setUpOTPInputs();

        btn_verifikasi_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String final_otp = otp_1 + otp_2 + otp_3 + otp_4 + otp_5;

                if ( final_otp.length() != 5 || final_otp.equals(otp) ){
                    Toast.makeText(VerifikasiOTP.this, "Kode OTP Tidak Valid", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VerifikasiOTP.this, "Kode OTP Terverifikasi", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(VerifikasiOTP.this, ResetPassword.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.exit_right_to_left);
                }
            }
        });

    }

    public void setUpOTPInputs(){

        input_code_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                otp_1 = s.toString();

                if (!s.toString().trim().isEmpty()){
                    input_code_2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_code_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                otp_2 = s.toString();

                if (!s.toString().trim().isEmpty()){
                    input_code_3.requestFocus();
                } else {
                    input_code_1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_code_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                otp_3 = s.toString();

                if (!s.toString().trim().isEmpty()){
                    input_code_4.requestFocus();
                } else {
                    input_code_2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_code_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                otp_4 = s.toString();

                if (!s.toString().trim().isEmpty()){
                    input_code_5.requestFocus();
                } else {
                    input_code_3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_code_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                otp_5 = s.toString();

                if (s.toString().trim().isEmpty()){
                    input_code_4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void ResendOTP(View view) {

        Boolean ExecTimer = false;

        if ( timer == 0 ){
            Limit_sending = 1;
        }

        if (Limit_sending == 1){

            Limit_sending = 0;
            ExecTimer = true;

            APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
            Call<ResponseAPI> FeedBack = API.SendOTP(
                    extra.getString("Email")
            );

            FeedBack.enqueue(new Callback<ResponseAPI>() {
                @Override
                public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                    String CheckStatus = response.body().getMeta().getStatus();

                    if (CheckStatus.equals("success")){
                        Toast.makeText(VerifikasiOTP.this, "Kode OTP Sudah Terkirim Ke Email Anda", Toast.LENGTH_SHORT).show();

                        otp = String.valueOf(response.body().getResponseData().getOTP());

                    } else {
                        Toast.makeText(VerifikasiOTP.this, response.body().getMeta().getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAPI> call, Throwable t) {
                    Toast.makeText(VerifikasiOTP.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {

            if (ExecTimer){

                timer = ((120000 / 60) % 60) / 10;

                new Timer().scheduleAtFixedRate(new TimerTask(){
                    @Override
                    public void run(){
                        if (timer != 0){
                            timer -= 1;
                        }
                    }
                },60000,120000);

                ExecTimer = false;

            }

            Toast.makeText(VerifikasiOTP.this, "Lakukan Pengiriman Ulang Dalam Waktu: " + timer + " Menit", Toast.LENGTH_SHORT).show();

        }

    }
}