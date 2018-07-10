package com.nayra.maraiina.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerDetails implements Parcelable {

    @SerializedName("Firstname")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("PhoneNumber")
    private String phone;
    @SerializedName("Address")
    private String address;

    @SerializedName("cityID")
    private int cityId;

    @SerializedName("Latitude")
    private double lat;
    @SerializedName("Longitude")
    private double lng;

    @SerializedName("Details")
    private ArrayList<OrderDetailsModel> ordersList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public ArrayList<OrderDetailsModel> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(ArrayList<OrderDetailsModel> ordersList) {
        this.ordersList = ordersList;
    }


    public CustomerDetails() {
    }

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", cityId=" + cityId +
                ", lat=" + lat +
                ", lng=" + lng +
                ", ordersList=" + ordersList +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeInt(this.cityId);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
        dest.writeTypedList(this.ordersList);
    }

    protected CustomerDetails(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.cityId = in.readInt();
        this.lat = in.readDouble();
        this.lng = in.readDouble();
        this.ordersList = in.createTypedArrayList(OrderDetailsModel.CREATOR);
    }

    public static final Creator<CustomerDetails> CREATOR = new Creator<CustomerDetails>() {
        @Override
        public CustomerDetails createFromParcel(Parcel source) {
            return new CustomerDetails(source);
        }

        @Override
        public CustomerDetails[] newArray(int size) {
            return new CustomerDetails[size];
        }
    };
}
