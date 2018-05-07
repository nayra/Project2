package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.CategoriesRecyclerViewAdapter;
import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.viewmodels.GetCategoriesViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {


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

        GetCategoriesViewModel getCategoriesViewModel = ViewModelProviders.of(this).get(GetCategoriesViewModel.class);
        categoryModelArrayList = getCategoriesViewModel.getCategoriesArrayListLiveData();
        categoryModelArrayList.observe(this, categories -> {
            Log.e("nahmed", categories.toString());
            CategoriesRecyclerViewAdapter adapter = new CategoriesRecyclerViewAdapter(getActivity(), categories);
            main_recycler_view.setAdapter(adapter);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
