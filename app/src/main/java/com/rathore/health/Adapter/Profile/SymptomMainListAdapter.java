package com.rathore.health.Adapter.Profile;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

//import com.example.archi.health.Fragment.Profile.FragmentIllness;
//import com.example.archi.health.R;
//import com.example.archi.health.model.IllnessData;

//import com.pierfrancescosoffritti.aytplayersample.Fragment.Profile.FragmentIllness;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.model.IllnessData;
import com.rathore.health.Fragment.Profile.FragmentIllness;
import com.rathore.health.R;
import com.rathore.health.model.IllnessData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archi on 1/4/2017.
 */

public class SymptomMainListAdapter extends BaseAdapter {
    Dialog dialogMain;
    Context context;
    List<String> stringList;
    LayoutInflater inflater;

    public SymptomMainListAdapter(Context dialog, List<String> stringList, Dialog dialog1) {
        this.context = dialog;
        this.stringList = stringList;
        this.dialogMain =  dialog1;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return stringList.size();
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
        convertView = inflater.inflate(R.layout.adapter_symptom_main_dialog_list, null);
        TextView tvName = (TextView) convertView.findViewById(R.id.adapter_symptom_main_dialog_tv_name);
        tvName.setText(stringList.get(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubSelectItem(stringList.get(position));
                dialogMain.dismiss();
            }
        });

        return convertView;
    }

    private void openSubSelectItem(String s) {
        ArrayList<IllnessData> arrayIlnessData = new ArrayList<>();

        for (int i = 0; i < FragmentIllness.arrayIlnessData.size(); i++) {
            if (FragmentIllness.arrayIlnessData.get(i).getStrName().toString().toLowerCase().contains(s.toString().toLowerCase())) {
                arrayIlnessData.add(FragmentIllness.arrayIlnessData.get(i));
            }
        }

       Log.i("sublistdata",arrayIlnessData.toString());
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_profile_symptom_display_main_array);
        ListView lvView = (ListView) dialog.findViewById(R.id.dialog_profile_symptom_main_array_lv);
        SymptomSubCatAdapter symptomMainListAdapter = new SymptomSubCatAdapter(context, arrayIlnessData,dialog,dialogMain);
        lvView.setAdapter(symptomMainListAdapter);
        dialog.show();
    }
}
