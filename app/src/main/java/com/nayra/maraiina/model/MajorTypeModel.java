package com.nayra.maraiina.model;

import java.util.ArrayList;

public class MajorTypeModel {
    private String type;
    private ArrayList<MinorTypeModel> minorTypeModelsArrayList;

    public MajorTypeModel() {
        type = "";
        minorTypeModelsArrayList = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public ArrayList<MinorTypeModel> getMinorTypeModelsArrayList() {
        return minorTypeModelsArrayList;
    }

    public void setMinorTypeModelsArrayList(final ArrayList<MinorTypeModel> minorTypeModelsArrayList) {
        this.minorTypeModelsArrayList = minorTypeModelsArrayList;
    }
}
