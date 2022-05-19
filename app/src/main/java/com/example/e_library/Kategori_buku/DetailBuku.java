package com.example.e_library.Kategori_buku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Chart.DaftarBuku;
import com.example.e_library.JWTOptions.JWTAuth;
import com.example.e_library.Login;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBuku extends AppCompatActivity {
    private int IDBuku;
    SharedPreferences SessionStorage;
    TextView judul_buku, penulis, penerbit, tanggal, deskripsi;
    MaterialButton kategori, jumlah_buku, pinjam_buku;
    int id_buku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), DetailBuku.this);

        Bundle extra = getIntent().getExtras();
        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);
        String Token = SessionStorage.getString("Tokens", "");
//        new JWTAuth().CheckTokens(DetailBuku.this, SessionStorage.getString("Tokens", ""));

        id_buku = extra.getInt("ID_BUKU", 0);

        judul_buku = findViewById(R.id.judul_buku);
        penulis = findViewById(R.id.penulis);
        penerbit = findViewById(R.id.penerbit);
        tanggal = findViewById(R.id.tanggal);
        deskripsi = findViewById(R.id.deskripsi);

        kategori = findViewById(R.id.kategori);
        jumlah_buku = findViewById(R.id.jumlah_buku);
        pinjam_buku = findViewById(R.id.pinjam_buku);

        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.DetailBuku(
                "Bearer " + Token,
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
//                    tanggal.setText();
//                    deskripsi.setText();
//
//                    kategori.setText();
//                    jumlah_buku.setText();

                } else {
                    Toast.makeText(DetailBuku.this, response.body().getMeta().getMessage(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(DetailBuku.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void BackToMenu(View view) {
        finish();
    }

    public void PinjamBuku(View view) {

        Toast.makeText(this, "Mohon tunggu peminjaman sedang di proses", Toast.LENGTH_SHORT).show();

        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        String Token = SessionStorage.getString("Tokens", "");
        Call<ResponseAPI> FeedBack = API.LoanDetail(
                "Bearer " + Token,
                id_buku
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String CheckStatus = response.body().getMeta().getStatus();

                if (CheckStatus.equals("success")){

                    Toast.makeText(DetailBuku.this, "Buku anda sudah terdaftar", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(DetailBuku.this, DaftarBuku.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
                } else {
                    Toast.makeText(DetailBuku.this, response.body().getResponseData().getMessage(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(DetailBuku.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}