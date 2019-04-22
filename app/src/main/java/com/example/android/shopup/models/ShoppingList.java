package com.example.android.shopup.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity(tableName = "shoppingList")
public class ShoppingList {

    @PrimaryKey
    public int id;
    public String name;
    public Date date;
    public List<ShoppingItem> shoppingItems;
    public int shoppingItemTotalNumber;
    public int shoppingItemBoughtNumber;
    public boolean isArchived;
}
