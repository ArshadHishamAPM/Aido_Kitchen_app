package com.rathore.health.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

/**
 * Created by archi on 12/24/2016.
 */

public class ActivityUpdateMedicine extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText etStartdate, etEnddate, etMName, etResinForTalking, etDurationHour, etTotalquantity, etPrescribeby, etTypes, etRout, etDosage, etInstruction, etDurationDate;
    private ImageView ivSave;
    private Button btnUpdate, btnDelete;
    private String strId;
    private DbHelper dbHelper;
    private String strSelectedItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medications);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.update_medications));

       // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityUpdateMedicine.this, R.color.white));
        etMName = (EditText) findViewById(R.id.activity_update_medications_ettxtname);
        etResinForTalking = (EditText) findViewById(R.id.activity_update_medications_edittxt_resonfortalking);
        etDurationHour = (EditText) findViewById(R.id.activity_update_medications_edttxt_duratin_hours);
        btnUpdate = (Button) findViewById(R.id.activity_update_medications_btn_update);
        etStartdate = (EditText) findViewById(R.id.activity_update_medications_edttxt_startdate);
        etEnddate = (EditText) findViewById(R.id.activity_update_medications_edttxt_enddate);
        etTotalquantity = (EditText) findViewById(R.id.activity_update_medications_edttxt_totalquantity);
        etPrescribeby = (EditText) findViewById(R.id.activity_update_medications_edttxt_prescribe_by);
        //btnDelete = (Button) findViewById(R.id.activity_update_medications_btn_delete);
        btnUpdate = (Button) findViewById(R.id.activity_update_medications_btn_update);
        etTypes = (EditText) findViewById(R.id.activity_update_medications_et_type);
        etRout = (EditText) findViewById(R.id.activity_update_medications_et_route);
        etDosage = (EditText) findViewById(R.id.activity_update_medications_et_dosage);
        etInstruction = (EditText) findViewById(R.id.activity_update_medications_et_Instruction);
        etDurationDate = (EditText) findViewById(R.id.activity_update_medications_et_duratin_days);
        init();
    }

    private void init() {
        dbHelper = new DbHelper(ActivityUpdateMedicine.this);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityUpdateMedicine.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (getIntent().getExtras() != null) {
            strId = getIntent().getExtras().getString("m_id");
            etMName.setText(getIntent().getExtras().getString("m_name"));
            etDurationHour.setText(getIntent().getExtras().getString("m_d_hours"));
            etEnddate.setText(getIntent().getExtras().getString("m_enddate"));
            etPrescribeby.setText(getIntent().getExtras().getString("m_prescrobeby"));
            etResinForTalking.setText(getIntent().getExtras().getString("m_resontalk"));
            etStartdate.setText(getIntent().getExtras().getString("m_startdate"));
            etTotalquantity.setText(getIntent().getExtras().getString("m_quantity"));
            etTypes.setText(getIntent().getExtras().getString("m_type"));
            etInstruction.setText(getIntent().getExtras().getString("m_instrtion"));
            etDosage.setText(getIntent().getExtras().getString("m_dosage"));
            etRout.setText(getIntent().getExtras().getString("m_route"));
            etDurationDate.setText(getIntent().getExtras().getString("m_d_days"));
        }
        //btnDelete.setOnClickListener(this);
        etTypes.setOnClickListener(this);
        etRout.setOnClickListener(this);
        etDosage.setOnClickListener(this);
        etInstruction.setOnClickListener(this);
        etDurationDate.setOnClickListener(this);
        etStartdate.setOnClickListener(this);
        etEnddate.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.activity_update_medications_btn_delete:
//                deleteRecordComfirmation();
//                break;

            case R.id.activity_update_medications_et_type:
                //strSelectedItem = etTypes.getText().toString();
                diaologSelectValue(getResources().getStringArray(R.array.medicin_type), etTypes);
                break;

            case R.id.activity_update_medications_et_route:
                diaologSelectValue(getResources().getStringArray(R.array.medicin_route), etRout);
                break;

            case R.id.activity_update_medications_et_dosage:
                diaologSelectValue(getResources().getStringArray(R.array.medicin_dosage), etDosage);
                break;

            case R.id.activity_update_medications_et_Instruction:
                diaologSelectValue(getResources().getStringArray(R.array.medicin_instruction), etInstruction);
                break;

            case R.id.activity_update_medications_et_duratin_days:
                diaologSelectValue(getResources().getStringArray(R.array.medicin_duration), etDurationDate);
                break;

            case R.id.activity_update_medications_edttxt_startdate:
                opencalender(etStartdate);
                break;
            case R.id.activity_update_medications_edttxt_enddate:
                opencalender(etEnddate);
                break;

            case R.id.activity_update_medications_btn_update:
                updateRecoed();
                Toast.makeText(ActivityUpdateMedicine.this, "update re record", Toast.LENGTH_SHORT).show();
                break;


        }

    }

    private void updateRecoed() {
        String strName, strDurationHour, strEndDate, strPrescribeBy, strReasonForTalking, strStartDate, strTotalquantity, strType, strInstruction, strDoses, strRout, strDurationDay;
        strName = etMName.getText().toString();
        strDurationHour = etDurationHour.getText().toString();
        strEndDate = etEnddate.getText().toString();
        strPrescribeBy = etPrescribeby.getText().toString();
        strReasonForTalking = etResinForTalking.getText().toString();
        strStartDate = etStartdate.getText().toString();
        strTotalquantity = etTotalquantity.getText().toString();
        strType = etTypes.getText().toString();
        strInstruction = etInstruction.getText().toString();
        strRout = etRout.getText().toString();
        strDurationDay = etDurationDate.getText().toString();
        strDoses = etDosage.getText().toString();
        dbHelper.updateMedicines(strId,strName,strType,strRout,strDoses,strInstruction,strReasonForTalking,strDurationHour,strDurationDay,strStartDate,strEndDate,strTotalquantity,strPrescribeBy);
        Toast.makeText(ActivityUpdateMedicine.this,"Recored update", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    private void opencalender(final EditText etStartdate) {
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
                        etStartdate.setText(strTime);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    private void diaologSelectValue(final String[] stringArray, final EditText etTypes) {
        final Dialog dialog = new Dialog(ActivityUpdateMedicine.this);
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
                strSelectedItem = strBuilder.toString();
                etTypes.setText(strSelectedItem);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void deleteRecordComfirmation() {
        final AlertDialog.Builder aleBuilder = new AlertDialog.Builder(ActivityUpdateMedicine.this);
        aleBuilder.setMessage(getString(R.string.do_you_realy_want_to_delete));
        aleBuilder.setCancelable(false);
        aleBuilder.setTitle(getString(R.string.delete));
        aleBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteMedicinsRaw(strId);
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
}
