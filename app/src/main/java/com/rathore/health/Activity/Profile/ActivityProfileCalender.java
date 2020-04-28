package com.rathore.health.Activity.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.archi.health.Activity.Profile.Calendar.MainCalendar;
//import com.example.archi.health.Adapter.Profile.ProfileCalenderAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.example.archi.health.model.ProfileAddValues;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
//
//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.Calendar.MainCalendar;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.Profile.ProfileCalenderAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.model.ProfileAddValues;
import com.rathore.health.R;
import com.rathore.health.Activity.Profile.Calendar.MainCalendar;
import com.rathore.health.Adapter.Profile.ProfileCalenderAdapter;
import com.rathore.health.DbHelper.DbHelper;
//import com.rathore.health.R;
import com.rathore.health.model.ProfileAddValues;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 12/29/2016.
 */

public class ActivityProfileCalender extends AppCompatActivity implements View.OnClickListener {
    private String strDay, strMonth, strYear, strSelectedDate;
    private TextView tvHeaderDay, tvHeaderMonth, tvHeaderYear;
    private ArrayList<String> numberList;
    private ProfileCalenderMainAdapter profileCalenderMainAdapter;
    private ViewPager calanderViewPager;
    private Handler handler;
    private Runnable runnable;
    public DbHelper dbHelper;
    private ImageView ivPagerBack, ivPagerForward, ivHeaderBack, ivCalenderView;
    private Calendar calendar;
    public static boolean counter = true;
    public static int calenderPosition;
    static int flip = 0;
    ProfileCalenderAdapter profileCalenderAdapter;

