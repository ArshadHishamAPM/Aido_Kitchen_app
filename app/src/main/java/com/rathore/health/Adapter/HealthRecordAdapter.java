package com.rathore.health.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.archi.health.Activity.ActivityUpdateHealthRecord;
//import com.example.archi.health.Activity.Profile.ActivityProfileCalender;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityUpdateHealthRecord;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileCalender;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityUpdateHealthRecord;
import com.rathore.health.Activity.Profile.ActivityProfileCalender;
import com.rathore.health.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by archi on 12/24/2016.
 */

public class HealthRecordAdapter extends RecyclerView.Adapter<HealthRecordAdapter.Holder> {

    List<HashMap<String, String>> users = new ArrayList<>();
    Context context;


    public HealthRecordAdapter(Context context, List<HashMap<String, String>> users) {

        this.context = context;
        this.users = users;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_health_record, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        HashMap<String, String> map = users.get(position);
        holder.itemmprofileName.setText(map.get("hr_name"));


        if (map.get("hr_profile_img") != null) {
            if (map.get("hr_profile_img").length() > 0) {

                Bitmap bitmap = BitmapFactory.decodeFile(map.get("hr_profile_img"));
                holder.itemprofileimg.setImageBitmap(bitmap);
            }
        }
        holder.itemmprofileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "hmmm", Toast.LENGTH_SHORT).show();
                editProfilePopup(position);
            }
        });
        holder.itemedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "hmmm", Toast.LENGTH_SHORT).show();
                editProfilePopup(position);
            }
        });
        holder.itemprofileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(context, ActivityProfileCalender.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("profileID",users.get(position).get("hr_id"));
                i.putExtra("hr_name",users.get(position).get("hr_name"));
                context.startActivity(i);

            }
        });

    }
    private void editProfilePopup(final int position) {

        HashMap<String, String> map = users.get(position);
        Intent updateHealthRecord = new Intent(context, ActivityUpdateHealthRecord.class);
        updateHealthRecord.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        updateHealthRecord.putExtra("hr_id", map.get("hr_id"));
        updateHealthRecord.putExtra("hr_name", map.get("hr_name"));
        updateHealthRecord.putExtra("hr_gender", map.get("hr_gender"));
        updateHealthRecord.putExtra("hr_profile_img", map.get("hr_profile_img"));
        updateHealthRecord.putExtra("hr_birthdate", map.get("hr_birthdate"));
        updateHealthRecord.putExtra("hr_bloodtype", map.get("hr_bloodtype"));
        updateHealthRecord.putExtra("hr_nomal_body_temp", map.get("hr_nomal_body_temp"));
        updateHealthRecord.putExtra("hr_allergies", map.get("hr_allergies"));
        updateHealthRecord.putExtra("hr_health_condition", map.get("hr_health_condition"));
        updateHealthRecord.putExtra("hr_surgeries_his", map.get("hr_surgeries_his"));
        updateHealthRecord.putExtra("hr_hospitalization_his", map.get("hr_hospitalization_his"));
        updateHealthRecord.putExtra("hr_notes", map.get("hr_notes"));
        context.startActivity(updateHealthRecord);
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_health_record_txtname )
        TextView itemmprofileName;
        @BindView(R.id.adapter_health_record_imgprofile)
        ImageView itemprofileimg;
        @BindView(R.id.adapter_health_record_txtname1)
        TextView itemedit;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
