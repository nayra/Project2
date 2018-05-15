package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nayra.maraiina.model.OffersModel;
import com.nayra.maraiina.service.MaraiinaRepository;

import java.util.ArrayList;

public class GetOffersViewModel extends ViewModel {
    public GetOffersViewModel() {

    }

    public LiveData<ArrayList<OffersModel>> getOffers() {
        LiveData<ArrayList<OffersModel>> offers = MaraiinaRepository.getOffers();

        return offers;
    }
}
