package com.example.e_library.Model;

import com.example.e_library.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIRequest {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseAPI> Authentication(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("CheckToken")
    Call<ResponseAPI> CheckTokens(
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("register")
    Call<ResponseAPI> Register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );

    @GET("buku_favorite")
    Call<ResponseAPI> BukuFavorite();

    @POST("logout")
    Call<ResponseAPI> Logout(
            @Header("Authorization") String Token
    );

}
