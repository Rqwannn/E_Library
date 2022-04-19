package com.example.e_library.Search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.e_library.R;

public class SearchKategori extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_kategori);
    }

    public void BackToInitial(View view) {
        finish();
    }
}