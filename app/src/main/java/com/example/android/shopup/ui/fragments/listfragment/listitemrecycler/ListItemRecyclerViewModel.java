package com.example.android.shopup.ui.fragments.listfragment.listitemrecycler;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.android.shopup.utils.BaseItemView;
import com.example.android.shopup.utils.RecyclerViewModel;

import java.util.ArrayList;

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

    public void setListItemsToAdapter(){
        adapter.clearItems();
        listItemRecyclerItemViewModels.clear();
        for(int i = 0; i < 20; i++){
            listItemRecyclerItemViewModels.add(new ListItemRecyclerItemViewModel());
        }
        handleResponse(this,listItemRecyclerItemViewModels);
    }

    public void setListItemsToAdapter2(){
        adapter.clearItems();
        listItemRecyclerItemViewModels.clear();
        for(int i = 0; i < 10; i++){
            listItemRecyclerItemViewModels.add(new ListItemRecyclerItemViewModel());
        }
        handleResponse(this,listItemRecyclerItemViewModels);
    }
}
