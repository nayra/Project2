package com.nayra.maraiina.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferenceNumberActivity extends AppCompatActivity {

    @BindView(R.id.textView2)
    MyTextView txtThankyou;

    @BindView(R.id.textView3)
    MyTextView txtYourRefNo;

    @BindView(R.id.textView4)
    MyTextView txtRefNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference_number);

        ButterKnife.bind(this);

        setTypeFace();

        String refNo = getIntent().getStringExtra(Constants.REF_NO);
        txtRefNo.setText(refNo);
    }


    private void setTypeFace() {
        Utils.setTypeFace(txtThankyou, Constants.KUFI_REGULAR);
        Utils.setTypeFace(txtYourRefNo, Constants.KUFI_REGULAR);

        Utils.setTypeFace(txtRefNo, Constants.KUFI_BOLD_font);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utils.displayNextActivityFinish(this, ChooseCountryActivity.class);
    }
}
