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
//import com.example.archi.health.Activity.Vital.ActivityVitalIntakeCalories;
//import com.example.archi.health.Adapter.AdapterVital.VitalIntakeCaloriesAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalIntakeCalories;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalIntakeCaloriesAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalIntakeCalories;
import com.rathore.health.Adapter.AdapterVital.VitalIntakeCaloriesAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDisplayIntakeCalories extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView lvIntakeCrecycleview;
    private Button btnAddIntakeC;
    List<HashMap<String, String>> IntakeCaloryArry;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_display_intake_calories);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("IntakeCaloies list");
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("IntakeCalories List");
        lvIntakeCrecycleview =(RecyclerView)findViewById(R.id.activity_vital_display_intake_calories_rcyview);
        btnAddIntakeC = (Button)findViewById(R.id.activity_vital_display_intake_calories_addbtn);
        init();

    }
    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDisplayIntakeCalories.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDisplayIntakeCalories.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });
        lvIntakeCrecycleview.setOnClickListener(this);
        btnAddIntakeC.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDisplayIntakeCalories.this);
        List<HashMap<String, String>> IntakeCaloryArry = helper.getVIntakeCalories();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvIntakeCrecycleview.setLayoutManager(mLayoutManager);
        lvIntakeCrecycleview.setItemAnimator(new DefaultItemAnimator());
        lvIntakeCrecycleview.setAdapter(new VitalIntakeCaloriesAdapter(ActivityVitalDisplayIntakeCalories.this, IntakeCaloryArry));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_vital_display_intake_calories_addbtn:
                Intent AddIntakeC = new Intent(ActivityVitalDisplayIntakeCalories.this, ActivityVitalIntakeCalories.class);
                startActivity(AddIntakeC);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
