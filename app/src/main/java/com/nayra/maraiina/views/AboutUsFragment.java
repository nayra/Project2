package com.nayra.maraiina.views;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.nayra.maraiina.Constants;
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
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageURL();
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
        });

        twitterImageButton.setOnClickListener(view -> {
            Intent intent = null;
            try {
                // get the Twitter app if possible
                getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.twitterLink));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } catch (Exception e) {
                // no Twitter app, revert to browser
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.twitterLink));
            }
            this.startActivity(intent);
        });
        instgramImageButton.setOnClickListener(view -> {
            watchYoutubeVideo(Constants.youtubeID);
        });
    }

    public void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL() {
        PackageManager packageManager = getActivity().getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + Constants.facebookLink;
            } else { //older versions of fb app
                return "fb://page/" + Constants.FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return Constants.facebookLink; //normal web url
        }
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
