package com.rathore.kitchen.Activity;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rathore.kitchen.Adapter.TimeTaskAdapter;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;
import com.rathore.kitchen.Utils.TimeTaskUtility;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//import android.support.v7.app.NotificationCompat;

/**
 * Created by Ravi Archi on 1/4/2017.
 */
public class TimeWizarAct extends AppCompatActivity {

    public Button mButtonsetTime;
    public ListView mListView;
    public Spinner mspSpinner;
    public ArrayList<TimeTaskUtility> arrayTaskList;
    String TimeTaskResult,TaskName,TaskMinut;
    NotificationCompat.Builder builder;
    DbHelper helper;
    TimeTaskAdapter adapter;
    public String status = "continue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timewizard);
        helper = new DbHelper(this);
        if(arrayTaskList == null) {
           // Toast.makeText(TimeWizarAct.this, "No Data", Toast.LENGTH_SHORT).show();
            arrayTaskList = helper.getAllTimeTaskRecord();

        }
        mButtonsetTime = (Button) findViewById(R.id.btn_setTime);
        mListView = (ListView) findViewById(R.id.lv_timewizard);
        mButtonsetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(TimeWizarAct.this);
//                LayoutInflater inflater = getLayoutInflater();
//                final View alertLayout = inflater.inflate(R.layout.customdialogsettime, null);
//                alert.setTitle("Add Time,Washer and dryer");
//                // this is set the view from XML inside AlertDialog
//                alert.setView(alertLayout);
//                // disallow cancel of AlertDialog on click of back button and outside touch
//                alert.setCancelable(false);
//                String[] fiilliste = getResources().getStringArray(R.array.TimeTask);
//                mspSpinner = (Spinner) alertLayout.findViewById(R.id.sp_TimeTask);
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TimeWizarAct.this,
//                        android.R.layout.simple_dropdown_item_1line, fiilliste);
//                mspSpinner.setAdapter(arrayAdapter);
//                mspSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        TimeTaskResult = parent.getItemAtPosition(position).toString();
//                        Log.v("TimeTask", TimeTaskResult);
//                        Toast.makeText(TimeWizarAct.this, TimeTaskResult, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        EditText mEditTextTimeTaskItem = (EditText) alertLayout.findViewById(R.id.edt_timetask);
//                        EditText mEditTextTimeTaskMinut = (EditText) alertLayout.findViewById(R.id.edt_timeTaskMinut);
//                        TaskName = mEditTextTimeTaskItem.getText().toString();
//                        TaskMinut = mEditTextTimeTaskMinut.getText().toString();
//                        long minit = Integer.parseInt(TaskMinut);
//
//                      //  arrayTaskList.add(new TimeTaskUtility(TimeTaskResult,TaskName,TaskMinut));
//                        helper.insert(new TimeTaskUtility(TimeTaskResult,TaskName,TaskMinut,status));
//
//                        arrayTaskList = helper.getAllTimeTaskRecord();
//
//                        Toast.makeText(TimeWizarAct.this, TaskName + "" + minit+""+TimeTaskResult, Toast.LENGTH_SHORT).show();
//
//                        //Log.d("hour", strhour);
//                        //setTimer(minit);
//                        long mm = minit * 60 * 1000;
//                        // int hh = hour * 3600 * 100;
//                        final CounterClass timer = new CounterClass(mm,1000);
//                        timer.start();
//                        if(arrayTaskList != null) {
//                            //  Toast.makeText(TimeWizarAct.this, "No Data", Toast.LENGTH_SHORT).show();
//                            arrayTaskList = helper.getAllTimeTaskRecord();
//                            adapter = new TimeTaskAdapter(TimeWizarAct.this, R.layout.activity_timetask_item,arrayTaskList);
//                            mListView.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
//                            // Log.v("DepartmentItem", GroceryList);
//                            //Toast.makeText(GroceryAct.this, GroceryList, Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                });
//                AlertDialog dialog = alert.create();
//                dialog.show();

