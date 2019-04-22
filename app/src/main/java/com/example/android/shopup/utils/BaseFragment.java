package com.example.android.shopup.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import java.io.Serializable;

public class BaseFragment extends Fragment implements Navigator, ViewModelIntegration {

    private ViewDataBinding viewDataBinding;
    public BaseAndroidViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        beforeViews();
        viewDataBinding = DataBindingUtil.inflate(inflater, getContentId(), container, false);
        viewModel = ViewModelProviders.of(this).get(createViewModel());

        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        afterViews(savedInstanceState);
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

    public void beforeViews() {

    }

    public void afterViews(Bundle savedInstanceState) {

    }

    public int getContentId() {
        throw new RuntimeException("You need to override getContentId method.");
    }

    @Override
    public void moveForward(Options options, Object... data) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            baseActivity.moveForward(options, data);
        }

    }

    private BaseActivity getBaseActivity() {
        return getActivity() instanceof BaseActivity ? (BaseActivity) super.getActivity() : null;
    }

    @Override
    public void finish() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }

    }

    @Override
    public void goBack() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }

    }

    @Override
    public void showProgress(@NonNull String title) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(@NonNull String title) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((Navigator) activity).showError(title);
        }

    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((Navigator) activity).startActivity(activityClass);
        }
    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass, Serializable serializable) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((Navigator) activity).startActivity(activityClass, serializable);
        }
    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass, String key, Serializable serializable) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((Navigator) activity).startActivity(activityClass, key, serializable);
        }
    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass, Bundle bundle) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((Navigator) activity).startActivity(activityClass, bundle);
        }
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, int requestCode) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, Serializable serializable, int requestCode) {
        Intent intent = new Intent(getActivity(), activityClass);
        intent.putExtra(Navigator.DEFAULT_ARG_KEY, serializable);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, String key, Serializable serializable, int requestCode) {
        Intent intent = new Intent(getActivity(), activityClass);
        intent.putExtra(key, serializable);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), activityClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startWebSiteFromUrl(String urlAddress) {
    }

    @Override
    public void showInfo(@NonNull String title,
                         @NonNull String content,
                         @NonNull String positiveButtonText,
                         DialogInterface.OnClickListener positiveClick) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((Navigator) activity).showInfo(title, content, positiveButtonText, positiveClick);
        }

    }

    @Override
    public void showToast(String message) {
        FragmentActivity fragmentActivity = getActivity();
        if (fragmentActivity != null) {
            ((Navigator) fragmentActivity).showToast(message);
        }

    }

    @Override
    public Class<? extends BaseAndroidViewModel> createViewModel() {
        return null;
    }

    @Override
    public BaseAndroidViewModel getViewModel() {
        return null;
    }


}
