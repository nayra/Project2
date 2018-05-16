package com.nayra.maraiina.model;

import com.google.gson.annotations.SerializedName;

public class OrderResultModel {
    @SerializedName("Version")
    private String version;

    @SerializedName("StatusCode")
    private int status_code;

    @SerializedName("ErrorMessage")
    private String error_msg;

    @SerializedName("Result")
    private String result;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
