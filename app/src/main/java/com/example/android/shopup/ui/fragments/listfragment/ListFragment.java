package com.example.android.shopup.ui.fragments.listfragment;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.shopup.R;
import com.example.android.shopup.databinding.FragmentListBinding;
import com.example.android.shopup.models.ShoppingItem;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.BaseFragment;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.List;

import static com.example.android.shopup.ui.activities.MainActivity.SHOPPING_LIST_KEY;

public class ListFragment extends BaseFragment {

    public static final String ARG_FRAGMENT = "listFragment";
    private static final String TAG = "listFragment";

    public static Fragment newInstance(int id, Bundle bundle) {
        Bundle args = bundle;
        Fragment listFragment = new ListFragment();

        if (args == null) {
            args = new Bundle();
        }

        args.putSerializable(ARG_FRAGMENT, id);
        listFragment.setArguments(args);
        return listFragment;
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_list;
    }

    @Override
    public ListViewModel getViewModel() {
        return (ListViewModel) viewModel;
    }

    @Override
    public FragmentListBinding getViewDataBinding() {
        return (FragmentListBinding) super.getViewDataBinding();
    }

    @Override
    public Class<? extends BaseAndroidViewModel> createViewModel() {
        return ListViewModel.class;
    }

    @Override
    public void afterViews(Bundle savedInstanceState) {
        super.afterViews(savedInstanceState);
        getViewDataBinding().setViewModel(getViewModel());
        getViewModel().attachNavigator(this);
        if(getArguments().getInt(SHOPPING_LIST_KEY) > 0){
            getViewModel().shoppingItemId.set(getArguments().getInt(SHOPPING_LIST_KEY));
            getViewModel().getShoppingListWithId().observe(getActivity(), new Observer<ShoppingList>() {
                @Override
                public void onChanged(ShoppingList shoppingList) {
                    updateList(shoppingList);
                    getViewModel().getShoppingListWithId().removeObservers(getActivity());
                }
            });
        } else {
            getViewModel().getLastShoppingList().observe(getActivity(), new Observer<ShoppingList>() {
                @Override
                public void onChanged(ShoppingList shoppingList) {
                    updateList(shoppingList);
                    getViewModel().getLastShoppingList().removeObservers(getActivity());
                }
            });
        }
        setupMainListsRecycler();
        getViewModel().shoppingItems.observe(getActivity(), new Observer<List<ShoppingItem>>() {
            @Override
            public void onChanged(List<ShoppingItem> shoppingItems) {
                getViewModel().setupListRecyclerAdapter();
                if(getViewModel().currentShoppingList.get() != null){
                    ShoppingList newShoppingList = getViewModel().currentShoppingList.get();
                    newShoppingList.setShoppingItems(shoppingItems);
                    getViewModel().updateList(newShoppingList);
                    getViewModel().setupListRecyclerAdapter();
                }
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {

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

                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                int iconBottom = iconTop + icon.getIntrinsicHeight();

                if (dX < 0) {
                    int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
                    int iconRight = itemView.getRight() - iconMargin;
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
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
                    List<ShoppingItem> currentShoppingItems = getViewModel().shoppingItems.getValue();
                    currentShoppingItems.remove(viewHolder.getAdapterPosition());
                    getViewModel().shoppingItems.postValue(currentShoppingItems);
                }
            }
        }).attachToRecyclerView(getViewDataBinding().listRecycler);
    }

    @Override
    public void moveForward(Options options, Object... data) {
        super.moveForward(options, data);
        switch(options){
            case OPEN_ADD_LIST_MENU:
                openAddListItemMenu();
                break;
            case CLOSE_ADD_LIST_MENU:
                closeAddListItemMenu();
                break;
        }
    }

    private void setupMainListsRecycler(){
        getViewDataBinding().listRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
    }

    public void openAddListItemMenu(){
        getViewDataBinding().addItemEditText.requestFocus();
        UIUtil.showKeyboard(getActivity(),getViewDataBinding().addItemEditText);
    }

    public void closeAddListItemMenu(){
        getViewDataBinding().addItemEditText.clearFocus();
        UIUtil.hideKeyboard(getActivity());
        getViewDataBinding().addItemContainer.animate().translationY(0).start();
        getViewDataBinding().listAddItemMenuButton.animate().translationX(0).start();
    }

    public void updateList(ShoppingList shoppingList){
        getViewModel().listName.set(shoppingList.name);
        getViewModel().shoppingItems.setValue(shoppingList.shoppingItems);
        getViewModel().currentShoppingList.set(shoppingList);
    }
}
