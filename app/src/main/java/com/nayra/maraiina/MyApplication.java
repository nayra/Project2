package com.nayra.maraiina;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.nayra.maraiina.notifications.MaraeenaNotificationOpenedHandler;
import com.nayra.maraiina.util.TypeFactory;
import com.onesignal.OneSignal;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    private Context context;
    private TypeFactory mTypeFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new MaraeenaNotificationOpenedHandler(this))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        context = this;
        mInstance = this;
    }

    public static MyApplication getmInstance() {
        return mInstance;
    }

    public Context getContext() {
        return context;
    }

    public Typeface getTypeFace(int type) {
        if (mTypeFactory == null)
            mTypeFactory = new TypeFactory(this);

        switch (type) {
            case Constants.KUFI_BOLD_font:
                return mTypeFactory.getKufiBoldTypeface();

            case Constants.KUFI_REGULAR:
                return mTypeFactory.getKufiRegularTypeface();

            case Constants.UNIQUE_BOLD:
                return mTypeFactory.getUniqueBoldTypeface();

            case Constants.UNIQUE_LIGHT:
                return mTypeFactory.getUniqueLightTypeface();

            case Constants.MUSEOSANS:
                return mTypeFactory.getMuseosansTypeface();

            case Constants.MUSEOSANS_0_OTF:
                return mTypeFactory.getMuseosans0OTFTypeface();

            case Constants.MUSEOSANS_0_TTF:
                return mTypeFactory.getMuseosans0TTFTypeface();

            case Constants.MUSEOSANS_1_OTF:
                return mTypeFactory.getMuseosans1OTFTypeface();

            case Constants.MUSEOSANS_2_OTF:
                return mTypeFactory.getMuseosans2OTFTypeface();

            case Constants.MUSEOSANS_2_TTF:
                return mTypeFactory.getMuseosans2TTFTypeface();

            case Constants.MUSEOSANS_3_OTF:
                return mTypeFactory.getMuseosans3OTFTypeface();

            case Constants.MUSEOSANS_4_OTF:
                return mTypeFactory.getMuseosans4OTFTypeface();

            case Constants.MUSEOSANS_100ITALIC:
                return mTypeFactory.getMuseosans100Italicypeface();

            case Constants.MUSEOSANS_300ITALIC:
                return mTypeFactory.getMuseosans300Italicypeface();

            case Constants.MUSEOSANS_500ITALIC:
                return mTypeFactory.getMuseosans500Italicypeface();

            case Constants.MUSEOSANS_700ITALIC:
                return mTypeFactory.getMuseosans700Italicypeface();

            case Constants.MUSEOSANS_900ITALIC:
                return mTypeFactory.getMuseosans900Italicypeface();
        }

        return mTypeFactory.getKufiRegularTypeface();
    }
}
