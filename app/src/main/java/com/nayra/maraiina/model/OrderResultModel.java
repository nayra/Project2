package com.nayra.maraiina.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OrderResultModel implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.version);
        dest.writeInt(this.status_code);
        dest.writeString(this.error_msg);
        dest.writeString(this.result);
    }

    public OrderResultModel() {
    }

    protected OrderResultModel(Parcel in) {
        this.version = in.readString();
        this.status_code = in.readInt();
        this.error_msg = in.readString();
        this.result = in.readString();
    }

    public static final Parcelable.Creator<OrderResultModel> CREATOR = new Parcelable.Creator<OrderResultModel>() {
        @Override
        public OrderResultModel createFromParcel(Parcel source) {
            return new OrderResultModel(source);
        }

        @Override
        public OrderResultModel[] newArray(int size) {
            return new OrderResultModel[size];
        }
    };
}
