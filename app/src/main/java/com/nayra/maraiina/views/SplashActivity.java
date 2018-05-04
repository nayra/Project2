package com.nayra.maraiina.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nayra.maraiina.R;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPrefsUtil.init(this);
        Utils.displayNextActivityFinish(this, ChooseCountryActivity.class);

    }
}
