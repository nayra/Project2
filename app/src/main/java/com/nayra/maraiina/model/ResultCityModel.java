package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultCityModel {
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("StatusCode")
    @Expose
    private int statusCode;
    @SerializedName("ErrorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("Result")
    @Expose
    private ArrayList<CityModel> result = null;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<CityModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<CityModel> result) {
        this.result = result;
    }
}
