package com.rathore.health.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.Calendar;

public class ActivityAddMedications extends AppCompatActivity implements View.OnClickListener {


    private int mYear, mMonth, mDay;
    private Spinner TypeSpinner, RouteSpinner, DosageSpinner, InstractionSpinner, DurationDaysSpinner;
    private Toolbar toolbar;
    private EditText startdate, enddate, MName, ResinForTalking, DurationHour, totalquantity, prescribeby;
    private ImageView ivSave;
    private ImageView btnsave;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medications);

        MName = (EditText) findViewById(R.id.activity_add_medications_ettxtname);
        TypeSpinner = (Spinner) findViewById(R.id.activity_add_medications_spntype);
        RouteSpinner = (Spinner) findViewById(R.id.activity_add_medications_spnroute);
        DosageSpinner = (Spinner) findViewById(R.id.activity_add_medications_spndosage);
        InstractionSpinner = (Spinner) findViewById(R.id.activity_add_medications_spninstruction);
        ResinForTalking = (EditText) findViewById(R.id.activity_add_medications_edittxt_resonfortalking);
        DurationHour = (EditText) findViewById(R.id.activity_add_medications_edttxt_duratin_hours);
        DurationDaysSpinner = (Spinner) findViewById(R.id.activity_add_medications_spe_duratin_days);
        btnsave = (ImageView) findViewById(R.id.activity_add_medications_btnsave);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startdate = (EditText) findViewById(R.id.activity_add_medications_edttxt_startdate);
        enddate = (EditText) findViewById(R.id.activity_add_medications_edttxt_enddate);
        totalquantity = (EditText) findViewById(R.id.activity_add_medications_edttxt_totalquantity);
        prescribeby = (EditText) findViewById(R.id.activity_add_medications_edttxt_prescribe_by);
       // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityAddMedications.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityAddMedications.this, R.drawable.ic_toolbar_back));
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Add Medication");
        init();

    }

    private void init() {
        startdate.setOnClickListener(this);
        enddate.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> TypeAdapter = ArrayAdapter
                .createFromResource(this, R.array.medicin_type,
                        R.layout.customspinnerlist);
        ArrayAdapter<CharSequence> RouteAdapter = ArrayAdapter
                .createFromResource(this, R.array.medicin_route,
                        R.layout.customspinnerlist);

        ArrayAdapter<CharSequence> DosageAdapter = ArrayAdapter
                .createFromResource(this, R.array.medicin_dosage,
                        R.layout.customspinnerlist);

        ArrayAdapter<CharSequence> InstAdapter = ArrayAdapter
                .createFromResource(this, R.array.medicin_instruction,
                        R.layout.customspinnerlist);

        ArrayAdapter<CharSequence> DurtionAdapter = ArrayAdapter
                .createFromResource(this, R.array.medicin_duration,
                        R.layout.customspinnerlist);

        // Specify the layout to use when the list of choices appears
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RouteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DosageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        InstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DurtionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        TypeSpinner.setAdapter(TypeAdapter);
        RouteSpinner.setAdapter(RouteAdapter);
        DosageSpinner.setAdapter(DosageAdapter);
        InstractionSpinner.setAdapter(InstAdapter);
        DurationDaysSpinner.setAdapter(DurtionAdapter);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_add_medications_edttxt_startdate:
                setStartDate();
                break;

            case R.id.activity_add_medications_edttxt_enddate:
                setEndDate();
                break;

            case R.id.activity_add_medications_btnsave:
                if (!MName.getText().toString().equalsIgnoreCase("")) {
                    if (!DurationHour.getText().toString().equalsIgnoreCase("")) {

                        if (!startdate.getText().toString().equalsIgnoreCase("")) {


                            if (!enddate.getText().toString().equalsIgnoreCase("")) {

                                if (!totalquantity.getText().toString().equalsIgnoreCase("")) {

                                    if (!prescribeby.getText().toString().equalsIgnoreCase("")) {
                                        DbHelper helper = new DbHelper(getApplicationContext());
                                        helper.addMedicanes(MName.getText().toString(), TypeSpinner.getSelectedItem().toString(),
                                                RouteSpinner.getSelectedItem().toString(), DosageSpinner.getSelectedItem().toString(),
                                                InstractionSpinner.getSelectedItem().toString(),
                                                ResinForTalking.getText().toString(),
                                                DurationHour.getText().toString(),
                                                DurationDaysSpinner.getSelectedItem().toString(),
                                                startdate.getText().toString(),
                                                enddate.getText().toString(),
                                                totalquantity.getText().toString(),
                                                prescribeby.getText().toString());
                                        Toast.makeText(this, "Medicinses Added Succesfully!!!", Toast.LENGTH_SHORT).show();
                                        String url = "http://10.10.10.1/ApiHealth.php?apicall=createmedicine&name="+MName.getText().toString()+"&type="+TypeSpinner.getSelectedItem().toString()+"&route="+RouteSpinner.getSelectedItem().toString()+"&dosage="+DosageSpinner.getSelectedItem().toString()+"&instruction="+InstractionSpinner.getSelectedItem().toString()+"&resone="+ResinForTalking.getText().toString()+"&durationhour="+DurationHour.getText().toString()+"&durationdays="+DurationDaysSpinner.getSelectedItem().toString()+"&startdate="+startdate.getText().toString()+"&enddate="+ enddate.getText().toString()+"&totalquantity="+totalquantity.getText().toString()+"&prescribed="+prescribeby.getText().toString();
                                        url=url.replaceAll(" ", "%20");
                                        queue = Volley.newRequestQueue(getApplicationContext());
                                        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {
                                                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                                            }

                                        },
                                                new Response.ErrorListener() {

                                            @Override

                                            public void onErrorResponse(VolleyError error) {
                                                error.printStackTrace();
                                                // hide the progress dialog
                                                Toast.makeText(getApplicationContext() ,error.toString(), Toast.LENGTH_LONG).show();}
                                            //})

//                                                    {
//
//                                                        @Override
//                                                        protected Map<String, String> getParams() throws AuthFailureError {
//                                                            Map<String, String> params = new HashMap<String, String>();
//                                                            params.put("name",MName.getText().toString());
//                                                            params.put("type",TypeSpinner.getSelectedItem().toString());
//                                                            params.put("dosage",RouteSpinner.getSelectedItem().toString());
//                                                            params.put("instruction",InstractionSpinner.getSelectedItem().toString());
//                                                            params.put("durationhour",DurationHour.getText().toString());
//                                                            params.put("durationdays",startdate.getText().toString());
//                                                            params.put("startdate",MName.getText().toString());
//                                                            params.put("enddate",enddate.getText().toString());
//                                                            params.put("totalquantity",totalquantity.getText().toString());
//                                                            params.put("prescribed",prescribeby.getText().toString());
//                                                            return params;
//
//                                                        }
//
//                                                        @Override
//                                                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                                                            if (response.headers == null)
//                                                            {
//                                                                // cant just set a new empty map because the member is final.
//                                                                response = new NetworkResponse(
//                                                                        response.statusCode,
//                                                                        response.data,
//                                                                        Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
//                                                                        response.notModified,
//                                                                        response.networkTimeMs);
//
//
//                                                            }
//
//                                                            return super.parseNetworkResponse(response);
//                                                        }
//
//



                                                        });
// {
//
//
//        };

                                        queue.add(jsonObjReq);
                                        onBackPressed();
                                    } else {
                                        Toast.makeText(ActivityAddMedications.this, "please enter the prescribed By", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ActivityAddMedications.this, "please select the medications", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(ActivityAddMedications.this, "please select end date", Toast.LENGTH_SHORT).show();

                            }

                        } else {

                            Toast.makeText(ActivityAddMedications.this, "please select the date ", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(ActivityAddMedications.this, "please select the time", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityAddMedications.this, "please enter the medicine name", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void setEndDate() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAddMedications.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        enddate.setText(selectDate);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    private void setStartDate() {
        Toast.makeText(ActivityAddMedications.this, "seleted date", Toast.LENGTH_SHORT).show();

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAddMedications.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        startdate.setText(selectDate);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
