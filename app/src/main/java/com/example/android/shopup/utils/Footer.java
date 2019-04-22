package com.example.android.shopup.utils;

public interface Footer<T> extends BaseItemView<T> {

    void setInfoText(CharSequence text);

    void showError();

    void showProgressBar();

    void showInfo();

    void hide();
}
