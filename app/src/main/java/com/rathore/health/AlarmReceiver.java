package com.rathore.health;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

//import com.example.archi.health.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
import com.rathore.health.DbHelper.DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rathore on 30/06/17.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {
    MediaPlayer mMediaPlayer;
    NotificationManager notificationManager;
    SharedPreferences sharedPreferences;
    PowerManager.WakeLock wakeLock;
    DbHelper dbHelper;
static int a=0;
    List<HashMap<String, String>> users;
    @SuppressLint("InvalidWakeLockTag")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Log.i("bootintent", "called");

            Calendar calendarr = Calendar.getInstance();
    DbHelper helper = new DbHelper(context);
    users = helper.getReminder();
    Log.i("saved Alarms", users.toString());
    for (int i = 0; i < users.size(); i++) {
        String title1 = users.get(i).get("r_name");
        String repeat1 = users.get(i).get("r_repeat");
        String id11 = users.get(i).get("r_id");
        long id1 = Long.parseLong(id11);
        String resone1 = users.get(i).get("r_for_r");
        String date = users.get(i).get("r_date");
        String[] date12 = date.split("-");
        String strDay = date12[0];
        String strMonth1 = date12[1];
        int strmonth = Integer.parseInt(strMonth1);

        String stryear1 = date12[2];

        String time = users.get(i).get("r_time");
        String[] hour1 = time.split(":");
        //Date date1 = StringToDateConvert(date);

        Calendar calendar = Calendar.getInstance();
        //int hour=calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
        calendar.set(Calendar.MONTH, strmonth - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(stryear1));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
        calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));

        Date date2 = new Date();
        Log.i("currentdatee", calendar.getTime().toString());
        Log.i("currentdatee1", calendarr.getTime().toString());


        if (repeat1.equals("Everyday")) {
            if (calendar.getTime().after(calendarr.getTime())) {

                // if (Integer.parseInt(hour1[0]) > calendarr.get(Calendar.HOUR_OF_DAY)){
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
                calendar.set(Calendar.MONTH, strmonth - 1);
                calendar.set(Calendar.YEAR, Integer.parseInt(stryear1));
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
                calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
                Log.i("alarmtime", calendar.getTime().toString());
                Intent intent1 = new Intent(context, AlarmReceiver.class);
                intent1.putExtra("id",id1);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)id1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);

//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("R_name", title1);
//                editor.putString("R_repeat", repeat1);
//                editor.putString("R_id", String.valueOf(id1));
//                editor.putString("R_resone", resone1);
//                editor.commit();

            }


            if (calendar.getTime().equals(calendarr.getTime())) {
                if (Integer.parseInt(hour1[0]) > calendarr.get(Calendar.HOUR_OF_DAY)) {
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
                    calendar.set(Calendar.MONTH, strmonth - 1);
                    calendar.set(Calendar.YEAR, Integer.parseInt(stryear1));
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
                    calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
                    Log.i("alarmtime", calendar.getTime().toString());
                    Intent intent1 = new Intent(context, AlarmReceiver.class);
                    intent1.putExtra("id",id1);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)id1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                    alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("R_name", title1);
//                    editor.putString("R_repeat", repeat1);
//                    editor.putString("R_id", String.valueOf(id1));
//                    editor.putString("R_resone", resone1);
//                    editor.commit();
                } else {
                    Log.i("calendertime", "have passed");
                }
            }
        } else if (repeat1.equals("weekly")) {

            while (calendar.getTime().after(calendarr.getTime()) || calendar.getTime().equals(calendarr.getTime())) {
                int dbdate = calendar.get(Calendar.DAY_OF_MONTH);
                int currdate = calendarr.get(Calendar.DAY_OF_MONTH);

                int diff = (currdate - dbdate);
                int currdate1 = 7 - diff;
                calendar.add(Calendar.DATE, currdate1);
            }

            Log.i("curtdatee", calendar.getTime().toString());

            if (calendar.getTime().after(calendarr.getTime())) {
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
                calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));

                Intent intent1 = new Intent(context, AlarmReceiver.class);
                intent1.putExtra("id",id1);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)id1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);

