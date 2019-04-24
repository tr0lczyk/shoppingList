package com.example.android.shopup.ui.fragments.archivefragment;

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

public class ArchiveViewModel extends BaseAndroidViewModel {

    public ListsRecyclerViewModel listsRecyclerViewModel;
    public ObservableField<Integer> emptyArchivedListTextVisibility;
    public ShoppingListsRepository shoppingListsRepository;
    public LiveData<List<ShoppingList>> allArchivedShoppingLists;

    public ArchiveViewModel(@NonNull Application application) {
        super(application);
        listsRecyclerViewModel = new ListsRecyclerViewModel();
        emptyArchivedListTextVisibility = new ObservableField<>(View.GONE);
        shoppingListsRepository = new ShoppingListsRepository(application);
        allArchivedShoppingLists = shoppingListsRepository.getAllArchivedLists();
    }

    public void setupArchivedListsRecyclerAdapter(){
        if(allArchivedShoppingLists.getValue().isEmpty()){
            emptyArchivedListTextVisibility.set(View.VISIBLE);
            listsRecyclerViewModel.setListsToRecycler(allArchivedShoppingLists.getValue());
        } else {
            emptyArchivedListTextVisibility.set(View.GONE);
            listsRecyclerViewModel.setListsToRecycler(allArchivedShoppingLists.getValue());
        }
    }

    public LiveData<List<ShoppingList>> getArchivedShoppingLists(){
        return allArchivedShoppingLists;
    }

    public void openMainListsFragment(View view){
        getNavigator().moveForward(Navigator.Options.OPEN_MAIN_LISTS_FRAGMENT);
    }
}
