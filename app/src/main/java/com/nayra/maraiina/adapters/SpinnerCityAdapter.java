package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.model.CityModel;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

public class SpinnerCityAdapter extends ArrayAdapter<CityModel> {

    private ArrayList<CityModel> cityModels;
    private LayoutInflater layoutInflater;

    private int selected_lang_index;

    private Context context;

    public SpinnerCityAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CityModel> cities) {
        super(context, resource, cities);
        this.cityModels = cities;
        layoutInflater = (LayoutInflater.from(context));
        this.context = context;

        selected_lang_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
    }

    @Override
    public int getCount() {
        return cityModels.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        MyTextView names = view.findViewById(R.id.textView);

        if (selected_lang_index == SharedPrefsUtil.ENGLISH) {
            names.setText(cityModels.get(i).getName());
        } else {
            names.setText(cityModels.get(i).getNameAr());
            Utils.setTypeFace(names, Constants.KUFI_REGULAR);
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        MyTextView names = view.findViewById(R.id.textView);

        names.setTextColor(ContextCompat.getColor(context, R.color.green_very_dark));

        if (selected_lang_index == SharedPrefsUtil.ENGLISH) {
            names.setText(cityModels.get(position).getName());
        } else {

            Utils.setTypeFace(names, Constants.KUFI_REGULAR);
            names.setText(cityModels.get(position).getNameAr());
        }

        return view;
    }
}