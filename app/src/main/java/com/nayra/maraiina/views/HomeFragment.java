package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.CategoriesRecyclerViewAdapter;
import com.nayra.maraiina.interfaces.SubCategoryRecyclerViewClickListener;
import com.nayra.maraiina.model.CategoryModel;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.util.ProgressDialogUtil;
import com.nayra.maraiina.util.Utils;
import com.nayra.maraiina.viewmodels.GetCategoriesViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment implements SubCategoryRecyclerViewClickListener {


    private static final int REQUEST = 500;
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rcv_main)
    RecyclerView main_recycler_view;

    private LiveData<ArrayList<CategoryModel>> categoryModelArrayList;
    private ArrayList<OrderDetailsModel> orders;

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

        Utils.setCallerClass(getActivity());
        Utils.setMenu_item_index(1);

        GetCategoriesViewModel getCategoriesViewModel = ViewModelProviders.of(this).get(GetCategoriesViewModel.class);
        categoryModelArrayList = getCategoriesViewModel.getCategoriesArrayListLiveData();
        categoryModelArrayList.observe(this, new Observer<ArrayList<CategoryModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CategoryModel> categories) {
                if (categories != null && categories.size() > 0) {
                    ProgressDialogUtil.dismiss();
                    Log.e("nahmed", categories.toString());
                    CategoriesRecyclerViewAdapter adapter = new CategoriesRecyclerViewAdapter(categories, HomeFragment.this);
                    main_recycler_view.setAdapter(adapter);
                }
            }
        });
    }

    public void setOrders(ArrayList<OrderDetailsModel> orders) {
        this.orders = orders;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && data != null) {
            orders = data.getParcelableArrayListExtra(Constants.ORDERS_LIST);
            mListener.onBackFromMyOrders(orders);
        }
    }

    @OnClick(R.id.bt_current_orders)
    void showMyOrders() {
        Intent intent = new Intent(getActivity(), MyOrdersActivity.class);
        intent.putParcelableArrayListExtra(Constants.ORDERS_LIST, orders);
        startActivityForResult(intent, REQUEST);
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
    public void OnRecyclerViewClickListener(int catId, int subCatId, String type, String img_url) {
        mListener.onFragmentInteraction(catId, subCatId, type, img_url);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int catId, int subCat, String type, String img_url);

        void onBackFromMyOrders(ArrayList<OrderDetailsModel> models);
    }
}
