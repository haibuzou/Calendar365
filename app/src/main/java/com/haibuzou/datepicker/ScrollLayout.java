package com.haibuzou.datepicker;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.haibuzou.datepicker.calendar.views.MonthView;
import com.haibuzou.datepicker.calendar.views.WeekView;


public class ScrollLayout extends FrameLayout implements MonthView.OnLineCountChangeListener,
        MonthView.OnLineChooseListener,MonthView.OnMonthViewChangeListener,
        WeekView.OnWeekViewChangeListener,WeekView.OnWeekDateClick,MonthView.OnMonthDateClick{

    private ViewDragHelper viewDragHelper;
    private MonthView monthView;
    private WeekView weekView;
    private RelativeLayout mainLayout;
    private LinearLayout contentLayout;
    //记录month calendar 行数和选择的哪一行的数字的变化
    private int line;
    private int lineCount;
    //初始的Y坐标
    private int orignalY;
    //滑动的过程中记录顶部坐标
    private int layoutTop;
    private int dragRang;


    public ScrollLayout(Context context) {
        this(context,null);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if(top >= orignalY){
                    return orignalY;
                }else{
                    if(contentLayout.getBottom()<=getHeight()){
                        return Math.max(top,-monthView.getHeight()*(lineCount-1)/lineCount);
                    }else{
                        return Math.max(top,getHeight()-contentLayout.getMeasuredHeight());
                    }
                }
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                    layoutTop = top;
                    if(top <= -monthView.getHeight()*line/lineCount&&dy<0){
                        weekView.setVisibility(View.VISIBLE);
                    }else if(top>=-monthView.getHeight()*line/lineCount&&dy>0){
                        weekView.setVisibility(View.INVISIBLE);
                    }
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return monthView.getHeight();
            }
        });
    }

    @Override
    public void onLineChange(int line) {
        this.line = line;
        weekView.setLine(line);
    }

    @Override
    public void onLineCountChange(int lineCount) {
        this.lineCount = lineCount;
    }

    @Override
    public void onMonthViewChange(boolean isforward) {
        if(isforward){
            weekView.moveForwad();
        }else{
            weekView.moveBack();
        }
    }

    @Override
    public void onMonthDateClick(int x, int y) {
        weekView.changeChooseDate(x,y-(monthView.getHeight()*(line-1)/lineCount));
    }

    @Override
    public void onWeekDateClick(int x, int y) {
        monthView.changeChooseDate(x,y+(monthView.getHeight()*(line-1)/lineCount));
    }

    @Override
    public void onWeekViewChange(boolean isForward) {
        if(isForward){
            monthView.moveForwad();
        }else{
            monthView.moveBack();
        }
    }

    @Override
    public void computeScroll() {
        if(viewDragHelper.continueSettling(true)){
            postInvalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainLayout = (RelativeLayout)findViewById(R.id.main_layout);
        monthView = (MonthView) findViewById(R.id.month_calendar);
        monthView.setOnLineChooseListener(this);
        monthView.setOnLineCountChangeListener(this);
        monthView.setOnMonthDateClickListener(this);
        weekView = (WeekView)findViewById(R.id.week_calendar);
        weekView.setOnWeekViewChangeListener(this);
        weekView.setOnWeekClickListener(this);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
        orignalY = monthView.getTop();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mainLayout.layout(0,layoutTop,mainLayout.getMeasuredWidth(),mainLayout.getMeasuredHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,getPaddingLeft()+getPaddingRight(),lp.width);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,lp.leftMargin+lp.rightMargin+getPaddingLeft()+getPaddingRight(),lp.width);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.topMargin+lp.bottomMargin,MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }
}
