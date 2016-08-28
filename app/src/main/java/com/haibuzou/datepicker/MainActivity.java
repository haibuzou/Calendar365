package com.haibuzou.datepicker;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haibuzou.datepicker.calendar.cons.DPMode;
import com.haibuzou.datepicker.calendar.views.MonthView;
import com.haibuzou.datepicker.calendar.views.WeekView;
import com.haibuzou.datepicker.view.ContentItemViewAbs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MonthView.OnDateChangeListener, MonthView.OnDatePickedListener {

    private MonthView monthView;
    private WeekView weekView;
    private Toolbar toolbar;
    private LinearLayout contentLayout;
    private TextView weekTxt;
    private Calendar now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        now = Calendar.getInstance();
        toolbar.setTitle(now.get(Calendar.YEAR) + "." + (now.get(Calendar.MONTH) + 1));
        setSupportActionBar(toolbar);

        monthView = (MonthView) findViewById(R.id.month_calendar);
        weekView = (WeekView) findViewById(R.id.week_calendar);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
        weekTxt = (TextView) findViewById(R.id.week_text);

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
        for(int i = 0; i< 20; i++) {
            ContentItemViewAbs cia = new ContentItemViewAbs(this);
            contentLayout.addView(cia);
        }

    }

    @Override
    public void onDateChange(int year, int month) {
        ActionBar toolbar = getSupportActionBar();
        if (null != toolbar)
            toolbar.setTitle(year + "." + month);
    }

    @Override
    public void onDatePicked(String date) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
            SimpleDateFormat format2 = new SimpleDateFormat("EEEE");
            Date choosedate = format1.parse(date);
            weekTxt.setText(format2.format(choosedate));
            if (date.equals(now.get(Calendar.YEAR) + "." + (now.get(Calendar.MONTH) + 1) + "." + now.get(Calendar.DAY_OF_MONTH))) {
                weekTxt.setVisibility(View.INVISIBLE);
            } else {
                weekTxt.setVisibility(View.VISIBLE);
            }
            contentLayout.removeAllViews();
            for(int i = 0; i< 2; i++) {
                ContentItemViewAbs cia = new ContentItemViewAbs(this);
                contentLayout.addView(cia);
            }
            Toast.makeText(this, "" + date, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
