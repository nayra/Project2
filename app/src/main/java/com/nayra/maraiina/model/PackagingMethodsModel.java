package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackagingMethodsModel {
    @SerializedName("PackagingMethodID")
    @Expose
    private int packagingMethodID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("NameAr")
    @Expose
    private String nameAr;
    @SerializedName("Cities")
    @Expose
    private String cities;

    public int getPackagingMethodID() {
        return packagingMethodID;
    }

    public void setPackagingMethodID(int packagingMethodID) {
        this.packagingMethodID = packagingMethodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public Object getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }
}
