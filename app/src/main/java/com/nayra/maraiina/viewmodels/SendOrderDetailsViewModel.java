package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.nayra.maraiina.model.CustomerDetails;
import com.nayra.maraiina.model.Details;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.model.OrderResultModel;
import com.nayra.maraiina.model.OrderToBeSent;
import com.nayra.maraiina.service.MaraiinaRepository;
import com.nayra.maraiina.util.SharedPrefsUtil;

import java.util.ArrayList;

public class SendOrderDetailsViewModel extends ViewModel {

    public SendOrderDetailsViewModel() {

    }

    public LiveData<OrderResultModel> postOrdersDetails(ArrayList<OrderDetailsModel> modelList, CustomerDetails customerDetails) {
        LiveData<OrderResultModel> orderResultModelLiveData;
        Log.e("n", modelList.toString());

        int cityId = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_CITY_ID);
        String lang = SharedPrefsUtil.getString(SharedPrefsUtil.SELECTED_LANGUAGE);

       /* JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("Address", customerDetails.getAddress());
            jsonObject.put("AreaID", 1);
            jsonObject.put("cityID", cityId);
            jsonObject.put("PhoneNumber", customerDetails.getPhone());
            jsonObject.put("email", customerDetails.getEmail());
            jsonObject.put("Firstname", customerDetails.getName());
            jsonObject.put("Lang", lang);
            jsonObject.put("Latitude", customerDetails.getLat());
            jsonObject.put("Longitude", customerDetails.getLng());

            JSONArray jsonArray = new JSONArray();
            JSONObject arrObj = new JSONObject();
            int num =  modelList.size();
            for (int i = 0; i < num; i++) {
                arrObj.put("CuttingMethodID", modelList.get(i).getCuttingId());
                arrObj.put("ProductID", modelList.get(i).getProductId());
                arrObj.put("CookingMethodID", modelList.get(i).getCookingId());
                arrObj.put("PackagingMethodID", modelList.get(i).getPackagingId());
                arrObj.put("DestrupMethodID", modelList.get(i).getDistributionMethod());
                arrObj.put("CuttingMethodOther", modelList.get(i).getCuttingMethod());
                jsonArray.put(arrObj);
            }
            jsonObject.put("Details", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("nahmed", e.toString());
        }*/

        OrderToBeSent orderToBeSent = new OrderToBeSent();
        orderToBeSent.setAddress(customerDetails.getAddress());
        orderToBeSent.setAreaID(1);
        orderToBeSent.setCityID(cityId);
        orderToBeSent.setEmail(customerDetails.getEmail());
        orderToBeSent.setFirstname(customerDetails.getName());
        orderToBeSent.setPhoneNumber(customerDetails.getPhone());
        orderToBeSent.setLang(lang);
        ArrayList<Details> details = new ArrayList<>();
        int num = modelList.size();
        for (int i = 0; i < num; i++) {
            Details detail = new Details();
            detail.setProductID(modelList.get(i).getProductId());
            detail.setCuttingMethodID(modelList.get(i).getCuttingId());
            detail.setCuttingMethodID(modelList.get(i).getCookingId());
            detail.setPackagingMethodId(modelList.get(i).getPackagingId());
            detail.setDistributionMethod(modelList.get(i).getDistributionMethod());
            detail.setCuttingMethodOther(modelList.get(i).getCuttingMethod());
            details.add(detail);
        }
        orderToBeSent.setDetails(details);
        orderResultModelLiveData = MaraiinaRepository.postMultipleOrders(orderToBeSent);
        return orderResultModelLiveData;
    }
}
