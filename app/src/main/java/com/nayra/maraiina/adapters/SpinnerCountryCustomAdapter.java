package com.nayra.maraiina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nayra.maraiina.R;
import com.nayra.maraiina.model.CountryModel;

import java.util.ArrayList;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class SpinnerCountryCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CountryModel> countryModels = new ArrayList<>();
    private LayoutInflater layoutInflater;

    private int selected_lang_index = 0;

    public SpinnerCountryCustomAdapter(Context applicationContext, ArrayList<CountryModel> countries) {
        this.context = applicationContext;
        this.countryModels = countries;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        TextView names = view.findViewById(R.id.textView);

        names.setText(countryModels.get(i).getCountryName());

        return view;
    }
}

