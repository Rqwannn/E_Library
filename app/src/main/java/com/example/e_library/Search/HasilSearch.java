package com.example.e_library.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_library.Adapter.KategoriBukuAdapter;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.JWTOptions.JWTAuth;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

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
    MaterialCardView filter_parent;
    MaterialButton filter_btn;
    FrameLayout black_screen;
    int before_id_aktif = 0, reset_animation = 0;

    String Filter = "", Search = "";
    Call<ResponseAPI> FeedBack;
    SearchView search;
    Animation animFadein, animFadeout, enter_bottom_to_top, exit_bottom_to_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_search);
        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), HasilSearch.this);
//        new JWTAuth().CheckTokens(HasilSearch.this, SessionStorage.getString("Tokens", ""));

        SWL = findViewById(R.id.parent_hasil_search);
        rvData = findViewById(R.id.data_hasil_search);
        PBData = findViewById(R.id.pb_data);

        filter_parent = findViewById(R.id.filter_parent);
        black_screen = findViewById(R.id.black_screen);

        animFadein = AnimationUtils.loadAnimation(HasilSearch.this,
                R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(HasilSearch.this,
                R.anim.fade_out);

        enter_bottom_to_top = AnimationUtils.loadAnimation(HasilSearch.this,
                R.anim.enter_bottom_to_top);
        exit_bottom_to_top = AnimationUtils.loadAnimation(HasilSearch.this,
                R.anim.exit_bottom_to_top);

        search = findViewById(R.id.searching_buku);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Search = query;
                setDataHasilSearch();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Bundle extra = getIntent().getExtras();

        Search = extra.getString("key");

        rlData = new GridLayoutManager(HasilSearch.this, 2);
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


        black_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reset_animation == 0){
                    black_screen.setVisibility(View.GONE);
                    black_screen.startAnimation(animFadeout);

                    filter_parent.setVisibility(View.GONE);
                    filter_parent.startAnimation(exit_bottom_to_top);

                    reset_animation = 1;

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            black_screen.clearAnimation();
                            filter_parent.clearAnimation();
                        }
                    }, 500);
                }
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
                    PBData.setVisibility(View.GONE);
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
        search.clearFocus();

        black_screen.startAnimation(animFadein);
        black_screen.setVisibility(View.VISIBLE);

        filter_parent.startAnimation(enter_bottom_to_top);
        filter_parent.setVisibility(View.VISIBLE);
        reset_animation = 0;
    }

    public void AddFilter(View view) {

        if (before_id_aktif != 0 && !String.valueOf(before_id_aktif).equals(String.valueOf(view.getId()))){
            filter_btn = findViewById(before_id_aktif);

            filter_btn.setStrokeColor(ColorStateList.valueOf(Color.BLACK));
            filter_btn.setTextColor(Color.BLACK);
        }

        String Params = view.getTag().toString();
        Filter = Params;

        filter_btn = findViewById(view.getId());

        filter_btn.setStrokeColor(ColorStateList.valueOf(Color.GREEN));
        filter_btn.setTextColor(Color.GREEN);

        before_id_aktif = view.getId();

    }

    public void AturUlang(View view) {
        Filter = "";
        search.setQueryHint("Cari Buku Anda");

        filter_btn = findViewById(before_id_aktif);

        filter_btn.setStrokeColor(ColorStateList.valueOf(Color.BLACK));
        filter_btn.setTextColor(Color.BLACK);

        black_screen.setVisibility(View.GONE);
        black_screen.startAnimation(animFadeout);

        filter_parent.setVisibility(View.GONE);
        filter_parent.startAnimation(exit_bottom_to_top);

        reset_animation = 1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                black_screen.clearAnimation();
                filter_parent.clearAnimation();
            }
        }, 500);

        setDataHasilSearch();
    }

    public void Pakai(View view) {
        black_screen.setVisibility(View.GONE);
        black_screen.startAnimation(animFadeout);

        filter_parent.setVisibility(View.GONE);
        filter_parent.startAnimation(exit_bottom_to_top);

        reset_animation = 1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                black_screen.clearAnimation();
                filter_parent.clearAnimation();
            }
        }, 500);

        search.setQueryHint("Cari " + Filter);
        setDataHasilSearch();
    }
}