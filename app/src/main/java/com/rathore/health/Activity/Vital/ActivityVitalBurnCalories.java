package com.rathore.health.Activity.Vital;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayBurnCalories;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayBurnCalories;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayBurnCalories;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

/**
 * Created by archi on 12/28/2016.
 */

public class ActivityVitalBurnCalories extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText etDate,etTime,etBurnCalories,etUnits;
    private Button bcBtnsave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_bun_calories);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        etDate = (EditText)findViewById(R.id.activity_vital_burn_calories_et_date);
        etTime = (EditText)findViewById(R.id.activity_vital_burn_calories_et_time);
        etBurnCalories = (EditText)findViewById(R.id.activity_vital_burn_calories_et_calories);
        etUnits = (EditText)findViewById(R.id.activity_vital_burn_calories_et_unit);
        bcBtnsave = (Button)findViewById(R.id.activity_vital_burn_calories_btnsave);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.add_burn_calories));
        init();
    }

    private void init()
    {
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalBurnCalories.this,R.drawable.ic_toolbar_back));
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalBurnCalories.this,R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etBurnCalories.setOnClickListener(this);
        etUnits.setOnClickListener(this);
        bcBtnsave.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
       switch (v.getId())
       {
           case R.id.activity_vital_burn_calories_et_date:
               selectDate(etDate);
               break;
           case R.id.activity_vital_burn_calories_et_time:
               selectTime(etTime);
               break;
           case R.id.activity_vital_burn_calories_btnsave:

               DbHelper helper = new DbHelper(ActivityVitalBurnCalories.this);

//                helper.test();
               helper.addVBurnCalories(etDate.getText().toString(),
                       etTime.getText().toString(),
                       etBurnCalories.getText().toString(),
                       etUnits.getText().toString());

               Toast.makeText(this, "BurnCalories Added Succesfully!!!", Toast.LENGTH_SHORT).show();
               // onBackPressed();

               Intent intent=new Intent(ActivityVitalBurnCalories.this, ActivityVitalDisplayBurnCalories.class);
               startActivity(intent);
               break;


       }
    }

    private void selectTime(final EditText etTime) {

        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        //launch timepicker modal
        TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityVitalBurnCalories.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        etTime.setText(time);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    private void selectDate(final EditText etDate) {

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityVitalBurnCalories.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        etDate.setText(selectDate);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
