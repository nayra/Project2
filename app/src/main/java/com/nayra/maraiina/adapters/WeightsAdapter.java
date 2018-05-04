package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nayra.maraiina.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeightsAdapter extends RecyclerView.Adapter<WeightsAdapter.MyViewHolder> {

    private ArrayList<String> weightsList;
    private int selected_weight = 0;

    private Context context;

    public WeightsAdapter(Context context, ArrayList<String> weightsList) {
        this.weightsList = weightsList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_weight, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String weight = weightsList.get(position);
        if (selected_weight == position) {
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.green_very_dark));
        } else {
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.grey));
        }


        holder.title.setText(weight);
    }

    @Override
    public int getItemCount() {
        return weightsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_weight)
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(view1 -> {
                selected_weight = getAdapterPosition();
                notifyDataSetChanged();
            });
        }
    }
}