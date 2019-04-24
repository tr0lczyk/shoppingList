package com.example.android.shopup.database.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

abstract class BaseRepository<T> {

    public ShopUpDatabase database;

    public BaseRepository(Application application){
        database = ShopUpDatabase.getInstance(application);
    }

    public abstract void insert(T object);

    public abstract void update(T object);

    public abstract void delete(T object);

    public abstract void nukeAllObjects();

    public abstract LiveData<List<T>> getAllObjects();

    public abstract LiveData<T> getOneObject(int id);
}
