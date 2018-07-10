package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {
    @SerializedName("CuttingMethodID")
    @Expose
    private int cuttingMethodID;
    @SerializedName("ProductID")
    @Expose
    private int productID;

    @SerializedName("CookingMethodID")
    @Expose
    private int cookingMethodId;

    @SerializedName("PackagingMethodID")
    @Expose
    private int packagingMethodId;

    @SerializedName("DestrupMethodID")
    @Expose
    private String distributionMethod;

    @SerializedName("CuttingMethodOther")
    @Expose
    private String cuttingMethodOther;

    public int getCuttingMethodID() {
        return cuttingMethodID;
    }

    public void setCuttingMethodID(int cuttingMethodID) {
        this.cuttingMethodID = cuttingMethodID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCookingMethodId() {
        return cookingMethodId;
    }

    public void setCookingMethodId(int cookingMethodId) {
        this.cookingMethodId = cookingMethodId;
    }

    public int getPackagingMethodId() {
        return packagingMethodId;
    }

    public void setPackagingMethodId(int packagingMethodId) {
        this.packagingMethodId = packagingMethodId;
    }

    public String getDistributionMethod() {
        return distributionMethod;
    }

    public void setDistributionMethod(String distributionMethod) {
        this.distributionMethod = distributionMethod;
    }

    public String getCuttingMethodOther() {
        return cuttingMethodOther;
    }

    public void setCuttingMethodOther(String cuttingMethodOther) {
        this.cuttingMethodOther = cuttingMethodOther;
    }
}
