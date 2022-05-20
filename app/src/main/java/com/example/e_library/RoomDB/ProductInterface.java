package com.example.e_library.RoomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductInterface {

    @Query("SELECT * FROM product")
    List<Product> getAllProduct();

    @Insert
    void insertProduct(Product... products);

}
