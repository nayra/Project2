package com.nayra.maraiina.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.nayra.maraiina.views.ChooseCountryActivity;

import java.util.Locale;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class LanguageUtil {
    public static void changeLanguage(final int language_index, final Context context) {
        int current_Lang_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
        if (current_Lang_index != language_index) {
            String lang;
            if (language_index == 0) {
                lang = "en";
            } else {
                lang = "ar";
            }
            Locale myLocale = new Locale(lang);
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            conf.setLayoutDirection(myLocale);
            res.updateConfiguration(conf, dm);

            SharedPrefsUtil.setInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX, language_index);

            Intent refresh = new Intent(context, ChooseCountryActivity.class);
            context.startActivity(refresh);
            ((AppCompatActivity) context).finish();
        }
    }

    public static void setLocaleLanguage(final int language_index, final Context context) {
        String lang;
        if (language_index == 0) {
            lang = "en";
        } else {
            lang = "ar";
        }
        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        conf.setLayoutDirection(myLocale);
        res.updateConfiguration(conf, dm);
    }
}
