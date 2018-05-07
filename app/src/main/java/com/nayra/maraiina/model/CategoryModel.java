package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryModel {
    @SerializedName("CategoryID")
    @Expose
    private int categoryID;
    @SerializedName("ParentID")
    @Expose
    private String parentID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("SubCategory")
    @Expose
    private ArrayList<CategoryModel> subCategory = null;
    @SerializedName("CuttingMethods")
    @Expose
    private ArrayList<Object> cuttingMethods = null;
    @SerializedName("CookingMethods")
    @Expose
    private ArrayList<Object> cookingMethods = null;
    @SerializedName("PackagingMethods")
    @Expose
    private ArrayList<Object> packagingMethods = null;
    @SerializedName("Products")
    @Expose
    private ArrayList<Object> products = null;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Object getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<CategoryModel> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ArrayList<CategoryModel> subCategory) {
        this.subCategory = subCategory;
    }

    public ArrayList<Object> getCuttingMethods() {
        return cuttingMethods;
    }

    public void setCuttingMethods(ArrayList<Object> cuttingMethods) {
        this.cuttingMethods = cuttingMethods;
    }

    public ArrayList<Object> getCookingMethods() {
        return cookingMethods;
    }

    public void setCookingMethods(ArrayList<Object> cookingMethods) {
        this.cookingMethods = cookingMethods;
    }

    public ArrayList<Object> getPackagingMethods() {
        return packagingMethods;
    }

    public void setPackagingMethods(ArrayList<Object> packagingMethods) {
        this.packagingMethods = packagingMethods;
    }

    public ArrayList<Object> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Object> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryID=" + categoryID +
                ", parentID='" + parentID + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", subCategory=" + subCategory +
                ", cuttingMethods=" + cuttingMethods +
                ", cookingMethods=" + cookingMethods +
                ", packagingMethods=" + packagingMethods +
                ", products=" + products +
                '}';
    }
}
