package com.nayra.maraiina.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nayra.maraiina.model.SuggestionModel;
import com.nayra.maraiina.service.MaraiinaRepository;

public class SendSuggestionViewModel extends ViewModel {
    public SendSuggestionViewModel() {
    }

    public LiveData<Boolean> sendSuggestion(SuggestionModel model) {

        LiveData<Boolean> isSent = MaraiinaRepository.sendSuggestion(model);

        return isSent;

    }
}
