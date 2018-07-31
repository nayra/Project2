package com.nayra.maraiina.views;

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
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

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

    @BindView(R.id.tvTypeName)
    MyTextView txtTypeName;

    @BindView(R.id.LinearCookingMethod)
    LinearLayout cookingMethodLinearLayout;

    @BindView(R.id.linearCuttingMethods)
    LinearLayout cuttingMethodLinearLayout;

    @BindView(R.id.linearPackagingMethods)
    LinearLayout packagingMethodLinearLayout;


    @BindView(R.id.linearDistributionMethods)
    LinearLayout distributionMethodLinearLayout;

    @BindView(R.id.rbCook)
    RadioButton cookRadioButton;

    @BindView(R.id.rbNoCook)
    RadioButton noCookRadioButton;

    /*@BindView(R.id.tv_delivery_duration)
    MyTextView txtDeliveryDuration;*/

    @BindView(R.id.tv_total_price_value)
    MyTextView txtTotalPriceValue;

    @BindView(R.id.spPackagingMethods)
    Spinner spPackagingMethods;

    @BindView(R.id.spCuttingMethods)
    Spinner spCuttingMethods;

    @BindView(R.id.spCookingMethods)
    Spinner spCookingMethods;

    @BindView(R.id.spDistributionMethods)
    Spinner spDistributionMethods;

    @BindView(R.id.img_selected)
    ImageView imgView;

    @BindView(R.id.etOtherCuttingMethod)
    AppCompatEditText etxtOtherCuttingMethod;

    @BindView(R.id.tvDistributionDesc)
    MyTextView txtDescDistribution;

    private static ArrayList<OrderDetailsModel> myOrdersList;

    private int selected_language_index = 0;
    private LiveData<Result> productOptions;

    private int packagingMethodID = 0;
    private int cuttingMethodID = 0;
    private int cookingMethodID = 0;

    private boolean isCooking = true;
    private String weightOrAge = "";
    private String cookingMethod = "";
    private String cuttingMethod = "";
    private String packagingMethod = "";

    private int category_id = 0, sub_category_id = 0;
    private int price = 0;
    private int cityId = 0;
    private int productId = 0;

    private String typeName = "";
    private String distributionMethod = "";
    private String img_url;
    private String[] distributionsMethods;
    private String[] distributionDescriptionDishes;
    private String[] distributionDescriptionBags;
    private static int REQUEST_CODE = 500;

    private boolean isBack = false;

    //private boolean isCamel = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        selected_language_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);


        ButterKnife.bind(this);

        distributionsMethods = getResources().getStringArray(R.array.meetDistributions);

        distributionDescriptionDishes = getResources().getStringArray(R.array.meetDistributionsDescDishes);
        distributionDescriptionBags = getResources().getStringArray(R.array.meetDistributionsDescBags);

        //distributionMethod = distributionsMethods[0];


        checkCategory();
        setTypeFace();

        initListeners();

        getProductsDetails();

        showOrHideCookingOption();
    }

    private void checkCategory() {
        category_id = getIntent().getIntExtra(Constants.CATEGORY_ID, 0);
        sub_category_id = getIntent().getIntExtra(Constants.SUBCATEGORY_ID, 0);
        typeName = getIntent().getStringExtra(Constants.TYPE_NAME);
        myOrdersList = getIntent().getParcelableArrayListExtra(Constants.ORDERS_LIST);

        txtTypeName.setText(typeName);

        if (category_id == Constants.CAMEL_ID) {
            txtChooseWeight.setText(getResources().getString(R.string.choose_age));
        }

        img_url = getIntent().getStringExtra(Constants.CATEGORY_IMAGE);
        if (img_url != null && !img_url.isEmpty()) {
            Picasso.get().load(img_url).into(imgView);
        }
        else
            Picasso.get().load(R.drawable.no_image).into(imgView);
    }

    private void showOrHideCookingOption() {
        cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);
        if (cityId == SharedPrefsUtil.ABUZABI && category_id != Constants.CAMEL_ID) {
            mainCookingMethodsLinearLayout.setVisibility(View.VISIBLE);
            cookingMethodLinearLayout.setVisibility(View.VISIBLE);
            //txtDeliveryDuration.setText(getResources().getString(R.string.two_hours));
        } else if (category_id == Constants.CAMEL_ID) {
            distributionMethodLinearLayout.setVisibility(View.GONE);
            packagingMethodLinearLayout.setVisibility(View.GONE);
            mainCookingMethodsLinearLayout.setVisibility(View.GONE);
            cookingMethodLinearLayout.setVisibility(View.GONE);
            cuttingMethodLinearLayout.setVisibility(View.VISIBLE);
            cookSegmentedGroup.setVisibility(View.GONE);
            isCooking = false;
        } else {
            isCooking = false;
            mainCookingMethodsLinearLayout.setVisibility(View.GONE);
            cookingMethodLinearLayout.setVisibility(View.GONE);
            packagingMethodLinearLayout.setVisibility(View.VISIBLE);
            cuttingMethodLinearLayout.setVisibility(View.VISIBLE);
            distributionMethodLinearLayout.setVisibility(View.VISIBLE);
            //txtDeliveryDuration.setText(getResources().getString(R.string.four_hours));
        }
    }

    private void getProductsDetails() {
        Utils.setCallerClass(this);
        Utils.setCategoryId(category_id);
        Utils.setSubCategoryId(sub_category_id);
        Utils.setType_name(typeName);
        Utils.setCategory_img(img_url);

        ProductDetailsViewModel productDetailsViewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);
        productOptions = productDetailsViewModel.getMethodsModelLiveData(sub_category_id);
        productOptions.observe(this, new Observer<Result>() {
            @Override
            public void onChanged(@Nullable Result methods) {
                if (methods != null) {
                    if (cityId == SharedPrefsUtil.ABUZABI) {
                        displayCookingMethods(methods.getCookingMethods());
                    }
                    if (category_id != Constants.CAMEL_ID) {
                        displayPackagingMethods(methods.getPackagingMethods());
                        displayDistributionMethods();

                    }
                    displayCuttingMethods(methods.getCuttingMethods());

                    displayWeights(methods.getProducts());
                }

            }
        });
    }

    private void displayWeights(ArrayList<Product> products) {

        WeightsAdapter adapter = new WeightsAdapter(this, products, this);
        weightsRecyclerView.setAdapter(adapter);

        if (products.size() > 0) {
            productId = products.get(0).getProductID();
            price = products.get(0).getPrice();
            if (selected_language_index == SharedPrefsUtil.ENGLISH) {
                weightOrAge = products.get(0).getName();
            } else {
                weightOrAge = products.get(0).getDescription();
            }
            if (cityId == SharedPrefsUtil.ABUZABI && cookRadioButton.isChecked()) {
                price += 150;
            }
            txtTotalPriceValue.setText(getResources().getString(R.string.fees, price));
        }

    }

    private void displayCookingMethods(final ArrayList<CookingMethod> cookingMethods) {
        CookingMethodsSpinnerAdapter adapter = new CookingMethodsSpinnerAdapter(this, R.layout.row_spinner, cookingMethods);
        spCookingMethods.setAdapter(adapter);
        spCookingMethods.setSelection(0);
        spCookingMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cookingMethodID = cookingMethods.get(i).getCookingMethodID();
                if (selected_language_index == SharedPrefsUtil.ARABIC) {
                    cookingMethod = cookingMethods.get(i).getNameAr();
                } else {
                    cookingMethod = cookingMethods.get(i).getName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void displayPackagingMethods(final ArrayList<PackagingMethod> packagingMethods) {
        PackagingMethodsSpinnerAdapter adapter = new PackagingMethodsSpinnerAdapter(this, R.layout.row_spinner, packagingMethods);
        spPackagingMethods.setAdapter(adapter);
        spPackagingMethods.setSelection(0);
        spPackagingMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                packagingMethodID = packagingMethods.get(i).getPackagingMethodID();
                if (selected_language_index == SharedPrefsUtil.ARABIC) {
                    packagingMethod = packagingMethods.get(i).getNameAr();
                } else {
                    packagingMethod = packagingMethods.get(i).getName();
                }

                if (packagingMethod.contains(getResources().getString(R.string.dishes))) {
                    if (distributionMethod.compareTo(distributionsMethods[0]) == 0) {
                        txtDescDistribution.setText(distributionDescriptionDishes[0]);
                    } else {
                        txtDescDistribution.setText(distributionDescriptionDishes[1]);
                    }
                } else {
                    if (distributionMethod.compareTo(distributionsMethods[0]) == 0) {
                        txtDescDistribution.setText(distributionDescriptionBags[0]);
                    } else {
                        txtDescDistribution.setText(distributionDescriptionBags[1]);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void displayCuttingMethods(final ArrayList<CuttingMethod> cuttingMethods) {
        CuttingMethodsSpinnerAdapter adapter = new CuttingMethodsSpinnerAdapter(this, R.layout.row_spinner, cuttingMethods);
        spCuttingMethods.setAdapter(adapter);
        spCuttingMethods.setSelection(0);
        spCuttingMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cuttingMethodID = cuttingMethods.get(i).getCuttingMethodID();
                if (selected_language_index == SharedPrefsUtil.ARABIC) {
                    cuttingMethod = cuttingMethods.get(i).getNameAr();
                } else {
                    cuttingMethod = cuttingMethods.get(i).getName();
                }

                if (cuttingMethodID == 0) {
                    etxtOtherCuttingMethod.setVisibility(View.VISIBLE);
                } else {
                    etxtOtherCuttingMethod.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void displayDistributionMethods() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row_spinner_dark, distributionsMethods);
        spDistributionMethods.setAdapter(adapter);
        spDistributionMethods.setSelection(0);
        spDistributionMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                distributionMethod = distributionsMethods[i];
                if (packagingMethod.contains(getResources().getString(R.string.dishes)))
                    txtDescDistribution.setText(distributionDescriptionDishes[i]);
                else
                    txtDescDistribution.setText(distributionDescriptionBags[i]);
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
                        mainCookingMethodsLinearLayout.setVisibility(View.VISIBLE);
                        cookingMethodLinearLayout.setVisibility(View.VISIBLE);
                        cuttingMethodLinearLayout.setVisibility(View.GONE);
                        packagingMethodLinearLayout.setVisibility(View.GONE);
                        distributionMethodLinearLayout.setVisibility(View.GONE);
                        cookRadioButton.setChecked(true);

                        price += 150;
                        txtTotalPriceValue.setText(getResources().getString(R.string.fees, price));

                        isCooking = true;
                        break;
                    case R.id.rbNoCook:
                        mainCookingMethodsLinearLayout.setVisibility(View.GONE);
                        cookingMethodLinearLayout.setVisibility(View.GONE);
                        cuttingMethodLinearLayout.setVisibility(View.VISIBLE);
                        /*if(isCamel) {
                            packagingMethodLinearLayout.setVisibility(View.GONE);
                        }else{*/
                        packagingMethodLinearLayout.setVisibility(View.VISIBLE);
                        distributionMethodLinearLayout.setVisibility(View.VISIBLE);

                        price -= 150;
                        txtTotalPriceValue.setText(getResources().getString(R.string.fees, price));

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
            // Utils.setTypeFace(txtDelivery, Constants.KUFI_BOLD_font);
            // Utils.setTypeFace(txtDeliveryDuration, Constants.KUFI_REGULAR);
            // Utils.setTypeFace(txtTotalPrice, Constants.KUFI_BOLD_font);
            Utils.setTypeFace(txtTotalPriceValue, Constants.KUFI_REGULAR);
            Utils.setTypeFace(etxtOtherCuttingMethod, Constants.KUFI_REGULAR);
        }
    }

    @OnClick(R.id.bt_add_more_orders)
    void addMoreOrders() {
        Intent result = new Intent();
        OrderDetailsModel model = new OrderDetailsModel();

        if (isCooking) {
            model.setCookingId(cookingMethodID);
        } else {
            model.setCookingId(0);
        }

        model.setCuttingId(cuttingMethodID);
        model.setPackagingId(packagingMethodID);
        model.setDoYouWantCooking(isCooking);
        model.setPrice(price);
        model.setWeight(weightOrAge);
        model.setProductId(productId);
        model.setCookingMethod(cookingMethod);
        model.setCuttingMethod(cuttingMethod);
        model.setPackagingMethod(packagingMethod);
        model.setType(typeName);
        model.setDistributionMethod(distributionMethod);
        model.setImg_url(img_url);

        Log.e("nahmed", model.toString());

        if (myOrdersList != null && !isBack) {
            myOrdersList.add(model);
        }
        result.putParcelableArrayListExtra(Constants.ORDERS_LIST, myOrdersList);
        setResult(RESULT_OK, result);
        finish();
    }
    @OnClick(R.id.btContinue)
    void continueInfo() {
        OrderDetailsModel model = new OrderDetailsModel();

        if (isCooking) {
            model.setCookingId(cookingMethodID);
        } else {
            model.setCookingId(0);
        }
        model.setCookingMethod(cookingMethod);

        model.setCuttingId(cuttingMethodID);
        model.setPackagingId(packagingMethodID);
        model.setCuttingMethod(cuttingMethod);
        model.setPackagingMethod(packagingMethod);
        model.setDistributionMethod(distributionMethod);


        model.setDoYouWantCooking(isCooking);
        model.setPrice(price);
        model.setWeight(weightOrAge);
        model.setProductId(productId);

        model.setType(typeName);
        model.setImg_url(img_url);

        if (myOrdersList != null && !isBack) {
            myOrdersList.add(model);
        }
        Log.e("nahmed", model.toString());
        Intent intent = new Intent(this, CustomerDetailsActivity.class);
        intent.putParcelableArrayListExtra(Constants.ORDERS_LIST, myOrdersList);
        startActivityForResult(intent, REQUEST_CODE);
    }

    showMyOrders() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            isBack = true;
        }
    }

    @Override
    public void OnWeightsRecyclerViewClickListener(int pos) {
        price = productOptions.getValue().getProducts().get(pos).getPrice();
        productId = productOptions.getValue().getProducts().get(pos).getProductID();

        weightOrAge = productOptions.getValue().getProducts().get(pos).getName();
        if (cookRadioButton.isChecked()) {
            price += 150;
        }
        txtTotalPriceValue.setText(getResources().getString(R.string.fees, price));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(getResources().getString(R.string.warning))
                .setMessage(getResources().getString(R.string.are_u_sure))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
