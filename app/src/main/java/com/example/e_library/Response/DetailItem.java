package com.example.e_library.Response;

import java.util.List;

public class DetailItem {
    private int id, qty;
    String created_at, updated_at;
    BukuModel book;

    public int getId() { return id; }

    public int getQty() { return qty; }

    public String getCreatedAt() { return created_at; }

    public String getUpdatedAt() { return updated_at; }

    public BukuModel getBook() { return book; }
}
