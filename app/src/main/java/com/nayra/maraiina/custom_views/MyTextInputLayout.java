package com.nayra.maraiina.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

/**
 * Created by nayrael-sayed on 3/5/18.
 */

public class MyTextInputLayout extends TextInputLayout {
    public MyTextInputLayout(final Context context) {
        super(context);
        init();
    }

    public MyTextInputLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextInputLayout(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/comic_sans.ttf");
        setTypeface(typeface);
    }
}
