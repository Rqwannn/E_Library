package com.example.e_library.Search;

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

import com.example.e_library.Adapter.SearchKategoriAdapter;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchKategori extends AppCompatActivity {

    RecyclerView rvData;
    RecyclerView.LayoutManager rlData;
    RecyclerView.Adapter raData;
    SwipeRefreshLayout SWL;
    ProgressBar PBData;
    SharedPreferences SessionStorage;

    public void getDataKategori(){
        String Token = SessionStorage.getString("Tokens", "");
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.getAllCategories(
                "Bearer " + Token
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String Success = response.body().getMeta().getMessage();
                String Status = response.body().getMeta().getStatus();

                if (Status.equals("success")){
                    raData = new SearchKategoriAdapter(SearchKategori.this, response.body().getResponseData().getCategories());
                    rvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                    PBData.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(SearchKategori.this, Success, Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(SearchKategori.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_kategori);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), SearchKategori.this);
//        new JWTAuth().CheckTokens(Beranda.this, SessionStorage.getString("Tokens", ""));

        SWL = findViewById(R.id.swl_data);
        rvData = findViewById(R.id.parent_data_search_kategori);
        PBData = findViewById(R.id.pb_data);

        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        rlData = new LinearLayoutManager(SearchKategori.this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rlData);
        getDataKategori();

        SWL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SWL.setRefreshing(true);
                getDataKategori();
                SWL.setRefreshing(false);
            }
        });
    }

    public void BackToInitial(View view) {
        finish();
    }
}