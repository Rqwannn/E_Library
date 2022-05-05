package com.example.e_library.JWTOptions;

import com.example.e_library.Response.BookCategoriesModel;
import com.example.e_library.Response.BukuModel;
import com.example.e_library.Response.TransactionsModel;
import com.example.e_library.Response.UserModel;

import java.util.List;


public class ResponseData {
    private String access_token;
    private String token_type;
    private String message;
    private int code;

    List<BukuModel> buku;
    List<TransactionsModel> transaction;
    List<BookCategoriesModel> categories;

    UserModel user;

    public String getTokenType() { return token_type; }

    public String getAccessToken() { return access_token; }

    public String getMessage() { return message; }

    public List<BukuModel> getBuku() { return buku; }

    public List<TransactionsModel> getPinjamanSaya() { return transaction; }

    public List<BookCategoriesModel> getCategories(){ return categories; }

    public UserModel getOneUser() { return user; }

    public int getOTP () { return code; }
}
