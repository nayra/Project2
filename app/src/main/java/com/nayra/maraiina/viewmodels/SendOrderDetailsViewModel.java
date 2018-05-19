package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.service.MaraiinaRepository;

public class SendOrderDetailsViewModel extends ViewModel {

    public SendOrderDetailsViewModel() {

    }

    public LiveData<OrderResultModel> postOrdersDetails(OrderDetailsModel model) {
        LiveData<OrderResultModel> orderResultModelLiveData;
        Log.e("n", model.toString());

        orderResultModelLiveData = MaraiinaRepository.postOrder(model);

        return orderResultModelLiveData;
    }
}
