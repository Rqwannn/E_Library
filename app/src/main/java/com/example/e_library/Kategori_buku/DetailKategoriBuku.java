package com.example.e_library.Kategori_buku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.R;
import com.example.e_library.Search.Searching;

public class DetailKategoriBuku extends AppCompatActivity {
    SearchView search_detail_buku;
    TextView text_mapel, kelas_X, kelas_XI, kelas_XII;
    ImageView back_kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori_buku);
        new TranslucentOptions().withBGStatusBar(this.getWindow(), DetailKategoriBuku.this);

        View view = findViewById(R.id.detail_kategori_header);

        search_detail_buku = findViewById(R.id.search_detail_buku);
        text_mapel = view.findViewById(R.id.text_mapel);
        back_kategori = view.findViewById(R.id.back_kategori);
        kelas_X = findViewById(R.id.kelas_X);
        kelas_XI = findViewById(R.id.kelas_XI);
        kelas_XII = findViewById(R.id.kelas_XII);

        Bundle extra = getIntent().getExtras();
        //        new JWTAuth().CheckTokens(Beranda.this, SessionStorage.getString("Tokens", ""));

        kelas_X.setText(extra.getString("Kategori") + " - Kelas X");
        kelas_XI.setText(extra.getString("Kategori") + " - Kelas XI");
        kelas_XII.setText(extra.getString("Kategori") + " - Kelas XII");
        text_mapel.setText(extra.getString("Kategori"));

        back_kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        search_detail_buku.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Intent intent = new Intent(DetailKategoriBuku.this, Searching.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_bottom_to_top, R.anim.stay_position);
                }

                search_detail_buku.clearFocus();
            }
        });

    }
}