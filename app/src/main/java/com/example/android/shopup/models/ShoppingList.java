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
    public int shoppingItemTotalNumber;
    public int shoppingItemBoughtNumber;
    public boolean isArchived;

    public ShoppingList(String name) {
        this.name = name;
        this.date = new Date();
        this.shoppingItems = new ArrayList<>();
        this.isArchived = false;
        this.shoppingItemTotalNumber = shoppingItems.size();
        this.shoppingItemBoughtNumber = 0;
    }

    public int getShoppingItemTotalNumber() {
        return shoppingItemTotalNumber;
    }

    public void setShoppingItemTotalNumber(int shoppingItemTotalNumber) {
        this.shoppingItemTotalNumber = shoppingItemTotalNumber;
    }

    public int getShoppingItemBoughtNumber() {
        return shoppingItemBoughtNumber;
    }

    public void setShoppingItemBoughtNumber(int shoppingItemBoughtNumber) {
        this.shoppingItemBoughtNumber = shoppingItemBoughtNumber;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
