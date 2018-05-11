
package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {

    @SerializedName("Products")
    @Expose
    private ArrayList<Product> products = null;
    @SerializedName("PackagingMethods")
    @Expose
    private ArrayList<PackagingMethod> packagingMethods = null;
    @SerializedName("CookingMethods")
    @Expose
    private ArrayList<CookingMethod> cookingMethods = null;
    @SerializedName("CuttingMethods")
    @Expose
    private ArrayList<CuttingMethod> cuttingMethods = null;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<PackagingMethod> getPackagingMethods() {
        return packagingMethods;
    }

    public void setPackagingMethods(ArrayList<PackagingMethod> packagingMethods) {
        this.packagingMethods = packagingMethods;
    }

    public ArrayList<CookingMethod> getCookingMethods() {
        return cookingMethods;
    }

    public void setCookingMethods(ArrayList<CookingMethod> cookingMethods) {
        this.cookingMethods = cookingMethods;
    }

    public ArrayList<CuttingMethod> getCuttingMethods() {
        return cuttingMethods;
    }

    public void setCuttingMethods(ArrayList<CuttingMethod> cuttingMethods) {
        this.cuttingMethods = cuttingMethods;
    }

    @Override
    public String toString() {
        return "Result{" +
                "products=" + products +
                ", packagingMethods=" + packagingMethods +
                ", cookingMethods=" + cookingMethods +
                ", cuttingMethods=" + cuttingMethods +
                '}';
    }

}
