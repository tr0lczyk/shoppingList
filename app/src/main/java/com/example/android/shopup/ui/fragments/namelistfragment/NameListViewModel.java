package com.example.android.shopup.ui.fragments.namelistfragment;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.example.android.shopup.R;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.Navigator;

public class NameListViewModel extends BaseAndroidViewModel {

    public ObservableField<Integer> createListButtonTextColor;
    public ObservableField<Integer> createListButtonBackground;
    public ObservableField<String> createListName;
    public ObservableField<Boolean> createListNameFilled;

    public NameListViewModel(@NonNull Application application) {
        super(application);
        createListButtonTextColor = new ObservableField<>(ContextCompat.getColor(getApplication().getApplicationContext(), R.color.white));
        createListButtonBackground = new ObservableField<>(R.drawable.namelist_button);
        createListName = new ObservableField<>();
        createListNameFilled = new ObservableField<>(false);
        createListName.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(!createListName.get().isEmpty() && !createListNameFilled.get()){
                    nameListEditTextFilled();
                    createListNameFilled.set(true);
                } else if(createListName.get().isEmpty() && createListNameFilled.get()) {
                    nameListEditTextEmpty();
                    createListNameFilled.set(false);
                }
            }
        });
    }

    private void nameListEditTextFilled() {
        createListButtonTextColor.set(ContextCompat.getColor(getApplication().getApplicationContext(), R.color.mainCoolColor));
        createListButtonBackground.set(R.drawable.namelist_filled_button);
    }

    private void nameListEditTextEmpty() {
        createListButtonTextColor.set(ContextCompat.getColor(getApplication().getApplicationContext(), R.color.white));
        createListButtonBackground.set(R.drawable.namelist_button);
    }

    public void onBackPressed(View view) {
        getNavigator().moveForward(Navigator.Options.ON_BACK_PRESSED);
    }

    public void openListFragment(View view){
        if(createListNameFilled.get()){
            getNavigator().moveForward(Navigator.Options.OPEN_LIST_FRAGMENT,createListName.get());
        } else {
            Toast.makeText(getApplication().getApplicationContext(),
                    "Please name your list :)", Toast.LENGTH_SHORT).show();
        }

    }
}