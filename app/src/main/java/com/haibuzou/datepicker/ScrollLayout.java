package com.haibuzou.datepicker;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.haibuzou.datepicker.calendar.views.MonthView;
import com.haibuzou.datepicker.calendar.views.WeekView;
import com.haibuzou.datepicker.view.MyTextView;


public class ScrollLayout extends FrameLayout implements MonthView.OnLineCountChangeListener,
        MonthView.OnLineChooseListener, MonthView.OnMonthViewChangeListener,
        WeekView.OnWeekViewChangeListener, WeekView.OnWeekDateClick, MonthView.OnMonthDateClick {

    private ViewDragHelper viewDragHelper;
    private MonthView monthView;
    private WeekView weekView;
    private LinearLayout mainLayout;
    private LinearLayout contentLayout;
    //记录month calendar 行数和选择的哪一行的数字的变化
    private int line;
    private int lineCount;
    //初始的Y坐标
    private int orignalY;
    //滑动的过程中记录顶部坐标
    private int layoutTop;
    private int dragRang;
    private MyTextView weekTxt;
    private int marginTop;
    private int mTouchSlop;
    private int lastX;


    public ScrollLayout(Context context) {
        this(context, null);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        marginTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13, getResources().getDisplayMetrics());
        viewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mainLayout;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top >= orignalY) {
                    return orignalY;
                } else {
                    if (contentLayout.getMeasuredHeight() <= monthView.getHeight() * (lineCount - 1) / lineCount) {
                        return Math.max(top, -monthView.getHeight() * (lineCount - 1) / lineCount);
                    } else {
                        return Math.max(top, -contentLayout.getMeasuredHeight());
                    }
                }
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                //不要让顶部坐标在weekview的上方，这样会导致 mainlayout 被weekview遮挡
                layoutTop = top <= -monthView.getHeight() * (lineCount - 1) / lineCount ? -monthView.getHeight() * (lineCount - 1) / lineCount : top;
                if (top <= -monthView.getHeight() * line / lineCount && dy < 0) {
                    weekView.setVisibility(View.VISIBLE);
                } else if (top >= -monthView.getHeight() * line / lineCount && dy > 0) {
                    weekView.setVisibility(View.INVISIBLE);
                }

                if (top <= -monthView.getHeight() * (lineCount - 1) / lineCount) {
                    //当已经滑动到顶部时 希望周的显示 固定在日历的下方 而不要继续随着手势滑动
                    weekTxt.layout((int) weekTxt.getX(), monthView.getMeasuredHeight(),
                            (int) weekTxt.getX() + weekTxt.getWidth(), monthView.getMeasuredHeight() + weekTxt.getHeight());
                }
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild.getTop() > -monthView.getHeight() * (lineCount - 1) / lineCount && yvel >= 0) {
                    viewDragHelper.settleCapturedViewAt(0, orignalY);
                    invalidate();
                } else if (releasedChild.getTop() > -monthView.getHeight() * (lineCount - 1) / lineCount && yvel < 0) {
                    viewDragHelper.settleCapturedViewAt(0, -monthView.getHeight() * (lineCount - 1) / lineCount);
                    invalidate();
                }
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
        if (lineCount == 6) {
            weekView.setCount(lineCount);
        }
    }

    @Override
    public void onMonthViewChange(boolean isforward) {
        if (isforward) {
            weekView.moveForwad();
        } else {
            weekView.moveBack();
        }
    }

    @Override
    public void onMonthDateClick(int x, int y) {
        weekView.changeChooseDate(x, y - (monthView.getHeight() * (line) / lineCount));
    }

    @Override
    public void onWeekDateClick(int x, int y) {
        monthView.changeChooseDate(x, y + (monthView.getHeight() * (line) / lineCount));
    }

    @Override
    public void onWeekViewChange(boolean isForward) {
        if (isForward) {
            monthView.moveForwad();
        } else {
            monthView.moveBack();
        }
    }


    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            postInvalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        monthView = (MonthView) findViewById(R.id.month_calendar);
        weekTxt = (MyTextView) findViewById(R.id.week_text);
        monthView.setOnLineChooseListener(this);
        monthView.setOnLineCountChangeListener(this);
        monthView.setOnMonthDateClickListener(this);
        monthView.setOnMonthViewChangeListener(this);
        weekView = (WeekView) findViewById(R.id.week_calendar);
        weekView.setOnWeekViewChangeListener(this);
        weekView.setOnWeekClickListener(this);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
        orignalY = monthView.getTop();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        weekView.layout(0, 0, weekView.getMeasuredWidth(), weekView.getMeasuredHeight());
        mainLayout.layout(0, layoutTop, mainLayout.getMeasuredWidth(), mainLayout.getMeasuredHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if ((int) Math.abs(lastX - ev.getX()) >= mTouchSlop) {
                    return false;
                }
                break;
        }
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }


    //重写 onMeasure 支持 wrap_content
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //计算所有childview的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //计算warp_content的时候的高度
        int wrapHeight = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight();
            wrapHeight += childHeight;
        }
        setMeasuredDimension(widthSize, heightMode == MeasureSpec.EXACTLY ? heightSize : wrapHeight);

    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();

        int childWidthMeasureSpec;
        int childHeightMeasureSpec;

        childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                getPaddingLeft() + getPaddingRight(), lp.width);

        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed,
                                           int parentHeightMeasureSpec, int heightUsed) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        final int childWidthMeasureSpec = getChildMeasureSpec(
                parentWidthMeasureSpec, lp.leftMargin + lp.rightMargin + widthUsed, lp.width);
        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                lp.topMargin + lp.bottomMargin, MeasureSpec.UNSPECIFIED);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

}
