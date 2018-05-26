package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.util.SharedPrefsUtil;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class DistributionMethodsCustomAdapter extends ArrayAdapter<String> {
    private String[] distributionModels;
    private LayoutInflater layoutInflater;

    private int selected_lang_index;

    private Context context;

    public DistributionMethodsCustomAdapter(@NonNull Context context, int resource, @NonNull String[] distributions) {
        super(context, resource, distributions);
        this.distributionModels = distributions;
        layoutInflater = (LayoutInflater.from(context));
        this.context = context;

        selected_lang_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
    }

    @Override
    public int getCount() {
        return distributionModels.length;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        MyTextView names = view.findViewById(R.id.textView);

        names.setText(distributionModels[i]);


        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.row_spinner_two_lines, null);

        MyTextView names = view.findViewById(R.id.tvMethod);

        names.setTextColor(ContextCompat.getColor(context, R.color.green_very_dark));

        names.setText(distributionModels[position]);

        MyTextView desc = view.findViewById(R.id.tvDesc);


        return view;
    }
}

