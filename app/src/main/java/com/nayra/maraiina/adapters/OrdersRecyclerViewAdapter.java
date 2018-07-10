package com.nayra.maraiina.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;
import com.nayra.maraiina.interfaces.DeleteOrderListener;
import com.nayra.maraiina.model.OrderDetailsModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter<OrdersRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<OrderDetailsModel> ordersList;
    private DeleteOrderListener listener;

    private Context mContext;

    public OrdersRecyclerViewAdapter(Context context, ArrayList<OrderDetailsModel> orders, DeleteOrderListener listener) {
        this.ordersList = orders;
        this.listener = listener;
        this.mContext = context;
    }

    @NonNull
    @Override
    public OrdersRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_item, parent, false);
        return new OrdersRecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersRecyclerViewAdapter.MyViewHolder holder, int position) {
        OrderDetailsModel orderDetailsModel = ordersList.get(position);

        holder.txtTotalPriceValue.setText(mContext.getResources().getString(R.string.fees, orderDetailsModel.getPrice()));

        String desc = orderDetailsModel.getType() + " ";

        if (orderDetailsModel.isDoYouWantCooking()) {
            desc += orderDetailsModel.getCookingMethod() + " ";
        } else {
            desc += orderDetailsModel.getCuttingMethod() + " " + orderDetailsModel.getPackagingMethod() + " ";
        }

        desc += orderDetailsModel.getWeight();

        holder.txtDescription.setText(desc);

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDesc)
        MyTextView txtDescription;

        /*@BindView(R.id.tv_delivery)
        MyTextView txtDelivery;*/

        @BindView(R.id.tv_delivery_duration)
        MyTextView txtDeliveryDuration;

        @BindView(R.id.tv_total_price)
        MyTextView txtTotalPrice;

        @BindView(R.id.tv_total_price_value)
        MyTextView txtTotalPriceValue;

        @BindView(R.id.imgBtnDelete)
        ImageButton imgBtnDelete;

        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            imgBtnDelete.setOnClickListener(view1 -> {
                int pos = getAdapterPosition();
                listener.deleteOrder(pos);
            });
        }
    }
}
