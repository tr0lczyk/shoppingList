package com.example.android.shopup.ui.fragments.mainlistsfragment;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;

import com.example.android.shopup.database.repository.ShoppingListsRepository;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.ui.fragments.mainlistsfragment.listsrecycler.ListsRecyclerViewModel;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.Navigator;

import java.util.List;

public class MainListsViewModel extends BaseAndroidViewModel {

    public ListsRecyclerViewModel listsRecyclerViewModel;
    public ObservableField<Integer> emptyMainListTextVisibility;
    public ObservableField<Integer> infoMessageVisibility;
    public ShoppingListsRepository shoppingListsRepository;
    public LiveData<List<ShoppingList>> allActiveShoppingLists;

    public MainListsViewModel(@NonNull Application application) {
        super(application);
        listsRecyclerViewModel = new ListsRecyclerViewModel();
        emptyMainListTextVisibility = new ObservableField<>(View.GONE);
        infoMessageVisibility = new ObservableField<>(View.GONE);
        shoppingListsRepository = new ShoppingListsRepository(application);
        allActiveShoppingLists = shoppingListsRepository.getAllActiveLists();
    }

    public void setupMainListsRecyclerAdapter(){
        if(allActiveShoppingLists.getValue().isEmpty()){
            emptyMainListTextVisibility.set(View.VISIBLE);
            infoMessageVisibility.set(View.GONE);
            listsRecyclerViewModel.setListsToRecycler(allActiveShoppingLists.getValue());
        } else {
            emptyMainListTextVisibility.set(View.GONE);
            infoMessageVisibility.set(View.VISIBLE);
            listsRecyclerViewModel.setListsToRecycler(allActiveShoppingLists.getValue());
        }
    }

    public void addNewList(View view){
        getNavigator().moveForward(Navigator.Options.OPEN_NAME_LIST_FRAGMENT);
    }

    public LiveData<List<ShoppingList>> getActiveShoppingLists(){
        return allActiveShoppingLists;
    }

    public void openArchiveFragment(View view){
        getNavigator().moveForward(Navigator.Options.OPEN_ARCHIVE_FRAGMENT);
    }
}
