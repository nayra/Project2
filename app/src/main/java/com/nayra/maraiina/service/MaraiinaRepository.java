package com.nayra.maraiina.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.nayra.maraiina.MyApplication;
import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.model.ResultCountryModel;
import com.nayra.maraiina.util.ConnectivityCheck;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MaraiinaRepository {
    private static final String TAG = MaraiinaRepository.class.getSimpleName();

    public static LiveData<ArrayList<CountryModel>> getCountries() {
        final MutableLiveData<ArrayList<CountryModel>> data = new MutableLiveData<>();

        if (ConnectivityCheck.isConnected(MyApplication.getContext())) {
            ApiConnection.getRetrofit().getCountriesList().enqueue(new Callback<ResultCountryModel>() {
                @Override
                public void onResponse(Call<ResultCountryModel> call, Response<ResultCountryModel> response) {
                    if (response != null && response.body() != null) {
                        Log.d(TAG, "response");
                        data.setValue(response.body().getResult());

                    }
                }

                @Override
                public void onFailure(Call<ResultCountryModel> call, Throwable t) {
                    Log.d(TAG, t.toString());
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
        }
        return data;
    }
}
