package com.example.android.shopup.utils;

import android.content.Context;

public interface BaseAdapterI<T> {

    BaseItemView<T> createViewItem(Context context, int viewType);
}
