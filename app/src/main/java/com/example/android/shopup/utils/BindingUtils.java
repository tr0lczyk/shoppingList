package com.example.android.shopup.utils;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BindingUtils {

    @BindingAdapter("textViewBackground")
    public static void textViewBackground(TextView view, int drawableId) {
        view.setBackgroundResource(drawableId);
    }

    @BindingAdapter("textViewTextColor")
    public static void textViewTextColor(TextView view, int color) {
        view.setTextColor(ContextCompat.getColor(view.getContext(),color));
    }

    @BindingAdapter("android:buttonViewBackground")
    public static void buttonViewBackground(Button view, int drawableId) {
        view.setBackgroundResource(drawableId);
    }

    @BindingAdapter("setTextSize")
    public static void setTextSize(TextView textView, int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    @BindingAdapter("android:setDrawableBackground")
    public static void setColorBackground(ConstraintLayout constraintLayout, int drawable) {
        constraintLayout.setBackground(ContextCompat.getDrawable(constraintLayout.getContext(),drawable));
    }

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, int width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("setFabAnimation")
    public static void setFabAnimation(FloatingActionButton floatingActionButton, boolean startAnimation) {
        if(startAnimation){
            floatingActionButton.animate().translationX(floatingActionButton.getWidth()*2).start();
        } else {
            floatingActionButton.animate().translationX(0).start();
        }
    }

    @BindingAdapter("setContainerAnimation")
    public static void setContainerAnimation(ConstraintLayout constraintLayout, boolean startAnimation) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
        int addItemHeight = layoutParams.height;
        if(startAnimation){
            constraintLayout.animate().translationY(-addItemHeight).start();
        } else {
            constraintLayout.animate().translationY(0).start();
        }
    }
}
