package com.example.android.shopup.ui.fragments.listfragment;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.example.android.shopup.ui.fragments.listfragment.listitemrecycler.ListItemRecyclerViewModel;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.Navigator;

public class ListViewModel extends BaseAndroidViewModel {

    public ObservableField<Integer> emptyListTextVisibility;
    public ObservableField<String> listName;
    public ObservableField<Boolean> fabAnimationStart;
    public ObservableField<Boolean> menuAddAnimationStart;
    public ListItemRecyclerViewModel listItemRecyclerViewModel;

    public ListViewModel(@NonNull Application application) {
        super(application);
        emptyListTextVisibility = new ObservableField<>(View.GONE);
        listItemRecyclerViewModel = new ListItemRecyclerViewModel();
        listName = new ObservableField<>();
        fabAnimationStart = new ObservableField<>();
        menuAddAnimationStart = new ObservableField<>();
    }

    public void setupListRecyclerAdapter(){
        listItemRecyclerViewModel.setListItemsToAdapter();
    }
    public void setupListRecyclerAdapter2(){
        listItemRecyclerViewModel.setListItemsToAdapter2();
    }

    public void addNewItem(View view){

    }

    public void openAddItemMenu(View view){
        fabAnimationStart.set(true);
        menuAddAnimationStart.set(true);
        getNavigator().moveForward(Navigator.Options.OPEN_ADD_LIST_MENU);
    }

    public void closeAddItemMenu(View view){
        fabAnimationStart.set(false);
        menuAddAnimationStart.set(false);
        getNavigator().moveForward(Navigator.Options.CLOSE_ADD_LIST_MENU);
    }
}
