package com.nayra.maraiina.views;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.OffersRecyclerViewAdapter;
import com.nayra.maraiina.model.OffersModel;
import com.nayra.maraiina.util.ProgressDialogUtil;
import com.nayra.maraiina.viewmodels.GetOffersViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends Fragment {


    @BindView(R.id.rcv_offers)
    RecyclerView recyclerView;

    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        ButterKnife.bind(this, view);
        displayOffers();
        return view;
    }

    private void displayOffers() {
        ProgressDialogUtil.show(getActivity());
        GetOffersViewModel _ViewModel = ViewModelProviders.of(this).get(GetOffersViewModel.class);
        LiveData<ArrayList<OffersModel>> modelOffers = _ViewModel.getOffers();
        modelOffers.observe(this, new Observer<ArrayList<OffersModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<OffersModel> offers) {
                if (offers != null && offers.size() > 0) {
                    ProgressDialogUtil.dismiss();
                    Log.e("nahmed", offers.toString());
                    OffersRecyclerViewAdapter adapter = new OffersRecyclerViewAdapter(offers);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }


}
