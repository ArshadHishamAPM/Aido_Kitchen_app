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
//import com.example.archi.health.Activity.Vital.ActivityVitalWeight;
//import com.example.archi.health.Adapter.AdapterVital.VitalWeightAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalWeight;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalWeightAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalWeight;
import com.rathore.health.Adapter.AdapterVital.VitalWeightAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDisplayWeight extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView lvWeightcycleview;
    private Button btnAddweight;
    List<HashMap<String, String>> WeightArray;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_display_weight);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setTitle("Weight list");
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Weight List");
        lvWeightcycleview = (RecyclerView) findViewById(R.id.activity_vital_display_weight_rcyview);
        btnAddweight = (Button) findViewById(R.id.activity_vital_display_weight_btnadd);
        init();

    }
    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDisplayWeight.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDisplayWeight.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });

        lvWeightcycleview.setOnClickListener(this);
        btnAddweight.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDisplayWeight.this);
        List<HashMap<String, String>> WeightArray = helper.getVWeight();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvWeightcycleview.setLayoutManager(mLayoutManager);
        lvWeightcycleview.setItemAnimator(new DefaultItemAnimator());
        lvWeightcycleview.setAdapter(new VitalWeightAdapter(ActivityVitalDisplayWeight.this, WeightArray));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_vital_display_weight_btnadd:
                Intent AddWeight = new Intent(ActivityVitalDisplayWeight.this, ActivityVitalWeight.class);
                startActivity(AddWeight);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
