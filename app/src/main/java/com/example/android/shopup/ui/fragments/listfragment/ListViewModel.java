package com.example.android.shopup.ui.fragments.listfragment;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.shopup.R;
import com.example.android.shopup.database.repository.ShoppingListsRepository;
import com.example.android.shopup.models.ShoppingItem;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.ui.fragments.listfragment.listitemrecycler.ListItemRecyclerViewModel;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.Navigator;
import com.example.android.shopup.utils.Utils;

import java.util.List;

import okhttp3.internal.Util;

public class ListViewModel extends BaseAndroidViewModel {

    private static final String TAG = "listViewModel";
    public ObservableField<Integer> emptyListTextVisibility;
    public ObservableField<String> listName;
    public ObservableField<String> newItemName;
    public ObservableField<Boolean> fabAnimationStart;
    public ObservableField<Boolean> menuAddAnimationStart;
    public ObservableField<Boolean> isArchived;
    public ObservableField<Integer> shoppingItemId;
    public ObservableField<Integer> infoMessageVisibility;
    public ObservableField<ShoppingList> currentShoppingList;
    public ListItemRecyclerViewModel listItemRecyclerViewModel;
    private ShoppingListsRepository shoppingListsRepository;
    private LiveData<ShoppingList> lastShoppingList;
    private LiveData<ShoppingList> shoppingList;
    public MutableLiveData<List<ShoppingItem>> shoppingItems;

    public ListViewModel(@NonNull Application application) {
        super(application);
        emptyListTextVisibility = new ObservableField<>(View.GONE);
        listItemRecyclerViewModel = new ListItemRecyclerViewModel();
        fabAnimationStart = new ObservableField<>();
        menuAddAnimationStart = new ObservableField<>();
        newItemName = new ObservableField<>();
        shoppingListsRepository = new ShoppingListsRepository(application);
        lastShoppingList = shoppingListsRepository.getLastShoppingList();
        currentShoppingList = new ObservableField<>();
        shoppingItemId = new ObservableField<>();
        listName = new ObservableField<>();
        infoMessageVisibility = new ObservableField<>(View.GONE);
        shoppingItems = new MutableLiveData<>();
        shoppingItemId.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                shoppingList = shoppingListsRepository.getOneObject(shoppingItemId.get());
            }
        });
        isArchived = new ObservableField<>();
    }

    public void setupListRecyclerAdapter() {
        if (shoppingItems.getValue().isEmpty()) {
            emptyListTextVisibility.set(View.VISIBLE);
            infoMessageVisibility.set(View.GONE);
            listItemRecyclerViewModel.setListItemsToAdapter(shoppingItems.getValue(),
                    isArchived.get());
        } else {
            emptyListTextVisibility.set(View.GONE);
            if(!isArchived.get()){
                infoMessageVisibility.set(View.VISIBLE);
            } else {
                infoMessageVisibility.set(View.GONE);
            }
            listItemRecyclerViewModel.setListItemsToAdapter(shoppingItems.getValue(),
                    isArchived.get());
        }
    }

    public void addNewItem(View view) {
        if(!Utils.isEmpty(newItemName.get())){
            ShoppingItem newShoppingItem = new ShoppingItem(newItemName.get());
            List<ShoppingItem> shoppingItemsList = shoppingItems.getValue();
            shoppingItemsList.add(newShoppingItem);
            shoppingItems.setValue(shoppingItemsList);
            newItemName.set("");
        } else {
            Toast.makeText(getApplication().getApplicationContext(), R.string.nameless_item,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public LiveData<ShoppingList> getLastShoppingList() {
        return lastShoppingList;
    }

    public LiveData<ShoppingList> getShoppingListWithId() {
        return shoppingList;
    }

    public void openAddItemMenu(View view) {
        if (!isArchived.get()) {
            fabAnimationStart.set(true);
            menuAddAnimationStart.set(true);
            getNavigator().moveForward(Navigator.Options.OPEN_ADD_LIST_MENU);
        } else {
            fabAnimationStart.set(false);
            menuAddAnimationStart.set(false);
            getNavigator().moveForward(Navigator.Options.SHOW_ISARCHIVED_DIALOG);
        }
    }

    public void closeAddItemMenu(View view) {
        fabAnimationStart.set(false);
        menuAddAnimationStart.set(false);
        getNavigator().moveForward(Navigator.Options.CLOSE_ADD_LIST_MENU);
    }

    public void updateList(ShoppingList shoppingList) {
        shoppingListsRepository.update(shoppingList);
    }
}
