package com.example.android.shopup.utils;

import android.os.Bundle;

public interface ViewModelNavigator {

    void attachNavigator(Navigator navigator);

    void detachNavigator();

    void onActivityResult(Navigator navigator, int requestCode, int resultCode, Bundle data);

    void onDestroy();

    void onCreate();

    void saveInstanceState(Bundle bundle);

    void restoreInstanceState(Bundle bundle);

}
