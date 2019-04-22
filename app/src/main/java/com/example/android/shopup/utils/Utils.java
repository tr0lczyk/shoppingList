package com.example.android.shopup.utils;

import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Map;

public class Utils {

    public static String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(@Nullable final Collection< ? > c ) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(@Nullable final Map< ?, ? > m ) {
        return m == null || m.isEmpty();
    }

}
