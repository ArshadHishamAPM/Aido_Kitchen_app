package com.rathore.health.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ActivityUpdateInvestigation extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText etDate, etTime, etInvestigation, etResult, etUnit, etReaport;
    private Button btnUpdate, btnDelete;
    private DbHelper dbHelper;
    private String strId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_investigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView mImage = (ImageView) toolbar.findViewById(R.id.imagtool);
        mImage.setImageResource(R.drawable.healthicontool);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Update Investigation ");
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityUpdateInvestigation.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityUpdateInvestigation.this, R.drawable.ic_toolbar_back));
        etDate = (EditText) findViewById(R.id.activity_update_investigation_et_date);
        etTime = (EditText) findViewById(R.id.activity_update_investigation_et_time);
        etInvestigation = (EditText) findViewById(R.id.activity_update_investigation_et_investigation);
        etResult = (EditText) findViewById(R.id.activity_update_investigation_et_result);
        etUnit = (EditText) findViewById(R.id.activity_update_investigation_et_unit);
        etReaport = (EditText) findViewById(R.id.activity_update_investigation_et_reaport);
        btnUpdate = (Button) findViewById(R.id.activity_update_investigation_btn_update);
        //btnDelete = (Button) findViewById(R.id.activity_update_investigation_btn_delete);

        init();
    }

    private void init() {
        if (getIntent().getExtras() != null) {
            strId = getIntent().getExtras().getString("investigation_id");
            etDate.setText(getIntent().getExtras().getString("investigation_date"));
            etTime.setText(getIntent().getExtras().getString("investigation_time"));
            etInvestigation.setText(getIntent().getExtras().getString("investigation_name"));
            etResult.setText(getIntent().getExtras().getString("investigation_result"));
            etUnit.setText(getIntent().getExtras().getString("investigation_unit"));
            etReaport.setText(getIntent().getExtras().getString("investigation_repeat"));
        } else {
            Toast.makeText(ActivityUpdateInvestigation.this, "Data not found", Toast.LENGTH_SHORT).show();
        }
        dbHelper = new DbHelper(ActivityUpdateInvestigation.this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etInvestigation.setOnClickListener(this);
        etResult.setOnClickListener(this);
        etUnit.setOnClickListener(this);
        etReaport.setOnClickListener(this);
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
            case R.id.activity_update_investigation_et_date:
                openDatePicker();
                break;

            case R.id.activity_update_investigation_et_time:
                openTimePIcker();
                break;

            case R.id.activity_update_investigation_btn_update:
                updateReminder();
                break;

//            case R.id.activity_update_investigation_btn_delete:
//                deleteRecordComfirmation();
//                break;


            case R.id.activity_update_investigation_et_unit:
                diaologSelectValue(getResources().getStringArray(R.array.investigation_unit_type),etUnit);
                break;

            case R.id.activity_update_investigation_et_reaport:
                diaologSelectValue(getResources().getStringArray(R.array.investigation_repeat_type),etReaport);
                break;

        }
    }

    private void diaologSelectValue(final String[] stringArray, final EditText etReminder) {
        final Dialog dialog = new Dialog(ActivityUpdateInvestigation.this);
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
        dbHelper.updateInvestigation(strId,etDate.getText().toString(),
                etTime.getText().toString(),
                etInvestigation.getText().toString(),
                etResult.getText().toString(),
                etUnit.getText().toString(),
                etReaport.getText().toString());
        Toast.makeText(ActivityUpdateInvestigation.this,"Record updated succesfully", Toast.LENGTH_SHORT).show();
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
                        etTime.setText(time);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    private void deleteRecordComfirmation() {
        final AlertDialog.Builder aleBuilder = new AlertDialog.Builder(ActivityUpdateInvestigation.this);
        aleBuilder.setMessage(getString(R.string.do_you_realy_want_to_delete));
        aleBuilder.setCancelable(false);
        aleBuilder.setTitle(getString(R.string.delete));
        aleBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteInvestigation(strId);
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
                        etDate.setText(strTime);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}


