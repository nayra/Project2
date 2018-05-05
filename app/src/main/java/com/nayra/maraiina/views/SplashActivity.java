package com.nayra.maraiina.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

public class SplashActivity extends AppCompatActivity {

    private long SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPrefsUtil.init(SplashActivity.this);
        Utils.displayNextActivityFinish(SplashActivity.this, ChooseCountryActivity.class);

    }
}
