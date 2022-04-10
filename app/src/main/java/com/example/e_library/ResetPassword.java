package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword extends AppCompatActivity {
    MaterialButton btn_reset_password;
    TextInputEditText new_password_field, konfirmasi_password_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), ResetPassword.this);

        new_password_field = findViewById(R.id.new_password_field);
        konfirmasi_password_field = findViewById(R.id.konfirmasi_password_field);
        btn_reset_password = findViewById(R.id.btn_reset_password);

        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new_password_field.getText().toString().equals("") || konfirmasi_password_field.getText().toString().equals("")){
                    Toast.makeText(ResetPassword.this, "Mohon Untuk Isi Field Terlebih Dulu", Toast.LENGTH_SHORT).show();
                    return;
                } else if (new_password_field.getText().toString().length() <= 7){
                    Toast.makeText(ResetPassword.this, "Password Minimal 8 Karakter", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (new_password_field.getText().toString().equals(konfirmasi_password_field.getText().toString())){
                    APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
                    Call<ResponseAPI> FeedBack = API.ResetPassword(
                            new_password_field.getText().toString()
                    );

                    FeedBack.enqueue(new Callback<ResponseAPI>() {
                        @Override
                        public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                            String CheckStatus = response.body().getMeta().getStatus();

                            if (CheckStatus.equals("success")){
                                Toast.makeText(ResetPassword.this, "Password Telah Di Ubah", Toast.LENGTH_SHORT).show();
                                Intent MoveAct = new Intent(ResetPassword.this, Login.class);
                                startActivity(MoveAct);
                                finish();
                                overridePendingTransition(R.anim.enter_left_to_right, R.anim.stay_position);
                            } else {
                                Toast.makeText(ResetPassword.this, response.body().getMeta().getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseAPI> call, Throwable t) {
                            Toast.makeText(ResetPassword.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(ResetPassword.this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}