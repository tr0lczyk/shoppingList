package com.example.android.shopup.database.typeconverters;

import androidx.room.TypeConverter;

import com.example.android.shopup.models.ShoppingItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class Converters implements Serializable {

    @TypeConverter
    public static String fromShoppingItemObject(List<ShoppingItem> shoppingItem){
        if(shoppingItem == null){
            return null;
        }
        Gson gson = new Gson();
        String json = gson.toJson(shoppingItem);
        return json;
    }

    @TypeConverter
    public static List<ShoppingItem> toListShoppingItemObject(String galleryItemListString){
        if(galleryItemListString == null){
            return null;
        }
        Type type = new TypeToken<List<ShoppingItem>>(){}.getType();
        return new Gson().fromJson(galleryItemListString,type);
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
