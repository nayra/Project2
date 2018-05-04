package com.nayra.maraiina.views;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nayra.maraiina.R;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderDetailsActivity extends AppCompatActivity {

    @BindView(R.id.linearWeights)
    LinearLayout weightsLinearLayout;

    private int selectedIndex = 0;
    private ArrayList<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        ButterKnife.bind(this);
        arr = new ArrayList<>();
        arr.add("10/8");
        arr.add("12/10");
        arr.add("14/12");

        displayWeights();
    }

    private void displayWeights() {
        int x = arr.size();
        weightsLinearLayout.removeAllViews();
        for (int i = 0; i < x; i++) {
            String weight = arr.get(i);
            View itemView = LayoutInflater.from(this).inflate(R.layout.row_weight, weightsLinearLayout, false);
            TextView txtWeights = itemView.findViewById(R.id.tv_weight);
            txtWeights.setText(weight);
            if (selectedIndex == i) {
                txtWeights.setTextColor(ContextCompat.getColor(this, R.color.grey_dark));
            } else {
                txtWeights.setTextColor(ContextCompat.getColor(this, R.color.grey));
            }
            itemView.setTag(i);
            itemView.setOnClickListener(view -> {
                int pos = Integer.parseInt(view.getTag().toString());
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
