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

//import com.example.archi.health.Activity.VitalUpdate.ActivityVitalUpdateBurnCalories;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalUpdate.ActivityVitalUpdateBurnCalories;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalUpdate.ActivityVitalUpdateBurnCalories;
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

public class VitalBurnCaloriesAdapter  extends RecyclerView.Adapter<VitalBurnCaloriesAdapter.Holder> {

    List<HashMap<String, String>> BurnCaloryArry = new ArrayList<>();
    Context context;
    private DbHelper dbHelper;


    public VitalBurnCaloriesAdapter(Context context, List<HashMap<String, String>> BurnCaloryArry) {

        this.context = context;
        this.BurnCaloryArry = BurnCaloryArry;
        dbHelper = new DbHelper(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vital_adapter_burn_calories, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
      final HashMap<String, String> map = BurnCaloryArry.get(position);
        holder.itemDate.setText(map.get("v_burn_calories_date"));
        holder.itemTimE.setText(map.get("v_burn_calories_time"));
        holder.itemBurnCalaory.setText(map.get("v_burn_calories_burn_calories"));
//        holder.itemBurnCllOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopup(position);
//
//            }
//        });

        holder.itemBurnCllOut.setOnClickListener(new View.OnClickListener() {
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
                deleteRecordComfirmation(map.get("v_burn_calories_id"),position);
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
        HashMap<String, String> map = BurnCaloryArry.get(position);
        Intent upIntent = new Intent(context, ActivityVitalUpdateBurnCalories.class);
        upIntent.putExtra("v_burn_calories_id", map.get("v_burn_calories_id"));
        upIntent.putExtra("v_burn_calories_date", map.get("v_burn_calories_date"));
        upIntent.putExtra("v_burn_calories_time", map.get("v_burn_calories_time"));
        upIntent.putExtra("v_burn_calories_burn_calories", map.get("v_burn_calories_burn_calories"));
        upIntent.putExtra("v_burn_calories_unit", map.get("v_burn_calories_unit"));
        upIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(upIntent);
    }

    @Override
    public int getItemCount() {
        return BurnCaloryArry.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.vital_adpter_burn_calories_txtdate)
        TextView itemDate;
        @BindView(R.id.vital_adpter_burn_calories_txttime)
        TextView itemTimE;
        @BindView(R.id.vital_adpter_burn_calories_txttype)
        TextView itemBurnCalaory;
        @BindView(R.id.vital_adpter_burn_calories_llout)
        LinearLayout itemBurnCllOut;

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
                dbHelper.deleteVBurnCalories(strId);
                BurnCaloryArry.remove(position);
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
