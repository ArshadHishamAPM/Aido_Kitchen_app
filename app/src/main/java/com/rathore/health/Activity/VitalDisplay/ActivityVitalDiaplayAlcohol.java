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
//import com.example.archi.health.Activity.Vital.ActivityVitalAlcohol;
//import com.example.archi.health.Adapter.AdapterVital.VitalAlcoholAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalAlcohol;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalAlcoholAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalAlcohol;
import com.rathore.health.Adapter.AdapterVital.VitalAlcoholAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDiaplayAlcohol extends AppCompatActivity implements View.OnClickListener{


    private RecyclerView lvAlcoholrecycleview;
    private Button btnAddDisplyBloodPressure1;
    List<HashMap<String, String>> alcoholArry;
    private Toolbar toolbar;
    private ImageView toolimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_diaplay_alcohol);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Alcohol List");
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Alcohol List");


        setSupportActionBar(toolbar);
        lvAlcoholrecycleview =(RecyclerView)findViewById(R.id.activity_vital_display_alcohol_rcyview);
        btnAddDisplyBloodPressure1 = (Button)findViewById(R.id.activity_vital_display_alcohol_btn_add);
        init();

    }
    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDiaplayAlcohol.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDiaplayAlcohol.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });
        lvAlcoholrecycleview.setOnClickListener(this);
        btnAddDisplyBloodPressure1.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDiaplayAlcohol.this);
        List<HashMap<String, String>> alcoholArry = helper.getVAlcohol();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvAlcoholrecycleview.setLayoutManager(mLayoutManager);
        lvAlcoholrecycleview.setItemAnimator(new DefaultItemAnimator());
        lvAlcoholrecycleview.setAdapter(new VitalAlcoholAdapter(ActivityVitalDiaplayAlcohol.this, alcoholArry));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_vital_display_alcohol_btn_add:
                Intent AddAlcohol = new Intent(ActivityVitalDiaplayAlcohol.this, ActivityVitalAlcohol.class);
                startActivity(AddAlcohol);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
