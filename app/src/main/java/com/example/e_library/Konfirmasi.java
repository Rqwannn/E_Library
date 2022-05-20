package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class Konfirmasi extends AppCompatActivity {
    SharedPreferences SessionStorage;
    String Tanggal;
    MaterialButton submit, oke;
    Animation animFadein, animFadeout;
    FrameLayout black_screen;
    MaterialCardView pop_up_success;

//    public void SubmitDaftarBuku(){
//        Items items = new Items();
//        items.loan_date = Tanggal;
//        items.status = "DIPINJAM";
//
//        String Token = SessionStorage.getString("Tokens", "");
//        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
//
//        Call<ResponseAPI> FeedBack = API.LoanBuku(
//                "Bearer " + Token,
//                items
//        );
//
//        FeedBack.enqueue(new Callback<ResponseAPI>() {
//            @Override
//            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
//                String Success = response.body().getMeta().getMessage();
//                String Status = response.body().getMeta().getStatus();
//
//                if (Status.equals("success")){
//                    Intent intent = new Intent(Konfirmasi.this, PinjamanSaya.class);
//                    startActivity(intent);
//                    finish();
//                    overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
//
//                    Toast.makeText(Konfirmasi.this, "Buku Berhasil Di Checkout", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Konfirmasi.this, Success, Toast.LENGTH_LONG ).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseAPI> call, Throwable t) {
//                Toast.makeText(Konfirmasi.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), Konfirmasi.this);
//        new JWTAuth().CheckTokens(Konfirmasi.this, SessionStorage.getString("Tokens", ""));

        Bundle extra = getIntent().getExtras();
        Tanggal = extra.getString("loan_date");

        animFadein = AnimationUtils.loadAnimation(Konfirmasi.this,
                R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(Konfirmasi.this,
                R.anim.fade_out);

        pop_up_success = findViewById(R.id.daftar_pop_up);
        black_screen = findViewById(R.id.black_screen);

        oke = findViewById(R.id.oke);

        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SubmitDaftarBuku();
                black_screen.setVisibility(View.GONE);
                black_screen.startAnimation(animFadeout);

                pop_up_success.setVisibility(View.GONE);
                pop_up_success.startAnimation(animFadeout);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        black_screen.clearAnimation();
                        pop_up_success.clearAnimation();
                    }
                }, 500);
            }
        });

        black_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    black_screen.setVisibility(View.GONE);
                    black_screen.startAnimation(animFadeout);

                    pop_up_success.setVisibility(View.GONE);
                    pop_up_success.startAnimation(animFadeout);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            black_screen.clearAnimation();
                            pop_up_success.clearAnimation();
                        }
                    }, 500);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                black_screen.startAnimation(animFadein);
                black_screen.setVisibility(View.VISIBLE);

                pop_up_success.startAnimation(animFadein);
                pop_up_success.setVisibility(View.VISIBLE);
            }
        });
    }

    public void Back(View view) {
        finish();
    }
}