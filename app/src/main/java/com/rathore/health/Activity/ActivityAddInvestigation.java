package com.rathore.health.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

public class ActivityAddInvestigation extends AppCompatActivity  implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText etDate, etTime, etResult, etUnit, etInvestigation,etRepeat;
    private Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_investigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Add Investigation");
        btnSave = (Button) findViewById(R.id.activity_add_investigation_btn_save);
        etDate = (EditText) findViewById(R.id.activity_add_investigation_et_date);
        etTime = (EditText) findViewById(R.id.activity_add_investigation_et_time);
        etResult = (EditText) findViewById(R.id.activity_add_investigation_et_result);
        etUnit = (EditText) findViewById(R.id.activity_add_investigation_et_unit);
        etInvestigation = (EditText) findViewById(R.id.activity_add_investigation_et_investigation);
        etRepeat = (EditText) findViewById(R.id.activity_add_investigation_et_repeat);
        init();
    }

    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityAddInvestigation.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityAddInvestigation.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSave.setOnClickListener(this);
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etResult.setOnClickListener(this);
        etUnit.setOnClickListener(this);
        etRepeat.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_add_investigation_et_date:
                openDateInvestigation(etDate);
                break;

            case R.id.activity_add_investigation_et_time:
                openTimeInvestigation(etTime);
                break;

            case R.id.activity_add_investigation_et_repeat:
                openDialog(getResources().getStringArray(R.array.investigation_repeat_type),etRepeat);
                break;

            case R.id.activity_add_investigation_et_unit:
                openDialog(getResources().getStringArray(R.array.investigation_unit_type),etUnit);
                break;


            case R.id.activity_add_investigation_btn_save:
                if (!etDate.getText().toString().equalsIgnoreCase("")) {

                    if (!etTime.getText().toString().equalsIgnoreCase("")) {

                        if (!etInvestigation.getText().toString().equalsIgnoreCase("")) {

                            if (!etResult.getText().toString().equalsIgnoreCase("")) {


                                if (!etUnit.getText().toString().equalsIgnoreCase("")) {

                                    if (!etRepeat.getText().toString().equalsIgnoreCase("")) {

                                        DbHelper helper = new DbHelper(getApplicationContext());
                                        helper.addInvestigation(etDate.getText().toString(),
                                                etTime.getText().toString(),
                                                etInvestigation.getText().toString(),
                                                etResult.getText().toString(),
                                                etUnit.getText().toString(),
                                                etRepeat.getText().toString());
                                        Toast.makeText(this, "Reminder Added Succesfully!!!", Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }
                                    else {
                                        Toast.makeText(ActivityAddInvestigation.this, "please select Repeated Data", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(ActivityAddInvestigation.this, "please select Unit", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(ActivityAddInvestigation.this, "please Enter Result", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ActivityAddInvestigation.this, "please Enter Investigation", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ActivityAddInvestigation.this, "please Select Time", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityAddInvestigation.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void openDialog(final String[] stringArray, final EditText etUnit)
    {
        final Dialog dialog = new Dialog(ActivityAddInvestigation.this);
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
                etUnit.setText(strSelectedItem);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void openTimeInvestigation(final EditText etTime) {
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
                        etTime.setText(time);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void openDateInvestigation(final EditText etTime) {
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
                        etDate.setText(strTime);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}

