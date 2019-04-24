package com.example.android.shopup.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.example.android.shopup.database.BaseDao;
import com.example.android.shopup.models.ShoppingList;
import java.util.List;

@Dao
public interface ShoppingListDao extends BaseDao<ShoppingList> {

    @Query("DELETE FROM shoppingList")
    void nukeTable();

    @Query("SELECT * FROM shoppingList ORDER BY date DESC")
    LiveData<List<ShoppingList>> getAllItems();

    @Query("SELECT * FROM shoppingList WHERE id = :id")
    LiveData<ShoppingList> getOneItem(int id);

    @Query("SELECT * FROM shoppingList ORDER BY id DESC LIMIT 1")
    LiveData<ShoppingList> getLastItem();

    @Query("SELECT * FROM shoppingList WHERE isArchived = :isArchived")
    LiveData<List<ShoppingList>> getAllItemsifArchivedOrNot(boolean isArchived);
}
