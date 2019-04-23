package com.example.android.shopup.ui.fragments.listfragment.listitemrecycler;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import com.example.android.shopup.models.ShoppingItem;

public class ListItemRecyclerItemViewModel extends BaseObservable {


    public ObservableField<String> itemName;
    private ShoppingItem shoppingItemModel;

    public ListItemRecyclerItemViewModel(ShoppingItem shoppingItem) {
        this.shoppingItemModel = shoppingItem;
        itemName = new ObservableField<>(shoppingItemModel.name);
    }
}
