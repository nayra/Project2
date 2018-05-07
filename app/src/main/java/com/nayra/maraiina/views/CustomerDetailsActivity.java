package com.nayra.maraiina.views;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        setTypeFace();
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
        }
    }

    @OnClick(R.id.btContinue)
    void continueDetails() {
        String name = etxtName.getText().toString();
        String phone = etxtPhone.getText().toString();
        String address = etxtAddress.getText().toString();
        String email = etxtEmail.getText().toString();

        if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {

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
