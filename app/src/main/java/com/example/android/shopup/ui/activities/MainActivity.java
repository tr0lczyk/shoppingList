package com.example.android.shopup.ui.activities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.android.shopup.R;
import com.example.android.shopup.databinding.ActivityMainBinding;
import com.example.android.shopup.ui.fragments.listfragment.ListFragment;
import com.example.android.shopup.ui.fragments.mainlistsfragment.MainListsFragment;
import com.example.android.shopup.ui.fragments.namelistfragment.NameListFragment;
import com.example.android.shopup.utils.BaseActivity;
import com.example.android.shopup.utils.BaseAndroidViewModel;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class MainActivity extends BaseActivity {

    public static final int MAIN_LISTS_ID = 1;
    private static final int NAME_LIST_ID = 2;
    public static final int LIST_ID = 3;
    public static final String LIST_NAME = "listName";
    public static final String SHOPPING_LIST_KEY = "shoppingListId";
    private FragmentManager fragmentManager;

    @Override
    public int contentId() {
        return R.layout.activity_main;
    }

    @Override
    public Class<? extends BaseAndroidViewModel> createViewModel() {
        return MainActivityViewModel.class;
    }

    @Override
    public MainActivityViewModel getViewModel() {
        return (MainActivityViewModel) viewModel;
    }

    @Override
    public ActivityMainBinding getViewDataBinding() {
        return (ActivityMainBinding) super.getViewDataBinding();
    }

    @Override
    public void beforeViews() {
        super.beforeViews();
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void afterViews(Bundle savedInstanceState) {
        super.afterViews(savedInstanceState);
        getViewDataBinding().setViewModel(getViewModel());
        getViewModel().attachNavigator(this);
        moveForward(Options.OPEN_MAIN_LISTS_FRAGMENT);;
    }

    @Override
    public void moveForward(Options options, Object... data) {
        super.moveForward(options, data);
        switch(options){
            case OPEN_MAIN_LISTS_FRAGMENT:
                fragmentManager
                        .beginTransaction()
                        .replace(
                                R.id.mainContainer,
                                MainListsFragment.newInstance(MAIN_LISTS_ID, new Bundle())
                        )
                        .addToBackStack("mainListsFragment")
                        .commit();
                break;
            case OPEN_NAME_LIST_FRAGMENT:
                FragmentTransaction nameListTransaction = fragmentManager.beginTransaction();
                addEnterExitAnimationsRight(nameListTransaction);
                nameListTransaction
                        .replace(
                                R.id.mainContainer,
                                NameListFragment.newInstance(NAME_LIST_ID, new Bundle())
                        )
                        .addToBackStack("nameListFragment")
                        .commit();
                break;
            case OPEN_LIST_FRAGMENT:
                Bundle listBundle = new Bundle();
                if(data.length > 0 && data[0] != null){
                    listBundle.putInt(SHOPPING_LIST_KEY, (Integer) data[0]);
                }
                FragmentTransaction listTransaction = fragmentManager.beginTransaction();
                addEnterExitAnimationsRight(listTransaction);
                listTransaction
                        .replace(
                                R.id.mainContainer,
                                ListFragment.newInstance(LIST_ID, listBundle)
                        )
                        .addToBackStack("listTransaction")
                        .commit();
                break;
            case ON_BACK_PRESSED:
                onBackPressed();
                break;
            case POP_BACKSTACK:
                fragmentManager.popBackStack();
                break;
            case HIDE_KEYBOARD:
                UIUtil.hideKeyboard(this);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.mainContainer);
        if(fragment instanceof MainListsFragment){
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void addEnterExitAnimationsRight(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setCustomAnimations(R.anim.entr_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
    }

    private void addEnterExitAnimationsLeft(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.entr_from_right, R.anim.exit_to_left);
    }
}
