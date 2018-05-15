package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.service.MaraiinaRepository;
import com.nayra.maraiina.util.SharedPrefsUtil;

public class SendOrderDetailsViewModel extends ViewModel {

    public SendOrderDetailsViewModel() {

    }

    public LiveData<OrderResultModel> postOrdersDetails(OrderDetailsModel model) {
        LiveData<OrderResultModel> orderResultModelLiveData;

        int cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);
        if (model.isDoYouWantCooking()) {
            orderResultModelLiveData = MaraiinaRepository.postCookedOrder(model.getCustomerDetails().getAddress(), cityId,
                    model.getCustomerDetails().getPhone(), model.getCustomerDetails().getEmail(), model.getCustomerDetails().getName()
                    , "", 0.0, 0.0, model.getCookingId(), model.getProductId());
        } else {
            orderResultModelLiveData = MaraiinaRepository.postUnCookedOrder(model.getCustomerDetails().getAddress(), cityId,
                    model.getCustomerDetails().getPhone(), model.getCustomerDetails().getEmail(), model.getCustomerDetails().getName(),
                    "", 0.0, 0.0, model.getCuttingId(), model.getPackagingId(), model.getProductId());
        }
        return orderResultModelLiveData;
    }
}
