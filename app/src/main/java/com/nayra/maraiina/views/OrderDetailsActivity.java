package com.nayra.maraiina.views;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.android.segmented.SegmentedGroup;


public class OrderDetailsActivity extends AppCompatActivity {

    @BindView(R.id.linearWeights)
    LinearLayout weightsLinearLayout;

    @BindView(R.id.tv_choose_weight)
    MyTextView txtChooseWeight;

    @BindView(R.id.tv_cook)
    MyTextView txtCook;

    @BindView(R.id.segmentedCook)
    SegmentedGroup cookSegmentedGroup;

    @BindView(R.id.tvCookingMethod)
    MyTextView txtCookingMethod;

    @BindView(R.id.tvPackagingMethod)
    MyTextView txtPackagingMethod;

    @BindView(R.id.tvCuttingMethod)
    MyTextView txtCuttingMethod;

    @BindView(R.id.LinearCookingMethod)
    LinearLayout cookingMethodLinearLayout;

    @BindView(R.id.linearCuttingMethods)
    LinearLayout cuttingMethodLinearLayout;

    @BindView(R.id.linearPackagingMethods)
    LinearLayout packagingMethodLinearLayout;


    @BindView(R.id.rbCook)
    RadioButton cookRadioButton;

    @BindView(R.id.rbNoCook)
    RadioButton noCookRadioButton;

    @BindView(R.id.tv_delivery)
    MyTextView txtDelivery;

    @BindView(R.id.tv_delivery_duration)
    MyTextView txtDeliveryDuration;

    @BindView(R.id.tv_total_price)
    MyTextView txtTotalPrice;

    @BindView(R.id.tv_total_price_value)
    MyTextView txtTotalPriceValue;

    private int selectedIndex = 0;
    private ArrayList<String> arr;

    private int selected_language_index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        selected_language_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);

        ButterKnife.bind(this);

        setTypeFace();

        arr = new ArrayList<>();
        arr.add("10/8");
        arr.add("12/10");
        arr.add("14/12");

        displayWeights();

        initListeners();
    }

    private void initListeners() {
        cookSegmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbCook:
                        cookingMethodLinearLayout.setVisibility(View.VISIBLE);
                        cuttingMethodLinearLayout.setVisibility(View.GONE);
                        packagingMethodLinearLayout.setVisibility(View.GONE);
                        cookRadioButton.setChecked(true);
                        break;
                    case R.id.rbNoCook:
                        cookingMethodLinearLayout.setVisibility(View.GONE);
                        cuttingMethodLinearLayout.setVisibility(View.VISIBLE);
                        packagingMethodLinearLayout.setVisibility(View.VISIBLE);
                        noCookRadioButton.setChecked(true);
                        break;
                }
            }
        });
    }

    private void setTypeFace() {
        if (selected_language_index == SharedPrefsUtil.ARABIC) {
            Utils.setTypeFace(txtChooseWeight, Constants.KUFI_REGULAR);
            Utils.setTypeFace(txtCook, Constants.KUFI_REGULAR);
            Utils.setTypeFace(txtCookingMethod, Constants.KUFI_REGULAR);
            Utils.setTypeFace(txtCuttingMethod, Constants.KUFI_REGULAR);
            Utils.setTypeFace(txtPackagingMethod, Constants.KUFI_REGULAR);
            Utils.setTypeFace(cookRadioButton, Constants.KUFI_REGULAR);
            Utils.setTypeFace(noCookRadioButton, Constants.KUFI_REGULAR);
            Utils.setTypeFace(txtDelivery, Constants.KUFI_BOLD_font);
            Utils.setTypeFace(txtDeliveryDuration, Constants.KUFI_REGULAR);
            Utils.setTypeFace(txtTotalPrice, Constants.KUFI_BOLD_font);
            Utils.setTypeFace(txtTotalPriceValue, Constants.KUFI_REGULAR);
        }
    }

    private void displayWeights() {
        final int x = arr.size();

        weightsLinearLayout.removeAllViews();
        for (int i = 0; i < x; i++) {
            final String weight = arr.get(i);
            final View itemView = LayoutInflater.from(this).inflate(R.layout.row_weight, weightsLinearLayout, false);
            final MyTextView txtWeights = itemView.findViewById(R.id.tv_weight);
            final MyTextView txtUnit = itemView.findViewById(R.id.tv_weight_unit);

            txtWeights.setText(weight);
            if (selectedIndex == i) {
                txtWeights.setTextColor(ContextCompat.getColor(this, R.color.grey_dark));
                txtUnit.setTextColor(ContextCompat.getColor(this, R.color.grey_dark));

            } else {
                txtWeights.setTextColor(ContextCompat.getColor(this, R.color.grey));
                txtUnit.setTextColor(ContextCompat.getColor(this, R.color.grey));
            }

            Utils.setTypeFace(txtUnit, Constants.MUSEOSANS_2_TTF);

            if (selected_language_index == SharedPrefsUtil.ARABIC) {
                Utils.setTypeFace(txtUnit, Constants.KUFI_BOLD_font);
            }
            itemView.setTag(i);
            itemView.setOnClickListener(view -> {
                final int pos = Integer.parseInt(view.getTag().toString());
                if (selectedIndex != pos) {
                    selectedIndex = pos;
                    displayWeights();
                }
            });
            weightsLinearLayout.addView(itemView);
        }
    }

    @OnClick(R.id.btContinue)
    void continueInfo(View view) {
        Utils.displayNextActivity(this, CustomerDetailsActivity.class);
    }
}
