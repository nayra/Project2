package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultPackagingMethodsModel {
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
    private ArrayList<PackagingMethodsModel> result = new ArrayList<>();

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

    public ArrayList<PackagingMethodsModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<PackagingMethodsModel> result) {
        this.result = result;
    }

}
