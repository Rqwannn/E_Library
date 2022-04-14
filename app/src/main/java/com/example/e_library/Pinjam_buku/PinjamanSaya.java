package com.example.e_library.Pinjam_buku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_library.Adapter.BukuFavorite;
import com.example.e_library.Adapter.PinjamanSayaAdapter;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Profile.EditProfile;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinjamanSaya extends AppCompatActivity {
    RecyclerView rvData;
    RecyclerView.LayoutManager rlData;
    RecyclerView.Adapter raData;
    SwipeRefreshLayout SWL;
    ProgressBar PBData;
    SharedPreferences SessionStorage;

    public void setDataPinjamanSaya(){
        String Token = SessionStorage.getString("Tokens", "");
        int Id = SessionStorage.getInt("ID_USER", 0);
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.PinjamanSaya(
                "Bearer " + Token,
                Id
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String Success = response.body().getMeta().getMessage();

                if (Success.equals("success")){
                    raData = new PinjamanSayaAdapter(PinjamanSaya.this, response.body().getResponseData().getPinjamanSaya());
                    rvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                    PBData.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(PinjamanSaya.this, response.body().getMeta().getMessage(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(PinjamanSaya.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjaman_saya);
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), PinjamanSaya.this);
//        new JWTAuth().CheckTokens(Beranda.this, SessionStorage.getString("Tokens", ""));

        SWL = findViewById(R.id.swl_data);
        rvData = findViewById(R.id.parent_data_pinjaman_saya);
        PBData = findViewById(R.id.pb_data);

        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        rlData = new LinearLayoutManager(PinjamanSaya.this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rlData);
        setDataPinjamanSaya();

        SWL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SWL.setRefreshing(true);
                setDataPinjamanSaya();
                SWL.setRefreshing(false);
            }
        });
    }

    public void BackToProfile(View view) {
        finish();
    }
}