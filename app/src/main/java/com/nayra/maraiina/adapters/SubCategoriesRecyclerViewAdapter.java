package com.nayra.maraiina.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.interfaces.SubCategoryRecyclerViewClickListener;
import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<SubCategoriesRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<CategoryModel> categoriesList;

    private SubCategoryRecyclerViewClickListener listener;

    private String parentCategory = "";
    private int selected_language_index = 0;

    public SubCategoriesRecyclerViewAdapter(String catName, ArrayList<CategoryModel> categoriesList, SubCategoryRecyclerViewClickListener listener) {
        this.categoriesList = categoriesList;
        this.listener = listener;
        parentCategory = catName;

        selected_language_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
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
        if (selected_language_index == SharedPrefsUtil.ARABIC) {
            holder.title.setText(categoryModel.getNameAr());
        } else {
            holder.title.setText(categoryModel.getName());
        }

        String img_url = categoryModel.getImageUrl();
        if (img_url != null && !img_url.isEmpty()) {
            Picasso.get().load(img_url).into(holder.imageView);
        }
        else
            Picasso.get().load(R.drawable.no_image).into(holder.imageView);
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

        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    int subCatId = categoriesList.get(pos).getCategoryID();
                    int catId = Integer.parseInt(categoriesList.get(pos).getParentID());
                    String subType;
                    if (selected_language_index == SharedPrefsUtil.ARABIC) {
                        subType = categoriesList.get(pos).getNameAr();
                    } else {
                        subType = categoriesList.get(pos).getName();
                    }
                    String fullCategoryName = parentCategory + " " + subType;
                    String img_url = categoriesList.get(pos).getImageUrl();
                    listener.OnRecyclerViewClickListener(catId, subCatId, fullCategoryName, img_url);
                }
            });
        }
    }
}
