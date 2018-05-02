package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.service.MaraiinaRepository;

import java.util.ArrayList;

public class GetCountriesViewModel extends ViewModel {

    private LiveData<ArrayList<CountryModel>> countryArrayListLiveData;

    public GetCountriesViewModel() {

        countryArrayListLiveData = MaraiinaRepository.getCountries();
    }

    public LiveData<ArrayList<CountryModel>> getCountryArrayListLiveData() {
        return countryArrayListLiveData;
    }
}
