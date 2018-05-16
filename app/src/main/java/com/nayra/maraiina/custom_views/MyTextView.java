package com.nayra.maraiina.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by nayrael-sayed on 3/3/18.
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Regular.ttf");
        setTypeface(typeface);
    }
}
