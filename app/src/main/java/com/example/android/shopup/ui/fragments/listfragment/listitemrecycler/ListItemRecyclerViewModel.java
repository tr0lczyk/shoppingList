package com.example.android.shopup.ui.fragments.listfragment.listitemrecycler;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.android.shopup.R;
import com.example.android.shopup.models.ShoppingItem;
import com.example.android.shopup.utils.BaseItemView;
import com.example.android.shopup.utils.Navigator;
import com.example.android.shopup.utils.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListItemRecyclerViewModel extends RecyclerViewModel<ListItemRecyclerItemViewModel> {

    private static final String TAG = "listItemRecycler";
    private ArrayList<ListItemRecyclerItemViewModel> listItemRecyclerItemViewModels;
    private View.OnClickListener onItemClickListener;

    public ListItemRecyclerViewModel() {
        listItemRecyclerItemViewModels = new ArrayList<>();
        onItemClickListener = view -> {
            ListItemRecyclerItemViewModel listItemRecyclerItemViewModel =
                    (ListItemRecyclerItemViewModel) view.getTag(R.attr.itemModel);
            switch (view.getId()) {
                case R.id.listItemCheckBox:
                    if (isNavigatorAttached()) {
                        getNavigator().moveForward(
                                Navigator.Options.UPDATE_ITEM_CHECKBOX,
                                listItemRecyclerItemViewModel.itemBought.get(),
                                listItemRecyclerItemViewModel.itemPosition.get());
                    }
                    break;
                case R.id.blockView:
                    if (isNavigatorAttached()) {
                        getNavigator().moveForward(
                                Navigator.Options.SHOW_ISARCHIVED_DIALOG);
                    }
                    break;
            }
        };
    }

    @NonNull
    @Override
    public BaseItemView<ListItemRecyclerItemViewModel> createItemView(Context context, int ViewType) {
        ListItemRecyclerItem listItemRecyclerItem = new ListItemRecyclerItem(context);
        listItemRecyclerItem.setOnClickListener(onItemClickListener);
        return listItemRecyclerItem;
    }

    public void setListItemsToAdapter(List<ShoppingItem> shoppingItemList, boolean isArchived) {
        adapter.clearItems();
        listItemRecyclerItemViewModels.clear();
        for (int i = 0; i < shoppingItemList.size(); i++) {
            listItemRecyclerItemViewModels.add(
                    new ListItemRecyclerItemViewModel(shoppingItemList.get(i), i, isArchived));
        }
        handleResponse(this, listItemRecyclerItemViewModels);
    }
}
