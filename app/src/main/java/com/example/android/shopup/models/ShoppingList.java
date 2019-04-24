package com.example.android.shopup.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "shoppingList")
public class ShoppingList {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public Date date;
    public List<ShoppingItem> shoppingItems;
    public boolean isArchived;

    public ShoppingList(String name) {
        this.name = name;
        this.date = new Date();
        this.shoppingItems = new ArrayList<>();
        this.isArchived = false;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }
}
