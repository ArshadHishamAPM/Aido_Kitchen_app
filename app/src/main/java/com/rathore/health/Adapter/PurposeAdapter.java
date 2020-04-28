package com.rathore.health.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


//import com.example.health.Activity.ActivityDrugInformation;
//import com.example.health.Activity.ActivityDrugInformationDescription;
//import com.example.health.R;
//import com.example.health.model.PurposeModel;

//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityDrugInformation;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityDrugInformationDescription;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.model.PurposeModel;
import com.rathore.health.Activity.ActivityDrugInformation;
import com.rathore.health.Activity.ActivityDrugInformationDescription;
import com.rathore.health.R;
import com.rathore.health.model.PurposeModel;

import java.util.ArrayList;

/**
 * Created by archi on 12/27/2016.
 */

public class PurposeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PurposeModel> purposeArray;
    private LayoutInflater inflater;

    public PurposeAdapter(ActivityDrugInformation activityDrugInformation, ArrayList<PurposeModel> arrayListPurpose) {
        context = activityDrugInformation;
        purposeArray = arrayListPurpose;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return purposeArray.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_purpose, null);
        TextView tvValues = (TextView) convertView.findViewById(R.id.adapter_purpose_tv);
        tvValues.setText(purposeArray.get(position).getStrTitle());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent descriptionActivity = new Intent(context, ActivityDrugInformationDescription.class);
                descriptionActivity.putExtra("title",purposeArray.get(position).getStrTitle());
                descriptionActivity.putExtra("detail",purposeArray.get(position).getPurpose());
                context.startActivity(descriptionActivity);
            }
        });
        return convertView;
    }

}
