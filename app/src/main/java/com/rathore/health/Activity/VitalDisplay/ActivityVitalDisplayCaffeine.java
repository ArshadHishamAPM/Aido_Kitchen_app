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
//import com.example.archi.health.Activity.Vital.ActivityVitalCoffine;
//import com.example.archi.health.Adapter.AdapterVital.VitalCaffeineAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Vital.ActivityVitalCoffine;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.AdapterVital.VitalCaffeineAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.Vital.ActivityVitalCoffine;
import com.rathore.health.Adapter.AdapterVital.VitalCaffeineAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityVitalDisplayCaffeine extends AppCompatActivity implements View.OnClickListener {


            private RecyclerView lvCaffeinerecycleview;
            private Button btnAddCaffeine;
            List<HashMap<String, String>> CaffeineArray;
            private Toolbar toolbar;
            private ImageView toolimg;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_vital_display_caffeine);
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                //toolbar.setTitle("Caffeine List");
                TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                mTitle.setText("Caffeine List");
                setSupportActionBar(toolbar);
                lvCaffeinerecycleview =(RecyclerView)findViewById(R.id.activity_vital_display_caffeine_rcyview);
                btnAddCaffeine = (Button)findViewById(R.id.activity_vital_display_caffeine_addbtn);
                init();

            }
    private void init() {
       // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityVitalDisplayCaffeine.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityVitalDisplayCaffeine.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
                Intent intent = new Intent(getApplicationContext(), ActivityAddVital.class);
                startActivity(intent);
            }
        });

        lvCaffeinerecycleview.setOnClickListener(this);
        btnAddCaffeine.setOnClickListener(this);

    }
    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(ActivityVitalDisplayCaffeine.this);
        List<HashMap<String, String>> CaffeineArray = helper.getVCoffine();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvCaffeinerecycleview.setLayoutManager(mLayoutManager);
        lvCaffeinerecycleview.setItemAnimator(new DefaultItemAnimator());
        lvCaffeinerecycleview.setAdapter(new VitalCaffeineAdapter(ActivityVitalDisplayCaffeine.this, CaffeineArray));
    }
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.activity_vital_display_caffeine_addbtn:
                        Intent AddBurnC = new Intent(ActivityVitalDisplayCaffeine.this, ActivityVitalCoffine.class);
                        startActivity(AddBurnC);
                        break;
                }
            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
