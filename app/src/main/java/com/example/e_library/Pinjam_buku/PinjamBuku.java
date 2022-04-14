package com.example.e_library.Pinjam_buku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.e_library.Beranda.Beranda;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class PinjamBuku extends AppCompatActivity {
    TextView judul_buku, kategori, tahun_terbit, penulis, penerbit, stok;
    MaterialButton submit;
    TextInputEditText tanggal_pinjam, jumlah_buku;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam_buku);
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), PinjamBuku.this);

        tanggal_pinjam = findViewById(R.id.tanggal_pinjam);

        //        new JWTAuth().CheckTokens(Beranda.this, SessionStorage.getString("Tokens", ""));

        Calendar calendar = Calendar.getInstance();
        Bundle extra = getIntent().getExtras();

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
    }

    public void BackToDetail(View view) {
        finish();
    }
}