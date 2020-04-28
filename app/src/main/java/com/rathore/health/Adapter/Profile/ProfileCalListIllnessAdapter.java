package com.rathore.health.Adapter.Profile;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.R;

import java.util.ArrayList;

/**
 * Created by archi on 1/16/2017.
 */

public class ProfileCalListIllnessAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
   ArrayList<String> arrayList;
    static  int count=0;
    TextView tvName;

    public ProfileCalListIllnessAdapter(Context context, ArrayList<String> arrayListIllnessList) {

        this.context = context;
        Log.i("profileadapterconstrucr","called");
        this.arrayList = arrayListIllnessList;
        Log.i("listdataaa",arrayList.toString());

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {

        Log.i("arraylistsizee", String.valueOf(arrayList.size()));
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
    public View getView(int position, View convertView, ViewGroup parent) {
        count=count+1;
        Log.i("countt", String.valueOf(count));
        Log.i("profileadapter","called");
        Log.i("listdata11",arrayList.toString());
        if (convertView == null){
            convertView = inflater.inflate(R.layout.adpater_profile_cal_illness_adapter,parent,false);
            tvName = (TextView)convertView.findViewById(R.id.adapter_profile_cal_ilness_tv);
            Log.i("listdata1",arrayList.get(position));
            tvName.setText(arrayList.get(position));
        }
//        for(int i=0;i<arrayList.size();i++)
//        {
////        Log.i("ILLNESSadapter",arrayList.get(position));
//
//
//            String txt=arrayList.get(i);
            //String txt=arrayList.toString();

            //Log.i("listdata1position",String.valueOf(position));
           // Log.i("listdata1",txt);




       // }
//notifyDataSetChanged();
        //arrayList.clear();
        return convertView;

}




}
