package com.rathore.health.Activity.VitalUpdate;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

public class ActivityVitalUpdateIntakeCalories extends AppCompatActivity implements View.OnClickListener {


    private Toolbar toolbar;
    private EditText etDate, ettime, etical,etunit;
    private Button btnUpdate, btnDelete;
    private String strId;
    private DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_update_intake_calories);
        btnUpdate = (Button) findViewById(R.id.activity_vital_update_intake_calories_btn_update);
        //btnDelete = (Button) findViewById(R.id.activity_vital_update_intake_calories_btn_delete);
        etDate = (EditText) findViewById(R.id.activity_vital_update_intake_calories_et_date);
        ettime = (EditText) findViewById(R.id.activity_vital_update_intake_calories_et_time);
        etical = (EditText) findViewById(R.id.activity_vital_update_intake_calories_et_calories);
        etunit = (EditText) findViewById(R.id.activity_vital_update_intake_calories_et_unit);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("IntakeCalories Update");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalUpdateIntakeCalories.this, R.color.white));
        init();
    }

    private void init() {
        dbHelper = new DbHelper(ActivityVitalUpdateIntakeCalories.this);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalUpdateIntakeCalories.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getIntent().getExtras() != null) {
            strId = getIntent().getExtras().getString("v_intake_calories_id");
            etDate.setText(getIntent().getExtras().getString("v_intake_calories_date"));
            ettime.setText(getIntent().getExtras().getString("v_intake_calories_time"));
            etical.setText(getIntent().getExtras().getString("v_intake_calories_intake_calories"));
            etunit.setText(getIntent().getExtras().getString("v_intake_calories_unit"));
        }

        etDate.setOnClickListener(this);
        ettime.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        //btnDelete.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.activity_vital_update_intake_calories_et_date:
                selectDate(etDate);
                break;

            case R.id.activity_vital_update_intake_calories_et_time:
                selectTime(ettime);
                break;

//            case R.id.activity_vital_update_intake_calories_btn_delete:
//                deleteRecordComfirmation();
//                break;

            case R.id.activity_vital_update_intake_calories_btn_update:
                updateRecoed();
                break;

        }
    }

    private void updateRecoed() {
        String strdate, strtime, strcal,strunit;
        strdate = etDate.getText().toString();
        strtime = ettime.getText().toString();
        strcal = etical.getText().toString();
        strunit = etunit.getText().toString();
        dbHelper.updateAIntakeCalories(strId, strdate, strtime,strcal, strunit);
        Toast.makeText(ActivityVitalUpdateIntakeCalories.this, "Recored update", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    private void deleteRecordComfirmation() {
        final AlertDialog.Builder aleBuilder = new AlertDialog.Builder(ActivityVitalUpdateIntakeCalories.this);
        aleBuilder.setMessage(getString(R.string.do_you_realy_want_to_delete));
        aleBuilder.setCancelable(false);
        aleBuilder.setTitle(getString(R.string.delete));
        aleBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteVIntakeCalories(strId);
                onBackPressed();
            }
        });

        aleBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        aleBuilder.create();
        aleBuilder.show();
    }

    private void selectTime(final EditText etTime)
    {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        //launch timepicker modal
        TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityVitalUpdateIntakeCalories.this,
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityVitalUpdateIntakeCalories.this,
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