package com.example.android.shopup.ui.fragments.namelistfragment;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.android.shopup.R;
import com.example.android.shopup.databinding.FragmentNamelistBinding;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.ui.fragments.mainlistsfragment.MainListsFragment;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.BaseFragment;

import java.util.List;

public class NameListFragment extends BaseFragment {

    public static final String ARG_FRAGMENT = "nameListFragment";
    private static final String TAG = "nameListFragment";

    public static Fragment newInstance(int id, Bundle bundle) {
        Bundle args = bundle;
        Fragment nameListFragment = new NameListFragment();

        if (args == null) {
            args = new Bundle();
        }

        args.putSerializable(ARG_FRAGMENT, id);
        nameListFragment.setArguments(args);
        return nameListFragment;
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_namelist;
    }

    @Override
    public NameListViewModel getViewModel() {
        return (NameListViewModel) viewModel;
    }

    @Override
    public FragmentNamelistBinding getViewDataBinding() {
        return (FragmentNamelistBinding) super.getViewDataBinding();
    }

    @Override
    public Class<? extends BaseAndroidViewModel> createViewModel() {
        return NameListViewModel.class;
    }

    @Override
    public void afterViews(Bundle savedInstanceState) {
        super.afterViews(savedInstanceState);
        getViewDataBinding().setViewModel(getViewModel());
        getViewModel().attachNavigator(this);
    }
}
