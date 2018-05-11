
package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackagingMethod {

    @SerializedName("PackagingMethodID")
    @Expose
    private int packagingMethodID;
    /*@SerializedName("SubCategoryID")
    @Expose
    private int subCategoryID;*/
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("NameAr")
    @Expose
    private String nameAr;
    /*@SerializedName("SubCategory")
    @Expose
    private int subCategory;*/

    public int getPackagingMethodID() {
        return packagingMethodID;
    }

    public void setPackagingMethodID(int packagingMethodID) {
        this.packagingMethodID = packagingMethodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    @Override
    public String toString() {
        return "PackagingMethod{" +
                "packagingMethodID=" + packagingMethodID +
                ", name='" + name + '\'' +
                ", nameAr=" + nameAr +
                '}';
    }

}
