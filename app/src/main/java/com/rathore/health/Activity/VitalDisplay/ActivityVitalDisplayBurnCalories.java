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
//import com.example.archi.health.Activity.Vital.ActivityVitalBurnCalories;
//import com.example.archi.health.Adapter.AdapterVital.VitalBurnCaloriesAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalBurnCalories;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalBurnCaloriesAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalBurnCalories;
import com.rathore.health.Adapter.AdapterVital.VitalBurnCaloriesAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDisplayBurnCalories extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView lvburncaloryrecycleview;
    private Button btnAddDisplyburncolary;
    List<HashMap<String, String>> BurnCaloryArry;
    private Toolbar toolbar;
    private ImageView toolimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_display_burn_calories);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Burn Calories List");
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Burn Calories List");
        setSupportActionBar(toolbar);
        lvburncaloryrecycleview =(RecyclerView)findViewById(R.id.activity_vital_display_burn_calories_rcyview);
        btnAddDisplyburncolary = (Button)findViewById(R.id.activity_vital_display_burn_calories_addbtn);
        init();

    }
    private void init() {
       // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDisplayBurnCalories.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDisplayBurnCalories.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });

        lvburncaloryrecycleview.setOnClickListener(this);
        btnAddDisplyburncolary.setOnClickListener(this);

    }
    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDisplayBurnCalories.this);
        List<HashMap<String, String>> BurnCaloryArry = helper.getVBurnCalories();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvburncaloryrecycleview.setLayoutManager(mLayoutManager);
        lvburncaloryrecycleview.setItemAnimator(new DefaultItemAnimator());
        lvburncaloryrecycleview.setAdapter(new VitalBurnCaloriesAdapter(ActivityVitalDisplayBurnCalories.this, BurnCaloryArry));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_vital_display_burn_calories_addbtn:
                Intent AddBurnC = new Intent(ActivityVitalDisplayBurnCalories.this, ActivityVitalBurnCalories.class);
                startActivity(AddBurnC);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}



