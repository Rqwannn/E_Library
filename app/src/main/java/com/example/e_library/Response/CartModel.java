package com.example.e_library.Response;

import java.util.List;

public class CartModel {
    private int id;
    private int users_id;
    private String no_invoice, status_cart, created_at, updated_at;

    List<DetailCartModel> detail;

    public int getId() { return id; }

    public String getNoInvoice() { return no_invoice; }

    public String getStatusCart() { return status_cart; }

    public String getCreated() { return created_at; }

    public String getUpdated() { return updated_at; }

    public List<DetailCartModel> getDetail() { return detail; }
}
