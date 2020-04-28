package com.rathore.health.Activity.Vital;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayHeight;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayHeight;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayHeight;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

/**
 * Created by archi on 12/28/2016.
 */

public class ActivityVitalHeight extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText etDate, etTime, etHeight, etUnit;
    private Button hBtnsave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_height);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.add_height));
        etDate = (EditText) findViewById(R.id.activity_vital_height_et_date);
        etTime = (EditText) findViewById(R.id.activity_vital_height_et_time);
        etHeight = (EditText) findViewById(R.id.activity_vital_height_et_height);
        etUnit = (EditText) findViewById(R.id.activity_vital_height_et_unit);
        hBtnsave = (Button) findViewById(R.id.activity_vital_height_btnsave);
        init();
    }

    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalHeight.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalHeight.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etHeight.setOnClickListener(this);
        etUnit.setOnClickListener(this);
        hBtnsave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_vital_height_et_date:
                selectDate(etDate);
                break;

            case R.id.activity_vital_height_et_time:
                selectTime(etTime);
                break;

            case R.id.activity_vital_height_et_unit:
                selectHeightUnit(getResources().getStringArray(R.array.vital_height_unit), etUnit);
                break;

            case R.id.activity_vital_height_btnsave:
                DbHelper helper = new DbHelper(ActivityVitalHeight.this);

//                helper.test();
                helper.addVHeight(etDate.getText().toString(),
                        etTime.getText().toString(),
                        etHeight.getText().toString(),
                        etUnit.getText().toString());

                Toast.makeText(this, "Height Added Succesfully!!!", Toast.LENGTH_SHORT).show();
                // onBackPressed();

                Intent intent = new Intent(ActivityVitalHeight.this, ActivityVitalDisplayHeight.class);
                startActivity(intent);
                break;

        }
    }

    private void selectHeightUnit(final String[] stringArray, final EditText etUnit) {

        final Dialog dialog = new Dialog(ActivityVitalHeight.this);
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

    private void selectTime(final EditText etTime) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        //launch timepicker modal
        TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityVitalHeight.this,
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityVitalHeight.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        etDate.setText(selectDate);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}