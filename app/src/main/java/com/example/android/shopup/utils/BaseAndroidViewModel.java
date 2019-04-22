package com.example.android.shopup.utils;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class BaseAndroidViewModel extends AndroidViewModel implements ViewModelNavigator {

    private Navigator navigator;

    public BaseAndroidViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void attachNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void detachNavigator() {

    }

    @Override
    public void onActivityResult(Navigator navigator, int requestCode, int resultCode, Bundle data) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void saveInstanceState(Bundle bundle) {

    }

    @Override
    public void restoreInstanceState(Bundle bundle) {

    }

    public boolean isNavigatorAttached() {
        return navigator != null;
    }

    public Navigator getNavigator() {
        checkNavigatorAttached();
        return navigator;
    }

    public void checkNavigatorAttached() {
        if (!isNavigatorAttached()) throw new MvvmViewNotAttachedException();
    }

    public static class MvvmViewNotAttachedException extends RuntimeException {
        public MvvmViewNotAttachedException() {
            super("Please call attachNavigator(Context) before requesting data to the ViewModel");
        }
    }
}
