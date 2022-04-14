package com.example.e_library.JWTOptions;

import com.example.e_library.Response.BukuModel;
import com.example.e_library.Response.UserModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;

public class ResponseData {
    private String access_token;
    private String token_type;
    private String message;
    private int kode_otp;
    List<BukuModel> MoreBookData;
    UserModel user;

    public String getTokenType() { return token_type; }

    public String getAccessToken() { return access_token; }

    public String getMessage() { return message; }

    public List<BukuModel> getBuku() { return MoreBookData; }

    public UserModel getOneUser() { return user; }

    public int getOTP () { return kode_otp; }
}
