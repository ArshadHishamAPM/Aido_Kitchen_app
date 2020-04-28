package com.rathore.health.Activity.Profile.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//import com.example.archi.health.Activity.Profile.ActivityProfileCalender;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileCalender;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.Profile.ActivityProfileCalender;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;


public class MainCalendar extends Activity {

    public static HashSet<Date> events;
    public static String calanderSelectedDate = "";
    List<HashMap<String, String>> profileCalenderDataArray;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        profileCalenderDataArray = new ArrayList<>();
        dbHelper = new DbHelper(MainCalendar.this);

        profileCalenderDataArray = dbHelper.getProfileCalenderData(ActivityProfileCalender.strProfileUniqId);

        String dtStart = "2017-1-4T09:27:37Z";
        Date date = StringToDateConvert(dtStart);

        String dtStartt = "2016-1-5T09:27:37Z";
        Date datee = StringToDateConvert(dtStartt);
        events = new HashSet<>();
        events.add(date);
        events.add(datee);
        for (int i=0;i<profileCalenderDataArray.size();i++)
        {
            String date1 = profileCalenderDataArray.get(i).get("key_profile_cal_date");
            String strDay,strMonth,strYear,strMonth1; ;
            String[] date12=date1.split("-");
            strDay = date12[0];
            strMonth1 = date12[1];
           int month= Integer.parseInt(strMonth1);
            strMonth= String.valueOf(month+1);
            strYear = date12[2];
            String dateSelected = strYear + "-"+strMonth +"-"+strDay + "T09:27:37Z";
            Log.i("calenderdate",dateSelected);
            Date dateSelected1 = StringToDateConvert(dateSelected);
            events.add(dateSelected1);
        }


        CalendarView cv = ((CalendarView) findViewById(R.id.calendar_view));
        cv.updateCalendar(events);
        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(MainCalendar.this, df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDayClickPress(Date date, View view) {
               // DateFormat df = SimpleDateFormat.getDateInstance();
                SimpleDateFormat df = new SimpleDateFormat("d-MM-yyyy", Locale.US);

              //  Toast.makeText(MainCalendar.this, df.format(date), Toast.LENGTH_SHORT).show();
                calanderSelectedDate = df.format(date);
                Log.i("calanderSelectedDate",calanderSelectedDate);
              //  Log.d("jai","selected date :"+calanderSelectedDate);

                //Log.d("jai","selectd date :"+calanderSelectedDate);
                finish();

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public Date StringToDateConvert(String dtStart) {
        Date date = null;


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
}
