
package com.nayra.maraiina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuttingMethod {

    @SerializedName("CuttingMethodID")
    @Expose
    private int cuttingMethodID;
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

    public int getCuttingMethodID() {
        return cuttingMethodID;
    }

    public void setCuttingMethodID(int cuttingMethodID) {
        this.cuttingMethodID = cuttingMethodID;
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
        return "CuttingMethod{" +
                "cuttingMethodID=" + cuttingMethodID +
                ", name='" + name + '\'' +
                ", nameAr=" + nameAr +
                '}';
    }

}
