package com.example.e_library.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.JWTOptions.JWTAuth;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {
    EditText nama, username, email, phone;
    SharedPreferences SessionStorage;
    MaterialButton simpan_perubahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        SessionStorage = getSharedPreferences("SESSION", MODE_PRIVATE);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), EditProfile.this);
//        new JWTAuth().CheckTokens(EditProfile.this, SessionStorage.getString("Tokens", ""));

        nama = findViewById(R.id.nama);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        simpan_perubahan = findViewById(R.id.simpan_perubahan);

        nama.setText(SessionStorage.getString("Name", ""));
        username.setText(SessionStorage.getString("Username", ""));
        email.setText(SessionStorage.getString("Email", ""));
        phone.setText(SessionStorage.getString("Phone", ""));

        simpan_perubahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String namaText = nama.getText().toString();
                String emailText = email.getText().toString();
                String phoneNumber = phone.getText().toString();

                if (usernameText.length() == 0 || namaText.length() == 0 || emailText.length() == 0 || phoneNumber.length() == 0){
                    Toast.makeText(EditProfile.this, "Mohon Isi Data Profile Anda Dengan Lengkap", Toast.LENGTH_SHORT).show();
                } else {
                    APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
                    Call<ResponseAPI> FeedBack = API.UpdateProfile(
                            "Bearer " + SessionStorage.getString("Tokens", ""),
                            namaText,
                            usernameText,
                            emailText,
                            phoneNumber
                    );

                    FeedBack.enqueue(new Callback<ResponseAPI>() {
                        @Override
                        public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                            String CheckStatus = response.body().getMeta().getStatus();

                            if (CheckStatus.equals("success")){
                                Toast.makeText(EditProfile.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(EditProfile.this, Beranda.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.enter_left_to_right, R.anim.stay_position);
                            } else {
                                Toast.makeText(EditProfile.this, response.body().getResponseData().getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseAPI> call, Throwable t) {
                            Toast.makeText(EditProfile.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    public void BackToProfile(View view) {
        finish();
    }
}