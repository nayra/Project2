package com.nayra.maraiina.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Switch;

/**
 * Created by nayrael-sayed on 3/5/18.
 */

public class MySwitch extends Switch {
    public MySwitch(final Context context) {
        super(context);
        init();
    }

    public MySwitch(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySwitch(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Regular.ttf");
        setTypeface(typeface);
    }
}
