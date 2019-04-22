package com.example.android.shopup.ui.fragments.listfragment.listitemrecycler;

import android.content.Context;

import com.example.android.shopup.R;
import com.example.android.shopup.databinding.ListItemBinding;
import com.example.android.shopup.utils.BaseCardView;
import com.example.android.shopup.utils.BaseItemView;

public class ListItemRecyclerItem extends BaseCardView implements BaseItemView<ListItemRecyclerItemViewModel> {

    public ListItemRecyclerItem(Context context) {
        super(context);
    }

    @Override
    protected int contentId() {
        return R.layout.list_item;
    }

    @Override
    public ListItemBinding getViewDataBinding() {
        return (ListItemBinding) super.getViewDataBinding();
    }

    @Override
    public void setData(int position, ListItemRecyclerItemViewModel listItemRecyclerItemViewModel) {
        setTag(R.attr.itemModel,listItemRecyclerItemViewModel);
        getViewDataBinding().listItemContainer.setTag(R.attr.itemModel,listItemRecyclerItemViewModel);
        getViewDataBinding().listItemCheckBox.setTag(R.attr.itemModel,listItemRecyclerItemViewModel);
        getViewDataBinding().listItemName.setTag(R.attr.itemModel,listItemRecyclerItemViewModel);
        getViewDataBinding().setViewModel(listItemRecyclerItemViewModel);
        getViewDataBinding().executePendingBindings();
    }
}
