package com.example.e_library.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Kategori_buku.DetailKategoriBuku;
import com.example.e_library.R;

public class Searching extends AppCompatActivity {
    SearchView search;

    RecyclerView rvData;
    RecyclerView.LayoutManager rlData;
    RecyclerView.Adapter raData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), Searching.this);
//        new JWTAuth().CheckTokens(Beranda.this, SessionStorage.getString("Tokens", ""));

        search = findViewById(R.id.searching_buku);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(Searching.this, HasilSearch.class);
                intent.putExtra("key", query);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public void BackToInitial(View view) {
        finish();
    }

    public void DetailBuku(View view) {
        String Params = view.getTag().toString();

        Intent intent = new Intent(Searching.this, DetailKategoriBuku.class);
        intent.putExtra("Kategori", Params);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
    }
}