package com.rathore.health.Adapter.Profile;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.archi.health.Activity.Profile.ActivityProfileCalender;
//import com.example.archi.health.Activity.Profile.ActivityProfileReportSomethingTab;
//import com.example.archi.health.R;
//import com.example.archi.health.model.IllnessData;
//import com.example.archi.health.model.ProfileAddValues;

//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileCalender;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileReportSomethingTab;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.model.IllnessData;
//import com.pierfrancescosoffritti.aytplayersample.model.ProfileAddValues;
import com.rathore.health.Activity.Profile.ActivityProfileCalender;
import com.rathore.health.Activity.Profile.ActivityProfileReportSomethingTab;
import com.rathore.health.R;
import com.rathore.health.model.IllnessData;
import com.rathore.health.model.ProfileAddValues;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by archi on 1/4/2017.
 */

public class SymptomSubCatAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<IllnessData> arrayList;
    private LayoutInflater inflater;
    private Dialog dialog;


    public SymptomSubCatAdapter(Context context, ArrayList<IllnessData> arrayIlnessData, Dialog dialog, Dialog dialogMain) {
        this.dialog = dialog;
        this.context = context;
        this.arrayList =  arrayIlnessData;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        convertView = inflater.inflate(R.layout.dialog_profile_symptom_display_sub_array,null);
        TextView tvName = (TextView)convertView.findViewById(R.id.dialog_profile_symptom_display_sub_array_tv);
        tvName.setText(arrayList.get(position).getStrName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int date  = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH)+1;
                int year = calendar.get(Calendar.YEAR);
                String currentDate  = date + "-" + month + "-"+year;
                ProfileAddValues profileAddValues = new ProfileAddValues();
                profileAddValues.setStrDate(currentDate);
                profileAddValues.setStrName(arrayList.get(position).getStrName());
                ActivityProfileCalender.arrayListillness.add(profileAddValues);
               // ActivityProfileCalender.arrayListillness1.put(position,profileAddValues);
                Toast.makeText(context,"selected", Toast.LENGTH_SHORT).show();
                ActivityProfileReportSomethingTab.tvHeaderTotal.setText("Selected items :"+ActivityProfileCalender.arrayListillness.size());
                dialog.dismiss();
            }
        });



        return convertView;
    }
}
