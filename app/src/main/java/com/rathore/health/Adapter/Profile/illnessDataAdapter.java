package com.rathore.health.Adapter.Profile;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archi on 1/3/2017.
 */

public class illnessDataAdapter extends RecyclerView.Adapter<illnessDataAdapter.Holder> {

    ArrayList<IllnessData> arrayList;
     Context context;
    private  int selectedPosition = -1;
    boolean[] chkArray;

    public illnessDataAdapter(Context activity, ArrayList<IllnessData> arrayIlnessData) {
        this.context =  activity;
       // this.arrayList = arrayIlnessData;
        arrayList = new ArrayList<>(arrayIlnessData);
         chkArray = new boolean[arrayList.size()];
    }

    public illnessDataAdapter(FragmentActivity activity) {
        this.context =  activity;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_illness_data, parent, false);
        illnessDataAdapter.Holder holder = new illnessDataAdapter.Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final illnessDataAdapter.Holder holder, final int position) {
        holder.tvName.setText(arrayList.get(position).getStrName());
//
       // holder.tvcheckbox.setOnCheckedChangeListener(null);
        holder.tvcheckbox.setChecked(arrayList.get(position).isSelected());

        holder.tvcheckbox.setTag(arrayList.get(position));
        holder.tvcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                IllnessData contact = (IllnessData) cb.getTag();

                contact.setSelected(cb.isChecked());
                arrayList.get(position).setSelected(cb.isChecked());


//                Toast.makeText(
//                        v.getContext(),
//                        "Clicked on Checkbox: " + cb.getText() + " is "
//                                + cb.isChecked(), Toast.LENGTH_LONG).show();
                if(cb.isChecked()){
                    Log.i("checked","true");
                    Calendar calendar = Calendar.getInstance();
                    int date  = calendar.get(Calendar.DATE);
                    int month = calendar.get(Calendar.MONTH)+1;
                    int year = calendar.get(Calendar.YEAR);
                    String currentDate  = date + "-" + month + "-"+year;
                    ProfileAddValues profileAddValues = new ProfileAddValues();
                    profileAddValues.setStrDate(currentDate);
                    profileAddValues.setId(position);
                    profileAddValues.setStrName(arrayList.get(position).getStrName());
                    ActivityProfileCalender.arrayListillness.add(profileAddValues);
                   // ActivityProfileCalender.arrayListillness1.put(position,profileAddValues);
                    //Toast.makeText(context,"selected",Toast.LENGTH_SHORT).show();
                    ActivityProfileReportSomethingTab.tvHeaderTotal.setText("Selecte Items :"+ActivityProfileCalender.arrayListillness.size());
                   // arrayList.remove(position);
                    //selectedPosition = position;
                    chkArray[position]=true;
                   // notifyDataSetChanged();
//                    try {
                    //notifyItemChanged(position);
//                    } catch (Exception e) {
//                        Log.e("onCheckChanged", e.getMessage());
//                    }
                }
                else
                {
                    Log.i("checked","false");
                    getdata(position);
                    chkArray[position]=false;
                    ActivityProfileReportSomethingTab.tvHeaderTotal.setText("Selecte Items :"+ActivityProfileCalender.arrayListillness.size());
                }
            }
        });
       //

        //if true, your checkbox will be selected, else unselected
       // holder.tvcheckbox.setChecked(arrayList.get(position).getStrName().isSelected());



//        holder.lineamView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                int date  = calendar.get(Calendar.DATE);
//                int month = calendar.get(Calendar.MONTH)+1;
//                int year = calendar.get(Calendar.YEAR);
//                String currentDate  = date + "-" + month + "-"+year;
//                ProfileAddValues profileAddValues = new ProfileAddValues();
//                profileAddValues.setStrDate(currentDate);
//                profileAddValues.setStrName(arrayList.get(position).getStrName());
//               // ActivityProfileCalender.arrayListillness.add(profileAddValues);
//
//                Toast.makeText(context,"selected",Toast.LENGTH_SHORT).show();
//                ActivityProfileReportSomethingTab.tvHeaderTotal.setText("selecte items :"+ActivityProfileCalender.arrayListillness.size());
//               // ActivityProfileReportSomethingTab.tvHeaderTotal.setText("selecte items :"+ActivityProfileCalender.arrayListillness1.size());
//                arrayList.remove(position);
//                notifyDataSetChanged();
//            }
//        });
//        if(selectedPosition==position){
//            holder.tvcheckbox.setChecked(true);
//        }
//        else {
//            holder.tvcheckbox.setChecked(false);
//        }

        //holder.tvcheckbox.setChecked(selectedPosition == position);
