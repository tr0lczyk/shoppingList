package com.example.android.shopup.models;

public class ShoppingItem {

    public String name;
    public boolean isBought;

    public ShoppingItem(String name) {
        this.name = name;
        this.isBought = false;
    }
}
