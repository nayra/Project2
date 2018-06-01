package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.interfaces.WeightsRecyclerViewClickListener;
import com.nayra.maraiina.model.Product;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeightsAdapter extends RecyclerView.Adapter<WeightsAdapter.MyViewHolder> {

    private ArrayList<Product> weightsList;
    private int selected_weight = 0;

    private Context context;
    private int selected_language_index;

    private WeightsRecyclerViewClickListener weightsRecyclerViewClickListener;

    public WeightsAdapter(Context context, ArrayList<Product> weightsList, WeightsRecyclerViewClickListener weightsRecyclerViewClickListener) {
        this.weightsList = weightsList;
        this.context = context;
        selected_language_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
        this.weightsRecyclerViewClickListener = weightsRecyclerViewClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_weight, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String weight;
        if (selected_weight == position) {
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.grey_dark));
        } else {
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.grey));
        }

        if (selected_language_index == SharedPrefsUtil.ENGLISH) {
            weight = weightsList.get(position).getName();
        } else {
            weight = weightsList.get(position).getDescription();
            Utils.setTypeFace(holder.title, Constants.KUFI_BOLD_font);
        }

        holder.title.setText(weight);
    }

    @Override
    public int getItemCount() {
        return weightsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_weight)
        public MyTextView title;

        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selected_weight = getAdapterPosition();
                    weightsRecyclerViewClickListener.OnWeightsRecyclerViewClickListener(selected_weight);
                    notifyDataSetChanged();
                }
            });
        }
    }
}