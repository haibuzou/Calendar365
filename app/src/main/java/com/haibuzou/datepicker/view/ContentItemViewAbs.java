package com.haibuzou.datepicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.haibuzou.datepicker.R;


public class ContentItemViewAbs extends LinearLayout {

    public ContentItemViewAbs(Context context) {
        this(context, null);
    }

    public ContentItemViewAbs(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentItemViewAbs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.content_list_item_abs, null);
        addView(view);
    }
}
