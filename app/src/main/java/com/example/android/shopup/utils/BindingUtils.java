package com.example.android.shopup.utils;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BindingUtils {

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view,
            BottomNavigationView.OnNavigationItemSelectedListener listener
    ) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("setSelection")
    public static void setOnImageButtonSelected(
            Button view,
            boolean isSelected
    ) {
        view.setSelected(isSelected);
    }

    @BindingAdapter("setSelection")
    public static void setOnTextViewSelected(
            TextView view,
            boolean isSelected
    ) {
        view.setSelected(isSelected);
    }

    @BindingAdapter("setOnDrawableBackground")
    public static void setOnDrawableBackground(
            ImageButton view, int drawableId
    ) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), drawableId));
    }

    @BindingAdapter("textViewBackground")
    public static void textViewBackground(TextView view, int drawableId) {
        view.setBackgroundResource(drawableId);
    }

    @BindingAdapter("android:buttonViewBackground")
    public static void buttonViewBackground(Button view, int drawableId) {
        view.setBackgroundResource(drawableId);
    }

    @BindingAdapter("setOnDrawableImageBackground")
    public static void setOnDrawableImageBackground(
            ImageView view, int drawableId
    ) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), drawableId));
    }

    @BindingAdapter("setOnCurrentPage")
    public static void setOnCurrentPage(
            ViewPager view, int currentPage
    ) {
        view.setCurrentItem(currentPage);
    }

    @BindingAdapter(value = {"selection", "selectionAttrChanged"}, requireAll = false)
    public static void setAdapter(
            AppCompatSpinner view,
            String newSelection,
            final InverseBindingListener bindingListener
    ) {
        view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (bindingListener != null) {
                    bindingListener.onChange();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });

        if (newSelection != null) {
            view.setSelection(getIndexOfItem(view, newSelection));
        }
    }

    private static int getIndexOfItem(AppCompatSpinner spinner, String item) {
        Adapter a = spinner.getAdapter();

        for (int i = 0; i < a.getCount(); i++) {
            if ((a.getItem(i)).equals(item)) {
                return i;
            }
        }
        return 0;
    }

    @InverseBindingAdapter(attribute = "selection", event = "selectionAttrChanged")
    public static String getSelectedValue(AppCompatSpinner view) {
        return (String) view.getSelectedItem();
    }

    @BindingAdapter("setChangeSelection")
    public static void setChangeSelection(SwitchCompat switchPlus, boolean isChecked) {
        switchPlus.setChecked(isChecked);
    }

    @BindingAdapter("setToolbarVisibility")
    public static void setToolbarVisibility(Toolbar toolbar, int visibility) {
        toolbar.setVisibility(visibility);
    }

    @BindingAdapter("setTextSize")
    public static void setTextSize(TextView textView, int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    @BindingAdapter("setBottomCommentBarAnimation")
    public static void setBottomCommentBarAnimation(ConstraintLayout constraintLayout, boolean startAnimation) {
        if(startAnimation){
            constraintLayout.animate().setDuration(300)
                    .translationY(constraintLayout.getHeight());
        } else {
            constraintLayout.animate().setDuration(300)
                    .translationY(0);
        }
    }

    @BindingAdapter("setRepliesCommentAnimation")
    public static void setRepliesCommentAnimation(EditText editText, boolean startAnimation) {
        if(startAnimation){
            editText.animate().setDuration(300)
                    .translationY(editText.getHeight() * 2);
        } else {
            editText.animate().setDuration(300)
                    .translationY(0);
        }
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

    @BindingAdapter("setShowMapButtonAnimation")
    public static void setShowMapButtonAnimation(CardView constraintLayout, boolean startAnimation) {
        constraintLayout.animate().setDuration(300)
                .translationY(startAnimation ? constraintLayout.getHeight() + 100 : 0);
    }

    @BindingAdapter("setFabAnimation")
    public static void setFabAnimation(FloatingActionButton floatingActionButton, boolean startAnimation) {
        if(startAnimation){
            floatingActionButton.animate().translationX(floatingActionButton.getWidth()*2).start();
        } else {
            floatingActionButton.animate().translationY(0).start();
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
