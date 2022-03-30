package com.example.e_library.Kategori_buku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.Pinjam_buku.PinjamBuku;
import com.example.e_library.R;

public class DetailBuku extends AppCompatActivity {
    private int IDBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle extra = getIntent().getExtras();

        Toast.makeText(DetailBuku.this, String.valueOf(extra.getInt("ID_BUKU", 0)), Toast.LENGTH_SHORT).show();
    }

    public void BackToMenu(View view) {
        finish();
        overridePendingTransition(R.anim.enter_left_to_right, R.anim.stay_position);
    }

    public void PinjamBuku(View view) {
        Intent intent = new Intent(DetailBuku.this, PinjamBuku.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
    }
}