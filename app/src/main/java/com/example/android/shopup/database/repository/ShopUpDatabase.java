package com.example.android.shopup.database.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.android.shopup.database.dao.ShoppingListDao;
import com.example.android.shopup.database.typeconverters.Converters;
import com.example.android.shopup.models.ShoppingList;

@Database(entities = ShoppingList.class, version = 5,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ShopUpDatabase extends RoomDatabase {

    private static ShopUpDatabase db;

    public abstract ShoppingListDao getSgoppingListDao();

    public static synchronized ShopUpDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    ShopUpDatabase.class,
                    "shopUpDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}
