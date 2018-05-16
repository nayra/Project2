package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.service.MaraiinaRepository;
import com.nayra.maraiina.util.SharedPrefsUtil;

public class SendOrderDetailsViewModel extends ViewModel {

    public SendOrderDetailsViewModel() {

    }

    public LiveData<OrderResultModel> postOrdersDetails(OrderDetailsModel model, String header) {
        LiveData<OrderResultModel> orderResultModelLiveData;
        Log.e("n", model.toString());
        int cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);
        if (model.isDoYouWantCooking()) {
            orderResultModelLiveData = MaraiinaRepository.postCookedOrder(model.getCustomerDetails().getAddress(), cityId,
                    model.getCustomerDetails().getPhone(), model.getCustomerDetails().getEmail(), model.getCustomerDetails().getName()
                    , "", model.getCustomerDetails().getLat(), model.getCustomerDetails().getLng(), model.getCookingId(), model.getProductId(), header);
        } else {
            orderResultModelLiveData = MaraiinaRepository.postUnCookedOrder(model.getCustomerDetails().getAddress(), cityId,
                    model.getCustomerDetails().getPhone(), model.getCustomerDetails().getEmail(), model.getCustomerDetails().getName(),
                    "", model.getCustomerDetails().getLat(), model.getCustomerDetails().getLng(), model.getCuttingId(), model.getPackagingId(), model.getProductId(), header);
        }
        return orderResultModelLiveData;
    }
}
