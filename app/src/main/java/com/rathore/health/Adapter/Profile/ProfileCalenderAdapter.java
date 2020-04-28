package com.rathore.health.Adapter.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

//import com.example.archi.health.Activity.Profile.ActivityProfileCalender;
//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileCalender;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.Profile.ActivityProfileCalender;
import com.rathore.health.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archi on 1/6/2017.
 */

public class ProfileCalenderAdapter extends RecyclerView.Adapter<ProfileCalenderAdapter.Holder> {
    ProfileCalListIllnessAdapter profileCalListIllnessAdapter;
    Context context;
    List<HashMap<String, String>> arrayList;
    ArrayList<String> arrayListIllnessList= new ArrayList<>();

    HashMap<String, String> arrayList1;

//    public ProfileCalenderAdapter(ActivityProfileCalender activityProfileCalender, List<HashMap<String, String>> profileCalenderDataArray) {
//        this.context = activityProfileCalender;
//        this.arrayList = profileCalenderDataArray;
//
//    //Log.i("datelist",arrayList.toString());
//    }

    public ProfileCalenderAdapter(ActivityProfileCalender activityProfileCalender) {


        this.context = activityProfileCalender;

        //this.arrayList=profileCalenderDataArray;
        //Log.i("arraylistt",arrayList.toString());
    }
//    public ProfileCalenderAdapter(ActivityProfileCalender activityProfileCalender, List<HashMap<String, String>> profileCalenderDataArray) {
//
//        this.context = activityProfileCalender;
//
//        this.arrayList=profileCalenderDataArray;
//        Log.i("arraylistt",arrayList.toString());
//    }
//    //new changes
    public void updateList (List<HashMap<String, String>> profileCalenderDataArray) {

        this.arrayList = profileCalenderDataArray;
        Log.i("arraylistt",arrayList.toString());
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("arraylistttimes","called");
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_calder_adapter,parent,false);
        ProfileCalenderAdapter.Holder holder = new ProfileCalenderAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        arrayListIllnessList.clear();
        Log.i("holderposition", String.valueOf(position));

        for(int i=0;i<arrayList.size();i++){
            Log.i("arraylistposition", String.valueOf(arrayList.size()));
            String date = arrayList.get(i).get("key_profile_cal_date");

            String uiqId = arrayList.get(i).get("key_profile_cal_uniq");
            String name = arrayList.get(i).get("key_profile_cal_name");
            String CalId = arrayList.get(i).get("key_profile_cal_id");
            String jsonString = arrayList.get(i).get("key_profile_cal_string");


           // holder.tvHeader.setText(name);

                try {

            JSONObject jsonObject = new JSONObject(jsonString.toString());
            JSONArray jsonArray1 =  jsonObject.getJSONArray("data");
         //Log.i("jsondata",String.valueOf(jsonArray1.length()));
                    Log.i("jsondata",jsonArray1.toString());
//                    String name1=jsonArray1.getJSONObject(1).getString("name");
//                    arrayListIllnessList.add(name1);
                    for (int i1=0;i1<jsonArray1.length();i1++)
            {

                //Log.i("jsonstring","jsonString");
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i1);
                String name1 = jsonObject1.getString("name");
                Log.i("namee",name1);
                Log.i("arraylistillnessname",name1);
                arrayListIllnessList.add(name1);

//
            }
//            Log.i("arraylistillness",arrayListIllnessList.toString());
//             profileCalListIllnessAdapter = new ProfileCalListIllnessAdapter(context,arrayListIllnessList);
//            profileCalListIllnessAdapter.notifyDataSetChanged();
//
//           // profileCalListIllnessAdapter.notifyDataSetInvalidated();
//
//
//
//            holder.Listview.setAdapter(profileCalListIllnessAdapter);
            //holder.Listview.setAdapter(profileCalListIllnessAdapter);


       // }


        } catch (JSONException e) {
                    e.printStackTrace();
                }}



        Log.i("arraylistillness",arrayListIllnessList.toString());
        profileCalListIllnessAdapter=null;
        profileCalListIllnessAdapter = new ProfileCalListIllnessAdapter(context,arrayListIllnessList);
        profileCalListIllnessAdapter.notifyDataSetChanged();

        // profileCalListIllnessAdapter.notifyDataSetInvalidated();

//ArrayAdapter arrayAdapter=new ArrayAdapter(context,android.R.layout.simple_list_item_1,arrayListIllnessList);
//        holder.Listview.setAdapter(arrayAdapter);

        holder.Listview.setAdapter(profileCalListIllnessAdapter);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
//        @BindView(R.id.adapter_calder_textview)
//        TextView tvHeader;
        @BindView(R.id.adapter_calder_listview)
ListView Listview;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
