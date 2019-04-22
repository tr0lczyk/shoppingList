package com.example.android.shopup.ui.fragments.mainlistsfragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.shopup.R;
import com.example.android.shopup.databinding.FragmentMainListsBinding;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.BaseFragment;

public class MainListsFragment extends BaseFragment {

    public static final String ARG_FRAGMENT = "mainListsFragment";

    public static Fragment newInstance(int id, Bundle bundle) {
        Bundle args = bundle;
        Fragment mainListsFragment = new MainListsFragment();

        if (args == null) {
            args = new Bundle();
        }

        args.putSerializable(ARG_FRAGMENT, id);
        mainListsFragment.setArguments(args);
        return mainListsFragment;
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_main_lists;
    }

    @Override
    public FragmentMainListsBinding getViewDataBinding() {
        return (FragmentMainListsBinding) super.getViewDataBinding();
    }

    @Override
    public Class<? extends BaseAndroidViewModel> createViewModel() {
        return MainListsViewModel.class;
    }

    @Override
    public MainListsViewModel getViewModel() {
        return (MainListsViewModel) viewModel;
    }

    @Override
    public void afterViews(Bundle savedInstanceState) {
        super.afterViews(savedInstanceState);
        getViewDataBinding().setViewModel(getViewModel());
        getViewModel().attachNavigator(this);

        setupMainListsRecycler();
        getViewModel().setupMainListsRecyclerAdapter();
    }

    private void setupMainListsRecycler(){
        getViewDataBinding().mainListsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
    }
}
