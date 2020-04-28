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

//import com.example.archi.health.Activity.VitalUpdate.ActivityVitalUpdateAlcohol;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalUpdate.ActivityVitalUpdateAlcohol;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.VitalUpdate.ActivityVitalUpdateAlcohol;
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

public class VitalAlcoholAdapter  extends RecyclerView.Adapter<VitalAlcoholAdapter.Holder> {

    List<HashMap<String, String>> alcoholArry = new ArrayList<>();
    Context context;
    private DbHelper dbHelper;



    public VitalAlcoholAdapter(Context context, List<HashMap<String, String>> alcoholArry) {

        this.context = context;
        this.alcoholArry = alcoholArry;
        dbHelper = new DbHelper(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.vital_adapter_alcohol, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final HashMap<String, String> map = alcoholArry.get(position);
        holder.alcoholDate.setText(map.get("v_alcohol_date"));
        holder.alcoholTime.setText(map.get("v_alcohol_time"));
        holder.alcoholpersontage.setText(map.get("v_alcohol_alcohol"));


//        holder.lllayoutmain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopup(position);
//                // Toast.makeText(context, "hiiii", Toast.LENGTH_SHORT).show();
//               /* Intent intent=new Intent(context, ActivityVitalUpdateAlcohol.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);*/
//            }
//        });
        holder.lllayoutmain.setOnClickListener(new View.OnClickListener() {
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
                deleteRecordComfirmation(map.get("v_alcohol_id"),position);
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

        HashMap<String, String> map = alcoholArry.get(position);

        Intent upIntent = new Intent(context, ActivityVitalUpdateAlcohol.class);
        upIntent.putExtra("v_alcohol_id", map.get("v_alcohol_id"));
        upIntent.putExtra("v_alcohol_date", map.get("v_alcohol_date"));
        upIntent.putExtra("v_alcohol_time", map.get("v_alcohol_time"));
        upIntent.putExtra("v_alcohol_alcohol", map.get("v_alcohol_alcohol"));
        upIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(upIntent);
    }

    @Override
    public int getItemCount() {
        return alcoholArry.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.vital_adpter_alcohol_txtdate )
        TextView alcoholDate;
        @BindView(R.id.vital_adpter_alcohol_txttime )
        TextView alcoholTime;
        @BindView(R.id.vital_adpter_alcohol_txtalocohol )
        TextView alcoholpersontage;
        @BindView(R.id.vital_adpter_alcohol_llmain )
        LinearLayout lllayoutmain;

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
                dbHelper.deleteVAlcohol(strId);
                alcoholArry.remove(position);
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

