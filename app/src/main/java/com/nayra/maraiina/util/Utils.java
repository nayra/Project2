package com.nayra.maraiina.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nayrael-sayed on 2/13/18.
 */

public class Utils {
    public static void setFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public static void displayNextActivityFinish(final AppCompatActivity currentActivity,
                                                 final Class<?> nextActivityClass) {

        final Intent i = new Intent(currentActivity, nextActivityClass);

        currentActivity.startActivity(i);
        currentActivity.overridePendingTransition(0, 0);
        currentActivity.finish();

    }

    public static void displayNextActivity(final AppCompatActivity currentActivity,
                                           final Class<?> nextActivityClass) {

        final Intent i = new Intent(currentActivity, nextActivityClass);

        currentActivity.startActivity(i);
        currentActivity.overridePendingTransition(0, 0);
    }


    public static void startIntent(final AppCompatActivity currentActivity, Intent intent) {
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(0, 0);
    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


}
