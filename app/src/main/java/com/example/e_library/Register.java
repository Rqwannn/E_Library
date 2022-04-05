package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextInputEditText UsernameField, PasswordField, EmailField;
    String username, password, email;
    MaterialButton Submit;
    SharedPreferences SessionStorage;
    SharedPreferences.Editor SessionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        EmailField = findViewById(R.id.email_field);
        UsernameField = findViewById(R.id.username_field);
        PasswordField = findViewById(R.id.password_field);
        Submit = findViewById(R.id.submit);

        SessionStorage = getSharedPreferences("SESSION", MODE_PRIVATE);

        if (SessionStorage.getInt("Submit", 0) == 1){
            startActivity(new Intent(Register.this, Beranda.class));
        }

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = UsernameField.getText().toString();
                password = PasswordField.getText().toString();
                email = EmailField.getText().toString();

                if (username.trim().equals("") || password.trim().equals("") || email.trim().equals("")) {
                    Toast.makeText(Register.this, "Mohon Untuk Isi Input Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else if(!email.contains("@") || (!email.contains("gmail.com") && !email.contains("yahoo.co.id"))){
                    Toast.makeText(Register.this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();
                } else if (password.length() <= 7) {
                    Toast.makeText(Register.this, "Password Minimal 8 Karakter", Toast.LENGTH_SHORT).show();
                } else {

                    APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
                    Call<ResponseAPI> FeedBack = API.Register(
                            username,
                            password,
                            email
                    );

                    FeedBack.enqueue(new Callback<ResponseAPI>() {
                        @Override
                        public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                            String CheckStatus = response.body().getMeta().getStatus();

                            if (CheckStatus.equals("success")){
                                SessionEdit = SessionStorage.edit();
                                SessionEdit.putBoolean("Submit", true);
                                SessionEdit.putString("Username", username);
                                SessionEdit.putString("Tokens", response.body().getResponseData().getAccessToken());
                                SessionEdit.apply();

                                Toast.makeText(Register.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();

                                Intent MoveAct = new Intent(Register.this, Beranda.class);
                                startActivity(MoveAct);
                                finish();
                                overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.exit_right_to_left);
                            } else {
                                Toast.makeText(Register.this, response.body().getMeta().getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseAPI> call, Throwable t) {
                            Toast.makeText(Register.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

    }

    public void Masuk(View view) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.enter_left_to_right, R.anim.stay_position);
    }
}