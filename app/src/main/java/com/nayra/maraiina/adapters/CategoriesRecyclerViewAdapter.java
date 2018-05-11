package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.interfaces.SubCategoryRecyclerViewClickListener;
import com.nayra.maraiina.model.CategoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<CategoryModel> categoriesList;
    private int selected_weight = 0;

    private Context context;

    private SubCategoryRecyclerViewClickListener listener;

    public CategoriesRecyclerViewAdapter(Context context, ArrayList<CategoryModel> weightsList, SubCategoryRecyclerViewClickListener listener) {
        this.categoriesList = weightsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_main, parent, false);
        return new CategoriesRecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesRecyclerViewAdapter.MyViewHolder holder, int position) {
        CategoryModel categoryModel = categoriesList.get(position);
        holder.title.setText(categoryModel.getName());

        SubCategoriesRecyclerViewAdapter adapter = new SubCategoriesRecyclerViewAdapter(context, categoryModel.getSubCategory(), listener);
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTypeName)
        public MyTextView title;

        @BindView(R.id.rcv_cat)
        RecyclerView recyclerView;

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
