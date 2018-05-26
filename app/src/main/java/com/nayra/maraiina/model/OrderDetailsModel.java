package com.nayra.maraiina.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDetailsModel implements Parcelable {
    private int cookingId, cuttingId, packagingId, price, productId;
    private boolean doYouWantCooking;
    private String Weight, cookingMethod, packagingMethod, cuttingMethod, type, distributionMethod, img_url, otherCuttingMethod;

    private CustomerDetails customerDetails;

    public OrderDetailsModel() {
        customerDetails = new CustomerDetails();
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public String getPackagingMethod() {
        return packagingMethod;
    }

    public void setPackagingMethod(String packagingMethod) {
        this.packagingMethod = packagingMethod;
    }

    public String getCuttingMethod() {
        return cuttingMethod;
    }

    public void setCuttingMethod(String cuttingMethod) {
        this.cuttingMethod = cuttingMethod;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistributionMethod() {
        return distributionMethod;
    }

    public void setDistributionMethod(String distributionMethod) {
        this.distributionMethod = distributionMethod;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getOtherCuttingMethod() {
        return otherCuttingMethod;
    }

    public void setOtherCuttingMethod(String otherCuttingMethod) {
        this.otherCuttingMethod = otherCuttingMethod;
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
        dest.writeInt(this.productId);
        dest.writeByte(this.doYouWantCooking ? (byte) 1 : (byte) 0);
        dest.writeString(this.Weight);
        dest.writeString(this.cookingMethod);
        dest.writeString(this.packagingMethod);
        dest.writeString(this.cuttingMethod);
        dest.writeString(this.type);
        dest.writeString(this.distributionMethod);
        dest.writeString(this.img_url);
        dest.writeString(this.otherCuttingMethod);
        dest.writeParcelable(this.customerDetails, flags);
    }

    protected OrderDetailsModel(Parcel in) {
        this.cookingId = in.readInt();
        this.cuttingId = in.readInt();
        this.packagingId = in.readInt();
        this.price = in.readInt();
        this.productId = in.readInt();
        this.doYouWantCooking = in.readByte() != 0;
        this.Weight = in.readString();
        this.cookingMethod = in.readString();
        this.packagingMethod = in.readString();
        this.cuttingMethod = in.readString();
        this.type = in.readString();
        this.distributionMethod = in.readString();
        this.img_url = in.readString();
        this.otherCuttingMethod = in.readString();
        this.customerDetails = in.readParcelable(CustomerDetails.class.getClassLoader());
    }

    public static final Creator<OrderDetailsModel> CREATOR = new Creator<OrderDetailsModel>() {
        @Override
        public OrderDetailsModel createFromParcel(Parcel source) {
            return new OrderDetailsModel(source);
        }

        @Override
        public OrderDetailsModel[] newArray(int size) {
            return new OrderDetailsModel[size];
        }
    };

    @Override
    public String toString() {
        return "OrderDetailsModel{" +
                "cookingId=" + cookingId +
                ", cuttingId=" + cuttingId +
                ", packagingId=" + packagingId +
                ", price=" + price +
                ", productId=" + productId +
                ", doYouWantCooking=" + doYouWantCooking +
                ", Weight='" + Weight + '\'' +
                ", cookingMethod='" + cookingMethod + '\'' +
                ", packagingMethod='" + packagingMethod + '\'' +
                ", cuttingMethod='" + cuttingMethod + '\'' +
                ", type='" + type + '\'' +
                ", distributionMethod='" + distributionMethod + '\'' +
                ", img_url='" + img_url + '\'' +
                ", otherCuttingMethod='" + otherCuttingMethod + '\'' +
                ", customerDetails=" + customerDetails +
                '}';
    }
}
