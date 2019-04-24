package com.example.android.shopup.ui.fragments.listfragment.listitemrecycler;

import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.example.android.shopup.R;
import com.example.android.shopup.models.ShoppingItem;

public class ListItemRecyclerItemViewModel extends BaseObservable {


    private static final String TAG = "listItemRecyclerItem";
    public ObservableField<String> itemName;
    public ObservableField<Boolean> itemBought;
    public ObservableField<Integer> itemPosition;
    public ObservableField<Integer> itemBackground;
    public ObservableField<Integer> blockViewVisibility;
    private ShoppingItem shoppingItemModel;

    public ListItemRecyclerItemViewModel(ShoppingItem shoppingItem, int position, boolean isArchived) {
        this.shoppingItemModel = shoppingItem;
        itemName = new ObservableField<>(shoppingItemModel.name);
        itemBought = new ObservableField<>(shoppingItem.isBought);
        itemPosition = new ObservableField<>(position);

        if(itemBought.get()){
            itemBackground = new ObservableField<>(R.drawable.main_lists_item_background_done);
        } else {
            itemBackground = new ObservableField<>(R.drawable.main_lists_item_background);
        }
        itemBought.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(itemBought.get()){
                    itemBackground.set(R.drawable.main_lists_item_background_done);
                } else {
                    itemBackground.set(R.drawable.main_lists_item_background);
                }
            }
        });

        if(!isArchived){
            blockViewVisibility = new ObservableField<>(View.GONE);
        } else {
            blockViewVisibility = new ObservableField<>(View.VISIBLE);
        }
    }
}
