package com.example.e_library.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    public static final String baseURL = "http://192.168.43.178/elibrary-app/public/api/";
    public static final String imgBukuURL = "http://192.168.43.178/elibrary-app/public/";
    public static Retrofit Server;

    public static Retrofit KonekServer(){

        if (Server == null){
            Server = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.level(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(interceptor)
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request newRequest  = chain.request().newBuilder()
//                                    .addHeader("Authorization", "Bearer " + API_KEY)
//                                    .build();
//                            return chain.proceed(newRequest);
//                        }
//                    })
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .build();
//
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//
//            Server = new Retrofit.Builder()
//                    .baseUrl(baseURL)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
        }

        return Server;
    }
}
