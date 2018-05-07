package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.service.MaraiinaRepository;

import java.util.ArrayList;

public class GetCategoriesViewModel extends ViewModel {

    private LiveData<ArrayList<CategoryModel>> categoriesArrayListLiveData;

    public GetCategoriesViewModel() {

        categoriesArrayListLiveData = MaraiinaRepository.getCategories();
    }

    public LiveData<ArrayList<CategoryModel>> getCategoriesArrayListLiveData() {
        return categoriesArrayListLiveData;
    }

}