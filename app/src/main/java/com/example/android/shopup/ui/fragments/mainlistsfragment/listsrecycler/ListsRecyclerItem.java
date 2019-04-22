package com.example.android.shopup.ui.fragments.mainlistsfragment.listsrecycler;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.example.android.shopup.R;
import com.example.android.shopup.databinding.MainListsItemBinding;
import com.example.android.shopup.utils.BaseCardView;
import com.example.android.shopup.utils.BaseItemView;

public class ListsRecyclerItem extends BaseCardView implements BaseItemView<ListsRecyclerItemViewModel> {

    public ListsRecyclerItem(Context context) {
        super(context);
    }

    @Override
    public void setData(int position, ListsRecyclerItemViewModel listsRecyclerItemViewModel) {
        setTag(R.attr.itemModel,listsRecyclerItemViewModel);
        getViewDataBinding().mainListsItemContainer.setTag(R.attr.itemModel,listsRecyclerItemViewModel);
        getViewDataBinding().mainListsName.setTag(R.attr.itemModel,listsRecyclerItemViewModel);
        getViewDataBinding().mainListsItemsNumber.setTag(R.attr.itemModel,listsRecyclerItemViewModel);
        getViewDataBinding().mainListsMaxItems.setTag(R.attr.itemModel,listsRecyclerItemViewModel);
        getViewDataBinding().setViewModel(listsRecyclerItemViewModel);
        getViewDataBinding().executePendingBindings();
    }

    @Override
    protected int contentId() {
        return R.layout.main_lists_item;
    }

    @Override
    public MainListsItemBinding getViewDataBinding() {
        return (MainListsItemBinding) super.getViewDataBinding();
    }
}
