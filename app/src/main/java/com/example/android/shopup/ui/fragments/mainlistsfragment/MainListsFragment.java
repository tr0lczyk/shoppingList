package com.example.android.shopup.ui.fragments.mainlistsfragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.shopup.R;
import com.example.android.shopup.databinding.FragmentMainListsBinding;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.BaseFragment;
import com.example.android.shopup.utils.SwipeListsController;

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
        getViewModel().listsRecyclerViewModel.attachNavigator(this);
        setupMainListsRecycler();
        getViewModel().getActiveShoppingLists().observe(getActivity(), shoppingLists ->
                getViewModel().setupMainListsRecyclerAdapter());
        initSwipe(getViewDataBinding().mainListsRecycler);
    }

    private void setupMainListsRecycler() {
        getViewDataBinding().mainListsRecycler.setLayoutManager(
                new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    private void initSwipe(RecyclerView recyclerView) {
        SwipeListsController swipeListsController = new SwipeListsController(getActivity()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    ShoppingList currentShoppingList =
                            getViewModel().allActiveShoppingLists.getValue()
                                    .get(viewHolder.getAdapterPosition());
                    getViewModel().shoppingListsRepository.delete(currentShoppingList);
                    String deletedList = String.format(("%s%s%s"),
                            "List ", currentShoppingList.name, " is deleted");
                    Toast.makeText(getActivity(), deletedList, Toast.LENGTH_SHORT).show();
                } else {
                    ShoppingList currentShoppingList =
                            getViewModel().allActiveShoppingLists.getValue()
                                    .get(viewHolder.getAdapterPosition());
                    currentShoppingList.isArchived = true;
                    getViewModel().shoppingListsRepository.update(currentShoppingList);
                    String archivedList = String.format(("%s%s%s"),
                            "List ", currentShoppingList.name, " is archived");
                    Toast.makeText(getActivity(), archivedList, Toast.LENGTH_SHORT).show();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeListsController);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
