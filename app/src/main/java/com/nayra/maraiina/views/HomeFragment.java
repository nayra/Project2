package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.CategoriesRecyclerViewAdapter;
import com.nayra.maraiina.interfaces.SubCategoryRecyclerViewClickListener;
import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.util.ProgressDialogUtil;
import com.nayra.maraiina.viewmodels.GetCategoriesViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements SubCategoryRecyclerViewClickListener {


    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rcv_main)
    RecyclerView main_recycler_view;

    private LiveData<ArrayList<CategoryModel>> categoryModelArrayList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        displayMainRecyclerView();
        return view;
    }

    private void displayMainRecyclerView() {


        ProgressDialogUtil.show(getActivity());
        GetCategoriesViewModel getCategoriesViewModel = ViewModelProviders.of(this).get(GetCategoriesViewModel.class);
        categoryModelArrayList = getCategoriesViewModel.getCategoriesArrayListLiveData();
        categoryModelArrayList.observe(this, categories -> {

            if (categories != null && categories.size() > 0) {
                ProgressDialogUtil.dismiss();
                Log.e("nahmed", categories.toString());
                CategoriesRecyclerViewAdapter adapter = new CategoriesRecyclerViewAdapter(categories, this);
                main_recycler_view.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnRecyclerViewClickListener(int catId, int subCatId, String type) {
        Toast.makeText(getActivity(), "" + subCatId, Toast.LENGTH_LONG).show();
        mListener.onFragmentInteraction(catId, subCatId, type);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int catId, int subCat, String type);
    }
}
