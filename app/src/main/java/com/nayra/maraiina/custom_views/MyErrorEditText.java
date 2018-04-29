package com.nayra.maraiina.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public class MyErrorEditText extends android.support.v7.widget.AppCompatEditText {

    public MyErrorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/comic_sans.ttf");
        setTypeface(typeface);
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        setCompoundDrawables(null, null, icon, null);
    }

    @Override
    public void setError(CharSequence error) {
        super.setError(error);
    }
}

