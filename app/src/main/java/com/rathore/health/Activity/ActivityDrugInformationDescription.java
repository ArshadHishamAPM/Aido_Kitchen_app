package com.rathore.health.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;

//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.R;

public class ActivityDrugInformationDescription extends AppCompatActivity
{


        private Toolbar toolbar;
        private TextView tvDescription;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_purpose_description);
            toolbar=findViewById(R.id.toolbar);
            tvDescription = (TextView)findViewById(R.id.activity_purpose_description_tv);
            setSupportActionBar(toolbar);
            init();
        }

        private void init() {
           // toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityDrugInformationDescription.this,R.drawable.ic_toolbar_back));
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//
//                }
//            });
            if (getIntent().getExtras() != null)
            {
                String title = getIntent().getExtras().getString("title");
                String description =  getIntent().getExtras().getString("detail");
//               toolbar.setTitle(title);
            //    TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            //     mTitle.setText(getString(R.string.txtdruginfo));
               // toolbar.setTitleTextColor(ContextCompat.getColor(ActivityDrugInformationDescription.this,R.color.white));
                tvDescription.setText(description);
            }
        }




        @Override
        public void onBackPressed() {
            super.onBackPressed();
        }
    }


