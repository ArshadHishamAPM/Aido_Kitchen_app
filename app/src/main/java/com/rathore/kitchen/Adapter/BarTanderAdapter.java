package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathore.kitchen.Activity.DatabaseHalper;
import com.rathore.kitchen.Model.BarTanderModal;
import com.rathore.kitchen.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/4/2017.
 */
public class BarTanderAdapter extends ArrayAdapter<BarTanderModal> {

    public Context mContext;
    public int ResourceId;
    public ArrayList<BarTanderModal> arraydata;
    public LayoutInflater inflater;
    public String grocery;
    public BarTanderModal itemtoshop;
    DatabaseHalper helper;
    public BarTanderAdapter(Context groceryshop, int groceryshop_item, ArrayList<BarTanderModal> arraydietdata) {
        super(groceryshop,groceryshop_item,arraydietdata);
        this.mContext = groceryshop;
        this.ResourceId = groceryshop_item;
        this.arraydata = arraydietdata;
        this.grocery = grocery;
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
            holder.vtstrdrink = (TextView) convertView.findViewById(R.id.txt_bartander_strdrink);
           // holder.drinhid = (TextView) convertView.findViewById(R.id.txt_bartander_drinkid);
            holder.imgThub = (ImageView) convertView.findViewById(R.id.img_bartanderimage);

            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
      String ss =   arraydata.get(position).getStrdrink();
        itemtoshop = arraydata.get(position);
        holder.vtstrdrink.setText("Drinks : " +ss);
       // holder.drinhid.setText("DrinkCode: " +itemtoshop.getDrinkid());
        Glide.with(mContext).load(itemtoshop.getStrdrinkthub()).placeholder(R.drawable.loading).into(holder.imgThub);
        return convertView;
    }

    class HeaderViewHolder {
        TextView vtstrdrink,drinhid;
        ImageView imgThub;


    }
}

