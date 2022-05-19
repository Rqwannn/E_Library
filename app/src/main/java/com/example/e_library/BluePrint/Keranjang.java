package com.example.e_library.BluePrint;

import java.io.Serializable;

public class Keranjang implements Serializable {

    private int id, quantity;

    public Keranjang(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
