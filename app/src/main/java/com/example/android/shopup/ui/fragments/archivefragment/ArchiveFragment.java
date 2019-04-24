package com.example.android.shopup.ui.fragments.archivefragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.shopup.R;
import com.example.android.shopup.databinding.FragmentArchiveBinding;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.BaseFragment;

public class ArchiveFragment extends BaseFragment {

    public static final String ARG_FRAGMENT = "mainListsFragment";
    private static final String TAG = "mainListsFragment";

    public static Fragment newInstance(int id, Bundle bundle) {
        Bundle args = bundle;
        Fragment archiveFragment = new ArchiveFragment();

        if (args == null) {
            args = new Bundle();
        }

        args.putSerializable(ARG_FRAGMENT, id);
        archiveFragment.setArguments(args);
        return archiveFragment;
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_archive;
    }

    @Override
    public FragmentArchiveBinding getViewDataBinding() {
        return (FragmentArchiveBinding) super.getViewDataBinding();
    }

    @Override
    public Class<? extends BaseAndroidViewModel> createViewModel() {
        return ArchiveViewModel.class;
    }

    @Override
    public ArchiveViewModel getViewModel() {
        return (ArchiveViewModel) viewModel;
    }

    @Override
    public void afterViews(Bundle savedInstanceState) {
        super.afterViews(savedInstanceState);
        getViewDataBinding().setViewModel(getViewModel());
        getViewModel().attachNavigator(this);
        getViewModel().listsRecyclerViewModel.attachNavigator(this);
        setupMainListsRecycler();
        getViewModel().getArchivedShoppingLists().observe(getActivity(), shoppingLists ->
            getViewModel().setupArchivedListsRecyclerAdapter());
    }

    private void setupMainListsRecycler(){
        getViewDataBinding().archiveRecycler.setLayoutManager(
                new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
    }
}