    private int savePosition;
    private RecyclerView recyclerViewPager;
    List<HashMap<String, String>> profileCalenderDataArray;
    public static ArrayList<ProfileAddValues> arrayListillness;
    //public static Map<Integer,ProfileAddValues> arrayListillness1;
    public static String strProfileUniqId, strProfleName;
    ProfileCalenderMainPager mAdapate;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_calnder_main);
        numberList = new ArrayList<>();

        tvHeaderDay = (TextView) findViewById(R.id.activity_profile_calender_tv_header_day);
        tvHeaderMonth = (TextView) findViewById(R.id.activity_profile_calender_tv_header_month);
        tvHeaderYear = (TextView) findViewById(R.id.activity_profile_calender_tv_header_year);
        calanderViewPager = (ViewPager) findViewById(R.id.activity_profile_calender_main_viewPager);
        calanderViewPager.setOffscreenPageLimit(1);
        ivPagerBack = (ImageView) findViewById(R.id.activity_profile_calender_iv_calender_header_back);
        ivPagerForward = (ImageView) findViewById(R.id.activity_profile_calender_iv_calender_header_forward);
        ivHeaderBack = (ImageView) findViewById(R.id.activity_profile_calender_header_home);
        ivCalenderView = (ImageView) findViewById(R.id.activity_profile_calender_header_calender);
        profileCalenderMainAdapter = new ProfileCalenderMainAdapter(ActivityProfileCalender.this);
        profileCalenderMainAdapter.notifyDataSetChanged();
        ProfileCalenderMainPager mAdapater = new ProfileCalenderMainPager(profileCalenderMainAdapter);
        mAdapater.notifyDataSetChanged();
        calanderViewPager.setAdapter(mAdapater);
        //calanderViewPager.setAdapter(profileCalenderMainAdapter);
        dbHelper = new DbHelper(ActivityProfileCalender.this);
        profileCalenderAdapter = new ProfileCalenderAdapter(ActivityProfileCalender.this);




        //new changes
        //profileCalenderAdapter=new ProfileCalenderAdapter(this);


        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();

        profileCalenderDataArray = dbHelper.getProfileCalenderData(strProfileUniqId);
        if (MainCalendar.calanderSelectedDate.equals("")) {
            return;
        } else {
            String selectdeDate[] = MainCalendar.calanderSelectedDate.split("-");
            int day = Integer.parseInt(selectdeDate[0]);
            int month = Integer.parseInt(selectdeDate[1]);
            int year = Integer.parseInt(selectdeDate[2]);
            Log.i("dateandtime", String.valueOf(day) + String.valueOf(month) + String.valueOf(year));
            // MainCalendar.calanderSelectedDate
            calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, year); // Add 5 years to current year
            calendar.set(Calendar.DATE, day); // Add 5 days to current date
            calendar.set(Calendar.MONTH, month-1); // Add 5 months to current month

            strSelectedDate = day + "-" + month + "-" + year;

            Log.i("selectdateev", calendar.getTime().toString());

            setCurrentDate();
            setCurrentDateAndTime();
            //init();

        }

    }

    private void init() {
        calendar = Calendar.getInstance();

        profileCalenderDataArray = dbHelper.getProfileCalenderData(strProfileUniqId);

        if (getIntent().getExtras() != null) {
            strProfileUniqId = getIntent().getExtras().getString("profileID");
            strProfleName = getIntent().getExtras().getString("hr_name");
        }


        setCurrentDate();
        numberList.clear();
        for (int i = 0; i < 100; i++) {
            numberList.add("" + i);
        }
        savePosition = numberList.size() / 2;
        calanderViewPager.setCurrentItem(numberList.size() / 2);
        ivPagerBack.setOnClickListener(this);
        ivPagerForward.setOnClickListener(this);
        ivCalenderView.setOnClickListener(this);
        ivHeaderBack.setOnClickListener(this);
        //calanderViewPager.setOffscreenPageLimit(0);
        calanderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private static final float thresholdOffset = 0.5f;
            private static final int thresholdOffsetPixels = 1;
            private boolean scrollStarted, checkDirection;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i("positionoffset",String.valueOf(positionOffset));
                //Log.i("positionoffset1",String.valueOf(positionOffsetPixels));

                if (thresholdOffset > positionOffset && positionOffsetPixels > thresholdOffsetPixels) {
                } else {
                }
                checkDirection = false;

            }

            @Override
            public void onPageSelected(int position) {



                if (flip == 0) {
                    if (counter == true) {
                        Log.i("counter", String.valueOf(counter));
                        if (savePosition > position) {
                            calendar.add(Calendar.DATE, -1);
                            Log.i("position is less", String.valueOf(position));
                            setCurrentDate();
                        } else if (savePosition < position) {
                            calendar.add(Calendar.DATE, +1);
                            Log.i("position is high", String.valueOf(position));
                            setCurrentDate();
                        } else {
                            calendar.add(Calendar.DATE, +0);
                            Log.i("position is equal", String.valueOf(position));
                            setCurrentDate();
                        }
                        Log.i("calenderPosition", String.valueOf(calenderPosition) + "   " + String.valueOf(position));
                        calenderPosition = position;
                        counter = false;
                        setCurrentDateAndTime();
                    } else {
                        Log.i("calenderPosition", String.valueOf(calenderPosition) + "   " + String.valueOf(position));

                        Log.i("counter", String.valueOf(counter));
                        if (position > calenderPosition) {
                            Log.i("higher", "called");
                            calendar.add(Calendar.DATE, +1);
                            setCurrentDate();
                            //calanderViewPager.setCurrentItem(calanderViewPager.getCurrentItem()-1);
                            //ProfileCalenderMainAdapter profileCalenderMainAdapter=new ProfileCalenderMainAdapter(getApplicationContext());
                            //calanderViewPager.setAdapter( profileCalenderMainAdapter);
                            calenderPosition = position;
                        } else if (position < calenderPosition) {
                            Log.i("lower", "called");
                            calendar.add(Calendar.DATE, -1);
                            setCurrentDate();
                            calenderPosition = position;
                        } else {
                            Log.i("samePage", "called");
                            calendar.add(Calendar.DATE, +0);

                            setCurrentDate();
                            calenderPosition = position;
                        }

                        setCurrentDateAndTime();
                    }
                    Log.i("currentitem1", String.valueOf(calanderViewPager.getCurrentItem()));

                } else {
                    flip = 0;
                    Log.i("positionn", String.valueOf(position));
                    setCurrentDateAndTime();

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    private void setCurrentDate() {
        Log.i("selectdateev", calendar.getTime().toString());
        strYear = String.valueOf(calendar.get(Calendar.YEAR));
        int findLeapYear = (calendar.get(Calendar.YEAR)) % 4;
        Log.i("dDate", String.valueOf(calendar.get(Calendar.DATE)));
        //Log.i("Month",String.valueOf(calendar.get(Calendar.MONTH)));
        strMonth = String.valueOf(calendar.get(Calendar.MONTH));
        int currentMonth = calendar.get(Calendar.MONTH);
        if (currentMonth == 0 || currentMonth == 2 || currentMonth == 4 || currentMonth == 6 || currentMonth == 7 || currentMonth == 9 || currentMonth == 11) {

            if (calendar.get(Calendar.DATE) > 31) {
                calendar.set(Calendar.MONTH, +1);
            }
        } else if (currentMonth == 1) {

            if (findLeapYear == 0) {
                Log.i("februray", "leap year");
                if (calendar.get(Calendar.DATE) > 28) {
                    calendar.set(Calendar.MONTH, +1);
                } else {
                    Log.i("februray", "not a leap year");
                    if (calendar.get(Calendar.DATE) > 29) {
                        calendar.set(Calendar.MONTH, +1);
                    }
                }
            }

        } else {
            if (calendar.get(Calendar.DATE) > 30) {
                calendar.set(Calendar.MONTH, +1);
            }
        }
//        if (MainCalendar.calanderSelectedDate.equals("")) {
//            calendar.set(Calendar.MONTH, +1);
//
//            strMonth = String.valueOf(calendar.get(Calendar.MONTH));
//            Log.i("month",String.valueOf(strMonth));
//
//        } else {
//            strMonth = String.valueOf(calendar.get(Calendar.MONTH));
//            Log.i("monthh",String.valueOf(strMonth));
//        }
        int month = calendar.get(Calendar.MONTH);
        strDay = String.valueOf(calendar.get(Calendar.DATE));

        Log.i("currentdate", strDay + "  " + String.valueOf(month) + "  " + strYear);

        //int month = Integer.parseInt(strMonth);
        //int month=calendar.get(Calendar.MONTH);
        Log.i("month", String.valueOf(month));
        String strNewMonth = "";
        String monthNumber = "";
        switch (month) {
            case 0:
                monthNumber = String.valueOf(0);
                strNewMonth = "January";
                break;
            case 1:
                monthNumber = String.valueOf(1);
                strNewMonth = "february";
                break;
            case 2:
                monthNumber = String.valueOf(2);
                strNewMonth = "March";
                break;
            case 3:
                monthNumber = String.valueOf(3);
                strNewMonth = "April";
                break;
            case 4:
                monthNumber = String.valueOf(4);
                strNewMonth = "May";
                break;
            case 5:
                monthNumber = String.valueOf(5);
                strNewMonth = "June";
                break;
            case 6:
                monthNumber = String.valueOf(6);
                strNewMonth = "July";
                break;
            case 7:
                monthNumber = String.valueOf(7);
                strNewMonth = "August";
                break;
            case 8:
                monthNumber = String.valueOf(8);
                strNewMonth = "september";
                break;
            case 9:
                monthNumber = String.valueOf(9);
                strNewMonth = "October";
                break;
            case 10:
                monthNumber = String.valueOf(10);
                strNewMonth = "November";
                break;
            case 11:
                monthNumber = String.valueOf(11);
                strNewMonth = "December";
                break;
        }
        tvHeaderDay.setText(strDay);
        tvHeaderYear.setText(strYear);
        tvHeaderMonth.setText(strNewMonth);

        strSelectedDate = strDay + "-" + monthNumber + "-" + strYear;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_profile_calender_iv_calender_header_back:
                Log.i("backheader", "called");
                flip = 1;


                Log.i("headerdate", String.valueOf(calendar.get(Calendar.DATE)));
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                Log.i("headerdate1", String.valueOf(calendar.get(Calendar.DATE)));
                setCurrentDate();
                //setCurrentDateAndTime();
                //profileCalenderMainAdapter.notifyDataSetChanged();
                calanderViewPager.setCurrentItem(calanderViewPager.getCurrentItem() - 1, true);

                //setCurrentDateAndTime();
                Log.i("currentitem", String.valueOf(calanderViewPager.getCurrentItem()));
                //profileCalenderMainAdapter = new ProfileCalenderMainAdapter(ActivityProfileCalender.this);
                //ProfileCalenderMainAdapter profileCalenderMainAdapter=new ProfileCalenderMainAdapter(this);
                //ProfileCalenderMainPager profileCalenderMainPager=new ProfileCalenderMainPager(profileCalenderMainAdapter);
                //calanderViewPager.setAdapter( profileCalenderMainAdapter);
                //calanderViewPager.
                //calendar.add(Calendar.DATE,-1);
                //ProfileCalenderMainAdapter profileCalenderMainAdapter=new ProfileCalenderMainAdapter(this);
                //calanderViewPager.setAdapter( profileCalenderMainAdapter);
                //profileCalenderMainAdapter.notifyDataSetChanged();
                //calanderViewPager.setAdapter( profileCalenderMainAdapter);


                break;

            case R.id.activity_profile_calender_iv_calender_header_forward:
                Log.i("forheader", "called");
                flip = 1;
                Log.i("currentitem", String.valueOf(calanderViewPager.getCurrentItem()));


                calendar.add(Calendar.DAY_OF_MONTH, +1);
                setCurrentDate();
                //profileCalenderMainAdapter.notifyDataSetChanged();
                calanderViewPager.setCurrentItem(calanderViewPager.getCurrentItem() + 1, true);


                //ProfileCalenderMainAdapter profileCalenderMainAdapter1=new ProfileCalenderMainAdapter(this);
                // profileCalenderMainAdapter.notifyDataSetChanged();
                //calanderViewPager.setAdapter( profileCalenderMainAdapter);
                //calendar.add(Calendar.DATE,+1);
                //setCurrentDate();
                break;

            case R.id.activity_profile_calender_header_calender:
                Intent profileCalnderView = new Intent(ActivityProfileCalender.this, MainCalendar.class);
                startActivity(profileCalnderView);
                break;

            case R.id.activity_profile_calender_header_home:
                onBackPressed();
                break;

        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ActivityProfileCalender Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class ProfileCalenderMainAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public ProfileCalenderMainAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {

            return numberList.size();
            //return POSITION_NONE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            boolean b = view == ((LinearLayout) object);
            //Log.i("viewfromobj",String.valueOf(b));
            return view == ((LinearLayout) object);

        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            Log.i("viewpagerposition", String.valueOf(position));


            View itemView = mLayoutInflater.inflate(R.layout.pager_calender_main, container, false);
            recyclerViewPager = (RecyclerView) itemView.findViewById(R.id.pager_calender_main_recycle);
            recyclerViewPager.setLayoutManager(new LinearLayoutManager(ActivityProfileCalender.this));
            //recyclerViewPager.setAdapter(profileCalenderAdapter);
            setCurrentDateAndTime();

            TextView tvReportSomething = (TextView) itemView.findViewById(R.id.pager_calender_main_tv_report_something);
            LinearLayout reportsomething = (LinearLayout) itemView.findViewById(R.id.reportlayout);
            TextView tvAttachedNotes = (TextView) itemView.findViewById(R.id.pager_calendar_tv_attched_file_note);


            // setCurrentDateAndTime();


            reportsomething.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayListillness = new ArrayList<ProfileAddValues>();
                    //arrayListillness1 = new HashMap<Integer,ProfileAddValues>();
                    int date = calendar.get(Calendar.DATE);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    String selectedDate = date + "-" + month + "-" + year;
                    Log.i("reportsomethingdate", selectedDate);
                    Intent profileTab = new Intent(ActivityProfileCalender.this, ActivityProfileReportSomethingTab.class);
                    profileTab.putExtra("selectedDate", selectedDate);
                    startActivity(profileTab);

                }
            });
//            tvAttachedNotes.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Dialog dialog = new Dialog(ActivityProfileCalender.this);
//                    dialog.setContentView(R.layout.dialog_profile_attached_note);
//                    dialog.show();
//                }
//            });
            container.addView(itemView);
//itemView.setTag(position);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = (int) v.getTag();// v here is your itemView
//               Log.i("itemviewposition",String.valueOf(position));
//                }});

            return itemView;
        }


