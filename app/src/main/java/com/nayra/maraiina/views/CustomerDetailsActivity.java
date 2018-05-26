package com.nayra.maraiina.views;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyButton;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.model.CustomerDetails;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

    @BindView(R.id.tv_delivery_duration)
    MyTextView txtDeliveryDuration;

    @BindView(R.id.tv_total_price_value)
    MyTextView txtTotalPriceValue;

    @BindView(R.id.btContinue)
    MyButton continueMyButton;

    @BindView(R.id.img_selected_customer)
    ImageView imgView;

    @BindView(R.id.tvTypeName)
    MyTextView txtTypeName;

    private OrderDetailsModel orderDetailsModel;
    private final int map_request_code = 100;

    private double lat = Constants.DEF_LAT, lng = Constants.DEF_LNG;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == map_request_code && resultCode == RESULT_OK && data != null) {
            lat = data.getDoubleExtra(Constants.LATITUDE, Constants.DEF_LAT);
            lng = data.getDoubleExtra(Constants.LONGITUDE, Constants.DEF_LNG);

            /*Address address = getAddress(lat, lng);

            if (address != null) {
                int mMaxxAddressLines = address.getMaxAddressLineIndex();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i <= mMaxxAddressLines; i++) {
                    stringBuilder.append(address.getAddressLine(i));
                }
                etxtAddress.setText(stringBuilder);
            }*/
        }
    }

    public Address getAddress(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        String lang = SharedPrefsUtil.getString(SharedPrefsUtil.SELECTED_LANGUAGE);
        geocoder = new Geocoder(this, new Locale(lang));

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            if (addresses != null && addresses.size() > 0)
                return addresses.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setPriceAndDuration() {
        txtTotalPriceValue.setText(getResources().getString(R.string.fees, orderDetailsModel.getPrice()));
        txtTypeName.setText(orderDetailsModel.getType());
        int cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);
        if (cityId == SharedPrefsUtil.ABUZABI) {
            txtDeliveryDuration.setText(getResources().getString(R.string.two_hours));
        } else {
            txtDeliveryDuration.setText(getResources().getString(R.string.four_hours));
        }

        String img_url = orderDetailsModel.getImg_url();

        if (img_url != null && !img_url.isEmpty())
            Picasso.get().load(img_url).into(imgView);
        else
            Picasso.get().load(R.drawable.no_image).into(imgView);
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

            Utils.setTypeFace(txtDeliveryDuration, Constants.KUFI_REGULAR);
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

        if (lat != 0.0 && lng != 0.0 && address.isEmpty()) {
            address = String.valueOf(lat) + "," + String.valueOf(lng);
        }
        if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty() && (email.isEmpty() || Utils.isValidEmail(email))) {
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setName(name);
            customerDetails.setAddress(address);
            customerDetails.setEmail(email);
            customerDetails.setPhone(phone);
            customerDetails.setLat(lat);
            customerDetails.setLng(lng);

            Log.e("nahmed", customerDetails.toString());

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

            if (!email.isEmpty() && !Utils.isValidEmail(email)) {
                etxtEmail.setError(getResources().getString(R.string.not_valid_email));
            }
        }
    }

    @OnClick(R.id.btShowMap)
    public void showMap() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivityForResult(intent, map_request_code);
    }
}
