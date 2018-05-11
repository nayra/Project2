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
import com.nayra.maraiina.model.CountryModel;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class SpinnerCountryCustomAdapter extends ArrayAdapter<CountryModel> {
    private ArrayList<CountryModel> countryModels;
    private LayoutInflater layoutInflater;

    private int selected_lang_index;

    private Context context;

    public SpinnerCountryCustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CountryModel> countries) {
        super(context, resource, countries);
        this.countryModels = countries;
        layoutInflater = (LayoutInflater.from(context));
        this.context = context;

        selected_lang_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
    }

    @Override
    public int getCount() {
        return countryModels.size();
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
            names.setText(countryModels.get(i).getName());
        } else {
            names.setText(countryModels.get(i).getNameAr());
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
            names.setText(countryModels.get(position).getName());
        } else {

            Utils.setTypeFace(names, Constants.KUFI_REGULAR);
            names.setText(countryModels.get(position).getNameAr());
        }

        return view;
    }
}

