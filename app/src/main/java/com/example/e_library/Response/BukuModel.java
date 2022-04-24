package com.example.e_library.Response;

public class BukuModel {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private String year_publisher;
    private int categories_id;
    private String description;
    private String quantity;
    private String image;
    private String created_at;
    private String updated_at;
    private String deleted_at;

    private BookCategoriesModel categories;

    public int getID() { return id; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public String getPublisher() { return publisher; }

    public String getYearPublisher() { return year_publisher; }

    public int getCategoriesId() { return categories_id; }

    public String getDescription() { return description; }

    public String getQuantity() { return quantity; }

    public String getNamaGambar() { return image; }

    public String getCreated() { return created_at; }

    public String getUpdated() { return updated_at; }

    public String getDeleted() { return deleted_at; }

    public BookCategoriesModel getCategories() { return categories; }
}
