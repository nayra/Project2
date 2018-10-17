package com.nayra.maraiina.views;

import android.app.Dialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.OrdersRecyclerViewAdapter;
import com.nayra.maraiina.custom_views.MyButton;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.interfaces.DeleteOrderListener;
import com.nayra.maraiina.model.CustomerDetails;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.util.ProgressDialogUtil;
import com.nayra.maraiina.util.Utils;
import com.nayra.maraiina.viewmodels.SendOrderDetailsViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewOrderDetailsActivity extends AppCompatActivity implements DeleteOrderListener {

    private ArrayList<OrderDetailsModel> orderDetailsModel;

    private CustomerDetails customerDetails;

    @BindView(R.id.tvNameTitle)
    MyTextView txtNameTitle;

    @BindView(R.id.tvPhoneTitle)
    MyTextView txtPhoneTitle;

    @BindView(R.id.tvAddressTitle)
    MyTextView txtAddressTitle;

    @BindView(R.id.tvEmailTitle)
    MyTextView txtEmailTitle;

    @BindView(R.id.tvNameValue)
    MyTextView txtNameValue;

    @BindView(R.id.tvPhoneValue)
    MyTextView txtPhoneValue;

    @BindView(R.id.tvAddressValue)
    MyTextView txtAddressValue;

    @BindView(R.id.tvEmailalue)
    MyTextView txtEmailValue;

    @BindView(R.id.rcv_orders)
    RecyclerView ordersRecyclerView;
    private OrdersRecyclerViewAdapter adapter;

    /*@BindView(R.id.tvDesc)
    MyTextView txtDescription;

    *//*@BindView(R.id.tv_delivery)
    MyTextView txtDelivery;*//*

    @BindView(R.id.tv_delivery_duration)
    MyTextView txtDeliveryDuration;

    @BindView(R.id.tv_total_price)
    MyTextView txtTotalPrice;

    @BindView(R.id.tv_total_price_value)
    MyTextView txtTotalPriceValue;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_order_details);

        ButterKnife.bind(this);

        orderDetailsModel = getIntent().getParcelableArrayListExtra(Constants.ORDERS_LIST);
        customerDetails = getIntent().getParcelableExtra(Constants.CUSTOMER_DETAILS);

        setTypeFace();

        displayData();
    }

    private void displayData() {
        txtNameValue.setText(customerDetails.getName());
        txtEmailValue.setText(customerDetails.getEmail());
        txtAddressValue.setText(customerDetails.getAddress());
        txtPhoneValue.setText(customerDetails.getPhone());

        adapter = new OrdersRecyclerViewAdapter(this, orderDetailsModel, this);
        ordersRecyclerView.setAdapter(adapter);

        //txtTotalPriceValue.setText(getResources().getString(R.string.fees, orderDetailsModel.getPrice()));

        /*int cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);
        if (cityId == SharedPrefsUtil.ABUZABI) {
            txtDeliveryDuration.setText(getResources().getString(R.string.two_hours));
        } else {
            txtDeliveryDuration.setText(getResources().getString(R.string.four_hours));
        }*/

        /*String desc = orderDetailsModel.getType() + " ";

        if (orderDetailsModel.isDoYouWantCooking()) {
            desc += orderDetailsModel.getCookingMethod() + " ";
        } else {
            desc += orderDetailsModel.getCuttingMethod() + " " + orderDetailsModel.getPackagingMethod() + " ";
        }

        desc += orderDetailsModel.getWeight();

        txtDescription.setText(desc);*/
    }

    private void setTypeFace() {
        Utils.setTypeFace(txtNameTitle, Constants.KUFI_REGULAR);
        Utils.setTypeFace(txtPhoneTitle, Constants.KUFI_REGULAR);
        Utils.setTypeFace(txtEmailTitle, Constants.KUFI_REGULAR);
        Utils.setTypeFace(txtAddressTitle, Constants.KUFI_REGULAR);

        Utils.setTypeFace(txtNameValue, Constants.KUFI_BOLD_font);
        Utils.setTypeFace(txtPhoneValue, Constants.KUFI_BOLD_font);
        Utils.setTypeFace(txtEmailValue, Constants.KUFI_BOLD_font);
        Utils.setTypeFace(txtAddressValue, Constants.KUFI_BOLD_font);
/*
        Utils.setTypeFace(txtDescription, Constants.KUFI_BOLD_font);

        //Utils.setTypeFace(txtDelivery, Constants.KUFI_BOLD_font);
        Utils.setTypeFace(txtDeliveryDuration, Constants.KUFI_REGULAR);
        Utils.setTypeFace(txtTotalPrice, Constants.KUFI_BOLD_font);
        Utils.setTypeFace(txtTotalPriceValue, Constants.KUFI_REGULAR);*/
    }

    private void showConfirmationMessage(String refId) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.confirmation_layout);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        MyButton okMyButton = (MyButton) dialog.findViewById(R.id.btOk);
        okMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewOrderDetailsActivity.this, ReferenceNumberActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra(Constants.REF_NO, refId);
                startActivity(intent);
                finish();
            }
        });


        dialog.show();
    }

    @OnClick(R.id.btConfirmOrder)
    void confirmOrder() {
        if (orderDetailsModel != null && orderDetailsModel.size() > 0) {
            ProgressDialogUtil.show(this);
            Utils.setCallerClass(this);
            SendOrderDetailsViewModel _viewModel = ViewModelProviders.of(this).get(SendOrderDetailsViewModel.class);
            LiveData<OrderResultModel> orderResultModelLiveData = _viewModel.postOrdersDetails(orderDetailsModel, customerDetails);
            orderResultModelLiveData.observe(this, new Observer<OrderResultModel>() {
                @Override
                public void onChanged(@Nullable OrderResultModel orderResultModel) {
                    if (orderResultModel != null) {
                        ProgressDialogUtil.dismiss();
                        Log.e("nahmed", orderResultModel.toString());

                        if (orderResultModel.getError_msg() == null || orderResultModel.getError_msg().isEmpty()) {
                            showConfirmationMessage(orderResultModel.getResult());
                        } else {
                            Toast.makeText(ReviewOrderDetailsActivity.this, orderResultModel.getError_msg(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        } else {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle(getResources().getString(R.string.error))
                    .setMessage(getResources().getString(R.string.no_selected_orders))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Utils.displayNextActivityFinish(ReviewOrderDetailsActivity.this, ChooseCountryActivity.class);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.ic_error)
                    .show();
        }
    }

    @Override
    public void deleteOrder(int pos) {
        if (orderDetailsModel != null) {
            if (orderDetailsModel.size() > pos) {
                orderDetailsModel.remove(pos);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
