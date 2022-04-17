package com.example.e_library.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.Kategori_buku.DetailKategoriBuku;
import com.example.e_library.R;

public class Searching extends AppCompatActivity {
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        search = findViewById(R.id.searching_buku);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Searching.this, query, Toast.LENGTH_SHORT).show();
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