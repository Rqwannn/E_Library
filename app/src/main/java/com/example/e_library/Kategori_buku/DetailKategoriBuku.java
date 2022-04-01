package com.example.e_library.Kategori_buku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_library.R;

public class DetailKategoriBuku extends AppCompatActivity {
    SearchView search_detail_buku;
    TextView text_mapel, kelas_X, kelas_XI, kelas_XII;
    ImageView back_kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori_buku);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        search_detail_buku = findViewById(R.id.search_detail_buku);
        text_mapel = findViewById(R.id.text_mapel);
        back_kategori = findViewById(R.id.back_kategori);
        kelas_X = findViewById(R.id.kelas_X);
        kelas_XI = findViewById(R.id.kelas_XI);
        kelas_XII = findViewById(R.id.kelas_XII);

        Bundle extra = getIntent().getExtras();

        kelas_X.setText(extra.getString("Kategori") + " - Kelas X");
        kelas_XI.setText(extra.getString("Kategori") + " - Kelas XI");
        kelas_XII.setText(extra.getString("Kategori") + " - Kelas XII");
        text_mapel.setText(extra.getString("Kategori"));

        back_kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        search_detail_buku.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(DetailKategoriBuku.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}