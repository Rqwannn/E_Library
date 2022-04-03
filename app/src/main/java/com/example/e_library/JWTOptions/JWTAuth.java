package com.example.e_library.JWTOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import com.example.e_library.Login;
import com.example.e_library.Model.APIRequest;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JWTAuth {
    private Boolean Success;
    private SharedPreferences SessionStorage;
    private SharedPreferences.Editor SessionEdit;
    private String token;

    public void CheckTokens(Context ctx, String OLDTokens){
        token = OLDTokens;
        APIRequest API = RetroServer.KonekServer().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = API.CheckTokens(
                "Bearer " + token
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                String Success = response.body().getMeta().getStatus();

                if (!Success.equals("success")){
                    Toast.makeText(ctx,response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SessionEdit = SessionStorage.edit();
                            SessionEdit.clear();

                            Intent ForceExit = new Intent(ctx, Login.class);
                            ctx.startActivity(ForceExit);
                            ((Activity)ctx).finish();
                        }
                    }, 100);
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(ctx,"Status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
