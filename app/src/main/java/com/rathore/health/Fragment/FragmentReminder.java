package com.rathore.health.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.example.archi.health.Activity.ActivityAddReminder;
//import com.example.archi.health.Adapter.ReminderAdapter;
//import com.example.archi.health.AlarmReceiver;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddReminder;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.ReminderAdapter;
//import com.pierfrancescosoffritti.aytplayersample.AlarmReceiver;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddReminder;
import com.rathore.health.Adapter.ReminderAdapter;
import com.rathore.health.AlarmReceiver;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 12/22/2016.
 */

public class FragmentReminder extends Fragment implements View.OnClickListener {
    private RecyclerView lvReminderrecycleview;
    final static int RQS_1 = 1;
    private Button btnAddReminder;
    List<HashMap<String, String>> users;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reminder, null);
        lvReminderrecycleview = (RecyclerView) rootView.findViewById(R.id.fragment_reminder_lv);
        btnAddReminder = (Button) rootView.findViewById(R.id.fragment_reminder_btn_add);
        DbHelper helper = new DbHelper(getActivity());
        users = helper.getReminder();
        lvReminderrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        lvReminderrecycleview.setAdapter(new ReminderAdapter(getContext(), users));
        init();
        return rootView;


        //new changes
//        Intent myIntent = new Intent(this , NotifyService.class);
//        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getService(getContext(), 0, myIntent, 0);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.AM_PM, Calendar.AM);
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24 , pendingIntent);
//    }

    }
    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(getActivity());
        users = helper.getReminder();

        Log.i("dbreminder", users.toString());

//        if (users.size() > 0) {
//            for (int i = 0; i < users.size(); i++) {
//                Log.i("alarmdate", users.get(i).get("r_date"));
//                String date = users.get(i).get("r_date");
//                String time = users.get(i).get("r_time");
//                //int date1 = Integer.parseInt(date.substring(0, 2));
//                //int date2 = Integer.parseInt(date.substring(3, 4));
//                //int date3 = Integer.parseInt(date.substring(5, 9));
//                int time1 = Integer.parseInt(time.substring(0, 2));
//                int time2 = Integer.parseInt(time.substring(3, 5));
//                //String s= date.split("-");
//                //Log.i("substring",date.substring(0,2));
//                //Log.i("substring",date.substring(3,4));
//                //Log.i("substring",date.substring(5,9));
//                //Log.i("substring",time.substring(0,2));
//                //Log.i("substring",time.substring(0,2));
//                //Log.i("substring",time.substring(3,5));
//                Calendar calNow = Calendar.getInstance();
//                Calendar calSet = (Calendar) calNow.clone();
//
//                calSet.set(Calendar.DAY_OF_MONTH, 1);
//                calSet.set(Calendar.YEAR, 2017);
//                calSet.set(Calendar.MONTH, 6);
//                calSet.set(Calendar.HOUR_OF_DAY, time1);
//                calSet.set(Calendar.MINUTE, time2);
//
//
//                if (calSet.compareTo(calNow) <= 0) {
//                    //Today Set time passed, count to tomorrow
//                    calSet.add(Calendar.DATE, 1);
//                } else {
//                    setAlarm(calSet);
//
//                }
//            }


       // }




        lvReminderrecycleview.setAdapter(new ReminderAdapter(getContext(), users));

    }


    private void init() {
        //btnAddReminder.setOnClickListener(this);
        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddReminderAct = new Intent(getActivity(), ActivityAddReminder.class);
                startActivity(AddReminderAct);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_reminder_btn_add:
                Log.i("addButton","clicked");
                Intent AddReminderAct = new Intent(getActivity(), ActivityAddReminder.class);
                startActivity(AddReminderAct);
                break;
        }

    }

    private void setAlarm(Calendar targetCal){
        Log.i("setAlarm","called");
//        textAlarmPrompt.setText(
//                "\n\n***\n"
//                        + "Alarm is set@ " + targetCal.getTime() + "\n"
//                        + "***\n");
        //Log.i("targettime",targetCal.getTime().toString());
        Log.i("time", String.valueOf(targetCal.getTimeInMillis()));

        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }
}