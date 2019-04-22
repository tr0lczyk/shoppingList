package com.example.android.shopup.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import androidx.annotation.Nullable;

import com.example.android.shopup.R;

class DialogFactory {

    public static Dialog createPositiveDialog(Activity context,
                                              String title,
                                              String message,
                                              String buttonText,
                                              DialogInterface.OnClickListener positiveAction) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.DialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonText, positiveAction);
        return alertDialog.create();
    }

    public static boolean safeShowDialog(@Nullable Activity activity, @Nullable Dialog dialog) {
        if (activity == null || dialog == null) {
            return false;
        }

        if (!activity.isFinishing()) {
            dialog.show();
        }

        return true;
    }

}
