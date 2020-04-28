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

//import com.example.archi.health.Activity.VitalUpdate.ActivityVitalUpdateBloodPressure;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalUpdate.ActivityVitalUpdateBloodPressure;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalUpdate.ActivityVitalUpdateBloodPressure;
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

public class VitalBloodPressureAdapter extends RecyclerView.Adapter<VitalBloodPressureAdapter.Holder> {

    List<HashMap<String, String>> BpressureArray = new ArrayList<>();
    Context context;
    private DbHelper dbHelper;


    public VitalBloodPressureAdapter(Context context, List<HashMap<String, String>> BpressureArray) {

        this.context = context;
        this.BpressureArray = BpressureArray;
        dbHelper = new DbHelper(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vital_adapter_blood_pressure, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final HashMap<String, String> map = BpressureArray.get(position);
        holder.bloodpressureDate.setText(map.get("v_bloodpressure_date"));
        holder.bloodpressureTime.setText(map.get("v_bloodpressure_time"));
        holder.bloodpressurestsc.setText(map.get("v_bloodpressure_ststolic"));
        holder.bloodpressuredistc.setText(map.get("v_bloodpressure_diastolic"));
//        holder.bloodpressurellout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopup(position);
//
//            }
//        });


        holder.bloodpressurellout.setOnClickListener(new View.OnClickListener() {
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
                deleteRecordComfirmation(map.get("v_bloodpressure_id"),position);
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
        HashMap<String, String> map = BpressureArray.get(position);
        Intent upIntent = new Intent(context, ActivityVitalUpdateBloodPressure.class);
        upIntent.putExtra("v_bloodpressure_id", map.get("v_bloodpressure_id"));
        upIntent.putExtra("v_bloodpressure_date", map.get("v_bloodpressure_date"));
        upIntent.putExtra("v_bloodpressure_time", map.get("v_bloodpressure_time"));
        upIntent.putExtra("v_bloodpressure_ststolic", map.get("v_bloodpressure_ststolic"));
        upIntent.putExtra("v_bloodpressure_diastolic", map.get("v_bloodpressure_diastolic"));
        upIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(upIntent);
    }

    @Override
    public int getItemCount() {
        return BpressureArray.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.vital_adpter_blood_pressure_txtdate)
        TextView bloodpressureDate;
        @BindView(R.id.vital_adpter_blood_pressure_txttime)
        TextView bloodpressureTime;
        @BindView(R.id.vital_adpter_blood_pressure_txtstatc)
        TextView bloodpressurestsc;
        @BindView(R.id.vital_adpter_blood_pressure_txtdistc)
        TextView bloodpressuredistc;
        @BindView(R.id.vital_adpter_blood_pressure_lloutbp)
        LinearLayout bloodpressurellout;

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
        //dbHelper.deleteVAlcohol(strId);


        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(context);
        aleBuilder.setMessage("do you realy want to delete");
        aleBuilder.setCancelable(false);
        aleBuilder.setTitle("delete");
        aleBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteVBloodPressure(strId);
                BpressureArray.remove(position);
                notifyDataSetChanged();
                //onBackPressed();
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


