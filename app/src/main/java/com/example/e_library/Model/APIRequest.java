package com.example.e_library.Model;

import com.example.e_library.Chart.Items;
import com.example.e_library.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequest {

    // Auth API

    @FormUrlEncoded
    @POST("login")
    Call<ResponseAPI> Authentication(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<ResponseAPI> Register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );

    // Forgot Password API

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
    @POST("password/code/check")
    Call<ResponseAPI> KonfirmasiOTP(
            @Field("code") String Code
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

    @GET("books?")
    Call<ResponseAPI> HasilSearch(
            @Header("Authorization") String Token,
            @Query("search") String search
    );

    @GET("books?")
    Call<ResponseAPI> HasilFilter(
            @Header("Authorization") String Token,
            @Query("categories") String search
    );

    // Pinjaman API

    @GET("transaction")
    Call<ResponseAPI> Pinjaman(
            @Header("Authorization") String Token
    );

    @GET("cart")
    Call<ResponseAPI> PinjamanSaya(
            @Header("Authorization") String Token
    );

    @GET("transaction/{id}")
    Call<ResponseAPI> DetailLoan(
            @Header("Authorization") String Token,
            @Path(value = "id", encoded = true) int id
    );

    @POST("transaction")
    Call<ResponseAPI> LoanBuku(
            @Header("Authorization") String Token,
            @Body Items item
    );

    @FormUrlEncoded
    @POST("cart-detail")
    Call<ResponseAPI> SingleLoan(
            @Header("Authorization") String Token,
            @Field("book_id") int ID_BOOK
    );

    // Cart

    @DELETE("cart-detail/{id}")
    Call<ResponseAPI> DeleteKeranjang(
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
            @Field("phone") String Phone
    );

    // Other API

    @POST("logout")
    Call<ResponseAPI> Logout(
            @Header("Authorization") String Token
    );

}