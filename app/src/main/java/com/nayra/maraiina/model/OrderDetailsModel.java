package com.nayra.maraiina.model;

public class OrderDetailsModel {
    private int cookingId, cuttingId, packagingId;
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

    @Override
    public String toString() {
        return "OrderDetailsModel{" +
                "cookingId=" + cookingId +
                ", cuttingId=" + cuttingId +
                ", packagingId=" + packagingId +
                ", doYouWantCooking=" + doYouWantCooking +
                ", Weight='" + Weight + '\'' +
                '}';
    }
}
