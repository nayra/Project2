package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

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
    Spinner countrySpinner;


    private LiveData<ArrayList<CountryModel>> countryModels;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);

        ButterKnife.bind(this);

        fillCountriesSpinner();
    }

    private void fillCountriesSpinner() {
        GetCountriesViewModel getCountriesViewModel = ViewModelProviders.of(this).get(GetCountriesViewModel.class);
        countryModels = getCountriesViewModel.getCountryArrayListLiveData();
        countryModels.observe(this, foodItems -> {
            Log.e(TAG, foodItems.toString());
            displayCountries();
        });
    }

    private void displayCountries() {
        if (countryModels != null && countryModels.getValue() != null) {
            SpinnerCountryCustomAdapter countryCustomAdapter = new SpinnerCountryCustomAdapter(this, R.layout.row_spinner, countryModels.getValue(), 0);
            countrySpinner.setAdapter(countryCustomAdapter);
        }
    }

    @OnClick(R.id.btnContinue) void continueToApp(final View view){
        Utils.displayNextActivity(this, MainActivity.class);
    }

    public void energyBreakPointStart(final int stateId, final String stateDescription) {
        final Intent stateUpdate = new Intent("com.quicinc.Trepn.UpdateAppState");
        stateUpdate.putExtra("com.quicinc.Trepn.UpdateAppState.Value", stateId);
        stateUpdate.putExtra("com.quicinc.Trepn.UpdateAppState.Value.Desc", stateDescription);
        sendBroadcast(stateUpdate);
    }// Generated  energyBreakPointStart method

    public void energyBreakPointEnd() {
        final Intent stateUpdate = new Intent("com.quicinc.Trepn.UpdateAppState");
        stateUpdate.putExtra("com.quicinc.Trepn.UpdateAppState.Value", 0);
        sendBroadcast(stateUpdate);
    }// Generated  energyBreakPointEnd method
}
