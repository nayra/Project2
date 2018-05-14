package com.nayra.maraiina.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDetailsModel implements Parcelable {
    private int cookingId, cuttingId, packagingId, price;
    private boolean doYouWantCooking;
    private String Weight;

    public OrderDetailsModel() {
    }

    public int getCookingId() {
        return cookingId;
    }

    public void setCookingId(int cookingId) {
        this.cookingId = cookingId;
    }

    public int getCuttingId() {
        return cuttingId;
    }

    public void setCuttingId(int cuttingId) {
        this.cuttingId = cuttingId;
    }

    public int getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(int packagingId) {
        this.packagingId = packagingId;
    }

    public boolean isDoYouWantCooking() {
        return doYouWantCooking;
    }

    public void setDoYouWantCooking(boolean doYouWantCooking) {
        this.doYouWantCooking = doYouWantCooking;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetailsModel{" +
                "cookingId=" + cookingId +
                ", cuttingId=" + cuttingId +
                ", packagingId=" + packagingId +
                ", price=" + price +
                ", doYouWantCooking=" + doYouWantCooking +
                ", Weight='" + Weight + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cookingId);
        dest.writeInt(this.cuttingId);
        dest.writeInt(this.packagingId);
        dest.writeInt(this.price);
        dest.writeByte(this.doYouWantCooking ? (byte) 1 : (byte) 0);
        dest.writeString(this.Weight);
    }

    protected OrderDetailsModel(Parcel in) {
        this.cookingId = in.readInt();
        this.cuttingId = in.readInt();
        this.packagingId = in.readInt();
        this.price = in.readInt();
        this.doYouWantCooking = in.readByte() != 0;
        this.Weight = in.readString();
    }

    public static final Parcelable.Creator<OrderDetailsModel> CREATOR = new Parcelable.Creator<OrderDetailsModel>() {
        @Override
        public OrderDetailsModel createFromParcel(Parcel source) {
            return new OrderDetailsModel(source);
        }

        @Override
        public OrderDetailsModel[] newArray(int size) {
            return new OrderDetailsModel[size];
        }
    };
}
