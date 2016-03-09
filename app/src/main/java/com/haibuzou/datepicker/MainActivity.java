package com.haibuzou.datepicker;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haibuzou.datepicker.calendar.cons.DPMode;
import com.haibuzou.datepicker.calendar.views.MonthView;
import com.haibuzou.datepicker.calendar.views.WeekView;
import com.haibuzou.datepicker.view.ContentItemViewAbs;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MonthView.OnDateChangeListener, MonthView.OnDatePickedListener {

    private MonthView monthView;
    private WeekView weekView;
    private Toolbar toolbar;
    private LinearLayout contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Calendar now = Calendar.getInstance();
        toolbar.setTitle(now.get(Calendar.YEAR) + "." + (now.get(Calendar.MONTH) + 1));
        setSupportActionBar(toolbar);


        monthView = (MonthView) findViewById(R.id.month_calendar);
        weekView = (WeekView) findViewById(R.id.week_calendar);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);

        monthView.setDPMode(DPMode.SINGLE);
        monthView.setDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1);
        monthView.setFestivalDisplay(true);
        monthView.setTodayDisplay(true);
        monthView.setOnDateChangeListener(this);
        monthView.setOnDatePickedListener(this);

        weekView.setDPMode(DPMode.SINGLE);
        weekView.setDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1);
        weekView.setFestivalDisplay(true);
        weekView.setTodayDisplay(true);
        weekView.setOnDatePickedListener(this);

        ContentItemViewAbs cia = new ContentItemViewAbs(this);
        contentLayout.addView(cia);

    }

    @Override
    public void onDateChange(int year, int month) {
        ActionBar toolbar = getSupportActionBar();
        if (null != toolbar)
            toolbar.setTitle(year + "." + month);
    }

    @Override
    public void onDatePicked(String date) {
        Toast.makeText(this, "" + date, Toast.LENGTH_LONG).show();
    }
}
