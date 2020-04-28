package com.rathore.health.Activity.Profile;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.Fragment.Profile.FragmentIllness;
//import com.example.archi.health.Fragment.Profile.FragmentSymptom;
//import com.example.archi.health.Fragment.Profile.FragmentTretment;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.Fragment.Profile.FragmentIllness;
//import com.pierfrancescosoffritti.aytplayersample.Fragment.Profile.FragmentSymptom;
//import com.pierfrancescosoffritti.aytplayersample.Fragment.Profile.FragmentTretment;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.Fragment.Profile.FragmentIllness;
import com.rathore.health.Fragment.Profile.FragmentSymptom;
import com.rathore.health.Fragment.Profile.FragmentTretment;
import com.rathore.health.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 12/30/2016.
 */

public class ActivityProfileReportSomethingTab extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPagerTab;
    private TabLayout tabLayout;
    public static TextView tvHeaderTotal;
    public static TextView ivSave, ivCancel;
    public static String selectedDate;
    public String strSelectedData;
    public DbHelper dbHelper;
    List<HashMap<String, String>> profileCalenderDataArray;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_report_something_tab);
        viewPagerTab = (ViewPager) findViewById(R.id.activity_profile_report_something_tab_pager);
        tabLayout = (TabLayout) findViewById(R.id.activity_profile_report_something_tab);
        tvHeaderTotal = (TextView) findViewById(R.id.activity_profile_report_something_tv_header_selected_item);
        ivSave = (TextView) findViewById(R.id.activity_profile_report_something_save);
        ivCancel = (TextView) findViewById(R.id.activity_profile_report_something_correct);
        dbHelper = new DbHelper(ActivityProfileReportSomethingTab.this);
        init();

    }


    private void init() {
        PagerAdapterTab adapter = new PagerAdapterTab(getSupportFragmentManager());
        adapter.addFragment(new FragmentSymptom(), "Symptom");
        adapter.addFragment(new FragmentIllness(), "illness");
        adapter.addFragment(new FragmentTretment(), "Treatment");
        viewPagerTab.setAdapter(adapter);
        tabLayout.setTabTextColors(ContextCompat.getColor(ActivityProfileReportSomethingTab.this, R.color.green), ContextCompat.getColor(ActivityProfileReportSomethingTab.this, R.color.white1));
        tabLayout.setupWithViewPager(viewPagerTab);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, tabLayout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setText(tab.getText());
            tab.setCustomView(relativeLayout);

            tab.select();
            if(i==2){
                View view = (View) relativeLayout.findViewById(R.id.tabdivider);
                view.setVisibility(View.GONE);
            }
        }
        ivCancel.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            selectedDate = getIntent().getExtras().getString("selectedDate");
            Log.i("datee",selectedDate.toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_profile_report_something_correct:
                onBackPressed();
                break;
            case R.id.activity_profile_report_something_save:
                if (ActivityProfileCalender.arrayListillness.size() <= 0) {
                    Toast.makeText(ActivityProfileReportSomethingTab.this, "please select the items", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    try {

                        JSONArray jsonArray1 = new JSONArray();
                        for (int i = 0; i < ActivityProfileCalender.arrayListillness.size(); i++) {
                            JSONObject jsonObject1 = new JSONObject();
                            jsonObject1.put("date", ActivityProfileCalender.arrayListillness.get(i).getStrDate());
                           // Log.i("reportdate",)
                            jsonObject1.put("name", ActivityProfileCalender.arrayListillness.get(i).getStrName());
                            jsonArray1.put(i, jsonObject1);
                        }
                        jsonObject.put("data", jsonArray1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    strSelectedData = jsonObject.toString();
                    Log.d("jai","date inserted :"+strSelectedData + "\n uniq id :"+ ActivityProfileCalender.strProfileUniqId + "\n name :"+ActivityProfileCalender.strProfleName  + "\n data :"+strSelectedData);
                    dbHelper.addProfileCalender(selectedDate, ActivityProfileCalender.strProfileUniqId, ActivityProfileCalender.strProfleName, strSelectedData);

                    onBackPressed();
                }
                break;

        }
    }

    private class postData extends AsyncTask<String, String, String> {
        String response;
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        //HttpPost httppost = new HttpPost("https://api.infermedica.com/v2/symptoms");
        HttpGet httpGet = new HttpGet("https://api.infermedica.com/v2/symptoms");
        ProgressDialog pd = new ProgressDialog(ActivityProfileReportSomethingTab.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setCancelable(false);
            pd.setMessage("please wait...");
            pd.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                //curl -X GET --header "Accept: application/json" --header "App-Id: 5b33dc26" --header "App-Key: 68a45c7b2e46de32a2b9cb530dd0f357" --header "Dev-Mode: true" "https://api.infermedica.com/v2/symptoms"
                httpGet.setHeader("Accept", "application/json");
                httpGet.setHeader("App-Id", "5b33dc26");
                httpGet.setHeader("App-Key", "68a45c7b2e46de32a2b9cb530dd0f357");
                HttpResponse response = httpclient.execute(httpGet);
                String responseStr = EntityUtils.toString(response.getEntity());
                Log.d("jai", "reponse of curl :" + responseStr);
                this.response = String.valueOf(response);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s.toString());
                Log.d("jai", "json object :" + jsonObject.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            pd.cancel();

        }
    }

    private class PagerAdapterTab extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public PagerAdapterTab(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }


        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("newdata","called");
        profileCalenderDataArray=dbHelper.getProfileCalenderData(ActivityProfileCalender.strProfileUniqId);
        Log.i("addindb",profileCalenderDataArray.toString());
        new ActivityProfileCalender().update(profileCalenderDataArray,strSelectedData);

    }
}

