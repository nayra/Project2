package com.nayra.maraiina.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nayra.maraiina.Constants;
import com.nayra.maraiina.R;
import com.nayra.maraiina.model.SuggestionModel;
import com.nayra.maraiina.util.ProgressDialogUtil;
import com.nayra.maraiina.util.Utils;
import com.nayra.maraiina.viewmodels.SendSuggestionViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuggestionsFragment extends Fragment {

    @BindView(R.id.til_subject)
    TextInputLayout subjectHint;

    @BindView(R.id.til_Email)
    TextInputLayout emailHint;

    @BindView(R.id.til_Name_hint)
    TextInputLayout nameHint;

    @BindView(R.id.til_Phone)
    TextInputLayout phoneHint;

    @BindView(R.id.tiet_details)
    TextInputEditText etDetails;

    @BindView(R.id.tiet_name)
    TextInputEditText etName;

    @BindView(R.id.tiet_subject)
    TextInputEditText etSubject;

    @BindView(R.id.tiet_phone)
    TextInputEditText etPhone;

    @BindView(R.id.tiet_email)
    TextInputEditText etEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggestions, container, false);
        ButterKnife.bind(this, view);

        setTypeFace();
        return view;
    }

    private void setTypeFace() {
        Utils.setTypeFace(subjectHint, Constants.KUFI_REGULAR);
        Utils.setTypeFace(emailHint, Constants.KUFI_REGULAR);
        Utils.setTypeFace(phoneHint, Constants.KUFI_REGULAR);
        Utils.setTypeFace(etDetails, Constants.KUFI_REGULAR);
        Utils.setTypeFace(etSubject, Constants.KUFI_REGULAR);
        Utils.setTypeFace(etPhone, Constants.KUFI_REGULAR);
        Utils.setTypeFace(etEmail, Constants.KUFI_REGULAR);
        Utils.setTypeFace(etName, Constants.KUFI_REGULAR);
        Utils.setTypeFace(nameHint, Constants.KUFI_REGULAR);
    }

    @OnClick(R.id.btSendSuggestion)
    void sendSuggestion() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String desc = etDetails.getText().toString();
        String subject = etSubject.getText().toString();


        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !desc.isEmpty() && !subject.isEmpty()) {


            SuggestionModel suggestionModel = new SuggestionModel();
            suggestionModel.setDescription(desc);
            suggestionModel.setEmail(email);
            suggestionModel.setName(name);
            suggestionModel.setPhoneNumber(phone);
            suggestionModel.setTitle(subject);

            ProgressDialogUtil.show(getActivity());
            SendSuggestionViewModel _ViewModel = ViewModelProviders.of(this).get(SendSuggestionViewModel.class);
            LiveData<Boolean> booleanLiveData = _ViewModel.sendSuggestion(suggestionModel);
            booleanLiveData.observe(this, isSent -> {
                if (isSent) {
                    ProgressDialogUtil.dismiss();
                    Toast.makeText(getActivity(), getString(R.string.thank_you), Toast.LENGTH_LONG).show();
                    ((MainActivity) getActivity()).menuNavigation(1);
                }
            });
        } else {
            if (name.isEmpty()) {
                etName.setError(getResources().getString(R.string.mandatory));
            }
            if (phone.isEmpty()) {
                etPhone.setError(getResources().getString(R.string.mandatory));
            }
            if (desc.isEmpty()) {
                etDetails.setError(getResources().getString(R.string.mandatory));
            }
            if (subject.isEmpty()) {
                etSubject.setError(getResources().getString(R.string.mandatory));
            }
            if (email.isEmpty()) {
                etEmail.setError(getResources().getString(R.string.mandatory));
            }

        }
    }


}
