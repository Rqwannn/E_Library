package com.example.e_library.Kategori_buku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_library.Adapter.KategoriBukuAdapter;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Pinjam_buku.PinjamanSaya;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;
import com.example.e_library.Search.HasilSearch;
import com.example.e_library.Search.Searching;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKategoriBuku extends AppCompatActivity {
    SearchView search_detail_buku;
    TextView text_mapel, kelas_X, kelas_XI, kelas_XII;
    ImageView back_kategori;
    String newKategori;

    RecyclerView rvData_1, rvData_2, rvData_3;
    RecyclerView.LayoutManager rlData;
    RecyclerView.Adapter raData;
    SwipeRefreshLayout parent_card_Detail_kategori_1, parent_card_Detail_kategori_2, parent_card_Detail_kategori_3;
    ProgressBar pb_data, pb_data_2, pb_data_3;
    SharedPreferences SessionStorage;

    public void setKategoriAdapter(ProgressBar PBData, RecyclerView newRvData){
        String Token = SessionStorage.getString("Tokens", "");
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.getBukuKategori(
                "Bearer " + Token,
                newKategori
        );

        rlData = new LinearLayoutManager(DetailKategoriBuku.this, LinearLayoutManager.VERTICAL, false);
        newRvData.setLayoutManager(rlData);

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String Success = response.body().getMeta().getMessage();
                String Status = response.body().getMeta().getStatus();

                if (Status.equals("success")){
                    raData = new KategoriBukuAdapter(DetailKategoriBuku.this, response.body().getResponseData().getBuku(), R.layout.card_detail_kategori);
                    newRvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                    PBData.setVisibility(View.GONE);
                } else {
                    Toast.makeText(DetailKategoriBuku.this, Success, Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(DetailKategoriBuku.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RefreshData(SwipeRefreshLayout newSWL, ProgressBar PBData, RecyclerView newRvData){
        newSWL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newSWL.setRefreshing(true);
                setKategoriAdapter(PBData, newRvData);
                newSWL.setRefreshing(false);
            }
        });
    }

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

        newKategori = extra.getString("Kategori");

        kelas_X.setText(newKategori + " - Kelas X");
        kelas_XI.setText(newKategori + " - Kelas XI");
        kelas_XII.setText(newKategori + " - Kelas XII");
        text_mapel.setText(newKategori);

        rvData_1 = findViewById(R.id.data_buku_kelas_x);
        rvData_2 = findViewById(R.id.data_buku_kelas_xi);
        rvData_3 = findViewById(R.id.data_buku_kelas_xii);

        pb_data = findViewById(R.id.pb_data);
        pb_data_2 = findViewById(R.id.pb_data_2);
        pb_data_3 = findViewById(R.id.pb_data_3);

        parent_card_Detail_kategori_1 = findViewById(R.id.parent_card_Detail_kategori);
        parent_card_Detail_kategori_2 = findViewById(R.id.parent_card_Detail_kategori_2);
        parent_card_Detail_kategori_3 = findViewById(R.id.parent_card_Detail_kategori_3);

        setKategoriAdapter(pb_data, rvData_1);
        setKategoriAdapter(pb_data_2, rvData_2);
        setKategoriAdapter(pb_data_3, rvData_3);

        RefreshData(parent_card_Detail_kategori_1, pb_data, rvData_1);
        RefreshData(parent_card_Detail_kategori_2, pb_data_2, rvData_2);
        RefreshData(parent_card_Detail_kategori_3, pb_data_3, rvData_3);

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