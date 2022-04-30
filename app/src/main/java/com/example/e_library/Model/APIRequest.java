package com.example.e_library.Model;

import com.example.e_library.BluePrint.Items;
import com.example.e_library.Response.ResponseAPI;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
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
    @POST("password/email")
    Call<ResponseAPI> SendOTP(
            @Field("email") String Email
    );

    @FormUrlEncoded
    @POST("password/reset")
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

    // Books API

    @GET("books/{categories}")
    Call<ResponseAPI> getBukuKategori(
            @Header("Authorization") String Token,
            @Path(value = "categories", encoded = true) String Kategori
    );

    @GET("books/{id}")
    Call<ResponseAPI> DetailBuku(
            @Header("Authorization") String Token,
            @Path(value = "id", encoded = true) int id
    );

    @GET("books/popular")
    Call<ResponseAPI> BukuFavorite(
            @Header("Authorization") String Token
    );

    @FormUrlEncoded
    @POST("books/hasil_search")
    Call<ResponseAPI> HasilSearch(
            @Header("Authorization") String Token,
            @Field("key") String Key,
            @Field("filter") String Filter
    );

    // Pinjaman API

    @GET("my-loan")
    Call<ResponseAPI> PinjamanSaya(
            @Header("Authorization") String Token
    );

    @POST("loan")
    Call<ResponseAPI> LoanBuku(
            @Header("Authorization") String Token,
            @Body Items item
    );

    @GET("loan/{id}")
    Call<ResponseAPI> SingleLoan(
            @Header("Authorization") String Token,
            @Path(value = "id", encoded = true) int id
    );

    // Categories API

    @GET("categories")
    Call<ResponseAPI> getAllCategories(
            @Header("Authorization") String Token
    );

    // Profile API

    @FormUrlEncoded
    @POST("user")
    Call<ResponseAPI> UpdateProfile(
            @Header("Authorization") String Token,
            @Field("name") String Nama,
            @Field("username") String Username,
            @Field("email") String Email,
            @Field("phone") int Phone
    );

    // Other API

    @POST("logout")
    Call<ResponseAPI> Logout(
            @Header("Authorization") String Token
    );

}