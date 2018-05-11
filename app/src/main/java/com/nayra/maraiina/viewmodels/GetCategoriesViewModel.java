package com.nayra.maraiina.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.service.MaraiinaRepository;

import java.util.ArrayList;

public class GetCategoriesViewModel extends AndroidViewModel {

    private LiveData<ArrayList<CategoryModel>> categoriesArrayListLiveData;

    public GetCategoriesViewModel(@NonNull Application application) {
        super(application);
        categoriesArrayListLiveData = MaraiinaRepository.getCategories();
    }

    public LiveData<ArrayList<CategoryModel>> getCategoriesArrayListLiveData() {
        return categoriesArrayListLiveData;
    }

}