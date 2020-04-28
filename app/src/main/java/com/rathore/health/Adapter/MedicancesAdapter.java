package com.rathore.health.Adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.archi.health.Activity.ActivityUpdateMedicine;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityUpdateMedicine;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityUpdateMedicine;
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
public class MedicancesAdapter extends RecyclerView.Adapter<MedicancesAdapter.Holder> {

    List<HashMap<String, String>> users = new ArrayList<>();
    Context context;
    DbHelper dbHelper;




    public MedicancesAdapter(Context context, List<HashMap<String, String>> users) {
        this.context = context;
        this.users = users;
        dbHelper = new DbHelper(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_medications, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        HashMap<String, String> map = users.get(position);
        holder.itemmName.setText(map.get("m_name"));
        holder.itemminstruction.setText(map.get("m_instrtion"));
        holder.itemmdoase.setText(map.get("m_dosage"));
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
                HashMap<String, String> map = users.get(position);
                String id = map.get("m_id");
                deleteRecordComfirmation(id,position);
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

//
//
//
//
// ClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopup(position);
//
//            }
//        });
    }

    private void openPopup(final int position) {

        HashMap<String, String> map = users.get(position);

        Intent updateMedicanes = new Intent(context, ActivityUpdateMedicine.class);
        updateMedicanes.putExtra("m_id", map.get("m_id"));

        updateMedicanes.putExtra("m_name", map.get("m_name"));
        updateMedicanes.putExtra("m_type", map.get("m_type"));
        updateMedicanes.putExtra("m_route", map.get("m_route"));
        updateMedicanes.putExtra("m_dosage", map.get("m_dosage"));
        updateMedicanes.putExtra("m_instrtion", map.get("m_instrtion"));
        updateMedicanes.putExtra("m_resontalk", map.get("m_resontalk"));
        updateMedicanes.putExtra("m_d_hours", map.get("m_d_hours"));
        updateMedicanes.putExtra("m_d_days", map.get("m_d_days"));
        updateMedicanes.putExtra("m_startdate", map.get("m_startdate"));
        updateMedicanes.putExtra("m_enddate", map.get("m_enddate"));
        updateMedicanes.putExtra("m_quantity", map.get("m_quantity"));
        updateMedicanes.putExtra("m_prescrobeby", map.get("m_prescrobeby"));
        context.startActivity(updateMedicanes);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_medicances_name)
        TextView itemmName;
        @BindView(R.id.adapter_medicances_instruction)
        TextView itemminstruction;
        @BindView(R.id.adapter_medicances_doase)
        TextView itemmdoase;
        @BindView(R.id.adapter_medication_linear)
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
    private void deleteRecordComfirmation(final String strId, final int position) {
        final AlertDialog.Builder aleBuilder = new AlertDialog.Builder(context);
        aleBuilder.setMessage("do you realy want to delete");
        aleBuilder.setCancelable(false);
        aleBuilder.setTitle("delete");
        aleBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteMedicinsRaw(strId);
                users.remove(position);
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
