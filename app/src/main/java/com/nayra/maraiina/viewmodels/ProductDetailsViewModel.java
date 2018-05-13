package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nayra.maraiina.model.Result;
import com.nayra.maraiina.service.MaraiinaRepository;

public class ProductDetailsViewModel extends ViewModel {
    private LiveData<Result> methodsModelLiveData;

    public ProductDetailsViewModel() {

    }

    public LiveData<Result> getMethodsModelLiveData(int subCatID) {

        methodsModelLiveData = MaraiinaRepository.getProductDetails(subCatID);
        return methodsModelLiveData;
    }
}
