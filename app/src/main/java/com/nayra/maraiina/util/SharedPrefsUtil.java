package com.nayra.maraiina.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class SharedPrefsUtil {

    public static final String SELECTED_CITY_NAME = "SELECTED_CITY_NAME";
    public static final String SELECTED_AREA_NAME = "SELECTED_AREA_NAME";
    public static final int ARABIC = 1;
    public static final int ENGLISH = 0;

    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    private static final String FILE_NAME = "User_Prefs";

    public static final String SELECTED_COUNTRY_ID = "SELECTED_COUNTRY";
    public static final String SELECTED_COUNTRY_INDEX = "SELECTED_COUNTRY_INDEX";
    public static final String SELECTED_LANGUAGE_INDEX = "SELECTED_LANGUAGE";
    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";

    public static void init(final Context context) {
        prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }


    public static void setString(final String key, final String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(final String key) {
        return prefs.getString(key, "");
    }

    public static void setInteger(final String key, final int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInteger(final String key) {
        return prefs.getInt(key, 0);
    }


    public static void setBoolean(final String key, final boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(final String key) {
        return prefs.getBoolean(key, false);
    }

    public static SharedPreferences getPrefs() {
        return prefs;
    }
}
