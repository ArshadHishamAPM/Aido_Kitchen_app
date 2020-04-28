package com.rathore.health.Activity;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

//import com.example.archi.health.AlarmReceiver;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.AlarmReceiver;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.AlarmReceiver;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;


/**
 * Created by archi on 12/24/2016.
 */

public class ActivityUpdateReminder extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private String strReminderId;
    private EditText etReminderName, etReminderReason, etDate, etTime, etNote,etReminder;
    private Button btnUpdate;
    Calendar calSet,calNow;
    private DbHelper dbHelper;
    SharedPreferences sharedPreferences;
    final static int RQS_1 = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);
        sharedPreferences=getApplicationContext().getSharedPreferences("alarm",MODE_PRIVATE);
        toolbar = (Toolbar) findViewById(R.id.toolbar_reminder);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.update_reminder));


       // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityUpdateReminder.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityUpdateReminder.this, R.drawable.ic_toolbar_back));
        etReminderName = (EditText) findViewById(R.id.activity_update_reminder_et_reminder_name);
        etReminderReason = (EditText) findViewById(R.id.activity_update_reminder_et_reason_for);
        etDate = (EditText) findViewById(R.id.activity_update_reminder_et_date);
        etTime = (EditText) findViewById(R.id.activity_update_reminder_et_time);
        etNote = (EditText) findViewById(R.id.activity_update_reminder_et_note);
        btnUpdate = (Button) findViewById(R.id.activity_update_reminder_update);
        etReminder = (EditText)findViewById(R.id.activity_update_reminder_et_select);
        calNow = Calendar.getInstance();
        calSet = (Calendar) calNow.clone();
        init();
    }

    private void init() {
        if (getIntent().getExtras() != null) {
            strReminderId = getIntent().getExtras().getString("ReminderId");
            etReminderName.setText(getIntent().getExtras().getString("ReminderName"));
            etDate.setText(getIntent().getExtras().getString("ReminderDate"));
            etReminderReason.setText(getIntent().getExtras().getString("ReminderReason"));
            etTime.setText(getIntent().getExtras().getString("ReminderTime"));
            etNote.setText(getIntent().getExtras().getString("ReminderNote"));
            etReminder.setText(getIntent().getExtras().getString("ReminderReapeat"));
        } else {
            Toast.makeText(ActivityUpdateReminder.this, "Data not found", Toast.LENGTH_SHORT).show();
        }
        dbHelper = new DbHelper(ActivityUpdateReminder.this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etTime.setOnClickListener(this);
        etDate.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        etReminder.setOnClickListener(this);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_update_reminder_et_date:
                openDatePicker();
                break;

            case R.id.activity_update_reminder_et_time:
                openTimePIcker();
                break;

            case R.id.activity_update_reminder_update:
                updateReminder();
                if (calSet.compareTo(calNow) <= 0) {
                    Toast.makeText(getApplicationContext(),"Invalid Date/Time.Please Re-enter", Toast.LENGTH_LONG).show();
                }
                else{
                int i= Integer.parseInt(strReminderId);
                    setAlarm(calSet,i,etReminder.getText().toString(),etReminder.getText().toString());
                }
                break;

            case R.id.activity_update_reminder_et_select:
                diaologSelectValue(getResources().getStringArray(R.array.repeat_day_array),etReminder);
                break;

        }
    }

    private void diaologSelectValue(final String[] stringArray, final EditText etReminder) {
        final Dialog dialog = new Dialog(ActivityUpdateReminder.this);
        dialog.setContentView(R.layout.dialog_list);
        ListView listView = (ListView) dialog.findViewById(R.id.dialog_list_listview);
        ArrayAdapter<String> selectedValue = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
        listView.setAdapter(selectedValue);
        final StringBuilder strBuilder = new StringBuilder();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strBuilder.append(stringArray[position].toString());
                // Toast.makeText(ActivityUpdateMedicine.this,selectedItem,Toast.LENGTH_SHORT).show();
               String strSelectedItem = strBuilder.toString();
                etReminder.setText(strSelectedItem);
                dialog.dismiss();
            }
        });
        dialog.show();



    }

    private void updateReminder()
    {

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Integer.parseInt(strReminderId), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        alarmManager.cancel(pendingIntent);
        dbHelper.updateReminder(strReminderId,etReminderName.getText().toString(),
                etReminderReason.getText().toString(),
                etDate.getText().toString(),
                etTime.getText().toString(),
                etReminder.getText().toString(),
                etNote.getText().toString());




        Toast.makeText(ActivityUpdateReminder.this,"Record updated succesfully", Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    private void openTimePIcker() {
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
                        calSet.set(Calendar.MINUTE, minute);
                        calSet.set(Calendar.SECOND, 0);
                        calSet.set(Calendar.MILLISECOND, 0);
                        etTime.setText(time);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

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
                        calSet.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        calSet.set(Calendar.YEAR, year);
                        calSet.set(Calendar.MONTH,monthOfYear );

                        etDate.setText(strTime);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setAlarm(Calendar targetCal, int id, String resone, String repeat){
        Log.i("setAlarm","called");
//        textAlarmPrompt.setText(
//                "\n\n***\n"
//                        + "Alarm is set@ " + targetCal.getTime() + "\n"
//                        + "***\n");
        Log.i("targettime",targetCal.getTime().toString());

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("R_name", etReminderName.getText().toString());
        editor.putString("R_id", String.valueOf(id));
        editor.putString("R_resone", etReminderReason.getText().toString());
        editor.putString("R_repeat",  etReminder.getText().toString());
        editor.commit();

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), id, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);
//        if(repeat.equals("Everyday")){
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),AlarmManager.INTERVAL_DAY ,pendingIntent);
//        }
//        if(repeat.equals("weekly")){
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7 ,pendingIntent);
//        }
//        if(repeat.equals("monthly")){
//            long monthly=  getDuration();
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),monthly ,pendingIntent);
//        }

        //alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);


    }
    private long getDuration(){
        // get todays date
        Calendar cal = Calendar.getInstance();
        // get current month
        int currentMonth = cal.get(Calendar.MONTH);

        // move month ahead
        currentMonth++;
        // check if has not exceeded threshold of december

        if(currentMonth > Calendar.DECEMBER){
            // alright, reset month to jan and forward year by 1 e.g fro 2013 to 2014
            currentMonth = Calendar.JANUARY;
            // Move year ahead as well
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);
        }

        // reset calendar to next month
        cal.set(Calendar.MONTH, currentMonth);
        // get the maximum possible days in this month
        int maximumDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // set the calendar to maximum day (e.g in case of fEB 28th, or leap 29th)
        cal.set(Calendar.DAY_OF_MONTH, maximumDay);
        long thenTime = cal.getTimeInMillis(); // this is time one month ahead



        return (thenTime); // this is what you set as trigger point time i.e one month after

    }

}
