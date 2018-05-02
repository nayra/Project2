package com.nayra.maraiina.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.nayra.maraiina.R;
import com.nayra.maraiina.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCountryActivity extends AppCompatActivity {

    @BindView(R.id.spCountries)
    Spinner countrySpinner;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnContinue) void continueToApp(final View view){
        Utils.displayNextActivity(this, MainActivity.class);
    }
}
