package com.nayra.maraiina.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.model.OffersModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersRecyclerViewAdapter extends RecyclerView.Adapter<OffersRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<OffersModel> list;

    public OffersRecyclerViewAdapter(ArrayList<OffersModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public OffersRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_offers, parent, false);
        return new OffersRecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersRecyclerViewAdapter.MyViewHolder holder, int position) {
        OffersModel model = list.get(position);
        holder.txtOffers.setText(model.getName());
        holder.txtDesc.setText(model.getDescription());

        String img_url = model.getImageUrl();
        if (img_url != null && !img_url.isEmpty())
            Picasso.get().load(img_url).error(R.drawable.no_image).into(holder.img);
        else
            Picasso.get().load(R.drawable.no_image).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvOffer)
        public MyTextView txtOffers;

        @BindView(R.id.tvOfferDesc)
        MyTextView txtDesc;

        @BindView(R.id.imgOffer)
        ImageView img;

        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
