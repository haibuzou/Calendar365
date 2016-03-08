package com.haibuzou.datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haibuzou.datepicker.calendar.cons.DPMode;
import com.haibuzou.datepicker.calendar.views.MonthView;
import com.haibuzou.datepicker.calendar.views.WeekView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private MonthView monthView;
    private WeekView weekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monthView = (MonthView) findViewById(R.id.month_calendar);
        weekView = (WeekView) findViewById(R.id.week_calendar);
        Calendar now = Calendar.getInstance();
        monthView.setDPMode(DPMode.SINGLE);
        monthView.setDate(now.get(Calendar.YEAR),now.get(Calendar.MONTH)+1);
        monthView.setFestivalDisplay(true);
        monthView.setTodayDisplay(true);

        weekView.setDPMode(DPMode.SINGLE);
        weekView.setDate(now.get(Calendar.YEAR),now.get(Calendar.MONTH)+1);
        weekView.setFestivalDisplay(true);
        weekView.setTodayDisplay(true);
    }
}
