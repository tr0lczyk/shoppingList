package com.example.android.shopup.utils;

public interface ViewModelIntegration {

    Class<? extends BaseAndroidViewModel> createViewModel();

    BaseAndroidViewModel getViewModel();
}
