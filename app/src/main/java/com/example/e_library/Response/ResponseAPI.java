package com.example.e_library.Response;

import com.example.e_library.JWTOptions.Meta;
import com.example.e_library.JWTOptions.ResponseData;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class ResponseAPI {
    private Meta meta;
    private ResponseData data;

    public Meta getMeta() { return meta; }

    public ResponseData getResponseData() { return data; }

}
