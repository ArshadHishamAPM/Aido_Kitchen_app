package com.rathore.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

//import com.pierfrancescosoffritti.aytplayersample.Adapter.HealthRecordAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Adapter.HealthRecordAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

public class ActivityHealthRecord extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddRecord;
    private RecyclerView lvHealthRecord;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_record);

        btnAddRecord = (Button)findViewById(R.id.act_health_record_add);
        lvHealthRecord = (RecyclerView)findViewById(R.id.activity_health_record_rcyv);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }


    @Override
    public void onResume() {
        super.onResume();

        DbHelper helper = new DbHelper(ActivityHealthRecord.this);
        List<HashMap<String, String>> users = helper.getHealthRecord();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        lvHealthRecord.setLayoutManager(mLayoutManager);
        lvHealthRecord.setItemAnimator(new DefaultItemAnimator());
        lvHealthRecord.setAdapter(new HealthRecordAdapter(getApplication(), users));

    }
    private void init()
    {
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityHealthRecord.this,R.drawable.ic_toolbar_back));
        toolbar.setTitleTextColor(ContextCompat.getColor(ActivityHealthRecord.this,R.color.white));
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.health_record));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnAddRecord.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.act_health_record_add:
                Intent addHealthRecord = new Intent(ActivityHealthRecord.this,ActivityAddHealthRecord.class);
                startActivity(addHealthRecord);
                break;
        }

    }
}
