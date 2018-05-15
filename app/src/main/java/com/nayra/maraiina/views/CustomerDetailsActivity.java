package com.nayra.maraiina.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyButton;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.model.CustomerDetails;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerDetailsActivity extends AppCompatActivity {

    @BindView(R.id.til_name)
    TextInputLayout nameTextInputLayout;

    @BindView(R.id.til_Email)
    TextInputLayout emailTextInputLayout;

    @BindView(R.id.til_Mobile)
    TextInputLayout phoneTextInputLayout;

    @BindView(R.id.etName)
    TextInputEditText etxtName;

    @BindView(R.id.etEmail)
    TextInputEditText etxtEmail;

    @BindView(R.id.etMobile)
    TextInputEditText etxtPhone;

    @BindView(R.id.etAddress)
    TextInputEditText etxtAddress;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvDeliveryDetails)
    MyTextView txtDeliveryDetails;

    @BindView(R.id.tv_delivery)
    MyTextView txtDelivery;

    @BindView(R.id.tv_delivery_duration)
    MyTextView txtDeliveryDuration;

    @BindView(R.id.tv_total_price)
    MyTextView txtTotalPrice;

    @BindView(R.id.tv_total_price_value)
    MyTextView txtTotalPriceValue;

    @BindView(R.id.btContinue)
    MyButton continueMyButton;

    private OrderDetailsModel orderDetailsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        setTypeFace();

        Intent intent = getIntent();
        orderDetailsModel = intent.getParcelableExtra(Constants.ORDER_DETAILS);

        setPriceAndDuration();
    }

    private void setPriceAndDuration() {
        txtTotalPriceValue.setText(String.valueOf(orderDetailsModel.getPrice()));
    }

    private void setTypeFace() {
        int selected_language = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
        if (selected_language == SharedPrefsUtil.ARABIC) {
            Utils.setTypeFace(nameTextInputLayout, Constants.KUFI_REGULAR);
            Utils.setTypeFace(emailTextInputLayout, Constants.KUFI_REGULAR);
            Utils.setTypeFace(phoneTextInputLayout, Constants.KUFI_REGULAR);

            Utils.setTypeFace(etxtName, Constants.KUFI_BOLD_font);
            Utils.setTypeFace(etxtPhone, Constants.KUFI_BOLD_font);
            Utils.setTypeFace(etxtAddress, Constants.KUFI_BOLD_font);
            Utils.setTypeFace(etxtEmail, Constants.KUFI_BOLD_font);

            Utils.setTypeFace(txtDeliveryDetails, Constants.KUFI_REGULAR);

            Utils.setTypeFace(txtDelivery, Constants.KUFI_BOLD_font);
            Utils.setTypeFace(txtDeliveryDuration, Constants.KUFI_REGULAR);
            Utils.setTypeFace(txtTotalPrice, Constants.KUFI_BOLD_font);
            Utils.setTypeFace(txtTotalPriceValue, Constants.KUFI_REGULAR);

            //  Utils.setTypeFace(continueMyButton, Constants.KUFI_REGULAR);
        }
    }

    @OnClick(R.id.btContinue)
    void continueDetails() {
        String name = etxtName.getText().toString();
        String phone = etxtPhone.getText().toString();
        String address = etxtAddress.getText().toString();
        String email = etxtEmail.getText().toString();

        if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setName(name);
            customerDetails.setAddress(address);
            customerDetails.setEmail(email);
            customerDetails.setPhone(phone);

            orderDetailsModel.setCustomerDetails(customerDetails);

            Intent intent = new Intent(this, ReviewOrderDetailsActivity.class);
            intent.putExtra(Constants.ORDER_DETAILS, orderDetailsModel);
            startActivity(intent);
        } else {
            if (name.isEmpty()) {
                etxtName.setError(getResources().getString(R.string.mandatory));
            }
            if (phone.isEmpty()) {
                etxtPhone.setError(getResources().getString(R.string.mandatory));
            }
            if (address.isEmpty()) {
                etxtAddress.setError(getResources().getString(R.string.mandatory));
            }
        }
    }

}
