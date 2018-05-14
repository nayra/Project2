package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.CookingMethodsSpinnerAdapter;
import com.nayra.maraiina.adapters.CuttingMethodsSpinnerAdapter;
import com.nayra.maraiina.adapters.PackagingMethodsSpinnerAdapter;
import com.nayra.maraiina.adapters.WeightsAdapter;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.interfaces.WeightsRecyclerViewClickListener;
import com.nayra.maraiina.model.CookingMethod;
import com.nayra.maraiina.model.CuttingMethod;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.model.PackagingMethod;
import com.nayra.maraiina.model.Product;
import com.nayra.maraiina.model.Result;
import com.nayra.maraiina.util.SharedPrefsUtil;
import com.nayra.maraiina.util.Utils;
import com.nayra.maraiina.viewmodels.ProductDetailsViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.android.segmented.SegmentedGroup;


public class OrderDetailsActivity extends AppCompatActivity implements WeightsRecyclerViewClickListener {

    @BindView(R.id.linearMainCookingMethods)
    LinearLayout mainCookingMethodsLinearLayout;

    @BindView(R.id.rcvWeights)
    RecyclerView weightsRecyclerView;

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

    @BindView(R.id.spPackagingMethods)
    Spinner spPackagingMethods;

    @BindView(R.id.spCuttingMethods)
    Spinner spCuttingMethods;

    @BindView(R.id.spCookingMethods)
    Spinner spCookingMethods;

    private int selectedIndex = 0;

    private int selected_language_index = 0;
    private LiveData<Result> productOptions;

    private int packagingMethodID = 0;
    private int cuttingMethodID = 0;
    private int cookingMethodID = 0;

    private boolean isCooking = true;
    private String weight = "";

    private int category_id = 0, sub_category_id = 0;
    private int price = 0;
    private int cityId = 0;

    //private boolean isCamel = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        selected_language_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);


        ButterKnife.bind(this);

        checkCategory();
        setTypeFace();

        initListeners();

        getProductsDetails();

        showOrHideCookingOption();
    }

    private void checkCategory() {
        category_id = getIntent().getIntExtra(Constants.CATEGORY_ID, 0);
        sub_category_id = getIntent().getIntExtra(Constants.SUBCATEGORY_ID, 0);

        if (category_id == Constants.CAMEL_ID) {
            txtChooseWeight.setText(getResources().getString(R.string.choose_age));
        }
    }

    private void showOrHideCookingOption() {
        cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);
        if (cityId == SharedPrefsUtil.ABUZABI) {
            mainCookingMethodsLinearLayout.setVisibility(View.VISIBLE);
        } else {
            mainCookingMethodsLinearLayout.setVisibility(View.GONE);
            packagingMethodLinearLayout.setVisibility(View.VISIBLE);
            cuttingMethodLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    private void getProductsDetails() {
        ProductDetailsViewModel productDetailsViewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);
        productOptions = productDetailsViewModel.getMethodsModelLiveData(sub_category_id);
        productOptions.observe(this, methods -> {
            Log.e("nahmed", methods.toString());

            displayCookingMethods(methods.getCookingMethods());
            displayPackagingMethods(methods.getPackagingMethods());
            displayCuttingMethods(methods.getCuttingMethods());

            displayWeights(methods.getProducts());
        });
    }

    private void displayWeights(ArrayList<Product> products) {

        WeightsAdapter adapter = new WeightsAdapter(this, products, this);
        weightsRecyclerView.setAdapter(adapter);

        if (products.size() > 0) {
            price = products.get(0).getPrice();
            if (cityId == SharedPrefsUtil.ABUZABI && cookRadioButton.isChecked()) {
                price += 150;
            }
            txtTotalPriceValue.setText(String.valueOf(price));
        }

    }

    private void displayCookingMethods(ArrayList<CookingMethod> cookingMethods) {
        CookingMethodsSpinnerAdapter adapter = new CookingMethodsSpinnerAdapter(this, R.layout.row_spinner, cookingMethods);
        spCookingMethods.setAdapter(adapter);
        spCookingMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cookingMethodID = cookingMethods.get(i).getCookingMethodID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void displayPackagingMethods(ArrayList<PackagingMethod> packagingMethods) {
        PackagingMethodsSpinnerAdapter adapter = new PackagingMethodsSpinnerAdapter(this, R.layout.row_spinner, packagingMethods);
        spPackagingMethods.setAdapter(adapter);
        spPackagingMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                packagingMethodID = packagingMethods.get(i).getPackagingMethodID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void displayCuttingMethods(ArrayList<CuttingMethod> cuttingMethods) {
        CuttingMethodsSpinnerAdapter adapter = new CuttingMethodsSpinnerAdapter(this, R.layout.row_spinner, cuttingMethods);
        spCuttingMethods.setAdapter(adapter);
        spCuttingMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cuttingMethodID = cuttingMethods.get(i).getCuttingMethodID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                        txtTotalPriceValue.setText(String.valueOf(price));

                        price += 150;
                        txtTotalPriceValue.setText(String.valueOf(price));

                        isCooking = true;
                        break;
                    case R.id.rbNoCook:
                        cookingMethodLinearLayout.setVisibility(View.GONE);
                        cuttingMethodLinearLayout.setVisibility(View.VISIBLE);
                        /*if(isCamel) {
                            packagingMethodLinearLayout.setVisibility(View.GONE);
                        }else{*/
                        packagingMethodLinearLayout.setVisibility(View.VISIBLE);

                        price -= 150;
                        txtTotalPriceValue.setText(String.valueOf(price));

                        //}
                        noCookRadioButton.setChecked(true);
                        isCooking = false;
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

    @OnClick(R.id.btContinue)
    void continueInfo() {
        OrderDetailsModel model = new OrderDetailsModel();
        model.setCookingId(cookingMethodID);
        model.setCuttingId(cuttingMethodID);
        model.setPackagingId(packagingMethodID);
        model.setDoYouWantCooking(isCooking);
        model.setPrice(price);
        model.setWeight(weight);
        Log.e("nahmed", model.toString());
        Intent intent = new Intent(this, CustomerDetailsActivity.class);
        intent.putExtra(Constants.ORDER_DETAILS, model);
        startActivity(intent);
    }

    @Override
    public void OnWeightsRecyclerViewClickListener(int pos) {
        price = productOptions.getValue().getProducts().get(pos).getPrice();
        if (cookRadioButton.isChecked()) {
            price += 150;
        }
        txtTotalPriceValue.setText(String.valueOf(price));
    }

}
