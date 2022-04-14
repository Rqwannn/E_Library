package com.example.e_library.Pinjam_buku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Profile.EditProfile;
import com.example.e_library.R;

public class PinjamanSaya extends AppCompatActivity {
    RecyclerView rvData;
    RecyclerView.LayoutManager rlData;
    RecyclerView.Adapter raData;
    SwipeRefreshLayout SWL;
    ProgressBar PBData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjaman_saya);
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), PinjamanSaya.this);
    }

    public void BackToProfile(View view) {
        finish();
    }
}