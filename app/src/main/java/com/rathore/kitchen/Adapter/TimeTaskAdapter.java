package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;
import com.rathore.kitchen.Utils.TimeTaskUtility;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/4/2017.
 */
public class TimeTaskAdapter extends ArrayAdapter<TimeTaskUtility> {

    public Context mContext;
    public int ResourceId;
    public ArrayList<TimeTaskUtility> arraydata;
    public LayoutInflater inflater;
    public String grocery;
    public TimeTaskUtility itemtoshop;
    public DbHelper helper;
    public TimeTaskAdapter(Context groceryshop, int groceryshop_item, ArrayList<TimeTaskUtility> arraydietdata) {
        super(groceryshop,groceryshop_item,arraydietdata);
        this.mContext = groceryshop;
        helper=new DbHelper(groceryshop);
        this.ResourceId = groceryshop_item;
        this.arraydata = arraydietdata;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arraydata.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(ResourceId, parent, false);
            holder.vttaskresult = (TextView) convertView.findViewById(R.id.txt_TimeTaskResult);
            holder.vtTaskNamevt = (TextView) convertView.findViewById(R.id.txt_TimeTaskName);
            holder.deleteimg = (ImageView) convertView.findViewById(R.id.timedelete);
            holder.deleteimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // arraydata.get(position);
                    helper.DeletetimeTask(arraydata.get(position).getId());
                    arraydata.remove(position);
                    notifyDataSetChanged();

                }
            });
            holder.TaskMinutItemwieght = (TextView) convertView.findViewById(R.id.txt_TimeTaskMinut);
            holder.vtTaskStatus = (TextView) convertView.findViewById(R.id.txt_TimeTaskstatus);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        itemtoshop = arraydata.get(position);
        holder.vttaskresult.setText("TaskType : " +itemtoshop.getTimetaskresukt());
        holder.vtTaskNamevt.setText("TaskName: " +itemtoshop.getTaskname());
        holder.TaskMinutItemwieght.setText(itemtoshop.getMinut());
        holder.vtTaskStatus.setText(itemtoshop.getStatus());

        return convertView;
    }

    class HeaderViewHolder {
        TextView vttaskresult,vtTaskNamevt,TaskMinutItemwieght,vtTaskStatus;
        ImageView deleteimg;


    }
}

