package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.model.CategoryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<SubCategoriesRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<CategoryModel> categoriesList;

    private Context context;

    public SubCategoriesRecyclerViewAdapter(Context context, ArrayList<CategoryModel> weightsList) {
        this.categoriesList = weightsList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubCategoriesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new SubCategoriesRecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoriesRecyclerViewAdapter.MyViewHolder holder, int position) {
        CategoryModel categoryModel = categoriesList.get(position);
        holder.title.setText(categoryModel.getName());

        String img_url = categoryModel.getImageUrl();
        if (img_url != null)
            Picasso.get().load(img_url).into(holder.imageView);
        else
            Picasso.get().load(R.drawable.ic_launcher_background).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.subTypeName)
        public MyTextView title;

        @BindView(R.id.subCatImg)
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(view1 -> {
                notifyDataSetChanged();
            });
        }
    }
}
