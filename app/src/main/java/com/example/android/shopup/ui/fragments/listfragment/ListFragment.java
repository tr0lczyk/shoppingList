package com.example.android.shopup.ui.fragments.listfragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.shopup.R;
import com.example.android.shopup.databinding.FragmentListBinding;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.BaseFragment;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.List;

import static com.example.android.shopup.ui.activities.MainActivity.LIST_NAME;

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
        getViewModel().getAllShoppingLists().observe(getActivity(), new Observer<List<ShoppingList>>() {
            @Override
            public void onChanged(List<ShoppingList> shoppingLists) {
                if(shoppingLists != null && !shoppingLists.isEmpty()){
                    getViewModel().listName.set(shoppingLists.get(0).name);
                }
            }
        });
        setupMainListsRecycler();
        getViewModel().setupListRecyclerAdapter();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT){
                    getViewModel().setupListRecyclerAdapter2();
                } else {
                    getViewModel().setupListRecyclerAdapter();
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

}
