package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    String Username, Password, Email;
    MaterialButton Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        EmailField = findViewById(R.id.email_field);
        UsernameField = findViewById(R.id.username_field);
        PasswordField = findViewById(R.id.password_field);
        Submit = findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = UsernameField.getText().toString();
                Password = PasswordField.getText().toString();
                Email = EmailField.getText().toString();

                if (Username.trim().equals("") || Password.trim().equals("") | Email.trim().equals("")) {
                    Toast.makeText(Register.this, "Mohon Untuk Isi Input Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {

                    APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
                    Call<ResponseAPI> FeedBack = API.Register(
                            Username,
                            Password,
                            Email
                    );

                    FeedBack.enqueue(new Callback<ResponseAPI>() {
                        @Override
                        public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                            Boolean CheckStatus = response.body().getStatus();

                            if (CheckStatus){
                                Intent MoveAct = new Intent(Register.this, Login.class);
                                startActivity(MoveAct);
                                finish();
                                overridePendingTransition(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                            } else {
                                Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT ).show();
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
        overridePendingTransition(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
    }
}