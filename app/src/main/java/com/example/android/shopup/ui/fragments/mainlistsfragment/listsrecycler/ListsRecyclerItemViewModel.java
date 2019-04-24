package com.example.android.shopup.ui.fragments.mainlistsfragment.listsrecycler;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import com.example.android.shopup.models.ShoppingList;

public class ListsRecyclerItemViewModel extends BaseObservable {

    public ObservableField<String> shoppingListName;
    public ObservableField<String> shoppingListShoppingItemsTotalNumber;
    public ObservableField<String> shoppingListShoppingItemsBoughtNumber;
    public ObservableField<Integer> shoppingListId;
    public ShoppingList shoppingListModel;

    public ListsRecyclerItemViewModel(ShoppingList shoppingList) {
        this.shoppingListModel = shoppingList;
        shoppingListName = new ObservableField<>(shoppingListModel.name);
        shoppingListShoppingItemsTotalNumber = new ObservableField<>(String.valueOf(shoppingListModel.shoppingItemTotalNumber));
        shoppingListShoppingItemsBoughtNumber = new ObservableField<>(String.valueOf(shoppingListModel.shoppingItemBoughtNumber));
        shoppingListId = new ObservableField<>(shoppingListModel.id);
    }
}
