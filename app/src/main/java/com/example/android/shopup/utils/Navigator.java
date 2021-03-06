package com.example.android.shopup.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.io.Serializable;

public interface Navigator {

    String DEFAULT_ARG_KEY = "com.example.android.shopup.utils.shopUpNavigator";

    void moveForward(Options options, Object... data);

    void finish();

    void goBack();

    void showProgress(@NonNull String title);

    void hideProgress();

    void showError(@NonNull String title);

    void startActivity(Class<? extends Activity> activityClass);

    void startActivity(Class<? extends Activity> activityClass, Serializable serializable);

    void startActivity(Class<? extends Activity> activityClass, String key, Serializable serializable);

    void startActivity(Class<? extends Activity> activityClass, Bundle bundle);

    void startActivityForResult(Class<? extends Activity> activityClass, int requestCode);

    void startActivityForResult(Class<? extends Activity> activityClass, Serializable serializable, int requestCode);

    void startActivityForResult(Class<? extends Activity> activityClass, String key, Serializable serializable, int requestCode);

    void startActivityForResult(Class<? extends Activity> activityClass, Bundle bundle, int requestCode);

    void startWebSiteFromUrl(String Url);

    void showInfo(@NonNull String title, @NonNull String content,
                  @NonNull String positiveButtonText,
                  DialogInterface.OnClickListener positiveClick);

    void showToast(String message);



    enum Options {
        OPEN_MAIN_LISTS_FRAGMENT, OPEN_NAME_LIST_FRAGMENT, ON_BACK_PRESSED, OPEN_LIST_FRAGMENT, OPEN_ADD_LIST_MENU, CLOSE_ADD_LIST_MENU, HIDE_ADD_CONTAINER, SHOW_ADD_CONTAINER, POP_BACKSTACK, HIDE_KEYBOARD, SAVE_CURRENT_LIST, OPEN_ARCHIVE_FRAGMENT, MAIN_COLOR_STATUS_BAR, ACCENT_COLOR_STATUS_BAR, OPEN_FIRST_MAIN_LISTS_FRAGMENT, UPDATE_ITEM_CHECKBOX, CHANGE_NAME_LIST_ANIMATION, SHOW_ISARCHIVED_DIALOG,

    }
}
