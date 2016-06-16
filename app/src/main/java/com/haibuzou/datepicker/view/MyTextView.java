package com.haibuzou.datepicker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;


public class MyTextView extends TextView {

    private Paint mPaint;
    private int width;
    private RectF rect;

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(rect,10,10,mPaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect = new RectF(0,0,w,h);
    }
}
