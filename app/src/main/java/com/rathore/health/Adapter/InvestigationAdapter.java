package com.rathore.health.Adapter;

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

//import com.example.archi.health.Activity.ActivityUpdateInvestigation;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityUpdateInvestigation;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityUpdateInvestigation;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archi on 1/4/2017.
 */

public class InvestigationAdapter extends RecyclerView.Adapter<InvestigationAdapter.Holder> {

    List<HashMap<String, String>> users = new ArrayList<>();
    Context context;
private DbHelper dbHelper;

    public InvestigationAdapter(Context context, List<HashMap<String, String>> users) {

        this.context = context;
        this.users = users;
        dbHelper = new DbHelper(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_investigation, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
       final HashMap<String, String> map = users.get(position);
        holder.itemdate.setText(map.get("investigation_date"));
        holder.itemtime.setText(map.get("investigation_time"));
        holder.itemname.setText(map.get("investigation_name"));
        holder.itemmresult.setText(map.get("investigation_result"));
        holder.itemunit.setText(map.get("investigation_unit"));
        holder.itemtime.setText(map.get("investigation_repeat"));
//        holder.itemllout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopup(position);
//
//            }
//        });

        holder.itemllout.setOnClickListener(new View.OnClickListener() {
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
                String id = map.get("investigation_id");
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


    }
    private void openPopup(final int position) {

        HashMap<String, String> map = users.get(position);
        Intent upIntent = new Intent(context, ActivityUpdateInvestigation.class);
        upIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        upIntent.putExtra("investigation_id", map.get("investigation_id"));
        upIntent.putExtra("investigation_date", map.get("investigation_date"));
        upIntent.putExtra("investigation_time", map.get("investigation_time"));
        upIntent.putExtra("investigation_name", map.get("investigation_name"));
        upIntent.putExtra("investigation_result", map.get("investigation_result"));
        upIntent.putExtra("investigation_unit", map.get("investigation_unit"));
        upIntent.putExtra("investigation_repeat", map.get("investigation_repeat"));

        context.startActivity(upIntent);
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_investigation_txt_name )
        TextView itemname;
        @BindView(R.id.adapter_investigation_txt_result )
        TextView itemmresult;
        @BindView(R.id.adapter_investigation_txt_unit )
        TextView itemunit;
        @BindView(R.id.adapter_investigation_txt_date )
        TextView itemdate;
        @BindView(R.id.adapter_investigation_txt_time )
        TextView itemtime;
        @BindView(R.id.adapter_investigation_linear )
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
                dbHelper.deleteInvestigation(strId);
                users.remove(position);
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
