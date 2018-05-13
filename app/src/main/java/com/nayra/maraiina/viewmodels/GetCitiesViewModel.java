package com.nayra.maraiina.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nayra.maraiina.model.CityModel;
import com.nayra.maraiina.service.MaraiinaRepository;

import java.util.ArrayList;

public class GetCitiesViewModel extends AndroidViewModel {

    private static LiveData<ArrayList<CityModel>> cityArrayListLiveData;

    public GetCitiesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<CityModel>> getCityArrayListLiveData() {
        cityArrayListLiveData = MaraiinaRepository.getCities();
        return cityArrayListLiveData;
    }
}