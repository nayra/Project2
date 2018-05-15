package com.nayra.maraiina.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.nayra.maraiina.MyApplication;
import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.model.CityModel;
import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.model.ProductAndMethodsResultModel;
import com.nayra.maraiina.model.Result;
import com.nayra.maraiina.model.ResultCategoryModel;
import com.nayra.maraiina.model.ResultCityModel;
import com.nayra.maraiina.model.ResultCountryModel;
import com.nayra.maraiina.util.ConnectivityCheck;
import com.nayra.maraiina.util.ProgressDialogUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MaraiinaRepository {
    private static final String TAG = MaraiinaRepository.class.getSimpleName();

    public static LiveData<ArrayList<CountryModel>> getCountries() {
        final MutableLiveData<ArrayList<CountryModel>> data = new MutableLiveData<>();


        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            ApiConnection.getRetrofit().getCountriesList().enqueue(new Callback<ResultCountryModel>() {
                @Override
                public void onResponse(Call<ResultCountryModel> call, Response<ResultCountryModel> response) {
                    if (response != null && response.body() != null && response.body().getResult() != null) {
                        Log.d(TAG, response.body().getResult().toString());
                        data.setValue(response.body().getResult());
                    }
                }

                @Override
                public void onFailure(Call<ResultCountryModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    getCountries();
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
        }
        return data;
    }

    public static LiveData<ArrayList<CategoryModel>> getCategories() {
        final MutableLiveData<ArrayList<CategoryModel>> data = new MutableLiveData<>();


        Context context = MyApplication.getmInstance().getContext();
        if (ConnectivityCheck.isConnected(context)) {
            ApiConnection.getRetrofit().getCategoriesList().enqueue(new Callback<ResultCategoryModel>() {
                @Override
                public void onResponse(Call<ResultCategoryModel> call, Response<ResultCategoryModel> response) {
                    if (response != null && response.body() != null) {
                        Log.d(TAG, response.body().getResult().toString());
                        data.setValue(response.body().getResult());
                    }
                }

                @Override
                public void onFailure(Call<ResultCategoryModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
        }
        return data;
    }

    public static LiveData<Result> getProductDetails(int subCategory) {
        final MutableLiveData<Result> data = new MutableLiveData<>();


        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            ApiConnection.getRetrofit().getProductsModel(subCategory).enqueue(new Callback<ProductAndMethodsResultModel>() {
                @Override
                public void onResponse(Call<ProductAndMethodsResultModel> call, Response<ProductAndMethodsResultModel> response) {
                    if (response != null && response.body() != null) {
                        Log.d(TAG, response.body().toString());
                        data.setValue(response.body().getResult());
                    }
                }

                @Override
                public void onFailure(Call<ProductAndMethodsResultModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
        }

        return data;
    }

    public static LiveData<ArrayList<CityModel>> getCities() {
        final MutableLiveData<ArrayList<CityModel>> data = new MutableLiveData<>();

        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            ApiConnection.getRetrofit().getCitiesList().enqueue(new Callback<ResultCityModel>() {
                @Override
                public void onResponse(Call<ResultCityModel> call, Response<ResultCityModel> response) {
                    if (response != null && response.body() != null && response.body().getResult() != null) {
                        Log.d(TAG, response.body().getResult().toString());
                        data.setValue(response.body().getResult());
                    }
                }

                @Override
                public void onFailure(Call<ResultCityModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    getCountries();
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
        }
        return data;
    }

    /*
                orderResultModelLiveData = MaraiinaRepository.postCookedOrder();

     */
    public static LiveData<OrderResultModel> postCookedOrder(String address, int cityId, String phone, String email, String fName,
                                                             String LName, double lat, double lng, int cookingMethodId, int productId) {
        final MutableLiveData<OrderResultModel> result = new MediatorLiveData<>();

        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            ApiConnection.getRetrofit().postCookedOrder(address, cityId, phone, email, fName, LName, lat, lng, cookingMethodId, productId).enqueue(new Callback<OrderResultModel>() {
                @Override
                public void onResponse(Call<OrderResultModel> call, Response<OrderResultModel> response) {
                    if (response != null && response.body() != null && response.body().getResult() != null) {
                        Log.d(TAG, response.body().getResult().toString());
                        result.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<OrderResultModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
        }
        return result;
    }

    public static LiveData<OrderResultModel> postUnCookedOrder(String address, int cityId, String phone, String email, String fName, String lName, double lat, double lng, int cuttingId, int packagingId, int productId) {

        final MutableLiveData<OrderResultModel> result = new MediatorLiveData<>();

        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            ApiConnection.getRetrofit().postUnCookedOrder(address, cityId, phone, email, fName, lName, lat, lng, cuttingId, packagingId, productId).enqueue(new Callback<OrderResultModel>() {
                @Override
                public void onResponse(Call<OrderResultModel> call, Response<OrderResultModel> response) {
                    if (response != null && response.body() != null && response.body().getResult() != null) {
                        Log.d(TAG, response.body().getResult().toString());
                        result.setValue(response.body());
                    } else {
                        ProgressDialogUtil.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<OrderResultModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
        }
        return result;
    }
}
