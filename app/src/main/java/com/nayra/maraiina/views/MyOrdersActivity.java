package com.nayra.maraiina.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.adapters.OrdersRecyclerViewAdapter;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.interfaces.DeleteOrderListener;
import com.nayra.maraiina.model.OrderDetailsModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyOrdersActivity extends AppCompatActivity implements DeleteOrderListener {

    @BindView(R.id.rcvMyOrders)
    RecyclerView recyclerView;

    @BindView(R.id.tvNoOrders)
    MyTextView txtNoOrders;

    private ArrayList<OrderDetailsModel> orderDetailsModel;
    private OrdersRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        orderDetailsModel = intent.getParcelableArrayListExtra(Constants.ORDERS_LIST);

        if (orderDetailsModel != null && orderDetailsModel.size() > 0) {
            adapter = new OrdersRecyclerViewAdapter(this, orderDetailsModel, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            txtNoOrders.setVisibility(View.INVISIBLE);
        } else {
            recyclerView.setVisibility(View.INVISIBLE);
            txtNoOrders.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.btContinue)
    void onContinueClick() {
        if (orderDetailsModel == null || orderDetailsModel.size() == 0) {
            Toast.makeText(this, R.string.no_selected_orders, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, CustomerDetailsActivity.class);
            intent.putParcelableArrayListExtra(Constants.ORDERS_LIST, orderDetailsModel);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(Constants.ORDERS_LIST, orderDetailsModel);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
