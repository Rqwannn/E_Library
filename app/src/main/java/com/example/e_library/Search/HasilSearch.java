package com.example.e_library.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_library.Adapter.BukuFavorite;
import com.example.e_library.Adapter.KategoriBukuAdapter;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilSearch extends AppCompatActivity {

    RecyclerView rvData;
    RecyclerView.LayoutManager rlData;
    RecyclerView.Adapter raData;
    SwipeRefreshLayout SWL;
    ProgressBar PBData;
    SharedPreferences SessionStorage;

    String Filter = "", Search = "";
    Call<ResponseAPI> FeedBack;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_search);

        SWL = findViewById(R.id.parent_hasil_search);
        rvData = findViewById(R.id.data_hasil_search);
        PBData = findViewById(R.id.pb_data);

        search = findViewById(R.id.searching_buku);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);
        Bundle extra = getIntent().getExtras();

        Search = extra.getString("key");

        rlData =new LinearLayoutManager(HasilSearch.this, LinearLayoutManager.HORIZONTAL, false);
        rvData.setLayoutManager(rlData);
        setDataHasilSearch();

        SWL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SWL.setRefreshing(true);
                setDataHasilSearch();
                SWL.setRefreshing(false);
            }
        });
    }

    public void setDataHasilSearch(){
        String Token = SessionStorage.getString("Tokens", "");
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        FeedBack = API.HasilSearch(
                "Bearer " + Token,
                Search,
                Filter
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String Success = response.body().getMeta().getMessage();
                String Status = response.body().getMeta().getStatus();

                if (Status.equals("success")){
                    raData = new KategoriBukuAdapter(HasilSearch.this, response.body().getResponseData().getBuku(), R.layout.card_detail_kategori_grid);
                    rvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                    PBData.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(HasilSearch.this, Success, Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(HasilSearch.this,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Filter(View view) {
    }
}