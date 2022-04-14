package com.example.e_library.Model;

import com.example.e_library.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIRequest {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseAPI> Authentication(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("send_otp")
    Call<ResponseAPI> SendOTP(
            @Field("email") String Email
    );

    @FormUrlEncoded
    @POST("reset_password")
    Call<ResponseAPI> ResetPassword(
            @Field("password") String Password
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

    @GET("books?id={id}")
    Call<ResponseAPI> DetailBuku(
            @Header("Authorization") String Token,
            @Path(value = "id", encoded = true) int id
    );

    @GET("books")
    Call<ResponseAPI> BukuFavorite(
            @Header("Authorization") String Token
    );

    @POST("logout")
    Call<ResponseAPI> Logout(
            @Header("Authorization") String Token
    );

    @FormUrlEncoded
    @POST("update_profile")
    Call<ResponseAPI> UpdateProfile(
            @Header("Authorization") String Token,
            @Field("name") String Nama,
            @Field("username") String Username,
            @Field("email") String Email,
            @Field("phone") int Phone
    );

}
