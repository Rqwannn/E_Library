package com.example.e_library.Response;

public class TransactionsModel {
    private int id;
    private int book_id;
    private int users_id;
    private String quantity, loan_date, return_date, status, created_at, updated_at, deleted_at, img;
    private BukuModel book;

    public int getId() { return id; }

    public String getLoanDate() { return loan_date; }

    public String getReturnDate() { return return_date; }

    public String getStatus() { return status; }

    public String getQuantity() { return quantity; }

    public String getCreated() { return created_at; }

    public String getUpdated() { return updated_at; }

    public String getDeleted() { return deleted_at; }

    public String getImg() { return img; }

    public BukuModel getBook() { return book; }
}