//        holder.tvcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if(isChecked){
//                    arrayList.get(holder.getAdapterPosition()).setSetseleted(isChecked);
//
//holder.tvcheckbox.setChecked(isChecked);
//
//                    Calendar calendar = Calendar.getInstance();
//                    int date  = calendar.get(Calendar.DATE);
//                    int month = calendar.get(Calendar.MONTH)+1;
//                    int year = calendar.get(Calendar.YEAR);
//                    String currentDate  = date + "-" + month + "-"+year;
//                    ProfileAddValues profileAddValues = new ProfileAddValues();
//                    profileAddValues.setStrDate(currentDate);
//                    profileAddValues.setId(position);
//                    profileAddValues.setStrName(arrayList.get(position).getStrName());
//                    ActivityProfileCalender.arrayListillness.add(profileAddValues);
//                   // ActivityProfileCalender.arrayListillness1.put(position,profileAddValues);
//                    Toast.makeText(context,"selected",Toast.LENGTH_SHORT).show();
//                    ActivityProfileReportSomethingTab.tvHeaderTotal.setText("Selecte Items :"+ActivityProfileCalender.arrayListillness.size());
//                   // arrayList.remove(position);
//                    //selectedPosition = position;
//                    chkArray[position]=true;
//                   // notifyDataSetChanged();
////                    try {
//                    //notifyItemChanged(position);
////                    } catch (Exception e) {
////                        Log.e("onCheckChanged", e.getMessage());
////                    }
//
//                }
//                else {
//                    //ActivityProfileCalender.arrayListillness.remove(arrayList.get(position).getStrName());
//                    getdata(position);
//                    chkArray[position]=false;
//                    ActivityProfileReportSomethingTab.tvHeaderTotal.setText("Selecte Items :"+ActivityProfileCalender.arrayListillness.size());
//                   // arrayList.remove(position);
//                   // notifyDataSetChanged();
////                    try {
////                        notifyItemChanged(position);
////                    } catch (Exception e) {
////                        Log.e("onCheckChanged", e.getMessage());
////                    }
//                }
//
//
//            }
//
//        });
//        //check condition for checkboxes
//        if(chkArray.length>0)
//        {
//            for (int i = 0; i < chkArray.length; i++)
//            {
//                if( chkArray[position])
//                {
//                    holder.tvcheckbox.setChecked(true);
//                }
//            }
//        }



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Holder extends RecyclerView.ViewHolder{
        @BindView(R.id.adapter_illness_data_tv_list)
        TextView tvName;
        @BindView(R.id.adapter_illness_ll)
        LinearLayout lineamView;

        @BindView(R.id.adapter_illness_data_tv_checkbox)
        CheckBox tvcheckbox;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.setIsRecyclable(false);

           // tvcheckbox.setChecked(selectedPosition == getAdapterPosition());
//            if(selectedPosition==getAdapterPosition()){
//                tvcheckbox.setChecked(true);
//            }
//            else {
//                tvcheckbox.setChecked(false);
//            }
//            tvcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(isChecked){
//
//                        Calendar calendar = Calendar.getInstance();
//                        int date  = calendar.get(Calendar.DATE);
//                        int month = calendar.get(Calendar.MONTH)+1;
//                        int year = calendar.get(Calendar.YEAR);
//                        String currentDate  = date + "-" + month + "-"+year;
//                        ProfileAddValues profileAddValues = new ProfileAddValues();
//                        profileAddValues.setStrDate(currentDate);
//                        profileAddValues.setId(getAdapterPosition());
//                        profileAddValues.setStrName(arrayList.get(getAdapterPosition()).getStrName());
//                        ActivityProfileCalender.arrayListillness.add(profileAddValues);
//                        // ActivityProfileCalender.arrayListillness1.put(position,profileAddValues);
//                        Toast.makeText(context,"selected",Toast.LENGTH_SHORT).show();
//                        ActivityProfileReportSomethingTab.tvHeaderTotal.setText("Selecte Items :"+ActivityProfileCalender.arrayListillness.size());
//                        // arrayList.remove(position);
//                        Log.i("adapterposition",String.valueOf(getAdapterPosition()));
//                        selectedPosition = getAdapterPosition();
//                       // notifyDataSetChanged();
////                    try {
//                        //notifyItemChanged(position);
////                    } catch (Exception e) {
////                        Log.e("onCheckChanged", e.getMessage());
////                    }
//
//                    }
//                    else {
//                        getdata(getAdapterPosition());
//                        Log.i("adapterposition",String.valueOf(getAdapterPosition()));
//
//                        ActivityProfileReportSomethingTab.tvHeaderTotal.setText("Selecte Items :"+ActivityProfileCalender.arrayListillness.size());
//                        // arrayList.remove(position);
//                        // notifyDataSetChanged();
////                    try {
////                        notifyItemChanged(position);
////                    } catch (Exception e) {
////                        Log.e("onCheckChanged", e.getMessage());
////                    }
//                    }
//
//                }
//            });
//            if(selectedPosition==getAdapterPosition()){
//                tvcheckbox.setChecked(true);
//            notifyDataSetChanged();}
//            else {
//                tvcheckbox.setChecked(false);
//            }
            }
        }
   public void getdata(int position){
       for(int i=0;i<ActivityProfileCalender.arrayListillness.size();i++){
           if(ActivityProfileCalender.arrayListillness.get(i).getId()==position){
               ActivityProfileCalender.arrayListillness.remove(i);
           }
       }
   }
    }

