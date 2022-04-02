package com.example.e_library.Profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_library.Login;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.ResponseAPI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    MaterialCardView keluar_btn, profile_pop_up;
    MaterialButton iya, tidak;
    SharedPreferences SessionStorage;
    SharedPreferences.Editor SessionEdit;
    Animation animFadein, animFadeout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextView UsernameProfil;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        UsernameProfil = view.findViewById(R.id.username_profile);
        SessionStorage = getActivity().getSharedPreferences("SESSION", Context.MODE_PRIVATE);
        UsernameProfil.setText(SessionStorage.getString("Username", ""));

        keluar_btn = view.findViewById(R.id.keluar_btn);
        animFadein = AnimationUtils.loadAnimation(getContext(),
                R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(getContext(),
                R.anim.fade_out);

        profile_pop_up = view.findViewById(R.id.profile_pop_up);
        iya = view.findViewById(R.id.iya);
        tidak = view.findViewById(R.id.tidak);

        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_pop_up.startAnimation(animFadeout);
                profile_pop_up.setVisibility(View.GONE);
            }
        });

        iya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_pop_up.startAnimation(animFadeout);
                profile_pop_up.setVisibility(View.GONE);

                Logout();
            }
        });

        keluar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_pop_up.setVisibility(View.VISIBLE);
                profile_pop_up.startAnimation(animFadein);
            }
        });

        return view;
    }

    public void Logout(){
        String Token = SessionStorage.getString("Tokens", "");
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.Logout(
                Token
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String CheckStatus = response.body().getMeta().getStatus();

                if (CheckStatus.equals("success")){
                    SessionEdit = SessionStorage.edit();
                    SessionEdit.clear();

                    Intent Exit = new Intent(getContext(), Login.class);
                    startActivity(Exit);
                    ((Activity)getContext()).finish();
                    ((Activity)getContext()).overridePendingTransition(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                } else {
                    Toast.makeText(getContext(), response.body().getResponseData().getMessage(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(getContext(),"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}