package com.nayra.maraiina.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.MyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsFragment extends Fragment {

    @BindView(R.id.tv_website_matteo)
    MyTextView txtWebSite;

    @BindView(R.id.imgFacebook)
    ImageButton facebookImageButton;
    @BindView(R.id.imgTwitter)
    ImageButton twitterImageButton;
    @BindView(R.id.imgInstgram)
    ImageButton instgramImageButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        setLink();
        setSocialMedialClickListeners();
        return view;
    }

    private void setSocialMedialClickListeners() {
        facebookImageButton.setOnClickListener(view -> {

        });

        twitterImageButton.setOnClickListener(view -> {

        });
        instgramImageButton.setOnClickListener(view -> {

        });
    }

    private void setLink() {
        SpannableString spanString = new SpannableString(getResources().getString(R.string.app_developed_by_matteo));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.matteoworks.com"));
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);

            }
        };
        //Developed By Matteo Works
        spanString.setSpan(clickableSpan, 13, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        txtWebSite.setText(spanString);
        txtWebSite.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
