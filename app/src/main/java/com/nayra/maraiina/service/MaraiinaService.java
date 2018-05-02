package com.nayra.maraiina.service;

import com.nayra.maraiina.model.ResultCountryModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MaraiinaService {
    @GET("Country/")
    Call<ResultCountryModel> getCountriesList();
}
