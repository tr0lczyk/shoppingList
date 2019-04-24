package com.example.android.shopup.ui.fragments.listfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

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
import com.example.android.shopup.utils.SwipeItemController;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.List;

import static com.example.android.shopup.ui.activities.MainActivity.IS_ARCHIVED_KEY;
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
        getViewModel().listItemRecyclerViewModel.attachNavigator(this);
        getViewModel().isArchived.set(getArguments().getBoolean(IS_ARCHIVED_KEY));
        if (getArguments().getInt(SHOPPING_LIST_KEY) > 0) {
            getViewModel().shoppingItemId.set(getArguments().getInt(SHOPPING_LIST_KEY));
            getViewModel().getShoppingListWithId().observe(getActivity(), shoppingList -> {
                updateList(shoppingList);
                getViewModel().getShoppingListWithId().removeObservers(getActivity());
            });
        } else {
            getViewModel().getLastShoppingList().observe(getActivity(), shoppingList -> {
                updateList(shoppingList);
                getViewModel().getLastShoppingList().removeObservers(getActivity());
            });
        }
        setupMainListsRecycler();
        getViewModel().shoppingItems.observe(getActivity(), shoppingItems -> {
            getViewModel().setupListRecyclerAdapter();
            if (getViewModel().currentShoppingList.get() != null) {
                ShoppingList newShoppingList = getViewModel().currentShoppingList.get();
                newShoppingList.setShoppingItems(shoppingItems);
                getViewModel().updateList(newShoppingList);
                getViewModel().setupListRecyclerAdapter();
            }
        });
        initSwipe(getViewDataBinding().listRecycler);
    }

    @Override
    public void moveForward(Options options, Object... data) {
        super.moveForward(options, data);
        switch (options) {
            case UPDATE_ITEM_CHECKBOX:
                if (data.length > 0 &&
                        data[0] != null &&
                        data[1] != null) {
                    boolean isBought = (boolean) data[0];
                    int itemPosition = (int) data[1];
                    updateItemList(isBought,itemPosition);
                    break;
                }
            case OPEN_ADD_LIST_MENU:
                openAddListItemMenu();
                break;
            case CLOSE_ADD_LIST_MENU:
                closeAddListItemMenu();
                break;
            case SHOW_ISARCHIVED_DIALOG:
                archivedDialog();
                break;
        }
    }

    private void setupMainListsRecycler() {
        getViewDataBinding().listRecycler.setLayoutManager(
                new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getViewDataBinding().listRecycler.setNestedScrollingEnabled(false);
    }

    private void openAddListItemMenu() {
        getViewDataBinding().addItemEditText.requestFocus();
        UIUtil.showKeyboard(getActivity(), getViewDataBinding().addItemEditText);
    }

    private void closeAddListItemMenu() {
        getViewDataBinding().addItemEditText.clearFocus();
        UIUtil.hideKeyboard(getActivity());
    }

    private void updateList(ShoppingList shoppingList) {
        getViewModel().listName.set(shoppingList.name);
        getViewModel().shoppingItems.setValue(shoppingList.shoppingItems);
        getViewModel().currentShoppingList.set(shoppingList);
    }

    private void updateItemList(boolean isBought, int position) {
        List<ShoppingItem> currentShoppingItems = getViewModel().shoppingItems.getValue();
        currentShoppingItems.get(position).isBought = isBought;
        getViewModel().shoppingItems.setValue(currentShoppingItems);
    }

    private void initSwipe(RecyclerView recyclerView) {
        if(!getViewModel().isArchived.get()){
            SwipeItemController swipeItemController = new SwipeItemController(getActivity()) {
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    if (direction == ItemTouchHelper.LEFT) {
                        List<ShoppingItem> currentShoppingItems =
                                getViewModel().shoppingItems.getValue();
                        currentShoppingItems.remove(viewHolder.getAdapterPosition());
                        getViewModel().shoppingItems.postValue(currentShoppingItems);
                    }
                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeItemController);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }

    public void archivedDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder
                .setTitle(R.string.app_name2)
                .setMessage(R.string.dialog_isarchived_text)
                .setCancelable(true)
                .setPositiveButton(R.string.dialog_isarchived_positive_button,
                        (dialogInterface, i) -> dialogInterface.dismiss())
                .create().show();
    }
}
