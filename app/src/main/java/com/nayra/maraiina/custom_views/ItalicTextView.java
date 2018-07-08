package com.nayra.maraiina.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class ItalicTextView extends android.support.v7.widget.AppCompatTextView {

    public ItalicTextView(Context context) {
        super(context);
        init();
    }

    public ItalicTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItalicTextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/MuseoSans_100Italic.otf");
        setTypeface(typeface);
    }
}