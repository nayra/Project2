package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityModel {
    @SerializedName("CityID")
    @Expose
    private int cityID;
    @SerializedName("CountryID")
    @Expose
    private int countryID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("NameAr")
    @Expose
    private String nameAr;
    @SerializedName("Areas")
    @Expose
    private Object areas;
    @SerializedName("Country")
    @Expose
    private Object country;
    @SerializedName("Orders")
    @Expose
    private Object orders;

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public Object getAreas() {
        return areas;
    }

    public void setAreas(Object areas) {
        this.areas = areas;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getOrders() {
        return orders;
    }

    public void setOrders(Object orders) {
        this.orders = orders;
    }
}
