
package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductAndMethodsResultModel {

    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("StatusCode")
    @Expose
    private int statusCode;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("Result")
    @Expose
    private Result result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ProductAndMethodsResultModel{" +
                "version='" + version + '\'' +
                ", statusCode=" + statusCode +
                ", errorMessage=" + errorMessage +
                ", result=" + result +
                '}';
    }

}
