package com.rathore.health.Activity;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

//import com.example.archi.health.AlarmReceiver;
//import com.example.archi.health.DbHelper.DbHelper;
////import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.AlarmReceiver;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.AlarmReceiver;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

/**
 * Created by archi on 12/21/2016.
 */

public class ActivityAddReminder extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText etReminderName, etReminderReason, etDate, etTime, etNote;
    private Button btnSave;
    Calendar calSet,calNow;
    private Spinner spnReason;
    SharedPreferences sharedPreferences;
    final static int RQS_1 = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getApplicationContext().getSharedPreferences("alarm",MODE_PRIVATE);
        setContentView(R.layout.activity_add_reminder);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Add Reminder");
        etReminderName = (EditText) findViewById(R.id.activity_add_reminder_et_reminder_name);
        etReminderReason = (EditText) findViewById(R.id.activity_add_reminder_et_reason_for);
        etDate = (EditText) findViewById(R.id.activity_add_reminder_et_date);
        etTime = (EditText) findViewById(R.id.activity_add_reminder_et_time);
        spnReason = (Spinner) findViewById(R.id.activity_add_reminder_spinner_select);
     //   spnReason.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        etNote = (EditText) findViewById(R.id.activity_add_reminder_et_note);
        btnSave = (Button) findViewById(R.id.activity_add_reminder_save);
        init();
         calNow = Calendar.getInstance();
        calSet = (Calendar) calNow.clone();
        Intent intent = new Intent();
        String packageName = getApplicationContext().getPackageName();
