package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.JWTOptions.Meta;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputEditText UsernameField, PasswordField;
    String username, password;
    MaterialButton Submit;
    SharedPreferences SessionStorage;
    SharedPreferences.Editor SessionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        UsernameField = findViewById(R.id.username_field);
        PasswordField = findViewById(R.id.password_field);
        Submit = findViewById(R.id.submit);

        SessionStorage = getSharedPreferences("SESSION", MODE_PRIVATE);

        if (SessionStorage.getInt("Submit", 0) == 1){
            startActivity(new Intent(Login.this, Beranda.class));
        }

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = UsernameField.getText().toString();
                password = PasswordField.getText().toString();

                if (username.trim().equals("") || password.trim().equals("")){
                    Toast.makeText(Login.this, "Mohon Untuk Isi Input Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {

                    APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
                    Call<ResponseAPI> FeedBack = API.Authentication(
                            username,
                            password
                    );

                    FeedBack.enqueue(new Callback<ResponseAPI>() {
                        @Override
                        public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                            Boolean CheckStatus = response.body().getMeta().getStatus();

                            if (CheckStatus){
                                SessionEdit = SessionStorage.edit();

                                SessionEdit = SessionStorage.edit();
                                SessionEdit.putInt("Submit", 1);
                                SessionEdit.putString("Username", username);
                                SessionEdit.putString("Tokens", response.body().getResponseData().getAccessToken());
                                SessionEdit.apply();

                                Intent MoveAct = new Intent(Login.this, Beranda.class);
                                startActivity(MoveAct);
                                finish();
                                overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.exit_right_to_left);
                            } else {
                                Toast.makeText(Login.this, response.body().getMeta().getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseAPI> call, Throwable t) {
                            Toast.makeText(Login.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


    }

    public void Daftar(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.exit_right_to_left);
    }
}