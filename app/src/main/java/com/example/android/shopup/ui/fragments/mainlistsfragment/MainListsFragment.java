package com.example.android.shopup.ui.fragments.mainlistsfragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.shopup.R;
import com.example.android.shopup.databinding.FragmentMainListsBinding;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.BaseFragment;

import java.util.List;

public class MainListsFragment extends BaseFragment {

    public static final String ARG_FRAGMENT = "mainListsFragment";
    private static final String TAG = "mainListsFragment";

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
        getViewModel().getAllShoppingLists().observe(getActivity(), new Observer<List<ShoppingList>>() {
            @Override
            public void onChanged(List<ShoppingList> shoppingLists) {
                getViewModel().setupMainListsRecyclerAdapter();
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT){
                   ShoppingList currentShoppingList = getViewModel().allShoppingLists.getValue().get(viewHolder.getAdapterPosition());
                   getViewModel().shoppingListsRepository.delete(currentShoppingList);
                } else {

                }
            }
        }).attachToRecyclerView(getViewDataBinding().mainListsRecycler);
    }

    private void setupMainListsRecycler(){
        getViewDataBinding().mainListsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
    }
}