//        @Override
//        public void notifyDataSetChanged() {
//            super.notifyDataSetChanged();
//        profileCalenderMainAdapter.notifyDataSetChanged();
//        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }


    }

    private void setCurrentDateAndTime() {
        Log.i("setcurrentdatee", "called");

        Log.i("listSize", String.valueOf(profileCalenderDataArray.toString()));
        profileCalenderDataArray=dbHelper.getProfileCalenderData(strProfileUniqId);

        List<HashMap<String, String>> selectedDateArray = new ArrayList<>();
        if (profileCalenderDataArray.size() > 0) {
            for (int i = 0; i < profileCalenderDataArray.size(); i++) {

                String uiqId = profileCalenderDataArray.get(i).get("key_profile_cal_uniq");
                String name = profileCalenderDataArray.get(i).get("key_profile_cal_name");
                String CalId = profileCalenderDataArray.get(i).get("key_profile_cal_id");
                String jsonString = profileCalenderDataArray.get(i).get("key_profile_cal_string");
                String date = profileCalenderDataArray.get(i).get("key_profile_cal_date");
                Log.i("profilecalenderArray", profileCalenderDataArray.get(i).toString());

                Log.i("dbdatee", date);
                Log.i("selectdatee", strSelectedDate);

                if (date.equals(strSelectedDate)) {

                    Log.i("dbcalled", "called");
                    selectedDateArray.add(profileCalenderDataArray.get(i));
                }


                //Log.i("selected date arrray",selectedDateArray.get(i).toString());

                else {
                    //recyclerViewPager.invalidate();
                    Log.i("dbcalled", "not called");

                }

            }
        }

        Log.i("selecteddatearray", selectedDateArray.toString());

        //ProfileCalenderAdapter profileCalenderAdapter = new ProfileCalenderAdapter(ActivityProfileCalender.this, selectedDateArray);
        //ProfileCalenderAdapter profileCalenderAdapter = new ProfileCalenderAdapter(ActivityProfileCalender.this,  profileCalenderDataArray.get(i));

        // ProfileCalenderAdapter
        // profileCalenderAdapter = (ProfileCalenderAdapter) recyclerViewPager.getAdapter();

        //ProfileCalenderAdapter profileCalenderAdapter = new ProfileCalenderAdapter(ActivityProfileCalender.this, selectedDateArray);

        profileCalenderAdapter.updateList(selectedDateArray);


        profileCalenderAdapter.notifyDataSetChanged();
        // recyclerViewPager.setLayoutManager(new LinearLayoutManager(ActivityProfileCalender.this));
        recyclerViewPager.setAdapter(profileCalenderAdapter);

        //recyclerViewPager.setAdapter(profileCalenderAdapter);
        MainCalendar.calanderSelectedDate = "";

//        ProfileCalenderAdapter profileCalenderAdapter = new ProfileCalenderAdapter(ActivityProfileCalender.this, selectedDateArray);
//        recyclerViewPager.setLayoutManager(new LinearLayoutManager(ActivityProfileCalender.this));
//        recyclerViewPager.setAdapter(profileCalenderAdapter);
//        profileCalenderAdapter.notifyDataSetChanged();
//        MainCalendar.calanderSelectedDate = "";
    }

    private class ProfileCalenderMainPager extends PagerAdapter {
        private PagerAdapter mPagerAdapter;

        ProfileCalenderMainPager(PagerAdapter pagerAdapter) {
            if (pagerAdapter == null) {
                throw new IllegalArgumentException("Did you forget initialize PagerAdapter?");
            }
            if ((pagerAdapter instanceof FragmentPagerAdapter || pagerAdapter instanceof FragmentStatePagerAdapter) && pagerAdapter.getCount() < 3) {
                throw new IllegalArgumentException("When you use FragmentPagerAdapter or FragmentStatePagerAdapter, it only supports >= 3 pages.");
            }
            mPagerAdapter = pagerAdapter;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("destroyitemposition", String.valueOf(position));
            mPagerAdapter.destroyItem(container, getVirtualPosition(position), object);
            if (mPagerAdapter.getCount() < 4) {
                mPagerAdapter.instantiateItem(container, getVirtualPosition(position));
            }
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            mPagerAdapter.finishUpdate(container);
        }

        @Override
        public int getCount() {
            //Log.i("totalitem",String.valueOf(Integer.MAX_VALUE));
            return Integer.MAX_VALUE;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mPagerAdapter.getPageTitle(getVirtualPosition(position));
        }

        @Override
        public float getPageWidth(int position) {
            return mPagerAdapter.getPageWidth(getVirtualPosition(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return mPagerAdapter.isViewFromObject(view, o);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // if (DEBUG) Log.d(TAG, "Instantiate: " + getVirtualPosition(position));
            Log.i("viewpagerposition1", String.valueOf(position));
            return mPagerAdapter.instantiateItem(container, getVirtualPosition(position));
        }

        @Override
        public Parcelable saveState() {
            return mPagerAdapter.saveState();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            mPagerAdapter.restoreState(state, loader);
        }

        @Override
        public void startUpdate(ViewGroup container) {
            mPagerAdapter.startUpdate(container);
        }

        int getVirtualPosition(int realPosition) {
            Log.i("realposiotion", String.valueOf(realPosition));
            Log.i("virtualposition", String.valueOf(realPosition % mPagerAdapter.getCount()));
            return realPosition % mPagerAdapter.getCount();
        }

        PagerAdapter getPagerAdapter() {
            return mPagerAdapter;
        }
    }

    public void update(List<HashMap<String, String>> profileCalenderDataArray, String date) {

        this.profileCalenderDataArray = profileCalenderDataArray;
        Log.i("updatelist",profileCalenderDataArray.toString() );
       // ActivityProfileCalender activityProfileCalender=new ActivityProfileCalender();


        //activityProfileCalender.setCurrentDateAndTime();
//this.strSelectedDate=date;
       // profileCalenderAdapter = new ProfileCalenderAdapter(ActivityProfileCalender.this);
        //profileCalenderMainAdapter = new ProfileCalenderMainAdapter(ActivityProfileCalender.this);
       // profileCalenderMainAdapter.notifyDataSetChanged();
        //ProfileCalenderMainPager mAdapater = new ProfileCalenderMainPager(profileCalenderMainAdapter);
        //mAdapater.notifyDataSetChanged();
        //calanderViewPager.setAdapter(mAdapater);
//        init();
     //  setCurrentDateAndTime();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("restar","calle");
        onResume();
        setCurrentDateAndTime();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("configuration","changed");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            //setCurrentDateAndTime();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            //setCurrentDateAndTime();
        }

    }

}