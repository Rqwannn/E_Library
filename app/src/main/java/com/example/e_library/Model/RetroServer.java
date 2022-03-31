package com.example.e_library.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    public static final String baseURL = "http://192.168.1.5/elibrary-app/public/api/";
    public static final String imgBukuURL = "URL";
    public static Retrofit Server;

    public static Retrofit KonekServer(){

        if (Server == null){
            Server = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return Server;
    }
}
