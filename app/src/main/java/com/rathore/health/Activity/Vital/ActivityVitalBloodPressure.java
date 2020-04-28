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

//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayBloodPressure;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayBloodPressure;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayBloodPressure;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

/**
 * Created by archi on 12/28/2016.
 */

public class ActivityVitalBloodPressure extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText etTime,etDate,etSystolic,etDiastolic;
    private Button BPaddbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_blood_presure);
        etTime = (EditText)findViewById(R.id.activity_vital_blood_et_time);
        etDate = (EditText)findViewById(R.id.activity_vital_blood_et_date);
        etSystolic = (EditText)findViewById(R.id.activity_vital_blood_et_systolic);
        etDiastolic = (EditText)findViewById(R.id.activity_vital_blood_et_diastolic);
        BPaddbtn = (Button)findViewById(R.id.activity_vital_blood_savebtn);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.add_blood_pressure));
        init();
    }

    private void init()
    {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalBloodPressure.this,R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalBloodPressure.this,R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etTime.setOnClickListener(this);
        etDate.setOnClickListener(this);
        etSystolic.setOnClickListener(this);
        etDiastolic.setOnClickListener(this);
        BPaddbtn.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_vital_blood_et_date:
                selelctDate(etDate);
                break;

            case R.id.activity_vital_blood_et_time:
               selectTine(etTime);
                break;

            case R.id.activity_vital_blood_savebtn:

                DbHelper helper = new DbHelper(ActivityVitalBloodPressure.this);

//                helper.test();
                helper.addVBloodPressure(etDate.getText().toString(),
                        etTime.getText().toString(),
                        etSystolic.getText().toString(),
                        etDiastolic.getText().toString());

                Toast.makeText(this, "BloodPressure Added Succesfully!!!", Toast.LENGTH_SHORT).show();
                // onBackPressed();

                Intent intent=new Intent(ActivityVitalBloodPressure.this, ActivityVitalDisplayBloodPressure.class);
                startActivity(intent);
                break;



        }


    }

    private void selectTine(final EditText etTime)
    {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        //launch timepicker modal
        TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityVitalBloodPressure.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        etTime.setText(time);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    private void selelctDate(final EditText etDate)
    {

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityVitalBloodPressure.this,
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
