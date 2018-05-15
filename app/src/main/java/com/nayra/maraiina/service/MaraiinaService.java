package com.nayra.maraiina.service;

import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.model.ProductAndMethodsResultModel;
import com.nayra.maraiina.model.ResultCategoryModel;
import com.nayra.maraiina.model.ResultCityModel;
import com.nayra.maraiina.model.ResultCountryModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MaraiinaService {
    @GET("Country/")
    Call<ResultCountryModel> getCountriesList();

    @GET("Cities/")
    Call<ResultCityModel> getCitiesList();

    @GET("Category/")
    Call<ResultCategoryModel> getCategoriesList();

    @GET("SubCategory/")
    Call<ProductAndMethodsResultModel> getProductsModel(@Query("SubCategoryID") int subCategory);

    /*
    Address:Address
    AreaID:1
    CityID:1
    PhoneNumber:971562368656
    email:ahmed.z.taalab@hotmail.com
    Firstname:ddd
    LastName:LastName
    Latitude:2.2
    Longitude:2.3
    CookingMethodID:1
    CuttingMethodID:1
    PackagingMethodID:1
    ProductID:1
     */

    @FormUrlEncoded
    @POST("Orders/")
    Call<OrderResultModel> postCookedOrder(@Field("Address") String address, @Field("CityID") int cityId,
                                           @Field("PhoneNumber") String phone, @Field("email") String email,
                                           @Field("Firstname") String firstName, @Field("LastName") String lastName,
                                           @Field("Latitude") double lat, @Field("Longitude") double lng,
                                           @Field("CookingMethodID") int cookingMethodId, @Field("ProductID") int productId);

    @FormUrlEncoded
    @POST("Orders/")
    Call<OrderResultModel> postUnCookedOrder(@Field("Address") String address, @Field("CityID") int cityId,
                                             @Field("PhoneNumber") String phone, @Field("email") String email,
                                             @Field("Firstname") String firstName, @Field("LastName") String lastName,
                                             @Field("Latitude") double lat, @Field("Longitude") double lng,
                                             @Field("CuttingMethodID") int cuttingMethodId,
                                             @Field("PackagingMethodID") int packagingMethodId, @Field("ProductID") int productId);

}
