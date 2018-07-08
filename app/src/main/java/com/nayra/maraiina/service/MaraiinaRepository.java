package com.nayra.maraiina.service;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.MyApplication;
import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.model.CityModel;
import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.model.OffersModel;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.model.ProductAndMethodsResultModel;
import com.nayra.maraiina.model.Result;
import com.nayra.maraiina.model.ResultCategoryModel;
import com.nayra.maraiina.model.ResultCityModel;
import com.nayra.maraiina.model.ResultCountryModel;
import com.nayra.maraiina.model.ResultOffersModel;
import com.nayra.maraiina.model.ResultSuggestion;
import com.nayra.maraiina.model.SuggestionModel;
import com.nayra.maraiina.util.ConnectivityCheck;
import com.nayra.maraiina.util.ProgressDialogUtil;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MaraiinaRepository {
    private static final String TAG = MaraiinaRepository.class.getSimpleName();
    private static Activity lastActivity;

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
                    ProgressDialogUtil.dismiss();
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
            ProgressDialogUtil.dismiss();
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
                    ProgressDialogUtil.dismiss();
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
            Utils.displayNoInternetConnectionActivity();
            ProgressDialogUtil.dismiss();
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
                    ProgressDialogUtil.dismiss();
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
            Utils.displayNoInternetConnectionActivity();
            ProgressDialogUtil.dismiss();
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
                    ProgressDialogUtil.dismiss();
                    Log.e(TAG, t.toString());
                    //getCountries();
                }
            });
        } else {
            ProgressDialogUtil.dismiss();
            Utils.displayNoInternetConnectionActivity();
            Log.e(TAG, "No internet connectivity");
        }
        return data;
    }

    public static LiveData<OrderResultModel> postOrder(OrderDetailsModel orderDetailsModel) {
        MutableLiveData<OrderResultModel> result = new MediatorLiveData<>();

        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            String address = orderDetailsModel.getCustomerDetails().getAddress();
            String phone = orderDetailsModel.getCustomerDetails().getPhone();
            String email = orderDetailsModel.getCustomerDetails().getEmail();
            String name = orderDetailsModel.getCustomerDetails().getName();

            int cookingMethodId = orderDetailsModel.getCookingId();
            int cuttingMethodId = orderDetailsModel.getCuttingId();
            int packagingMethodId = orderDetailsModel.getPackagingId();

            int productId = orderDetailsModel.getProductId();

            int cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);

            String distributionMethod = orderDetailsModel.getDistributionMethod();

            double lat = orderDetailsModel.getCustomerDetails().getLat();
            double lng = orderDetailsModel.getCustomerDetails().getLng();

            String lang = SharedPrefsUtil.getString(SharedPrefsUtil.SELECTED_LANGUAGE);

            /*@Field("Address") String address, @Field("Lang") String lang, @Field("CityID") int cityId,
            @Field("AreaID") int areaId ,
            @Field("PhoneNumber") String phone, @Field("email") String email,
            @Field("Firstname") String fName, @Field("LastName") String lName,
            @Field("Latitude") String lat, @Field("Longitude") String lng,
            @Field("CookingMethodID") int cookingMethodId, @Field("CuttingMethodID") int cuttingMethodId,
            @Field("PackagingMethodID") int packagingMethodId, @Field("DestrupMethodID") String distributionMethodId,
            @Field("ProductID") int productId,
            @Field("CuttingMethodOther") String otherCutting,
            @Header("Authorization") String header*/

            /*
            Address:Address
AreaID:1
CityID:1
PhoneNumber:9715
email:n@gmail.com
Firstname:ddd
LastName:LastName
Latitude:2.2
Longitude:2.3
CookingMethodID:0
//CuttingMethodID:1
//PackagingMethodID:1
ProductID:1
Lang:ar
CuttingMethodOther:fffff
             */
            Call<OrderResultModel> call = ApiConnection.getRetrofit().postOrder(address, lang, cityId, 1, phone, email, name, "", lat, lng,
                    cookingMethodId, cuttingMethodId, packagingMethodId, distributionMethod, productId, "", Constants.auth);

            Log.e("ORDER", call.request().header("Authorization"));
            Log.e("ORDER", call.request().body().toString());

            call.enqueue(new Callback<OrderResultModel>() {
                @Override
                public void onResponse(Call<OrderResultModel> call, Response<OrderResultModel> response) {
                    if (response != null && response.body() != null) {
                        Log.d(TAG, response.body().toString());
                        result.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<OrderResultModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    ProgressDialogUtil.dismiss();
                }
            });
        } else {
            Log.e(TAG, "No internet connectivity");
            Utils.displayNoInternetConnectionActivity();
            ProgressDialogUtil.dismiss();
        }
        return result;
    }

    public static LiveData<OrderResultModel> postCookedOrder(OrderDetailsModel orderDetailsModel) {
        MutableLiveData<OrderResultModel> result = new MediatorLiveData<>();

        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            String address = orderDetailsModel.getCustomerDetails().getAddress();
            String phone = orderDetailsModel.getCustomerDetails().getPhone();
            String email = orderDetailsModel.getCustomerDetails().getEmail();
            String name = orderDetailsModel.getCustomerDetails().getName();

            int cookingMethodId = orderDetailsModel.getCookingId();
            //int cuttingMethodId = orderDetailsModel.getCuttingId();
            //int packagingMethodId = orderDetailsModel.getPackagingId();

            int productId = orderDetailsModel.getProductId();

            int cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);

            //String distributionMethod = orderDetailsModel.getDistributionMethod();

            String lang = SharedPrefsUtil.getString(SharedPrefsUtil.SELECTED_LANGUAGE);

            ApiConnection.getRetrofit().postOrder(address, lang, cityId, phone, email, name,
                    "", cookingMethodId, "", productId, Constants.auth)
                    .enqueue(new Callback<OrderResultModel>() {
                        @Override
                        public void onResponse(Call<OrderResultModel> call, Response<OrderResultModel> response) {
                            if (response != null && response.body() != null) {
                                Log.d(TAG, response.body().toString());
                                result.setValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<OrderResultModel> call, Throwable t) {
                            Log.e(TAG, t.toString());
                            ProgressDialogUtil.dismiss();
                        }
                    });
        } else {
            Log.e(TAG, "No internet connectivity");
            ProgressDialogUtil.dismiss();
        }
        return result;
    }


    public static LiveData<ArrayList<OffersModel>> getOffers() {
        final MutableLiveData<ArrayList<OffersModel>> result = new MediatorLiveData<>();

        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            ApiConnection.getRetrofit().getOffers().enqueue(new Callback<ResultOffersModel>() {
                @Override
                public void onResponse(Call<ResultOffersModel> call, Response<ResultOffersModel> response) {
                    if (response != null && response.body() != null) {
                        result.setValue(response.body().getResult());
                    } else {
                        ProgressDialogUtil.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResultOffersModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    ProgressDialogUtil.dismiss();
                }
            });
        } else {
            ProgressDialogUtil.dismiss();
            Utils.displayNoInternetConnectionActivity();
            Log.e(TAG, "No internet connectivity");
        }
        return result;
    }

    public static LiveData<Boolean> sendSuggestion(final SuggestionModel model) {
        final MutableLiveData<Boolean> isSent = new MediatorLiveData<>();

        Context context = MyApplication.getmInstance().getContext();

        if (ConnectivityCheck.isConnected(context)) {
            ApiConnection.getRetrofit().postSuggestion(model.getTitle(), model.getName(), model.getEmail(), model.getPhoneNumber(),
                    model.getDescription()).enqueue(new Callback<ResultSuggestion>() {
                @Override
                public void onResponse(Call<ResultSuggestion> call, Response<ResultSuggestion> response) {
                    if (response != null && response.body() != null) {
                        if (response.body().getResult() != null)
                            isSent.setValue(true);
                    } else {
                        isSent.setValue(false);
                        ProgressDialogUtil.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResultSuggestion> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    isSent.setValue(false);
                    ProgressDialogUtil.dismiss();
                }
            });
        } else {
            ProgressDialogUtil.dismiss();
            Utils.displayNoInternetConnectionActivity();
            isSent.setValue(false);
            Log.e(TAG, "No internet connectivity");
        }
        return isSent;
    }
}
