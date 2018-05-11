package com.nayra.maraiina.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nayra.maraiina.model.Result;
import com.nayra.maraiina.service.MaraiinaRepository;

public class ProductDetailsViewModel extends AndroidViewModel {
    private LiveData<Result> methodsModelLiveData;

    public ProductDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Result> getMethodsModelLiveData(int subCatID) {

        methodsModelLiveData = MaraiinaRepository.getProductDetails(subCatID);
        return methodsModelLiveData;
    }
}
