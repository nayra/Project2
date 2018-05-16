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
    @SerializedName("NameAr")
    @Expose
    private String nameAr;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("SubCategory")
    @Expose
    private ArrayList<CategoryModel> subCategory = null;
    @SerializedName("CuttingMethods")
    @Expose
    private ArrayList<CuttingMethod> cuttingMethods = null;
    @SerializedName("CookingMethods")
    @Expose
    private ArrayList<CookingMethod> cookingMethods = null;
    @SerializedName("PackagingMethods")
    @Expose
    private ArrayList<PackagingMethod> packagingMethods = null;
    @SerializedName("Products")
    @Expose
    private ArrayList<Product> products = null;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getParentID() {
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

    public ArrayList<CuttingMethod> getCuttingMethods() {
        return cuttingMethods;
    }

    public void setCuttingMethods(ArrayList<CuttingMethod> cuttingMethods) {
        this.cuttingMethods = cuttingMethods;
    }

    public ArrayList<CookingMethod> getCookingMethods() {
        return cookingMethods;
    }

    public void setCookingMethods(ArrayList<CookingMethod> cookingMethods) {
        this.cookingMethods = cookingMethods;
    }

    public ArrayList<PackagingMethod> getPackagingMethods() {
        return packagingMethods;
    }

    public void setPackagingMethods(ArrayList<PackagingMethod> packagingMethods) {
        this.packagingMethods = packagingMethods;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryID=" + categoryID +
                ", parentID='" + parentID + '\'' +
                ", name='" + name + '\'' +
                ", nameAr='" + nameAr + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", subCategory=" + subCategory +
                ", cuttingMethods=" + cuttingMethods +
                ", cookingMethods=" + cookingMethods +
                ", packagingMethods=" + packagingMethods +
                ", products=" + products +
                '}';
    }
}
