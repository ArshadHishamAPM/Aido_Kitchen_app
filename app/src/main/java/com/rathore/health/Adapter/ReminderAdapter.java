package com.rathore.health.Adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.archi.health.Activity.ActivityUpdateReminder;
//import com.example.archi.health.AlarmReceiver;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityUpdateReminder;
//import com.pierfrancescosoffritti.aytplayersample.AlarmReceiver;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityUpdateReminder;
import com.rathore.health.AlarmReceiver;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android on 10/28/2016.
 */
public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.Holder> {

    public List<HashMap<String, String>> users = new ArrayList<>();
    Context context;
    HashMap<String, String> map;


    public ReminderAdapter(Context context, List<HashMap<String, String>> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_reminder, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        map = users.get(position);

        Log.i("uid", String.valueOf(users.get(position).get("r_id")));
        holder.itemrName.setText(map.get("r_name"));
        holder.itemrfor.setText(map.get("r_for_r"));
        holder.itemrdate.setText(map.get("r_date"));
        holder.itemrtime.setText(map.get("r_time"));
//        holder.llMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopup(position);
//            }
//        });
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.delete.setVisibility(View.VISIBLE);
                holder.update.setVisibility(View.VISIBLE);
                holder.ellipse.setVisibility(View.VISIBLE);
                holder.ellipse1.setVisibility(View.VISIBLE);

            }
        });
        holder.ellipse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("delete","pressed");
               // HashMap<String, String> map = users.get(position);
               // String id = map.get("m_id");
                deleteRecordComfirmation(position);
                holder.delete.setVisibility(View.INVISIBLE);
                holder.update.setVisibility(View.INVISIBLE);
                holder.ellipse.setVisibility(View.INVISIBLE);
                holder.ellipse1.setVisibility(View.INVISIBLE);

            }
        });
        holder.ellipse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("update","pressed");
                openPopup(position);
                holder.delete.setVisibility(View.INVISIBLE);
                holder.update.setVisibility(View.INVISIBLE);
                holder.ellipse.setVisibility(View.INVISIBLE);
                holder.ellipse1.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void openPopup(final int position) {
        final HashMap<String, String> map = users.get(position);
//        final CharSequence colors[] = new CharSequence[]{"Delete", "Update"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setItems(colors, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0) {
//                    notifyDataSetChanged();
//                    DbHelper dbHelper = new DbHelper(context);
//                    dbHelper.DeleteReminderRaw(map.get("r_id"));
//                    users.remove(position);
//                    Intent intent = new Intent(context, AlarmReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(map.get("r_id")), intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                    AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//                    //alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
//                    alarmManager.cancel(pendingIntent);
//                    Toast.makeText(context, "reminder deleted ", Toast.LENGTH_SHORT).show();
//                    notifyDataSetChanged();
//                } else {


        Intent updateReminder = new Intent(context, ActivityUpdateReminder.class);
                    updateReminder.putExtra("ReminderId", map.get("r_id"));
                    updateReminder.putExtra("ReminderName", map.get("r_name"));
                    updateReminder.putExtra("ReminderReason", map.get("r_for_r"));
                    updateReminder.putExtra("ReminderDate", map.get("r_date"));
                    updateReminder.putExtra("ReminderTime", map.get("r_time"));
                    updateReminder.putExtra("ReminderReapeat", map.get("r_repeat"));
                    updateReminder.putExtra("ReminderNote", map.get("r_note"));
                    context.startActivity(updateReminder);
                }
          //  }
       // });
        //builder.show();
   // }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_reminder_rname)
        TextView itemrName;
        @BindView(R.id.adapter_reminder_rfor)
        TextView itemrfor;
        @BindView(R.id.adapter_reminder_rdate)
        TextView itemrdate;
        @BindView(R.id.adapter_reminder_rtime)
        TextView itemrtime;
        @BindView(R.id.adapter_reminder_linear_main)
        LinearLayout llMain;

        @BindView(R.id.delete)
        ImageView delete;
        @BindView(R.id.update)
        ImageView update;
        @BindView(R.id.ellipse)
        ImageView ellipse;
        @BindView(R.id.ellipse1)
        ImageView ellipse1;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
public void deleteRecordComfirmation(int position){
    final HashMap<String, String> map = users.get(position);
    notifyDataSetChanged();
    DbHelper dbHelper = new DbHelper(context);
    dbHelper.DeleteReminderRaw(map.get("r_id"));
    users.remove(position);
    Intent intent = new Intent(context, AlarmReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(map.get("r_id")), intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    //alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    alarmManager.cancel(pendingIntent);
    Toast.makeText(context, "reminder deleted ", Toast.LENGTH_SHORT).show();
    notifyDataSetChanged();
}

}
