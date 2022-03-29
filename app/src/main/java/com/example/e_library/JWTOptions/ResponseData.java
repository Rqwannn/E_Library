package com.example.e_library.JWTOptions;

import com.example.e_library.Response.BukuModel;

import java.util.List;

import retrofit2.Call;

public class ResponseData {
    private String access_token;
    private String token_type;
    private String message;
    List<BukuModel> buku;

    public String getTokenType() { return token_type; }

    public String getAccessToken() { return access_token; }

    public String getMessage() { return message; }

    public List<BukuModel> getBuku() { return buku; }
}
