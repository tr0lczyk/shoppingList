package com.example.android.shopup.ui.fragments.namelistfragment;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.example.android.shopup.R;
import com.example.android.shopup.database.repository.ShoppingListsRepository;
import com.example.android.shopup.models.ShoppingList;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.Navigator;
import com.example.android.shopup.utils.Utils;

public class NameListViewModel extends BaseAndroidViewModel {

    public ObservableField<Integer> createListButtonTextColor;
    public ObservableField<Integer> createListButtonBackground;
    public ObservableField<String> createListName;
    public ObservableField<Boolean> createListNameFilled;
    private ShoppingListsRepository shoppingListsRepository;

    public NameListViewModel(@NonNull Application application) {
        super(application);
        createListButtonTextColor = new ObservableField<>(R.color.white);
        createListButtonBackground = new ObservableField<>(R.drawable.namelist_button);
        createListName = new ObservableField<>();
        createListNameFilled = new ObservableField<>(false);
        shoppingListsRepository = new ShoppingListsRepository(application);
        observeListName();
    }

    private void nameListEditTextFilled() {
        createListButtonTextColor.set(R.color.mainCoolColor);
        createListButtonBackground.set(R.drawable.namelist_filled_button);
        createListNameFilled.set(true);
    }

    private void nameListEditTextEmpty() {
        createListButtonTextColor.set(R.color.white);
        createListButtonBackground.set(R.drawable.namelist_button);
        createListNameFilled.set(false);
    }

    public void onBackPressed(View view) {
        getNavigator().moveForward(Navigator.Options.ON_BACK_PRESSED);
    }

    public void openListFragment(View view){
        if(createListNameFilled.get()){
            shoppingListsRepository.insert(setupNewShoppingList(createListName.get()));
            getNavigator().moveForward(Navigator.Options.HIDE_KEYBOARD);
            getNavigator().moveForward(Navigator.Options.CHANGE_NAME_LIST_ANIMATION);
            getNavigator().moveForward(Navigator.Options.POP_BACKSTACK);
            getNavigator().moveForward(Navigator.Options.OPEN_LIST_FRAGMENT);
        } else {
            Toast.makeText(getApplication().getApplicationContext(),
                    R.string.nameListWarning, Toast.LENGTH_SHORT).show();
        }
    }

    public ShoppingList setupNewShoppingList(String name){
        ShoppingList currentShoppingList = new ShoppingList(name);
        return currentShoppingList;
    }

    public void observeListName(){
        createListName.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(!Utils.isEmpty(createListName.get()) && !createListNameFilled.get()){
                    nameListEditTextFilled();
                } else if(Utils.isEmpty(createListName.get()) && createListNameFilled.get()) {
                    nameListEditTextEmpty();
                }
            }
        });
    }
}
