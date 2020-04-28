package com.rathore.health.Adapter.Profile;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.archi.health.Activity.Profile.ActivityProfileCalender;
//import com.example.archi.health.Activity.Profile.ActivityProfileReportSomethingTab;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.example.archi.health.model.ProfileAddValues;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileCalender;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileReportSomethingTab;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.model.ProfileAddValues;
import com.rathore.health.Activity.Profile.ActivityProfileCalender;
import com.rathore.health.Activity.Profile.ActivityProfileReportSomethingTab;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;
import com.rathore.health.model.ProfileAddValues;

import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 1/5/2017.
 */

public class TreatmentProfileAdapter extends BaseAdapter
{
    LayoutInflater inflater;
    Context context;
    List<HashMap<String, String>> list;
    int selectedItem;
    DbHelper dbHelper;
    public TreatmentProfileAdapter(FragmentActivity activity, List<HashMap<String, String>> listDocVisit, int selectedItem) {
        this.context = activity;
        this.list = listDocVisit;
        this.selectedItem = selectedItem;
        dbHelper =  new DbHelper(context);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
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
        convertView = inflater.inflate(R.layout.dialog_adapter_treatment_profile,null);
        TextView tvDetail = (TextView)convertView.findViewById(R.id.dialog_adapter_treatment_tv_list);
        ImageView ivDetail = (ImageView)convertView.findViewById(R.id.dialog_adapter_treatment_iv_list);
        tvDetail.setText(list.get(position).get("key_treatment_medi_name"));
        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ProfileAddValues profileAddValues = new ProfileAddValues();
                profileAddValues.setStrDate(ActivityProfileReportSomethingTab.selectedDate);
                profileAddValues.setStrName(list.get(position).get("key_treatment_medi_name"));
                ActivityProfileCalender.arrayListillness.add(profileAddValues);
                //ActivityProfileCalender.arrayListillness1.put(position,profileAddValues);
                ActivityProfileReportSomethingTab.tvHeaderTotal.setText("selected item :"+ActivityProfileCalender.arrayListillness.size());
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String id = list.get(position).get("key_treatment_medi_id");
                removeItemFromDatabase(id,selectedItem);
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private void removeItemFromDatabase(String position, int selectedItem)
    {

        switch (selectedItem) {
            case 1:
                dbHelper.deleteProfileTreatDoctorVisit(position);
                break;
            case 2:
                dbHelper.deleteProfileTreatMedication(position);
                break;
            case 3:
                dbHelper.deleteProfileTreatOther(position);
                break;
            case 4:
                dbHelper.deleteProfileTreatStayHome(position);
                break;
            case 5:
                dbHelper.deleteProfileTreatSpeciality(position);
                break;
            case 6:
                dbHelper.deleteProfileTreatVaccine(position);
                break;
        }
    }
}
