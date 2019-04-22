package com.example.android.shopup.ui.fragments.mainlistsfragment.listsrecycler;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.android.shopup.utils.BaseItemView;
import com.example.android.shopup.utils.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListsRecyclerViewModel extends RecyclerViewModel<ListsRecyclerItemViewModel> {

    private ArrayList<ListsRecyclerItemViewModel> listsRecyclerItemViewModels;


    public ListsRecyclerViewModel() {
        listsRecyclerItemViewModels = new ArrayList<>();
    }


    @NonNull
    @Override
    public BaseItemView<ListsRecyclerItemViewModel> createItemView(Context context, int ViewType) {
        ListsRecyclerItem listsRecyclerItem = new ListsRecyclerItem(context);
        return listsRecyclerItem;
    }

    public void setListsToRecycler(){
        adapter.clearItems();
        listsRecyclerItemViewModels.clear();
        for(int i = 0; i < 10; i++){
            listsRecyclerItemViewModels.add(new ListsRecyclerItemViewModel());
        }
        handleResponse(this,listsRecyclerItemViewModels);
    }
}
