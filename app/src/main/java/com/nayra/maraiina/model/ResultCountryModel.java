package com.nayra.maraiina.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultCountryModel {
    @SerializedName("Version")
    private String version;
    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("ErrorMessage")
    private String errorMessage;
    @SerializedName("Result")
    private ArrayList<CountryModel> result = null;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<CountryModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<CountryModel> result) {
        this.result = result;
    }
}
