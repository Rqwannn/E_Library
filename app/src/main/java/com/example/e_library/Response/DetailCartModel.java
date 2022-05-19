package com.example.e_library.Response;

import java.util.List;

public class DetailCartModel {
    private int id;
    private int users_id;
    private String qty, created_at, updated_at;

    BukuModel book;

    public int getId() { return id; }

    public String getCreated() { return created_at; }

    public String getUpdated() { return updated_at; }

    public BukuModel getBook() { return book; }
}
