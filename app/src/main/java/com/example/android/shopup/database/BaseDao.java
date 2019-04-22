package com.example.android.shopup.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {

    @Insert
    void insert(T object);

    @Update
    void update(T object);

    @Delete
    void delete(T object);
}
