package com.nayra.maraiina.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by nayrael-sayed on 3/3/18.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton {
    public MyButton(final Context context) {
        super(context);
        init();
    }

    public MyButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/comic_sans.ttf");
        setTypeface(typeface);
    }
}
