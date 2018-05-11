
package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CookingMethod {

    @SerializedName("CookingMethodID")
    @Expose
    private int cookingMethodID;
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

    public int getCookingMethodID() {
        return cookingMethodID;
    }

    public void setCookingMethodID(int cookingMethodID) {
        this.cookingMethodID = cookingMethodID;
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
        return "CookingMethod{" +
                "cookingMethodID=" + cookingMethodID +
                ", name='" + name + '\'' +
                ", nameAr=" + nameAr +
                '}';
    }

}
