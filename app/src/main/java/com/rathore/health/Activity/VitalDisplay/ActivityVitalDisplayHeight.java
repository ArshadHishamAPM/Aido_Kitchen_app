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
//import com.example.archi.health.Activity.Vital.ActivityVitalHeight;
//import com.example.archi.health.Adapter.AdapterVital.VitalHeightAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalHeight;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalHeightAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalHeight;
import com.rathore.health.Adapter.AdapterVital.VitalHeightAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDisplayHeight extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView lvHeightrecycleview;
    private Button btnAddHeight;
    List<HashMap<String, String>> HeightArry;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_display_height);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Height List");
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Height List");
        lvHeightrecycleview =(RecyclerView)findViewById(R.id.activity_vital_display_height_rcyview);
        btnAddHeight = (Button)findViewById(R.id.activity_vital_display_height_addbtn);
        init();

    }
    private void init() {
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDisplayHeight.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDisplayHeight.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });

        lvHeightrecycleview.setOnClickListener(this);
        btnAddHeight.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDisplayHeight.this);
        List<HashMap<String, String>> HeightArry = helper.getVHeight();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvHeightrecycleview.setLayoutManager(mLayoutManager);
        lvHeightrecycleview.setItemAnimator(new DefaultItemAnimator());
        lvHeightrecycleview.setAdapter(new VitalHeightAdapter(ActivityVitalDisplayHeight.this, HeightArry));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_vital_display_height_addbtn:
                Intent AddHeight = new Intent(ActivityVitalDisplayHeight.this, ActivityVitalHeight.class);
                startActivity(AddHeight);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

