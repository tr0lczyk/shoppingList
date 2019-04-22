package com.example.android.shopup.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DimenRes;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.shopup.R;

public abstract class BaseCardView extends CardView implements BaseView {

    private ViewDataBinding viewDataBinding;

    public BaseCardView(Context context) {
        super(context);
        init(context);
    }

    public BaseCardView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    public BaseCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorFullTransparent));
        beforeViews();
        viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                contentId(), this, true);

        afterViews();
    }


    protected abstract int contentId();

    public void beforeViews() {

    }

    public void afterViews() {
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

    @Override
    public View getView() {
        return this;
    }

    public void setItemMargins(@DimenRes int top, @DimenRes int bottom, @DimenRes int left, @DimenRes int right) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        layoutParams.leftMargin = (int) getContext().getResources().getDimension(left);
        layoutParams.rightMargin = (int) getContext().getResources().getDimension(right);
        layoutParams.topMargin = (int) getContext().getResources().getDimension(top);
        layoutParams.bottomMargin = (int) getContext().getResources().getDimension(bottom);
        setLayoutParams(layoutParams);
    }

}
