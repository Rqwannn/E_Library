package com.example.e_library.Pinjam_buku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinjamBuku extends AppCompatActivity {
    TextView judul_buku, kategori, tahun_terbit, penulis, penerbit, stok;
    MaterialButton submit, iya, tidak;
    TextInputEditText tanggal_pinjam, jumlah_buku;
    DatePickerDialog.OnDateSetListener setListener;
    SharedPreferences SessionStorage;
    int id_buku;
    String BearerToken;
    MaterialCardView pinjaman_pop_up;
    Animation animFadein, animFadeout;

    public void getBukuById(){
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.DetailBuku(
                "Bearer " + BearerToken,
                id_buku
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String CheckStatus = response.body().getMeta().getStatus();

                if (CheckStatus.equals("success")){

//                    judul_buku.setText();
//                    penulis.setText();
//                    penerbit.setText();
//                    tahun_terbit.setText();
//
//                    kategori.setText();
//                    stok.setText();

                } else {
                    Toast.makeText(PinjamBuku.this, response.body().getMeta().getMessage(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(PinjamBuku.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void PinjamBuku(){

        String text_tanggal_pinjam = tanggal_pinjam.getText().toString();
        String text_jumlah_buku = jumlah_buku.getText().toString();

        if (text_tanggal_pinjam.length() > 0 && text_jumlah_buku.length() > 0){
            APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
            Call<ResponseAPI> FeedBack = API.PinjamBuku(
                    "Bearer " + BearerToken,
                    id_buku,
                    text_tanggal_pinjam,
                    Integer.parseInt(text_jumlah_buku)
            );

            FeedBack.enqueue(new Callback<ResponseAPI>() {
                @Override
                public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                    String CheckStatus = response.body().getMeta().getStatus();

                    if (CheckStatus.equals("success")){
                        Toast.makeText(PinjamBuku.this, "Pinjaman Anda Telah Di Proses Harap Cek Email", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(PinjamBuku.this, PinjamanSaya.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter_bottom_to_top, R.anim.stay_position);
                    } else {
                        Toast.makeText(PinjamBuku.this, response.body().getMeta().getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAPI> call, Throwable t) {
                    Toast.makeText(PinjamBuku.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(PinjamBuku.this, "Mohon Untuk Isi Input Terlebih Dahulu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam_buku);
        //        new JWTAuth().CheckTokens(Beranda.this, SessionStorage.getString("Tokens", ""));
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), PinjamBuku.this);

        tanggal_pinjam = findViewById(R.id.tanggal_pinjam);
        jumlah_buku = findViewById(R.id.jumlah_buku);
        submit = findViewById(R.id.submit);

        judul_buku = findViewById(R.id.judul_buku);
        kategori = findViewById(R.id.kategori);
        tahun_terbit = findViewById(R.id.tahun_terbit);
        penulis = findViewById(R.id.penulis);
        penerbit = findViewById(R.id.penerbit);
        stok = findViewById(R.id.stok);

        pinjaman_pop_up = findViewById(R.id.pinjaman_pop_up);
        iya = findViewById(R.id.iya);
        tidak = findViewById(R.id.tidak);

        animFadein = AnimationUtils.loadAnimation(PinjamBuku.this,
                R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(PinjamBuku.this,
                R.anim.fade_out);

        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinjaman_pop_up.startAnimation(animFadeout);
                pinjaman_pop_up.setVisibility(View.GONE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pinjaman_pop_up.clearAnimation();
                    }
                }, 520);
            }
        });

        iya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinjaman_pop_up.startAnimation(animFadeout);
                pinjaman_pop_up.setVisibility(View.GONE);

                PinjamBuku();
            }
        });

        Calendar calendar = Calendar.getInstance();
        Bundle extra = getIntent().getExtras();

        id_buku = extra.getInt("ID_BUKU");

        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);
        BearerToken = SessionStorage.getString("Tokens", "");

        getBukuById();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tanggal_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        PinjamBuku.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        tanggal_pinjam.setText(date);
                    }
                }, year, month, day
                );

                datePickerDialog.show();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinjaman_pop_up.setVisibility(View.VISIBLE);
                pinjaman_pop_up.startAnimation(animFadein);
            }
        });

    }

    public void BackToDetail(View view) {
        finish();
    }
}