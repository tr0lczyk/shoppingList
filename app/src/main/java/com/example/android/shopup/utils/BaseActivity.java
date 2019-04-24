package com.example.android.shopup.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;
import java.io.Serializable;

public abstract class BaseActivity extends AppCompatActivity implements
        Navigator,
        ViewModelIntegration {

    private ViewDataBinding viewDataBinding;
    public BaseAndroidViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeViews();
        if (contentId() != 0) {
            viewDataBinding = DataBindingUtil.setContentView(this, contentId());
        } else {

        }

        if (viewModel == null) {
            viewModel = ViewModelProviders.of(this).get(createViewModel());
        }

        afterViews(savedInstanceState);
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

    public void beforeViews() {

    }

    public void afterViews(Bundle savedInstanceState) {

    }

    public abstract int contentId();

    public abstract Class<? extends BaseAndroidViewModel> createViewModel();

    @Override
    public void moveForward(Options options, Object... data) {

    }

    @Override
    public void goBack() {

    }

    @Override
    public void showProgress(@NonNull String title) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(@NonNull String title) {

    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass, Serializable serializable) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtra(Navigator.DEFAULT_ARG_KEY, serializable);
        startActivity(intent);
    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass, String key, Serializable serializable) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtra(key, serializable);
        startActivity(intent);
    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass, Bundle bundle) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, Serializable serializable, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtra(Navigator.DEFAULT_ARG_KEY, serializable);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, String key, Serializable serializable, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtra(key, serializable);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getViewModel();//            getViewModel().onActivityResult(requestCode, resultCode, data == null ? null : data.getExtras());
    }

    @Override
    public void startWebSiteFromUrl(String Url) {

    }

    @Override
    public void showInfo(@NonNull String title, @NonNull String content, @NonNull String positiveButtonText, DialogInterface.OnClickListener positiveClick) {
        boolean showed = DialogFactory.safeShowDialog(this, DialogFactory.createPositiveDialog(this, title, content, positiveButtonText, positiveClick));
        if (!showed) {
//            dataManager.showToast(String.format(Locale.getDefault(), "%s: %s", title, content), Toast.LENGTH_LONG);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (viewModel != null) {
            viewModel.attachNavigator(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (viewModel != null) {
            viewModel.detachNavigator();
        }
    }


    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
