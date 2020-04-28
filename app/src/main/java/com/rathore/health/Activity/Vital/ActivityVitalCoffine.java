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

//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayCaffeine;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayCaffeine;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayCaffeine;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

/**
 * Created by archi on 12/28/2016.
 */

public class ActivityVitalCoffine extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText etDate, etTime, etCoffine, etUnit;
    private Button cBtnsave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_coffine);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        etDate = (EditText) findViewById(R.id.activity_vital_coffine_et_date);
        etTime = (EditText) findViewById(R.id.activity_vital_coffin_et_time);
        etCoffine = (EditText) findViewById(R.id.activity_vital_burn_calories_et_calories);
        etUnit = (EditText) findViewById(R.id.activity_vital_coffine_et_unit);
        cBtnsave = (Button) findViewById(R.id.activity_vital_coffine_btnsave);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Add Caffeine");
        init();
    }

    private void init() {


        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalCoffine.this,R.drawable.ic_toolbar_back));
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalCoffine.this,R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etCoffine.setOnClickListener(this);
        etUnit.setOnClickListener(this);
        cBtnsave.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.activity_vital_coffine_et_date:
                selectDate(etDate);
                break;

            case R.id.activity_vital_coffin_et_time:
                selectTime(etTime);
                break;

            case R.id.activity_vital_coffine_btnsave:

                DbHelper helper = new DbHelper(ActivityVitalCoffine.this);

//                helper.test();
                helper.addVCaffeine(etDate.getText().toString(),
                        etTime.getText().toString(),
                        etCoffine.getText().toString(),
                        etUnit.getText().toString());

                Toast.makeText(this, "Coffine Added Succesfully!!!", Toast.LENGTH_SHORT).show();
                // onBackPressed();

                Intent intent=new Intent(ActivityVitalCoffine.this, ActivityVitalDisplayCaffeine.class);
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
        TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityVitalCoffine.this,
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityVitalCoffine.this,
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
