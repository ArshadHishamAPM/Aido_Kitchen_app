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
import android.widget.TextView;

//import com.example.archi.health.Activity.ActivityAddVital;
//import com.example.archi.health.Activity.Vital.ActivityVitalTemparature;
//import com.example.archi.health.Adapter.AdapterVital.VitalTemperatureAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalTemparature;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalTemperatureAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalTemparature;
import com.rathore.health.Adapter.AdapterVital.VitalTemperatureAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDisplayTemperature extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView lvTemprecycleview;
    private Button btnAddtemp;
    List<HashMap<String, String>> TempArray;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_display_temperature);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Temperature List");
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Temperature List");
        lvTemprecycleview = (RecyclerView) findViewById(R.id.activity_vital_display_temperature_rcyview);
        btnAddtemp = (Button) findViewById(R.id.activity_vital_display_temperature_btnadd);
        init();

    }
    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDisplayTemperature.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDisplayTemperature.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });
        lvTemprecycleview.setOnClickListener(this);
        btnAddtemp.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDisplayTemperature.this);
        List<HashMap<String, String>> TempArray = helper.getVTemperature();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvTemprecycleview.setLayoutManager(mLayoutManager);
        lvTemprecycleview.setItemAnimator(new DefaultItemAnimator());
        lvTemprecycleview.setAdapter(new VitalTemperatureAdapter(ActivityVitalDisplayTemperature.this, TempArray));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_vital_display_temperature_btnadd:
                Intent AddIntakeC = new Intent(ActivityVitalDisplayTemperature.this, ActivityVitalTemparature.class);
                startActivity(AddIntakeC);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
