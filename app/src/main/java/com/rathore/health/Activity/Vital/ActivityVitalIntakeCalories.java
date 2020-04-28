package com.rathore.health.Activity.Vital;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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

//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayIntakeCalories;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayIntakeCalories;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayIntakeCalories;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

public class ActivityVitalIntakeCalories extends AppCompatActivity implements View.OnClickListener {


    private Toolbar toolbar;
    private EditText etDate,etTime,etIntakeCalories,etUnits;
    private Button icBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_intake_calories);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Add Intake Calories");
        etDate = (EditText)findViewById(R.id.activity_vital_intake_calories_et_date);
        etTime = (EditText)findViewById(R.id.activity_vital_intake_calories_et_time);
        etIntakeCalories = (EditText)findViewById(R.id.activity_vital_intake_calories_et_calories);
        etUnits = (EditText)findViewById(R.id.activity_vital_intake_calories_et_unit);
        icBtnSave = (Button)findViewById(R.id.activity_vital_intake_calories_btnsave);

        init();
    }

    private void init()
    {
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalIntakeCalories.this,R.drawable.ic_toolbar_back));
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalIntakeCalories.this,R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etIntakeCalories.setOnClickListener(this);
        etUnits.setOnClickListener(this);
        icBtnSave.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_vital_intake_calories_et_date:
                selectDate(etDate);
                break;
            case R.id.activity_vital_intake_calories_et_time:
                selectTime(etTime);
                break;

            case R.id.activity_vital_intake_calories_btnsave:
                DbHelper helper = new DbHelper(ActivityVitalIntakeCalories.this);

//                helper.test();
                helper.addVIntakeCalories(etDate.getText().toString(),
                        etTime.getText().toString(),
                        etIntakeCalories.getText().toString(),
                        etUnits.getText().toString());

                Toast.makeText(this, "IntakeCalories Added Succesfully!!!", Toast.LENGTH_SHORT).show();
                // onBackPressed();

                Intent intent=new Intent(ActivityVitalIntakeCalories.this, ActivityVitalDisplayIntakeCalories.class);
                startActivity(intent);
                break;

        }
    }

    private void selectTime(final EditText etTime) {

        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        //launch timepicker modal
        TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityVitalIntakeCalories.this,
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityVitalIntakeCalories.this,
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