//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        if (pm.isIgnoringBatteryOptimizations(packageName))
//            intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
//        else {
//            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//            intent.setData(Uri.parse("package:" + packageName));
//        }
//        startActivity(intent);


    }

    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityAddReminder.this, R.color.white));

        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityAddReminder.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> TypeAdapter = ArrayAdapter
                .createFromResource(this, R.array.repeat_day_array,
                        R.layout.customspinnerlist);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnReason.setAdapter(TypeAdapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_add_reminder_et_date:
               // Toast.makeText(getApplicationContext(), "hello this is", Toast.LENGTH_SHORT).show();
                openDatePicker();
                break;

            case R.id.activity_add_reminder_et_time:
                openTimePicker();

                break;

            case R.id.activity_add_reminder_save:
                if (!etReminderName.getText().toString().equalsIgnoreCase("")) {

                    if (!etReminderReason.getText().toString().equalsIgnoreCase("")) {

                        if (!etDate.getText().toString().equalsIgnoreCase("")) {

                            if (!etTime.getText().toString().equalsIgnoreCase("")) {


                                if (!spnReason.getSelectedItem().toString().equalsIgnoreCase("")) {
                                    DbHelper helper = new DbHelper(getApplicationContext());
                                   long uid= helper.addReminder(etReminderName.getText().toString(),
                                            etReminderReason.getText().toString(),
                                            etDate.getText().toString(),
                                            etTime.getText().toString(),
                                            spnReason.getSelectedItem().toString(),
                                            etNote.getText().toString());
                                    Log.i("spnReason",spnReason.getSelectedItem().toString());

                                    Toast.makeText(this, "Reminder Added Succesfully!!!", Toast.LENGTH_SHORT).show();

//                                    Calendar current = Calendar.getInstance();    //initialize an instance of Calendar
//                                    Calendar cal = Calendar.getInstance();
//                                    cal.set(dp.getYear(),
//                                            dp.getMonth(),
//                                            dp.getDayOfMonth(),
//                                            tp.getCurrentHour(),
//                                            tp.getCurrentMinute(),
//                                            00);        //get current time and date
                                    if (calSet.compareTo(calNow) <= 0) {
                                        Toast.makeText(getApplicationContext(),"Invalid Date/Time.Please Re-enter", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        setAlarm(getApplicationContext(),calSet,uid,spnReason.getSelectedItem().toString());
                                    }
//                                }



                                    onBackPressed();

                                } else {
                                    Toast.makeText(ActivityAddReminder.this, "please select alarm Reapeat", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(ActivityAddReminder.this, "please select the time", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ActivityAddReminder.this, "please select the date", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ActivityAddReminder.this, "please Enter the reminder reason", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityAddReminder.this, "Please Enter the reminder name", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void openTimePicker() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        //launch timepicker modal
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calSet.set(Calendar.MINUTE, minute);
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);
                        etTime.setText(time);

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
//        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Log.i("timesetlistener","called");
//                Calendar calNow = Calendar.getInstance();
//                Calendar calSet = (Calendar) calNow.clone();
//
//                calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                calSet.set(Calendar.MINUTE, minute);
//                calSet.set(Calendar.SECOND, 0);
//                calSet.set(Calendar.MILLISECOND, 0);
//
//                if(calSet.compareTo(calNow) <= 0){
//                    //Today Set time passed, count to tomorrow
//                    calSet.add(Calendar.DATE, 1);
//                }
//
//                setAlarm(calSet);
//            }};

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void openDatePicker() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String strTime = dayOfMonth + "-" + (mMonth + 1) + "-" + year;
                        Log.i("datechange",strTime);

//                        Calendar calNow = Calendar.getInstance();
//                        Log.i("currentmilisec",String.valueOf(calNow.getTimeInMillis()));
//                 calSet = (Calendar) calNow.clone();
//
                calSet.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calSet.set(Calendar.YEAR, year);
                calSet.set(Calendar.MONTH,monthOfYear );
//                calSet.set(Calendar.HOUR_OF_DAY, 16);
//                        calSet.set(Calendar.MINUTE, 56);
//
//
//                if(calSet.compareTo(calNow) <= 0){
//                    //Today Set time passed, count to tomorrow
//                    calSet.add(Calendar.DATE, 1);
//                }
//long milisec=calSet.getTimeInMillis()-calNow.getTimeInMillis();
//               // setAlarm(calSet,milisec);
//                        setAlarm(calSet,milisec);


                        etDate.setText(strTime);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }
    //@TargetApi(Build.VERSION_CODES.M)
    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    //@RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setAlarm(Context context, Calendar targetCal, long id, String repeat){
    Log.i("setAlarm","called");
//        textAlarmPrompt.setText(
//                "\n\n***\n"
//                        + "Alarm is set@ " + targetCal.getTime() + "\n"
//                        + "***\n");
        Log.i("targettime",targetCal.getTime().toString());

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("id",id);



        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("R_name", etReminderName.getText().toString());
        editor.putString("R_repeat",  spnReason.getSelectedItem().toString());
        editor.putString("R_id", String.valueOf(id));
        editor.putString("R_resone",etReminderReason.getText().toString());
        editor.commit();

        //intent.putExtra("R_name",etReminderName.getText().toString());
        //intent.putExtra("R_resone",etReminderReason.getText().toString());

        int uid=(int)id;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), uid, intent, PendingIntent.FLAG_ONE_SHOT);
       Log.i("senderid", String.valueOf(uid));

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Log.i("dozen","called");
//            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,targetCal.getTimeInMillis(),pendingIntent);
//        }







//remove commment from setAlarmclock *******************************
         alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);



















        // alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,targetCal.getTimeInMillis(),pendingIntent);
// if(repeat.equals("Everyday")){
//
//     targetCal.set(Calendar.DAY_OF_MONTH,+1);
// }
// alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo((targetCal.getTimeInMillis()),pendingIntent),pendingIntent);
// alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),AlarmManager.INTERVAL_DAY ,pendingIntent);
//        }8
//        if(repeat.equals("weekly")){
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7 ,pendingIntent);
//        }
//        if(repeat.equals("monthly")){
//          long monthly=  getDuration();
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),monthly ,pendingIntent);
//
//        }



    }



}


