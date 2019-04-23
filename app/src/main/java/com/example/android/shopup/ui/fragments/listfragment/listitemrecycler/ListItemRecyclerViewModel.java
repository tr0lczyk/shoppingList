package com.example.android.shopup.ui.fragments.listfragment.listitemrecycler;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.android.shopup.models.ShoppingItem;
import com.example.android.shopup.utils.BaseItemView;
import com.example.android.shopup.utils.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListItemRecyclerViewModel extends RecyclerViewModel<ListItemRecyclerItemViewModel> {

    private ArrayList<ListItemRecyclerItemViewModel> listItemRecyclerItemViewModels;

    public ListItemRecyclerViewModel() {
        listItemRecyclerItemViewModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseItemView<ListItemRecyclerItemViewModel> createItemView(Context context, int ViewType) {
        ListItemRecyclerItem listItemRecyclerItem = new ListItemRecyclerItem(context);
        return listItemRecyclerItem;
    }

    public void setListItemsToAdapter(List<ShoppingItem> shoppingItemList){
        adapter.clearItems();
        listItemRecyclerItemViewModels.clear();
        for(ShoppingItem shoppingItem : shoppingItemList){
            listItemRecyclerItemViewModels.add(new ListItemRecyclerItemViewModel(shoppingItem));
        }
        handleResponse(this,listItemRecyclerItemViewModels);
    }

//    public void setListItemsToAdapter2(){
//        adapter.clearItems();
//        listItemRecyclerItemViewModels.clear();
//        for(int i = 0; i < 10; i++){
//            listItemRecyclerItemViewModels.add(new ListItemRecyclerItemViewModel());
//        }
//        handleResponse(this,listItemRecyclerItemViewModels);
//    }
}
