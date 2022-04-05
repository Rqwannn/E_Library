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
    @POST("forgot_password")
    Call<ResponseAPI> ForgotPassword(
            @Field("email") String Email
    );

    @FormUrlEncoded
    @POST("register")
    Call<ResponseAPI> Register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );

    //  With Token

    @POST("CheckToken")
    Call<ResponseAPI> CheckTokens(
            @Header("Authorization") String token
    );

    @GET("buku_favorite")
    Call<ResponseAPI> BukuFavorite(
            @Header("Authorization") String Token
    );

    @POST("logout")
    Call<ResponseAPI> Logout(
            @Header("Authorization") String Token
    );

}
