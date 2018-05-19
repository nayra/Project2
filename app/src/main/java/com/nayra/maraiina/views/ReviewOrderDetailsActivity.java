package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.util.ProgressDialogUtil;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;
import com.nayra.maraiina.viewmodels.SendOrderDetailsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewOrderDetailsActivity extends AppCompatActivity {

    private OrderDetailsModel orderDetailsModel;

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

    @BindView(R.id.tvDesc)
    MyTextView txtDescription;

    @BindView(R.id.tv_delivery)
    MyTextView txtDelivery;

    @BindView(R.id.tv_delivery_duration)
    MyTextView txtDeliveryDuration;

    @BindView(R.id.tv_total_price)
    MyTextView txtTotalPrice;

    @BindView(R.id.tv_total_price_value)
    MyTextView txtTotalPriceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_order_details);

        ButterKnife.bind(this);

        orderDetailsModel = getIntent().getParcelableExtra(Constants.ORDER_DETAILS);

        setTypeFace();

        displayData();
    }

    private void displayData() {
        txtNameValue.setText(orderDetailsModel.getCustomerDetails().getName());
        txtEmailValue.setText(orderDetailsModel.getCustomerDetails().getEmail());
        txtAddressValue.setText(orderDetailsModel.getCustomerDetails().getAddress());
        txtPhoneValue.setText(orderDetailsModel.getCustomerDetails().getPhone());

        txtTotalPriceValue.setText(String.valueOf(orderDetailsModel.getPrice()));

        int cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);
        if (cityId == SharedPrefsUtil.ABUZABI) {
            txtDeliveryDuration.setText(getResources().getString(R.string.two_hours));
        } else {
            txtDeliveryDuration.setText(getResources().getString(R.string.four_hours));
        }

        String desc = orderDetailsModel.getType() + " ";

        if (orderDetailsModel.isDoYouWantCooking()) {
            desc += orderDetailsModel.getCookingMethod() + " ";
        } else {
            desc += orderDetailsModel.getCuttingMethod() + " " + orderDetailsModel.getPackagingMethod() + " ";
        }

        desc += orderDetailsModel.getWeight();

        txtDescription.setText(desc);
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

        Utils.setTypeFace(txtDescription, Constants.KUFI_BOLD_font);

        Utils.setTypeFace(txtDelivery, Constants.KUFI_BOLD_font);
        Utils.setTypeFace(txtDeliveryDuration, Constants.KUFI_REGULAR);
        Utils.setTypeFace(txtTotalPrice, Constants.KUFI_BOLD_font);
        Utils.setTypeFace(txtTotalPriceValue, Constants.KUFI_REGULAR);
    }

    @OnClick(R.id.btConfirmOrder)
    void confirmOrder() {
        ProgressDialogUtil.show(this);
        SendOrderDetailsViewModel _viewModel = ViewModelProviders.of(this).get(SendOrderDetailsViewModel.class);
        LiveData<OrderResultModel> orderResultModelLiveData = _viewModel.postOrdersDetails(orderDetailsModel);
        orderResultModelLiveData.observe(this, new Observer<OrderResultModel>() {
            @Override
            public void onChanged(@Nullable OrderResultModel orderResultModel) {
                if (orderResultModel != null) {
                    ProgressDialogUtil.dismiss();
                    Log.e("nahmed", orderResultModel.toString());

                    if (orderResultModel.getError_msg() == null || orderResultModel.getError_msg().isEmpty()) {
                        Intent intent = new Intent(ReviewOrderDetailsActivity.this, ReferenceNumberActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra(Constants.REF_NO, orderResultModel.getResult());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ReviewOrderDetailsActivity.this, orderResultModel.getError_msg(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
