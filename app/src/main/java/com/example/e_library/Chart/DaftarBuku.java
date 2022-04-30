package com.example.e_library.Chart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_library.Adapter.DaftarBukuAdapter;
import com.example.e_library.Adapter.PinjamanSayaAdapter;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Pinjam_buku.PinjamanSaya;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarBuku extends AppCompatActivity {

    RecyclerView rvData;
    RecyclerView.LayoutManager rlData;
    RecyclerView.Adapter raData;
    SwipeRefreshLayout SWL;
    ProgressBar PBData;
    SharedPreferences SessionStorage;

    public void setDaftarBuku(){
        String Token = SessionStorage.getString("Tokens", "");
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.PinjamanSaya(
                "Bearer " + Token
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String Success = response.body().getMeta().getMessage();
                String Status = response.body().getMeta().getStatus();

                if (Status.equals("success")){
                    raData = new DaftarBukuAdapter(DaftarBuku.this, response.body().getResponseData().getPinjamanSaya());
                    rvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                    PBData.setVisibility(View.GONE);
                } else {
                    Toast.makeText(DaftarBuku.this, Success, Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(DaftarBuku.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_buku);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), DaftarBuku.this);
//        new JWTAuth().CheckTokens(DaftarBuku.this, SessionStorage.getString("Tokens", ""));

        SWL = findViewById(R.id.swl_data);
        rvData = findViewById(R.id.parent_data_daftar_buku);
        PBData = findViewById(R.id.pb_data);

        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        rlData = new LinearLayoutManager(DaftarBuku.this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rlData);
        setDaftarBuku();

        SWL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SWL.setRefreshing(true);
                setDaftarBuku();
                SWL.setRefreshing(false);
            }
        });
    }

    public void BackToProfile(View view) {
        finish();
    }
}