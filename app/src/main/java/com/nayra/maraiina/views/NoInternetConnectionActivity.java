package com.nayra.maraiina.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.util.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoInternetConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_try_again)
    public void tryAgain() {
        Intent intent = new Intent(this, Utils.getCurrentActivity().getClass());
        if (Utils.getCurrentActivity() instanceof MainActivity) {
            intent.putExtra(Constants.MENU_ITEM_NUMBER, Utils.getMenu_item_index());
        } else {
            intent.putExtra(Constants.CATEGORY_ID, Utils.getCategoryId());
            intent.putExtra(Constants.SUBCATEGORY_ID, Utils.getSubCategoryId());
            intent.putExtra(Constants.TYPE_NAME, Utils.getType_name());
            intent.putExtra(Constants.CATEGORY_IMAGE, Utils.getCategory_img());
        }
        startActivity(intent);
        finish();
    }
}
