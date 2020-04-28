package com.rathore.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.archi.health.Activity.Vital.ActivityVitalAlcohol;
//import com.example.archi.health.Activity.Vital.ActivityVitalBloodPressure;
//import com.example.archi.health.Activity.Vital.ActivityVitalBurnCalories;
//import com.example.archi.health.Activity.Vital.ActivityVitalCoffine;
//import com.example.archi.health.Activity.Vital.ActivityVitalHeight;
//import com.example.archi.health.Activity.Vital.ActivityVitalIntakeCalories;
//import com.example.archi.health.Activity.Vital.ActivityVitalPulse;
//import com.example.archi.health.Activity.Vital.ActivityVitalTemparature;
//import com.example.archi.health.Activity.Vital.ActivityVitalWeight;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalAlcohol;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalBloodPressure;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalBurnCalories;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalCoffine;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalHeight;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalIntakeCalories;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalPulse;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalTemparature;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalWeight;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.Vital.ActivityVitalAlcohol;
import com.rathore.health.Activity.Vital.ActivityVitalBloodPressure;
import com.rathore.health.Activity.Vital.ActivityVitalBurnCalories;
import com.rathore.health.Activity.Vital.ActivityVitalCoffine;
import com.rathore.health.Activity.Vital.ActivityVitalHeight;
import com.rathore.health.Activity.Vital.ActivityVitalIntakeCalories;
import com.rathore.health.Activity.Vital.ActivityVitalPulse;
import com.rathore.health.Activity.Vital.ActivityVitalTemparature;
import com.rathore.health.Activity.Vital.ActivityVitalWeight;
import com.rathore.health.R;

/**
 * Created by archi on 12/28/2016.
 */

public class ActivityAddVital extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private LinearLayout llTemprature,llPulse,llBloodPressure,llHeight,llWeight,llIntakeCalories,llBurnCalories,llAlcohol,llCoffine;
    private Button btnAddVital;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView mImage = (ImageView) toolbar.findViewById(R.id.imagtool);
        mImage.setImageResource(R.drawable.eyesvital);
        mTitle.setText("Add Vital");
        llTemprature = (LinearLayout)findViewById(R.id.activity_vital_ll_temprature);
        llPulse = (LinearLayout)findViewById(R.id.activity_vital_ll_pulse);
        llAlcohol = (LinearLayout)findViewById(R.id.activity_vital_ll_alcohol);
        llBloodPressure = (LinearLayout)findViewById(R.id.activity_vital_ll_blood_pressure);
        llHeight = (LinearLayout)findViewById(R.id.activity_vital_ll_height);
        llWeight = (LinearLayout)findViewById(R.id.activity_vital_ll_weight);
        llIntakeCalories = (LinearLayout)findViewById(R.id.activity_vital_ll_intake_calories);
        llBurnCalories = (LinearLayout)findViewById(R.id.activity_vital_ll_burn_calories);
        llCoffine = (LinearLayout)findViewById(R.id.activity_vital_ll_coffine);
        setSupportActionBar(toolbar);
        init();


    }

    private void init() {
       // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityAddVital.this,R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityAddVital.this,R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        llAlcohol.setOnClickListener(this);
        llCoffine.setOnClickListener(this);
        llIntakeCalories.setOnClickListener(this);
        llBurnCalories.setOnClickListener(this);
        llWeight.setOnClickListener(this);
        llHeight.setOnClickListener(this);
        llBloodPressure.setOnClickListener(this);
        llTemprature.setOnClickListener(this);
        llPulse.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),ActivityTabMedicineReminder.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v)
    {
     switch (v.getId())
     {

         case R.id.activity_vital_ll_temprature:
             Intent tempreTure = new Intent(ActivityAddVital.this, ActivityVitalTemparature.class);
             startActivity(tempreTure);
             break;
         case R.id.activity_vital_ll_alcohol:
             Intent alcohol = new Intent(ActivityAddVital.this, ActivityVitalAlcohol.class);
             startActivity(alcohol);
             break;
         case R.id.activity_vital_ll_blood_pressure:
             Intent bloodPresure = new Intent(ActivityAddVital.this, ActivityVitalBloodPressure.class);
             startActivity(bloodPresure);
             break;

         case R.id.activity_vital_ll_intake_calories:
             Intent intakeCalories = new Intent(ActivityAddVital.this, ActivityVitalIntakeCalories.class);
             startActivity(intakeCalories);
             break;

         case R.id.activity_vital_ll_burn_calories:
             Intent burnCalories = new Intent(ActivityAddVital.this, ActivityVitalBurnCalories.class);
             startActivity(burnCalories);
             break;

         case R.id.activity_vital_ll_coffine:
             Intent coffine = new Intent(ActivityAddVital.this, ActivityVitalCoffine.class);
             startActivity(coffine);
             break;

         case R.id.activity_vital_ll_pulse:
             Intent pulse = new Intent(ActivityAddVital.this, ActivityVitalPulse.class);
             startActivity(pulse);
             break;

         case R.id.activity_vital_ll_weight:
             Intent weight = new Intent(ActivityAddVital.this, ActivityVitalWeight.class);
             startActivity(weight );
             break;

         case R.id.activity_vital_ll_height:
             Intent height = new Intent(ActivityAddVital.this, ActivityVitalHeight.class);
             startActivity(height);
             break;
     }
    }
}
