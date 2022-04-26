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

    @GET("pinjaman_saya/{id}")
    Call<ResponseAPI> PinjamanSaya(
            @Header("Authorization") String Token,
            @Path(value = "id", encoded = true) int id
    );

    @FormUrlEncoded
    @POST("pinjam_buku")
    Call<ResponseAPI> PinjamBuku(
            @Header("Authorization") String Token,
            @Field("id") int Id,
            @Field("tanggal_pinjam") String Tanggal,
            @Field("jumlah_buku") int Jumlah_Buku
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

    @GET("categories")
    Call<ResponseAPI> getAllCategories(
            @Header("Authorization") String Token
    );

    @POST("logout")
    Call<ResponseAPI> Logout(
            @Header("Authorization") String Token
    );

    @FormUrlEncoded
    @POST("user")
    Call<ResponseAPI> UpdateProfile(
            @Header("Authorization") String Token,
            @Field("name") String Nama,
            @Field("username") String Username,
            @Field("email") String Email,
            @Field("phone") int Phone
    );

}