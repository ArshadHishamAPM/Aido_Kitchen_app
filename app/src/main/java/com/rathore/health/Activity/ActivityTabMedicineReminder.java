package com.rathore.health.Activity;

//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
//
//import com.example.archi.health.Fragment.FragmentHome;
//import com.example.archi.health.Fragment.FragmentInvestigation;
//import com.example.archi.health.Fragment.FragmentReminder;
//import com.example.archi.health.Fragment.FragmentMedications;
//import com.example.archi.health.Fragment.FragmentVital;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Fragment.FragmentHome;
//import com.pierfrancescosoffritti.aytplayersample.Fragment.FragmentInvestigation;
//import com.pierfrancescosoffritti.aytplayersample.Fragment.FragmentMedications;
//import com.pierfrancescosoffritti.aytplayersample.Fragment.FragmentReminder;
//import com.pierfrancescosoffritti.aytplayersample.Fragment.FragmentVital;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Fragment.FragmentHome;
import com.rathore.health.Fragment.FragmentInvestigation;
import com.rathore.health.Fragment.FragmentMedications;
import com.rathore.health.Fragment.FragmentReminder;
import com.rathore.health.Fragment.FragmentVital;
import com.rathore.health.R;


public class ActivityTabMedicineReminder extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_medicie_reminder);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        // mTitle.setText(getString(R.string.medicine_and_reminder));
        mTitle.setText("Medicine and Reminder");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.activity_tab_medicine_reminder_viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.activity_tab_medicine_tab_layout);
        tabLayout.setTabTextColors(ContextCompat.getColor(ActivityTabMedicineReminder.this, R.color.white), ContextCompat.getColor(ActivityTabMedicineReminder.this, R.color.black));
        tabLayout.setupWithViewPager(viewPager);
        init();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentHome(), "Home");
        adapter.addFragment(new FragmentMedications(), "Medications");
        adapter.addFragment(new FragmentReminder(), "Reminder");
        adapter.addFragment(new FragmentVital(), "Vital");
        adapter.addFragment(new FragmentInvestigation(), "Investigation");
        viewPager.setAdapter(adapter);
    }

    private void init() {
        //toolbar.setTitle(getString(R.string.medicine_and_reminder));
        //toolbar.setTitleTextColor(ContextCompat.getColor(ActivityTabMedicineReminder.this, R.color.white));

        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityTabMedicineReminder.this, R.drawable.ic_toolbar_back));

        ImageView imageView = (ImageView) toolbar.findViewById(R.id.toolbar_save_iv1);
        imageView.setImageResource(R.drawable.medicineandreminder);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}



