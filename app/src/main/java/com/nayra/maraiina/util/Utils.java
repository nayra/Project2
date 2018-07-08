package com.nayra.maraiina.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import com.nayra.maraiina.MyApplication;
import com.nayra.maraiina.custom_views.MyErrorEditText;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.views.NoInternetConnectionActivity;
/*
 * Created by nayrael-sayed on 2/13/18.
 */

public class Utils {
    private static Activity currentActivity;
    private static int menu_item_index = 1;
    private static int categoryId;
    private static int subCategoryId;

    private static String type_name;
    private static String category_img;

    public static int getMenu_item_index() {
        return menu_item_index;
    }

    public static void setMenu_item_index(int menu_item_index) {
        Utils.menu_item_index = menu_item_index;
    }

    public static void setCallerClass(Activity callerClass) {
        Utils.currentActivity = callerClass;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

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

    public static void displayNoInternetConnectionActivity() {
        final Intent i = new Intent(currentActivity, NoInternetConnectionActivity.class);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    /*public static String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }*/


    public static void setTypeFace(final MyTextView myTextView, final int type) {
        final Typeface typeface = MyApplication.getmInstance().getTypeFace(type);
        myTextView.setTypeface(typeface);
    }

    public static void setTypeFace(final RadioButton myRadioButton, final int type) {
        final Typeface typeface = MyApplication.getmInstance().getTypeFace(type);
        myRadioButton.setTypeface(typeface);
    }

    public static void setTypeFace(final TextInputLayout myRadioButton, final int type) {
        final Typeface typeface = MyApplication.getmInstance().getTypeFace(type);
        myRadioButton.setTypeface(typeface);
    }

    public static void setTypeFace(final TextInputEditText myRadioButton, final int type) {
        final Typeface typeface = MyApplication.getmInstance().getTypeFace(type);
        myRadioButton.setTypeface(typeface);
    }

    public static void setTypeFace(final MyErrorEditText myRadioButton, final int type) {
        final Typeface typeface = MyApplication.getmInstance().getTypeFace(type);
        myRadioButton.setTypeface(typeface);
    }

    public static void setTypeFace(final AppCompatEditText mEditTexxt, final int type) {
        final Typeface typeface = MyApplication.getmInstance().getTypeFace(type);
        mEditTexxt.setTypeface(typeface);
    }

    public static int getCategoryId() {
        return categoryId;
    }

    public static int getSubCategoryId() {
        return subCategoryId;
    }

    public static void setCategoryId(int categoryId) {
        Utils.categoryId = categoryId;
    }

    public static void setSubCategoryId(int subCategoryId) {
        Utils.subCategoryId = subCategoryId;
    }

    public static String getType_name() {
        return type_name;
    }

    public static void setType_name(String type_name) {
        Utils.type_name = type_name;
    }

    public static String getCategory_img() {
        return category_img;
    }

    public static void setCategory_img(String category_img) {
        Utils.category_img = category_img;
    }
}
