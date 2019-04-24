package com.example.android.shopup.ui.fragments.mainlistsfragment.listsrecycler;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import com.example.android.shopup.models.ShoppingItem;
import com.example.android.shopup.models.ShoppingList;

public class ListsRecyclerItemViewModel extends BaseObservable {

    public ObservableField<String> shoppingListName;
    public ObservableField<String> shoppingListShoppingItemsTotalNumber;
    public ObservableField<String> shoppingListShoppingItemsBoughtNumber;
    public ObservableField<Integer> shoppingListId;
    public ObservableField<Boolean> isArchived;
    public ShoppingList shoppingListModel;

    public ListsRecyclerItemViewModel(ShoppingList shoppingList) {
        this.shoppingListModel = shoppingList;
        int totalBoughtItemsNumber = 0;
        for(ShoppingItem shoppingItem : shoppingList.shoppingItems){
            if(shoppingItem.isBought){
                totalBoughtItemsNumber++;
            }
        }
        shoppingListName = new ObservableField<>(shoppingListModel.name);
        shoppingListShoppingItemsTotalNumber = new ObservableField<>(String.valueOf(shoppingListModel.shoppingItems.size()));
        shoppingListShoppingItemsBoughtNumber = new ObservableField<>(String.valueOf(totalBoughtItemsNumber));
        shoppingListId = new ObservableField<>(shoppingListModel.id);
        isArchived = new ObservableField<>(shoppingListModel.isArchived);
    }
}
