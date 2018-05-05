package com.nayra.maraiina.util;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFactory {
    private final String KUFI_BOLD = "fonts/DroidKufi-Bold.ttf";
    private final String KUFI_REGULAR = "fonts/DroidKufi-Regular.ttf";
    private final String UNIQUE_BOLD = "fonts/GE SS Unique Bold.otf";
    private final String UNIQUE_REGULAR = "fonts/GE SS Unique Light.otf";
    private final String MUSEOSANS = "fonts/MuseoSans.ttf";
    private final String MUSEOSANS_0_OTF = "fonts/MuseoSans_0.otf";
    private final String MUSEOSANS_0_TTF = "fonts/museosans_0.ttf";
    private final String MUSEOSANS_1_OTF = "fonts/MuseoSans_1.otf";
    private final String MUSEOSANS_2_OTF = "fonts/MuseoSans_2.otf";
    private final String MUSEOSANS_2_TTF = "fonts/museosans_2.ttf";
    private final String MUSEOSANS_3_OTF = "fonts/MuseoSans_3.otf";
    private final String MUSEOSANS_4_OTF = "fonts/MuseoSans_4.otf";
    private final String MUSEOSANS_100ITALIC = "fonts/MuseoSans_100Italic.otf";
    private final String MUSEOSANS_300ITALIC = "fonts/MuseoSans_300Italic.otf";
    private final String MUSEOSANS_500ITALIC = "fonts/MuseoSans_500Italic.otf";
    private final String MUSEOSANS_700ITALIC = "fonts/MuseoSans_700Italic.otf";
    private final String MUSEOSANS_900ITALIC = "fonts/MuseoSans_900Italic.otf";

    private Typeface kufiBoldTypeface;
    private Typeface kufiRegularTypeface;
    private Typeface uniqueBoldTypeface;
    private Typeface uniqueLightTypeface;
    private Typeface museosansTypeface;
    private Typeface museosans0OTFTypeface;
    private Typeface museosans0TTFTypeface;
    private Typeface museosans1OTFTypeface;
    private Typeface museosans2OTFTypeface;
    private Typeface museosans2TTFTypeface;
    private Typeface museosans3OTFTypeface;
    private Typeface museosans4OTFTypeface;
    private Typeface museosans100Italicypeface;
    private Typeface museosans300Italicypeface;
    private Typeface museosans500Italicypeface;
    private Typeface museosans700Italicypeface;
    private Typeface museosans900Italicypeface;

    public TypeFactory(Context context) {
        kufiBoldTypeface = Typeface.createFromAsset(context.getAssets(), KUFI_BOLD);
        kufiRegularTypeface = Typeface.createFromAsset(context.getAssets(), KUFI_REGULAR);
        uniqueBoldTypeface = Typeface.createFromAsset(context.getAssets(), UNIQUE_BOLD);
        uniqueLightTypeface = Typeface.createFromAsset(context.getAssets(), UNIQUE_REGULAR);

        museosansTypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS);
        museosans0OTFTypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_0_OTF);
        museosans0TTFTypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_0_TTF);
        museosans1OTFTypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_1_OTF);
        museosans2OTFTypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_2_OTF);
        museosans2TTFTypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_2_TTF);
        museosans3OTFTypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_3_OTF);
        museosans4OTFTypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_4_OTF);
        museosans100Italicypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_100ITALIC);
        museosans300Italicypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_300ITALIC);
        museosans500Italicypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_500ITALIC);
        museosans700Italicypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_700ITALIC);
        museosans900Italicypeface = Typeface.createFromAsset(context.getAssets(), MUSEOSANS_900ITALIC);
    }

    public Typeface getKufiBoldTypeface() {
        return kufiBoldTypeface;
    }

    public Typeface getKufiRegularTypeface() {
        return kufiRegularTypeface;
    }

    public Typeface getUniqueBoldTypeface() {
        return uniqueBoldTypeface;
    }

    public Typeface getUniqueLightTypeface() {
        return uniqueLightTypeface;
    }

    // ENGLISH


    public Typeface getMuseosansTypeface() {
        return museosansTypeface;
    }

    public Typeface getMuseosans0OTFTypeface() {
        return museosans0OTFTypeface;
    }

    public Typeface getMuseosans0TTFTypeface() {
        return museosans0TTFTypeface;
    }

    public Typeface getMuseosans1OTFTypeface() {
        return museosans1OTFTypeface;
    }

    public Typeface getMuseosans2OTFTypeface() {
        return museosans2OTFTypeface;
    }

    public Typeface getMuseosans2TTFTypeface() {
        return museosans2TTFTypeface;
    }

    public Typeface getMuseosans3OTFTypeface() {
        return museosans3OTFTypeface;
    }

    public Typeface getMuseosans4OTFTypeface() {
        return museosans4OTFTypeface;
    }

    public Typeface getMuseosans100Italicypeface() {
        return museosans100Italicypeface;
    }

    public Typeface getMuseosans300Italicypeface() {
        return museosans300Italicypeface;
    }

    public Typeface getMuseosans500Italicypeface() {
        return museosans500Italicypeface;
    }

    public Typeface getMuseosans700Italicypeface() {
        return museosans700Italicypeface;
    }

    public Typeface getMuseosans900Italicypeface() {
        return museosans900Italicypeface;
    }
}
