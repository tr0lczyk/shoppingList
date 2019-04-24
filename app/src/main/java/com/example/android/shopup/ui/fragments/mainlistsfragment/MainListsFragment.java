package com.example.android.shopup.ui.fragments.mainlistsfragment;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
        getViewModel().listsRecyclerViewModel.attachNavigator(this);
        setupMainListsRecycler();
        getViewModel().getActiveShoppingLists().observe(getActivity(), shoppingLists -> getViewModel().setupMainListsRecyclerAdapter());
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            private Drawable icon = getResources().getDrawable(R.drawable.ic_delete_white_24dp);
            private ColorDrawable background = new ColorDrawable(0xFFFF0000);

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState,
                                    boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder,
                        dX, dY, actionState, isCurrentlyActive);
                View itemView = viewHolder.itemView;
                int backgroundCornerOffset = 20;

                if (dX > 0) {
                    icon = getResources().getDrawable(R.drawable.ic_archive_white_32dp);
                    int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                    int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                    int iconBottom = iconTop + icon.getIntrinsicHeight();
                    int iconLeft = itemView.getLeft() + iconMargin;
                    int iconRight = iconLeft + icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                    background = new ColorDrawable(0xFFFFBB33);
                    background.setBounds(itemView.getLeft(), itemView.getTop(),
                            itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());
                } else if (dX < 0) {
                    icon = getResources().getDrawable(R.drawable.ic_delete_white_24dp);
                    int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                    int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                    int iconBottom = iconTop + icon.getIntrinsicHeight();
                    int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
                    int iconRight = itemView.getRight() - iconMargin;
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                    background = new ColorDrawable(0xFFFF0000);
                    background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                            itemView.getTop(), itemView.getRight(), itemView.getBottom());
                } else {
                    background.setBounds(0, 0, 0, 0);
                }

                background.draw(c);
                icon.draw(c);
            }
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT){
                   ShoppingList currentShoppingList = getViewModel().allActiveShoppingLists.getValue().get(viewHolder.getAdapterPosition());
                   getViewModel().shoppingListsRepository.delete(currentShoppingList);
                } else {
                    ShoppingList currentShoppingList = getViewModel().allActiveShoppingLists.getValue().get(viewHolder.getAdapterPosition());
                    currentShoppingList.isArchived = true;
                    getViewModel().shoppingListsRepository.update(currentShoppingList);
                }
            }
        }).attachToRecyclerView(getViewDataBinding().mainListsRecycler);
    }

    private void setupMainListsRecycler(){
        getViewDataBinding().mainListsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
    }
}
