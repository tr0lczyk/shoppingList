package com.example.android.shopup.ui.fragments.listfragment;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;

import com.example.android.shopup.database.repository.ShoppingListsRepository;
import com.example.android.shopup.models.ShoppingItem;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.ui.fragments.listfragment.listitemrecycler.ListItemRecyclerViewModel;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends BaseAndroidViewModel {

    private static final String TAG = "listViewModel";
    public ObservableField<Integer> emptyListTextVisibility;
    public ObservableField<String> listName;
    public ObservableField<String> newItemName;
    public ObservableField<Boolean> fabAnimationStart;
    public ObservableField<Boolean> menuAddAnimationStart;
    public ListItemRecyclerViewModel listItemRecyclerViewModel;
    private ShoppingListsRepository shoppingListsRepository;
    LiveData<List<ShoppingList>> allShoppingLists;
    List<ShoppingItem> shoppingItems;

    public ListViewModel(@NonNull Application application) {
        super(application);
        emptyListTextVisibility = new ObservableField<>(View.GONE);
        listItemRecyclerViewModel = new ListItemRecyclerViewModel();
        fabAnimationStart = new ObservableField<>();
        menuAddAnimationStart = new ObservableField<>();
        newItemName = new ObservableField<>();
        shoppingListsRepository = new ShoppingListsRepository(application);
        allShoppingLists = shoppingListsRepository.getAllObjects();
        listName = new ObservableField<>();
    }

    public void setupListRecyclerAdapter(){
        listItemRecyclerViewModel.setListItemsToAdapter(shoppingItems);
    }

    public void addNewItem(View view){
        ShoppingItem shoppingItem = new ShoppingItem(newItemName.get());
        Log.d(TAG,"name is " + shoppingItem.name);
        newItemName.set("");
    }

    public LiveData<List<ShoppingList>> getAllShoppingLists(){
        return allShoppingLists;
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
