package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nayra.maraiina.R;


public class SpinnerDefaultAdapter extends ArrayAdapter<String> {
    private String[] mModels;
    private LayoutInflater layoutInflater;

    private int selected_lang_index = 0;

    private Context context;

    public SpinnerDefaultAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.mModels = objects;
        layoutInflater = (LayoutInflater.from(context));
        this.context = context;
    }

    @Override
    public int getCount() {
        return mModels.length;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        TextView names = view.findViewById(R.id.textView);
        names.setText(mModels[i]);


        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        TextView names = view.findViewById(R.id.textView);

        names.setTextColor(ContextCompat.getColor(context, R.color.green_very_dark));
        names.setText(mModels[position]);

        return view;
    }
}