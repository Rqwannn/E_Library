package com.example.e_library.Beranda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_library.Adapter.BukuFavorite;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;
import com.example.e_library.Search.SearchKategori;
import com.example.e_library.Search.Searching;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView rvData;
    RecyclerView.LayoutManager rlData;
    RecyclerView.Adapter raData;
    SwipeRefreshLayout SWL;
    ProgressBar PBData;
    SharedPreferences SessionStorage;

    SearchView search;
    TextView lihat_lainya;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public void setDataBukuFavorite(){
        String Token = SessionStorage.getString("Tokens", "");
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.BukuFavorite(
                "Bearer " + Token
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String Success = response.body().getMeta().getMessage();
                String Status = response.body().getMeta().getStatus();

                if (Status.equals("success")){
                    raData = new BukuFavorite(getActivity(), response.body().getResponseData().getBuku());
                    rvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                    PBData.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getActivity(), Success, Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(getActivity(),"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        View header = view.findViewById(R.id.header_home);

        SWL = view.findViewById(R.id.parent_sering_di_pinjam);
        rvData = view.findViewById(R.id.data_buku_favorite);
        PBData = view.findViewById(R.id.pb_data);
        lihat_lainya = view.findViewById(R.id.lihat_lainya);

        lihat_lainya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchKategori.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
            }
        });

        search = header.findViewById(R.id.search_buku);

        SessionStorage = getActivity().getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        rlData = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvData.setLayoutManager(rlData);
        setDataBukuFavorite();

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Intent intent = new Intent(getContext(), Searching.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.enter_bottom_to_top, R.anim.stay_position);
                }
                search.clearFocus();
            }
        });

        SWL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SWL.setRefreshing(true);
                setDataBukuFavorite();
                SWL.setRefreshing(false);
            }
        });

        return view;
    }
}