                final Dialog dialog = new Dialog(TimeWizarAct.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.customdialogfortask);
                EditText editText = (EditText)dialog.findViewById(R.id.edt_timeTaskMinut) ;
                editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
                Button cancelbutton = (Button) dialog.findViewById(R.id.cancel);
                Button savebutton = (Button) dialog.findViewById(R.id.save);
                String[] fiilliste = getResources().getStringArray(R.array.TimeTask);
                mspSpinner = (Spinner) dialog.findViewById(R.id.sp_TimeTask);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TimeWizarAct.this,
                        android.R.layout.simple_dropdown_item_1line, fiilliste);
                mspSpinner.setAdapter(arrayAdapter);
                mspSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TimeTaskResult = parent.getItemAtPosition(position).toString();
                        Log.v("TimeTask", TimeTaskResult);
                        Toast.makeText(TimeWizarAct.this, TimeTaskResult, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                cancelbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                savebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText mEditTextTimeTaskItem = (EditText) dialog.findViewById(R.id.edt_timetask);
                        EditText mEditTextTimeTaskMinut = (EditText) dialog.findViewById(R.id.edt_timeTaskMinut);
                        TaskName = mEditTextTimeTaskItem.getText().toString();
                        TaskMinut = mEditTextTimeTaskMinut.getText().toString();
                       if(TaskMinut.length()>0){
                        long minit = Integer.parseInt(TaskMinut);

                      //  arrayTaskList.add(new TimeTaskUtility(TimeTaskResult,TaskName,TaskMinut));
                        helper.insert(new TimeTaskUtility(TimeTaskResult,TaskName,TaskMinut,status));

                        arrayTaskList = helper.getAllTimeTaskRecord();

                        Toast.makeText(TimeWizarAct.this, TaskName + "" + minit+""+TimeTaskResult, Toast.LENGTH_SHORT).show();

                        //Log.d("hour", strhour);
                        //setTimer(minit);
                        long mm = minit * 60 * 1000;
                        // int hh = hour * 3600 * 100;
                        final CounterClass timer = new CounterClass(mm,1000);
                        timer.start();
                        if(arrayTaskList != null) {
                            //  Toast.makeText(TimeWizarAct.this, "No Data", Toast.LENGTH_SHORT).show();
                            arrayTaskList = helper.getAllTimeTaskRecord();
                            adapter = new TimeTaskAdapter(TimeWizarAct.this, R.layout.activity_timetask_item,arrayTaskList);
                            mListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            // Log.v("DepartmentItem", GroceryList);
                            //Toast.makeText(GroceryAct.this, GroceryList, Toast.LENGTH_SHORT).show();

                        }}
                        dialog.dismiss();
                    }
                });

                dialog.show();


                if(arrayTaskList != null) {
                    //  Toast.makeText(TimeWizarAct.this, "No Data", Toast.LENGTH_SHORT).show();
                    arrayTaskList = helper.getAllTimeTaskRecord();
                    adapter = new TimeTaskAdapter(TimeWizarAct.this, R.layout.activity_timetask_item,arrayTaskList);
                    mListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    // Log.v("DepartmentItem", GroceryList);
                    //Toast.makeText(GroceryAct.this, GroceryList, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //textViewTime.setText("Completed.");
            String str = "Done";
            int size = arrayTaskList.size();
            int lastid = 0;
            for(int i = 0; i<=size; i++)
            {
                lastid = arrayTaskList.get(size-1).getId();
                Toast.makeText(TimeWizarAct.this, String.valueOf(lastid), Toast.LENGTH_SHORT).show();
            }
           helper.UpdateStatus(lastid,str);
           adapter.notifyDataSetChanged();
            addNotificationEnd();
        }
        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            //textViewTime.setText(hms);
            addNotification(hms,TaskName);
        }
    }
    private void addNotificationEnd() {
        builder =(NotificationCompat.Builder) new NotificationCompat.Builder(getApplication())
                .setSmallIcon(R.drawable.clock)
                .setContentTitle(TaskName)
                .setContentText("Done");
        Intent notificationIntent = new Intent(this, RecipesDetailsAct.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void addNotification(String time, String Taskname) {

        //  String title = bookmarkRecipesModelIntent.getTitle().toString();
        builder =(NotificationCompat.Builder) new NotificationCompat.Builder(getApplication())
                .setSmallIcon(R.drawable.clock)
                .setContentTitle(Taskname)
                .setContentText(time);
        Intent notificationIntent = new Intent(this, RecipesDetailsAct.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }
}
