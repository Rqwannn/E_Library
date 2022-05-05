package com.example.e_library.Chart;

import androidx.appcompat.app.AppCompatActivity;
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

import com.example.e_library.Adapter.DaftarBukuAdapter;
import com.example.e_library.BluePrint.Items;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Konfirmasi;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Pinjam_buku.PinjamanSaya;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

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
    int id_book[], quantity[];
    MaterialButton submit;
    TextInputEditText tanggal_pinjam;
    DaftarBukuAdapter getAdapterClass;

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
                    getAdapterClass = new DaftarBukuAdapter(DaftarBuku.this, response.body().getResponseData().getPinjamanSaya());
                    raData = getAdapterClass;

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
        SessionStorage = getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), DaftarBuku.this);
//        new JWTAuth().CheckTokens(DaftarBuku.this, SessionStorage.getString("Tokens", ""));

        SWL = findViewById(R.id.swl_data);
        rvData = findViewById(R.id.parent_data_daftar_buku);
        PBData = findViewById(R.id.pb_data);

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

        submit = findViewById(R.id.submit);
        tanggal_pinjam = findViewById(R.id.tanggal_pinjam);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tanggal_pinjam.getText().toString().equals("")){
                    Toast.makeText(DaftarBuku.this, "Mohon Isi Tanggal Peminjaman", Toast.LENGTH_SHORT).show();
                } else {
                    id_book = getAdapterClass.getIdBook();
                    quantity = getAdapterClass.getQuantity();

                    Intent intent = new Intent(DaftarBuku.this, Konfirmasi.class);
                    intent.putExtra("loan_date", tanggal_pinjam.getText().toString());
                    intent.putExtra("id_book", id_book);
                    intent.putExtra("quantity", quantity);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
                }
            }
        });
    }

    public void BackToProfile(View view) {
        finish();
    }
}