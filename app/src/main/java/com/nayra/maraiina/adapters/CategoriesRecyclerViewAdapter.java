package com.nayra.maraiina.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.interfaces.SubCategoryRecyclerViewClickListener;
import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.util.SharedPrefsUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<CategoryModel> categoriesList;
    private int selected_lang_index = 0;

    private SubCategoryRecyclerViewClickListener listener;

    public CategoriesRecyclerViewAdapter(ArrayList<CategoryModel> weightsList, SubCategoryRecyclerViewClickListener listener) {
        this.categoriesList = weightsList;
        this.listener = listener;

        selected_lang_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
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
        String category;
        if (selected_lang_index == SharedPrefsUtil.ARABIC) {
            category = categoryModel.getNameAr();
        } else {
            category = categoryModel.getName();
        }
        holder.title.setText(category);

        SubCategoriesRecyclerViewAdapter adapter = new SubCategoriesRecyclerViewAdapter(category, categoryModel.getSubCategory(), listener);
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

        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    notifyDataSetChanged();
                }
            });
        }
    }
}
