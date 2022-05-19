package com.example.e_library.Response;

import java.util.List;

public class ItemsModel {
    private int id;
    String no_invoice, status_cart, created_at, updated_at;
    List<DetailItem> detail;

    public int getId() { return id; }

    public String getNoInvoice() { return no_invoice; }

    public String getStatusCart() { return status_cart; }

    public String getCreatedAt() { return created_at; }

    public String getUpdatedAt() { return updated_at; }

    public List<DetailItem> getDetail() { return detail; }
}
