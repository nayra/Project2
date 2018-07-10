package com.nayra.maraiina.service;

import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.model.OrderToBeSent;
import com.nayra.maraiina.model.ProductAndMethodsResultModel;
import com.nayra.maraiina.model.ResultCategoryModel;
import com.nayra.maraiina.model.ResultCityModel;
import com.nayra.maraiina.model.ResultCountryModel;
import com.nayra.maraiina.model.ResultOffersModel;
import com.nayra.maraiina.model.ResultSuggestion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
DestrupMethodID:Different parts
     */
    @FormUrlEncoded
    @POST("Orders/")
    Call<OrderResultModel> postOrder(@Field("Address") String address, @Field("Lang") String lang, @Field("CityID") int cityId,
                                     @Field("AreaID") int areaId,
                                     @Field("PhoneNumber") String phone, @Field("email") String email,
                                     @Field("Firstname") String fName, @Field("LastName") String lName,
                                     @Field("Latitude") double lat, @Field("Longitude") double lng,
                                     @Field("CookingMethodID") int cookingMethodId, @Field("CuttingMethodID") int cuttingMethodId,
                                     @Field("PackagingMethodID") int packagingMethodId, @Field("DestrupMethodID") String distributionMethodId,
                                     @Field("ProductID") int productId,
                                     @Field("CuttingMethodOther") String otherCutting,
                                     @Header("Authorization") String header);

    @FormUrlEncoded
    @POST("Orders/")
    Call<OrderResultModel> postOrder(@Field("Address") String address, @Field("Lang") String lang, @Field("CityID") int cityId,
                                     @Field("PhoneNumber") String phone, @Field("email") String email,
                                     @Field("Firstname") String fName, @Field("LastName") String lName,
                                     @Field("CookingMethodID") int cookingMethodId, @Field("CuttingMethodOther") String otherCutting,
                                     @Field("ProductID") int productId, @Header("Authorization") String header);

    @GET("offers/")
    Call<ResultOffersModel> getOffers();

    /*
    Title:title
Name:name
Email:n@gmail.com
PhoneNumber:01098045881
Description:fdffsf
     */
    @FormUrlEncoded
    @POST("Suggestions/")
    Call<ResultSuggestion> postSuggestion(@Field("Title") String subject, @Field("Name") String name,
                                          @Field("Email") String email, @Field("PhoneNumber") String phone,
                                          @Field("Description") String desc);

    @POST("Orders")
    Call<OrderResultModel> postMultipleOrder(@Body OrderToBeSent json, @Header("Authorization") String header, @Header("Content-Type") String contentType);
}