//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("R_name", title1);
//                editor.putString("R_repeat", repeat1);
//                editor.putString("R_id", String.valueOf(id1));
//                editor.putString("R_resone", resone1);
//                editor.commit();
            } else {
                if (calendar.getTime().equals(calendarr.getTime())) {
                    if (Integer.parseInt(hour1[0]) > calendarr.get(Calendar.HOUR_OF_DAY)) {
//                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
//                                        calendar.set(Calendar.MONTH, strmonth - 1);
//                                        calendar.set(Calendar.YEAR, Integer.parseInt(stryear1));
                        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
                        calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
                        Log.i("alarmtime", calendar.getTime().toString());
                        Intent intent1 = new Intent(context, AlarmReceiver.class);
                        intent1.putExtra("id",id1);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)id1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);

//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("R_name", title1);
//                        editor.putString("R_repeat", repeat1);
//                        editor.putString("R_id", String.valueOf(id1));
//                        editor.putString("R_resone", resone1);
//                        editor.commit();
                    }
                }

            }
        } else {
            if (calendar.get(Calendar.MONTH) > calendarr.get(Calendar.MONTH)) {


                Intent intent1 = new Intent(context, AlarmReceiver.class);
                intent1.putExtra("id",id1);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)id1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);

//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("R_name", title1);
//                editor.putString("R_repeat", repeat1);
//                editor.putString("R_id", String.valueOf(id1));
//                editor.putString("R_resone", resone1);
//                editor.commit();
            } else {
                Log.i("alarmdate", "time passed");
            }
      }
}
}

        else {

        //}





        dbHelper = new DbHelper(context);
//String intent1=intent.getAction();
        //Log.i("intentreceived",intent.getStringExtra("id"));
        ArrayList arrayList=dbHelper.selectTriggredAlarm(intent.getLongExtra("id",-1));
       //if(arrayList.size()>3) {
           Log.i("arraylist", arrayList.get(0).toString());
           Log.i("arraylist", arrayList.get(1).toString());
           Log.i("arraylist", arrayList.get(2).toString());


//        sharedPreferences= context.getSharedPreferences("alarm",Context.MODE_PRIVATE);
//        String title=sharedPreferences.getString("R_name"," ");
//        String repeat=sharedPreferences.getString("R_repeat"," ");
//        String id=sharedPreferences.getString("R_id"," ");
//        String resone=sharedPreferences.getString("R_resone"," ");
           Log.i("alarmReceiver", "called");
           Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
           NotificationCompat.Builder mBuilder =
                   new NotificationCompat.Builder(context)
                           .setSmallIcon(R.drawable.icon)
                           .setContentTitle(arrayList.get(0).toString())
                           .setContentText(arrayList.get(1).toString())
                           .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
           NotificationManager mNotificationManager =
                   (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
           mNotificationManager.notify(1, mBuilder.build());


//        try {
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//            Ringtone r = RingtoneManager.getRingtone(context, notification);
//            r.play();
//            Log.i("play","play");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
           wakeLock = ((PowerManager) context.getSystemService(Context.POWER_SERVICE)).newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "AlarmActivity");
           wakeLock.acquire();


//    Log.i("bootintent", "called");
//    Calendar calendarr = Calendar.getInstance();
//    DbHelper helper = new DbHelper(context);
//    users = helper.getReminder();
//    Log.i("saved Alarms", users.toString());
//    for (int i = 0; i < users.size(); i++) {
//        String title1 = users.get(i).get("r_name");
//        String repeat1 = users.get(i).get("r_repeat");
//        String id1 = users.get(i).get("r_id");
//        String resone1 = users.get(i).get("r_for_r");
//        String date = users.get(i).get("r_date");
//        String[] date12 = date.split("-");
//        String strDay = date12[0];
//        String strMonth1 = date12[1];
//        int strmonth = Integer.parseInt(strMonth1);
//
//        String stryear1 = date12[2];
//
//        String time = users.get(i).get("r_time");
//        String[] hour1 = time.split(":");
//        //Date date1 = StringToDateConvert(date);
//
//        Calendar calendar = Calendar.getInstance();
//        //int hour=calendar.get(Calendar.HOUR_OF_DAY);
//        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
//        calendar.set(Calendar.MONTH, strmonth - 1);
//        calendar.set(Calendar.YEAR, Integer.parseInt(stryear1));
//        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
//        calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
//
//        Date date2 = new Date();
//        Log.i("currentdatee", calendar.getTime().toString());
//        Log.i("currentdatee1", calendarr.getTime().toString());
//
//
//        if (repeat1.equals("Everyday")) {
//            if (calendar.getTime().after(calendarr.getTime())) {
//
//                // if (Integer.parseInt(hour1[0]) > calendarr.get(Calendar.HOUR_OF_DAY)){
//                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
//                calendar.set(Calendar.MONTH, strmonth - 1);
//                calendar.set(Calendar.YEAR, Integer.parseInt(stryear1));
//                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
//                calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
//                Log.i("alarmtime", calendar.getTime().toString());
//                Intent intent1 = new Intent(context, AlarmReceiver.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("R_name", title1);
//                editor.putString("R_repeat", repeat1);
//                editor.putString("R_id", String.valueOf(id1));
//                editor.putString("R_resone", resone1);
//                editor.commit();
//
//            }
//
//
//            if (calendar.getTime().equals(calendarr.getTime())) {
//                if (Integer.parseInt(hour1[0]) > calendarr.get(Calendar.HOUR_OF_DAY)) {
//                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
//                    calendar.set(Calendar.MONTH, strmonth - 1);
//                    calendar.set(Calendar.YEAR, Integer.parseInt(stryear1));
//                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
//                    calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
//                    Log.i("alarmtime", calendar.getTime().toString());
//                    Intent intent1 = new Intent(context, AlarmReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//                    alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("R_name", title1);
//                    editor.putString("R_repeat", repeat1);
//                    editor.putString("R_id", String.valueOf(id1));
//                    editor.putString("R_resone", resone1);
//                    editor.commit();
//                } else {
//                    Log.i("calendertime", "have passed");
//                }
//            }
//        } else if (repeat1.equals("weekly")) {
//
//            while (calendar.getTime().after(calendarr.getTime()) || calendar.getTime().equals(calendarr.getTime())) {
//                int dbdate = calendar.get(Calendar.DAY_OF_MONTH);
//                int currdate = calendarr.get(Calendar.DAY_OF_MONTH);
//
//                int diff = (currdate - dbdate);
//                int currdate1 = 7 - diff;
//                calendar.add(Calendar.DATE, currdate1);
//            }
//
//            Log.i("curtdatee", calendar.getTime().toString());
//
//            if (calendar.getTime().after(calendarr.getTime())) {
//                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
//                calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
//
//                Intent intent1 = new Intent(context, AlarmReceiver.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("R_name", title1);
//                editor.putString("R_repeat", repeat1);
//                editor.putString("R_id", String.valueOf(id1));
//                editor.putString("R_resone", resone1);
//                editor.commit();
//            } else {
//                if (calendar.getTime().equals(calendarr.getTime())) {
//                    if (Integer.parseInt(hour1[0]) > calendarr.get(Calendar.HOUR_OF_DAY)) {
////                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
////                                        calendar.set(Calendar.MONTH, strmonth - 1);
////                                        calendar.set(Calendar.YEAR, Integer.parseInt(stryear1));
//                        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
//                        calendar.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
//                        Log.i("alarmtime", calendar.getTime().toString());
//                        Intent intent1 = new Intent(context, AlarmReceiver.class);
//                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//                        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("R_name", title1);
//                        editor.putString("R_repeat", repeat1);
//                        editor.putString("R_id", String.valueOf(id1));
//                        editor.putString("R_resone", resone1);
//                        editor.commit();
//                    }
//                }
//
//            }
//        } else {
//            if (calendar.get(Calendar.MONTH) > calendarr.get(Calendar.MONTH)) {
//
//
//                Intent intent1 = new Intent(context, AlarmReceiver.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("R_name", title1);
//                editor.putString("R_repeat", repeat1);
//                editor.putString("R_id", String.valueOf(id1));
//                editor.putString("R_resone", resone1);
//                editor.commit();
//            } else {
//                Log.i("alarmdate", "time passed");
//            }
//        }}}
           //till here


//                        Calendar calendar1 = Calendar.getInstance();
//                            calendar1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDay));
//                            calendar1.set(Calendar.MONTH, strmonth - 1);
//                            calendar1.set(Calendar.YEAR, Integer.parseInt(stryear1));
//                            calendar1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
//                            calendar1.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
////
//                            if (repeat1.equals("Everday")) {
//                                while (!calendar1.getTime().equals(calendarr.getTime()) && calendar1.getTime().after(calendarr.getTime())) {
//                                    calendar1.add(Calendar.DATE, 1);
//
//                                }
////                            if(Integer.parseInt(hour1[0])<calendar1.get(Calendar.HOUR_OF_DAY))
////                            {
//                                Log.i("alarmfiring", "nextday");
//                                calendar1.add(Calendar.DATE, 1);
////                                calendar1.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour1[0]));
////                                calendar1.set(Calendar.SECOND,Integer.parseInt(hour1[1]));
//
//                                Intent intent1 = new Intent(context, AlarmReceiver.class);
//                                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//                                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar1.getTimeInMillis(), pendingIntent), pendingIntent);
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putString("R_name", title1);
//                                editor.putString("R_repeat", repeat1);
//                                editor.putString("R_id", String.valueOf(id1));
//                                editor.putString("R_resone", resone1);
//                                editor.commit();
////
//                            }
//                            if (repeat1.equals("weekly")) {
//                                while (!calendar1.getTime().after(calendarr.getTime()) || !calendar1.getTime().equals(calendarr.getTime())) {
//                                    calendar1.add(Calendar.DATE, 7);
//                                }
//
//
//                                calendar1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
//                                calendar1.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
//
//                                Intent intent1 = new Intent(context, AlarmReceiver.class);
//                                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//                                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar1.getTimeInMillis(), pendingIntent), pendingIntent);
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putString("R_name", title1);
//                                editor.putString("R_repeat", repeat1);
//                                editor.putString("R_id", String.valueOf(id1));
//                                editor.putString("R_resone", resone1);
//                                editor.commit();
//
//                            }
////
//                            if (repeat1.equals("monthly")) {
//
//                                while (!calendar1.getTime().equals(calendarr.getTime()) || calendar1.getTime().after(calendarr.getTime())) {
//                                    calendar1.add(Calendar.MONTH, 1);
//                                }
//                                calendar1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour1[0]));
//                                calendar1.set(Calendar.SECOND, Integer.parseInt(hour1[1]));
//                                Intent intent1 = new Intent(context, AlarmReceiver.class);
//                                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//                                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar1.getTimeInMillis(), pendingIntent), pendingIntent);
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putString("R_name", title1);
//                                editor.putString("R_repeat", repeat1);
//                                editor.putString("R_id", String.valueOf(id1));
//                                editor.putString("R_resone", resone1);
//                                editor.commit();
//
//                            }


//
//
//
//  uncomment one brace                          }
           //}
           // }
           // }
           // Set the alarm here.

           //}
//        else {
//            Log.i("bootintent","not called");
//
//                    repeatEveryDay(title,repeat,context,id,resone);
//                }


           //activityAddReminder.setAlarm(context,Calendar.getInstance(),Integer.parseInt(id),"Everday");
//       if(resone.equals("Everyday")) {
//
//           AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//           PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id), intent, PendingIntent.FLAG_UPDATE_CURRENT);
//           Calendar calendar = Calendar.getInstance();
//           calendar.set(Calendar.DAY_OF_MONTH, +1);
//           alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//           Log.i("everday","called");
//           //
//       }
//        else {
//           Log.i("everday","not called");
//       }
// Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        mMediaPlayer = new MediaPlayer();
//        try {
//            mMediaPlayer.setDataSource(context, alert);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        final AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
//
//        if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
//            player.setAudioStreamType(AudioManager.STREAM_ALARM);
//            player.setLooping(true);
//            player.prepare();
//            player.start();
//        }
           //Intent service1 = new Intent(context, MyAlarmService.class);
           //context.startService(service1);
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(context)
//                        .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
//                        .setContentTitle(title)
//                        .setContentText(resone);
//        NotificationManager mNotificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//// mId allows you to update the notification later on.
//        mNotificationManager.notify(1, mBuilder.build());
//
//
//
//
//        try {
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//            Ringtone r = RingtoneManager.getRingtone(context, notification);
//            r.play();
//            Log.i("play","play");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//}
           //else {
           long id = intent.getLongExtra("id", -1);

           repeatEveryDay(arrayList.get(0).toString(), arrayList.get(2).toString(), context, id, arrayList.get(1).toString());
       //}
    //}

        //wakeLock.release();
    }}

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public void repeatEveryDay(String title, String repeat, Context context, long id, String resone){







    if(repeat.equals("Everyday")) {

        Log.i("uidd", String.valueOf(id));
        Calendar calendar= Calendar.getInstance();
        Log.i("currentdate", String.valueOf(calendar.getTime()));
        calendar.add(Calendar.DATE,1);
        Log.i("updateddate", String.valueOf(calendar.getTime()));
        Intent intent1 = new Intent(context, AlarmReceiver.class);
        intent1.putExtra("id",id);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)id, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);

    }
    else if(repeat.equals("weekly")) {
        Calendar calendar= Calendar.getInstance();
        Log.i("currentdate", String.valueOf(calendar.getTime()));
        calendar.add(Calendar.DATE,7);
        Log.i("updateddate", String.valueOf(calendar.getTime()));
        Intent intent1 = new Intent(context, AlarmReceiver.class);
        intent1.putExtra("id",id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)id, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);

    }
    else {

        Calendar calendar= Calendar.getInstance();
        Log.i("currentdate", String.valueOf(calendar.getTime()));
        calendar.add(Calendar.MONTH,1);
        Log.i("updateddate", String.valueOf(calendar.getTime()));
        Intent intent1 = new Intent(context, AlarmReceiver.class);
        intent1.putExtra("id",id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)id, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);


    }
}
    private long getDuration(){
        // get todays date
        Calendar cal = Calendar.getInstance();
        // get current month
        int currentMonth = cal.get(Calendar.MONTH);

        // move month ahead
        currentMonth++;
        // check if has not exceeded threshold of december

        if(currentMonth > Calendar.DECEMBER){
            // alright, reset month to jan and forward year by 1 e.g fro 2013 to 2014
            currentMonth = Calendar.JANUARY;
            // Move year ahead as well
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);
        }

        // reset calendar to next month
        cal.set(Calendar.MONTH, currentMonth);
        // get the maximum possible days in this month
        int maximumDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // set the calendar to maximum day (e.g in case of fEB 28th, or leap 29th)
        cal.set(Calendar.DAY_OF_MONTH, maximumDay);
        long thenTime = cal.getTimeInMillis(); // this is time one month ahead



        return (thenTime); // this is what you set as trigger point time i.e one month after

    }
    public Date StringToDateConvert(String dtStart) {
        Date date = null;


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

}
//}
