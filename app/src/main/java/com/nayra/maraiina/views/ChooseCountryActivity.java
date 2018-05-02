package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.SpinnerCountryCustomAdapter;
import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.util.Utils;
import com.nayra.maraiina.viewmodels.GetCountriesViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCountryActivity extends AppCompatActivity {

    private static final String TAG = ChooseCountryActivity.class.getSimpleName();
    @BindView(R.id.spCountries)
    AppCompatSpinner countrySpinner;


    private LiveData<ArrayList<CountryModel>> countryModels;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);

        ButterKnife.bind(this);

        spinnerOnItemSelectedListener();
        fillCountriesSpinner();
    }

    private void spinnerOnItemSelectedListener() {
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void fillCountriesSpinner() {
        GetCountriesViewModel getCountriesViewModel = ViewModelProviders.of(this).get(GetCountriesViewModel.class);
        countryModels = getCountriesViewModel.getCountryArrayListLiveData();
        countryModels.observe(this, countries -> {
            displayCountries(countries);
        });
    }

    private void displayCountries(ArrayList<CountryModel> countries) {
        if (countries != null) {
            Log.d(TAG, countries.toString());
            SpinnerCountryCustomAdapter countryCustomAdapter = new SpinnerCountryCustomAdapter(this, R.layout.row_spinner, countries, 0);
            countrySpinner.setAdapter(countryCustomAdapter);
        }
    }

    @OnClick(R.id.btnContinue) void continueToApp(final View view){
        Utils.displayNextActivity(this, MainActivity.class);
    }
}
