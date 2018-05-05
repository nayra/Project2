package com.nayra.maraiina.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.service.MaraiinaRepository;

import java.util.ArrayList;

public class GetCountriesViewModel extends AndroidViewModel {

    private static LiveData<ArrayList<CountryModel>> countryArrayListLiveData;

    public GetCountriesViewModel(@NonNull Application application) {
        super(application);
        countryArrayListLiveData = MaraiinaRepository.getCountries();
    }


    public LiveData<ArrayList<CountryModel>> getCountryArrayListLiveData() {
        return countryArrayListLiveData;
    }
}
