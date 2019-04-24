package com.example.android.shopup.ui.fragments.mainlistsfragment.listsrecycler;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.android.shopup.R;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.utils.BaseItemView;
import com.example.android.shopup.utils.Navigator;
import com.example.android.shopup.utils.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListsRecyclerViewModel extends RecyclerViewModel<ListsRecyclerItemViewModel> {

    private ArrayList<ListsRecyclerItemViewModel> listsRecyclerItemViewModels;
    private View.OnClickListener onItemClickListener;

    public ListsRecyclerViewModel() {
        listsRecyclerItemViewModels = new ArrayList<>();
        onItemClickListener = view -> {
            ListsRecyclerItemViewModel listsRecyclerItemViewModel = (ListsRecyclerItemViewModel) view.getTag(R.attr.itemModel);
            switch (view.getId()) {
                case R.id.mainListsItemContainer:
                    if (isNavigatorAttached()) {
                        getNavigator().moveForward(Navigator.Options.OPEN_LIST_FRAGMENT,listsRecyclerItemViewModel.shoppingListId.get());
                    }
                    break;
            }
        };
    }

    @NonNull
    @Override
    public BaseItemView<ListsRecyclerItemViewModel> createItemView(Context context, int ViewType) {
        ListsRecyclerItem listsRecyclerItem = new ListsRecyclerItem(context);
        listsRecyclerItem.setOnClickListener(onItemClickListener);
        return listsRecyclerItem;
    }

    public void setListsToRecycler(List<ShoppingList> shoppingLists) {
        adapter.clearItems();
        listsRecyclerItemViewModels.clear();
        for (ShoppingList shoppingList : shoppingLists) {
            listsRecyclerItemViewModels.add(new ListsRecyclerItemViewModel(shoppingList));
        }
        handleResponse(this, listsRecyclerItemViewModels);
    }
}
