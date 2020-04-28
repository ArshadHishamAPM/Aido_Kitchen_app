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
//import com.example.archi.health.Activity.Vital.ActivityVitalPulse;
//import com.example.archi.health.Adapter.AdapterVital.VitalPulseAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalPulse;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalPulseAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalPulse;
import com.rathore.health.Adapter.AdapterVital.VitalPulseAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDisplayPulse extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView lvPulserecycleview;
    private Button btnAddpulse;
    List<HashMap<String, String>> PulseArray;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_display_pulse);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Pulse List");
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Pulse List");
        lvPulserecycleview =(RecyclerView)findViewById(R.id.activity_vital_display_pulse_rcyview);
        btnAddpulse = (Button)findViewById(R.id.activity_vital_display_pulse_addbtn);
        init();

    }
    private void init() {
       // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDisplayPulse.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDisplayPulse.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });
        lvPulserecycleview.setOnClickListener(this);
        btnAddpulse.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDisplayPulse.this);
        List<HashMap<String, String>> PulseArray = helper.getVPulse();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvPulserecycleview.setLayoutManager(mLayoutManager);
        lvPulserecycleview.setItemAnimator(new DefaultItemAnimator());
        lvPulserecycleview.setAdapter(new VitalPulseAdapter(ActivityVitalDisplayPulse.this, PulseArray));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_vital_display_pulse_addbtn:
                Intent AddIntakeC = new Intent(ActivityVitalDisplayPulse.this, ActivityVitalPulse.class);
                startActivity(AddIntakeC);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}