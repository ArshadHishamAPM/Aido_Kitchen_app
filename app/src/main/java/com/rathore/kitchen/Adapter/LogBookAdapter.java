package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.archirayan.kitchen.R;

import com.rathore.kitchen.R;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 12/24/2016.
 */
public class LogBookAdapter extends BaseAdapter {

    public Context mContext;
    public int ResourceId;
    public ArrayList<String> arraydata;
    public LayoutInflater inflater;

    public LogBookAdapter(Context context, int dietwhizlogbook_item, ArrayList<String> array) {
        this.mContext = context;
        this.ResourceId = dietwhizlogbook_item;
        this.arraydata = array;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arraydata.size();
    }

    @Override
    public Object getItem(int position) {
        return arraydata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(ResourceId, null);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.txt_logbooktitle_item);

        tvTitle.setText(arraydata.get(position));

        return convertView;
    }

}