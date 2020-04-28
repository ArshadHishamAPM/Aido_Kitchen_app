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

//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayPulse;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayPulse;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayPulse;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

/**
 * Created by archi on 12/28/2016.
 */

public class ActivityVitalPulse extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button pBtnSave;
    private EditText etDate, etTime, etPulse, etUnit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_vital_pulse);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.add_pulse));
        etDate = (EditText) findViewById(R.id.activity_vital_pulse_et_date);
        etTime = (EditText) findViewById(R.id.activity_vital_pulse_et_time);
        etPulse = (EditText) findViewById(R.id.activity_vital_pulse_et_pulse);
        etUnit = (EditText) findViewById(R.id.activity_vital_pulse_et_unit);
        pBtnSave = (Button) findViewById(R.id.activity_vital_pulse_btnsave);
        init();
    }

    private void init() {
       // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalPulse.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalPulse.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etTime.setOnClickListener(this);
        etDate.setOnClickListener(this);
        etPulse.setOnClickListener(this);
        etUnit.setOnClickListener(this);
        pBtnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_vital_pulse_et_date:
                selectDate(etDate);
                break;
            case R.id.activity_vital_pulse_et_time:
                selectTime(etTime);
                break;
            case R.id.activity_vital_pulse_btnsave:
                DbHelper helper = new DbHelper(ActivityVitalPulse.this);

//                helper.test();
                helper.addVPulse(etDate.getText().toString(),
                        etTime.getText().toString(),
                        etPulse.getText().toString(),
                        etUnit.getText().toString());

                Toast.makeText(this, "Pulse Added Succesfully!!!", Toast.LENGTH_SHORT).show();
                // onBackPressed();

                Intent intent=new Intent(ActivityVitalPulse.this, ActivityVitalDisplayPulse.class);
                startActivity(intent);
                break;

        }
    }

    private void selectTime(final EditText etTime)
    {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        //launch timepicker modal
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        etTime.setText(time);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();


    }

    private void selectDate(final EditText etDate)
    {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityVitalPulse.this,
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
