package com.example.android.shopup.ui.fragments.mainlistsfragment;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.example.android.shopup.ui.fragments.mainlistsfragment.listsrecycler.ListsRecyclerViewModel;
import com.example.android.shopup.utils.BaseAndroidViewModel;
import com.example.android.shopup.utils.Navigator;

public class MainListsViewModel extends BaseAndroidViewModel {

    public ListsRecyclerViewModel listsRecyclerViewModel;
    public ObservableField<Integer> emptyMainListTextVisibility;

    public MainListsViewModel(@NonNull Application application) {
        super(application);
        listsRecyclerViewModel = new ListsRecyclerViewModel();
        emptyMainListTextVisibility = new ObservableField<>(View.GONE);
    }

    public void setupMainListsRecyclerAdapter(){
        listsRecyclerViewModel.setListsToRecycler();
    }

    public void addNewList(View view){
        getNavigator().moveForward(Navigator.Options.OPEN_NAME_LIST_FRAGMENT);
    }
}
