package com.rathore.health.Adapter.AdapterVital;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.archi.health.Activity.VitalUpdate.ActivityVitalUpdateIntakeCalories;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalUpdate.ActivityVitalUpdateIntakeCalories;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalUpdate.ActivityVitalUpdateIntakeCalories;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archi on 12/30/2016.
 */

public class VitalIntakeCaloriesAdapter extends RecyclerView.Adapter<VitalIntakeCaloriesAdapter.Holder> {

    List<HashMap<String, String>> IntakeCaloryArry = new ArrayList<>();
    Context context;
    private DbHelper dbHelper;


    public VitalIntakeCaloriesAdapter(Context context, List<HashMap<String, String>> IntakeCaloryArry) {

        this.context = context;
        this.IntakeCaloryArry = IntakeCaloryArry;
        dbHelper = new DbHelper(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vital_adapter_intake_calories, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
       final HashMap<String, String> map = IntakeCaloryArry.get(position);
        holder.itemIcDate.setText(map.get("v_intake_calories_date"));
        holder.itemIcTime.setText(map.get("v_intake_calories_time"));
        holder.itemIcType.setText(map.get("v_intake_calories_intake_calories"));
        holder.itemIcUnit.setText(map.get("v_intake_calories_unit"));
//        holder.itemllout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopup(position);
//
//            }
//        });

        holder.itemllout.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
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
                deleteRecordComfirmation(map.get("v_intake_calories_id"),position);
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
        HashMap<String, String> map = IntakeCaloryArry.get(position);
        Intent upIntent = new Intent(context, ActivityVitalUpdateIntakeCalories.class);
        upIntent.putExtra("v_intake_calories_id", map.get("v_intake_calories_id"));
        upIntent.putExtra("v_intake_calories_date", map.get("v_intake_calories_date"));
        upIntent.putExtra("v_intake_calories_time", map.get("v_intake_calories_time"));
        upIntent.putExtra("v_intake_calories_intake_calories", map.get("v_intake_calories_intake_calories"));
        upIntent.putExtra("v_intake_calories_unit", map.get("v_intake_calories_unit"));
        upIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(upIntent);
    }

    @Override
    public int getItemCount() {
        return IntakeCaloryArry.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.vital_adpter_intake_calories_txtdate )
        TextView itemIcDate;
        @BindView(R.id.vital_adpter_intake_calories_txttime )
        TextView itemIcTime;
        @BindView(R.id.vital_adpter_intake_calories_txttype )
        TextView itemIcType;
        @BindView(R.id.vital_adpter_intake_calories_txtunit )
        TextView itemIcUnit;
        @BindView(R.id.vital_adpter_intake_calories_llout )
        LinearLayout itemllout;

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
    private void deleteRecordComfirmation(final String strId, final int position) {
        final AlertDialog.Builder aleBuilder = new AlertDialog.Builder(context);
        aleBuilder.setMessage(context.getString(R.string.do_you_realy_want_to_delete));
        aleBuilder.setCancelable(false);
        aleBuilder.setTitle(context.getString(R.string.delete));
        aleBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteVIntakeCalories(strId);
                IntakeCaloryArry.remove(position);
                notifyDataSetChanged();


            }
        });

        aleBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        aleBuilder.create();
        aleBuilder.show();
    }
}




