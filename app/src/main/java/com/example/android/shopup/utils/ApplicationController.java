package com.example.android.shopup.utils;

import android.app.Application;
import android.content.Context;

public class ApplicationController extends Application {

    public static ApplicationController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ApplicationController get(Context context) {
        return (ApplicationController) context.getApplicationContext();
    }

    public static synchronized ApplicationController getInstance() {
        return instance;
    }

}
