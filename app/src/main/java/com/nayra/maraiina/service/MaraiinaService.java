package com.nayra.maraiina.service;

import com.nayra.maraiina.model.ProductAndMethodsResultModel;
import com.nayra.maraiina.model.ResultCategoryModel;
import com.nayra.maraiina.model.ResultCountryModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MaraiinaService {
    @GET("Country/")
    Call<ResultCountryModel> getCountriesList();

    @GET("Category/")
    Call<ResultCategoryModel> getCategoriesList();

    @GET("SubCategory/")
    Call<ProductAndMethodsResultModel> getProductsModel(@Query("SubCategoryID") int subCategory);
}
