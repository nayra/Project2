package com.nayra.maraiina.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    @SerializedName("CountryID")
    private int countryID;
    @SerializedName("Name")
    private String name;
    @SerializedName("NameAr")
    private String nameAr;
    @SerializedName("Cities")
    private Object cities;

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

    public Object getCities() {
        return cities;
    }

    public void setCities(Object cities) {
        this.cities = cities;
    }
}
