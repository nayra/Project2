package com.nayra.maraiina.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.SpinnerCountryCustomAdapter;
import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCountryActivity extends AppCompatActivity {

    @BindView(R.id.spCountries)
    Spinner countrySpinner;

    private ArrayList<CountryModel> countryModels;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);

        ButterKnife.bind(this);

        countryModels = new ArrayList<>();
        CountryModel c = new CountryModel();
        c.setCountryName("AbuZabi");
        countryModels.add(c);
        c = new CountryModel();
        c.setCountryName("Dubai");
        countryModels.add(c);
        c = new CountryModel();
        c.setCountryName("Asharqah");
        countryModels.add(c);

        SpinnerCountryCustomAdapter countryCustomAdapter = new SpinnerCountryCustomAdapter(this, countryModels);
        countrySpinner.setAdapter(countryCustomAdapter);
    }

    @OnClick(R.id.btnContinue) void continueToApp(final View view){
        Utils.displayNextActivity(this, MainActivity.class);
    }
}
