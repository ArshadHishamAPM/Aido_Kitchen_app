package com.rathore.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

//import androidx.appcompat.app.AppCompatActivity;

//import com.example.health.R;
//import com.example.health.utilities.Utils;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.utilities.Utils;
import com.rathore.health.R;
import com.rathore.health.utilities.Utils;

//import com.example.archi.health.R;
//import com.example.archi.health.utilities.Utils;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener
{

    private LinearLayout  llReminder, llHealthRecord, llHospitalFinder;
    RelativeLayout llDrugInfo;
    private Toolbar toolbarHome;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      //  MultiDex.install(this);

                llDrugInfo = (RelativeLayout) findViewById(R.id.activity_main_drug_info);
                llReminder = (LinearLayout) findViewById(R.id.activity_main_madicns_reminders);
                llHealthRecord = (LinearLayout) findViewById(R.id.activity_main_helthrecord);
                llHospitalFinder = (LinearLayout) findViewById(R.id.activity_main_hospitalfinder);
                // toolbarHome = (Toolbar) findViewById(R.id.toolbar_home);

                init();
            }

            private void init() {
                //setSupportActionBar(toolbarHome);
                //toolbarHome.setTitleTextColor(ContextCompat.getColor(ActivityHome.this, R.color.black));
                // toolbarHome.setNavigationIcon(ContextCompat.getDrawable(ActivityHome.this, R.mipmap.ic_launcher));
                llDrugInfo.setOnClickListener(this);
                llReminder.setOnClickListener(this);
                llHealthRecord.setOnClickListener(this);
                llHospitalFinder.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.activity_main_drug_info:
                        Intent intentDrugInfo = new Intent(ActivityHome.this, ActivityDrugInformation.class);
                        startActivity(intentDrugInfo);
                        break;
                    case R.id.activity_main_helthrecord:
                        Intent healthRecord = new Intent(ActivityHome.this, ActivityHealthRecord.class);
                        startActivity(healthRecord);
                        break;
                    case R.id.activity_main_hospitalfinder:
//                Intent hosFinder = new Intent(ActivityHome.this, MyLocationActivity.class);
                        Utils.WriteSharePrefrence(getApplicationContext(), "is_first", "1");
                        Intent hosFinder = new Intent(ActivityHome.this, ActivityHospitalFind.class);
                        startActivity(hosFinder);
                        break;
                    case R.id.activity_main_madicns_reminders:
                        Intent reminder = new Intent(ActivityHome.this, ActivityTabMedicineReminder.class);
                        startActivity(reminder);
                        break;
                }
            }
}


