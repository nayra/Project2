
package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("ProductID")
    @Expose
    private int productID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("Price")
    @Expose
    private int price;
    @SerializedName("Barcode")
    @Expose
    private String barcode;
    @SerializedName("SaleUnit")
    @Expose
    private int saleUnit;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("SubCategoryID")
    @Expose
    private int subCategoryID;
    @SerializedName("InStock")
    @Expose
    private boolean inStock;
    @SerializedName("DiscountPrice")
    @Expose
    private int discountPrice;
    @SerializedName("SubCategory")
    @Expose
    private int subCategory;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(int saleUnit) {
        this.saleUnit = saleUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name=" + name +
                ", imageUrl=" + imageUrl +
                ", price=" + price +
                ", barcode=" + barcode +
                ", saleUnit=" + saleUnit +
                ", description='" + description + '\'' +
                ", subCategoryID=" + subCategoryID +
                ", inStock=" + inStock +
                ", discountPrice=" + discountPrice +
                ", subCategory=" + subCategory +
                '}';
    }

}
