package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.model.PackagingMethod;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

public class PackagingMethodsSpinnerAdapter extends ArrayAdapter<PackagingMethod> {
    private final LayoutInflater layoutInflater;
    private ArrayList<PackagingMethod> packagingMethodsList;
    private int selected_lang = 0;

    private Context context;

    public PackagingMethodsSpinnerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PackagingMethod> packagingMethodsList) {
        super(context, resource, packagingMethodsList);

        this.packagingMethodsList = packagingMethodsList;
        this.context = context;

        selected_lang = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);

        layoutInflater = (LayoutInflater.from(context));

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        MyTextView names = view.findViewById(R.id.textView);
        names.setTextColor(ContextCompat.getColor(context, R.color.grey_dark));

        if (selected_lang == SharedPrefsUtil.ENGLISH) {
            names.setText(packagingMethodsList.get(position).getName());
        } else {
            String arName = packagingMethodsList.get(position).getNameAr();
            names.setText(arName);

            Utils.setTypeFace(names, Constants.KUFI_REGULAR);
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        MyTextView names = view.findViewById(R.id.textView);
        names.setTextColor(ContextCompat.getColor(context, R.color.grey_dark));

        if (selected_lang == SharedPrefsUtil.ENGLISH) {
            names.setText(packagingMethodsList.get(position).getName());
        } else {
            String arName = packagingMethodsList.get(position).getNameAr();
            names.setText(arName);

            Utils.setTypeFace(names, Constants.KUFI_REGULAR);
        }

        return view;
    }

    @Override
    public int getCount() {
        return packagingMethodsList.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}

