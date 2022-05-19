package com.example.e_library.JWTOptions;

import com.example.e_library.Response.BookCategoriesModel;
import com.example.e_library.Response.BukuModel;
import com.example.e_library.Response.CartModel;
import com.example.e_library.Response.TransactionsModel;
import com.example.e_library.Response.UserModel;

import java.util.List;


public class ResponseData {
    private String access_token;
    private String token_type;
    private String message;
    private int code;

    List<BukuModel> books;
    List<TransactionsModel> transactions;
    List<BookCategoriesModel> categories;

    UserModel user;
    BukuModel SingleBooks;
    CartModel cart;

    public String getTokenType() { return token_type; }

    public String getAccessToken() { return access_token; }

    public String getMessage() { return message; }

    public List<BukuModel> getBuku() { return books; }

    public List<TransactionsModel> getPinjamanSaya() { return transactions; }

    public List<BookCategoriesModel> getCategories(){ return categories; }


    public UserModel getOneUser() { return user; }

    public BukuModel getSingleBooks() { return SingleBooks; }

    public CartModel getCart() { return cart; }

    public int getOTP () { return code; }
}
