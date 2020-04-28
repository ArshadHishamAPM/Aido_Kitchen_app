package com.rathore.health.Activity.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;

//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.R;

import java.util.Calendar;

/**
 * Created by archi on 12/30/2016.
 */

public class ActivityProfileCalenderView extends AppCompatActivity
{
    private CalendarView calendarView;
    private TextView tvdateDisplay;
  //  compactCalendarView = (CompactCalendarView) v.findViewById(R.id.compactcalendar_view);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_calender_view_main);
        calendarView = (CalendarView)findViewById(R.id.activity_profile_calendar_main);
        tvdateDisplay = (TextView)findViewById(R.id.activity_profile_calender_main_date_display);
        init();
    }
    private void init()
    {
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                tvdateDisplay.setText(year +"-"+month + "-"+ dayOfMonth);
//            }
//        });

        String date =  "10/1/2017";
        String parts[] = date.split("/");

         int day = Integer.parseInt(parts[0]);
         int month = Integer.parseInt(parts[1]);
         int year = Integer.parseInt(parts[2]);
         Calendar calendar = Calendar.getInstance();
         calendar.set(calendar.YEAR,year);
         calendar.set(calendar.MONTH,month);
         calendar.set(calendar.DATE,day);
         long miles = calendar.getTimeInMillis();




//         List<Event> events = getEvents(miles, 10);
         //calendarView.addEvents(events);
        //  calendarView.addView(miles);
        //calendarView.addEvent(miles,true);

      //calendarView.setDate(miles,false,true);
        //calendarView.setBackground(ContextCompat.getDrawable(ActivityProfileCalenderView.this,R.drawable.ic_back));
        // calendarView.set
        //calendarView.set

    }
}

