package com.rathore.health.Activity.VitalDisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.archi.health.Activity.ActivityAddVital;
//import com.example.archi.health.Activity.Vital.ActivityVitalBloodPressure;
//import com.example.archi.health.Adapter.AdapterVital.VitalBloodPressureAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalBloodPressure;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalBloodPressureAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalBloodPressure;
import com.rathore.health.Adapter.AdapterVital.VitalBloodPressureAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDisplayBloodPressure extends AppCompatActivity implements View.OnClickListener{


    private RecyclerView lvbloodpressurerecycleview;
    private Button btnAddDisplybpressure;
    List<HashMap<String, String>> BpressureArray;
    private Toolbar toolbar;
    private ImageView toolimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_display_blood_pressure);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setTitle("Blood Pressure List");
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Blood Pressure List");
        setSupportActionBar(toolbar);
        lvbloodpressurerecycleview =(RecyclerView)findViewById(R.id.activity_vital_display_blood_pressure_rcyview);
        btnAddDisplybpressure = (Button)findViewById(R.id.activity_vital_display_blood_pressure_addbtn);
        init();

    }
    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDisplayBloodPressure.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDisplayBloodPressure.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });
        lvbloodpressurerecycleview.setOnClickListener(this);
        btnAddDisplybpressure.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDisplayBloodPressure.this);
        List<HashMap<String, String>> BpressureArray = helper.getVBloodPressure();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvbloodpressurerecycleview.setLayoutManager(mLayoutManager);
        lvbloodpressurerecycleview.setItemAnimator(new DefaultItemAnimator());
        lvbloodpressurerecycleview.setAdapter(new VitalBloodPressureAdapter(ActivityVitalDisplayBloodPressure.this, BpressureArray));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_vital_display_blood_pressure_addbtn:
                Intent AddBloodP = new Intent(ActivityVitalDisplayBloodPressure.this, ActivityVitalBloodPressure.class);
                startActivity(AddBloodP);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


