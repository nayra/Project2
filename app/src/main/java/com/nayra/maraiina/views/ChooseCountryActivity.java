package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.SpinnerCountryCustomAdapter;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.util.LanguageUtil;
import com.nayra.maraiina.util.ProgressDialogUtil;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;
import com.nayra.maraiina.viewmodels.GetCountriesViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.android.segmented.SegmentedGroup;

public class ChooseCountryActivity extends AppCompatActivity {

    private static final String TAG = ChooseCountryActivity.class.getSimpleName();
    @BindView(R.id.spCountries)
    AppCompatSpinner countrySpinner;

    @BindView(R.id.segmented2)
    SegmentedGroup langSegmentedGroup;

    @BindView(R.id.rb_ar)
    RadioButton arabicRadioButton;

    @BindView(R.id.rb_en)
    RadioButton englishRadioButton;

    @BindView(R.id.tv_welcome)
    MyTextView txtWelcome;

    @BindView(R.id.tv_choose_country)
    MyTextView txtChooseCountry;

    private LiveData<ArrayList<CountryModel>> countryModels;

    private int selectedLanguage;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedLanguage = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);

        LanguageUtil.setLocaleLanguage(selectedLanguage, this);

        setContentView(R.layout.activity_choose_country);
        ButterKnife.bind(this);

        setTypeFace();
        spinnerOnItemSelectedListener();
        fillCountriesSpinner();

        initLang();
    }

    private void setTypeFace() {
        if (selectedLanguage == 1) {
            Utils.setTypeFace(txtWelcome, Constants.KUFI_REGULAR);
            Utils.setTypeFace(txtChooseCountry, Constants.KUFI_REGULAR);
        }
    }

    private void initLang() {
        int selectedLang = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
        if (selectedLang == 0) {
            englishRadioButton.setChecked(true);
        } else {
            arabicRadioButton.setChecked(true);
        }

        langSegmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int lang = 0;
                switch (i) {
                    case R.id.rb_en:
                        lang = 0;
                        break;
                    case R.id.rb_ar:
                        lang = 1;
                        break;
                }

                LanguageUtil.changeLanguage(lang, ChooseCountryActivity.this);
            }
        });
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

        ProgressDialogUtil.show(this);
        GetCountriesViewModel getCountriesViewModel = ViewModelProviders.of(this).get(GetCountriesViewModel.class);
        countryModels = getCountriesViewModel.getCountryArrayListLiveData();
        countryModels.observe(this, countries -> {
            displayCountries(countries);
        });
    }

    private void displayCountries(ArrayList<CountryModel> countries) {
        if (countries != null) {
            ProgressDialogUtil.dismiss();
            Log.d(TAG, countries.toString());
            SpinnerCountryCustomAdapter countryCustomAdapter = new SpinnerCountryCustomAdapter(this, R.layout.row_spinner, countries);
            countrySpinner.setAdapter(countryCustomAdapter);
        }
    }

    @OnClick(R.id.btnContinue)
    void continueToApp() {
        Utils.displayNextActivity(this, MainActivity.class);
    }
}
