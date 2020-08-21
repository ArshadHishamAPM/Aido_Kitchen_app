package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rathore.kitchen.Activity.*;
import com.rathore.kitchen.Model.AddWineBottleModal;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.Constant;
import com.rathore.kitchen.Utils.DbHelper;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Created by archi on 1/20/2017//////////////////Bottle list Fragment////////.
 */

public class MyCallerAdapter extends BaseAdapter {

    public ArrayList<AddWineBottleModal> arrayaddbottel;
    public LayoutInflater inflater;
    public Context context;
    public DbHelper dbHelper;

    public MyCallerAdapter(ArrayList<AddWineBottleModal> arrayaddbottel, Context context) {

        this.arrayaddbottel = arrayaddbottel;
        dbHelper=new DbHelper(context);
        this.context = context;
    }


    @Override
    public int getCount() {
        return arrayaddbottel.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_vinebottel_detail, parent, false);
            holder.llout = (LinearLayout) convertView.findViewById(R.id.vine_bottle_linearlayout);
            holder.llout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("id", "" + arrayaddbottel.get(position).getId());
                    //Toast.makeText(context, "" + arrayaddbottel.get(position).getId(), Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    Intent intent = new Intent(context, WineBottelDetailDisplay.class);
                    intent.putExtra("obj", gson.toJson(arrayaddbottel.get(position)));
                    intent.putExtra(Constant.Id, String.valueOf(arrayaddbottel.get(position).getId()));
                    context.startActivity(intent);
                }
            });
            holder.ItemName = (TextView) convertView.findViewById(R.id.vine_name);
            holder.ItemUpdate = (Button) convertView.findViewById(R.id.updatebtn);
            holder.ItemUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();
                    Intent intent = new Intent(context, WineBottleDetailUpdate.class);
                    intent.putExtra("obj", gson.toJson(arrayaddbottel.get(position)));
                    intent.putExtra(Constant.Id, String.valueOf(arrayaddbottel.get(position).getId()));
                    context.startActivity(intent);

                }
            });
            holder.ItemDelete = (ImageView) convertView.findViewById(R.id.vine_delete);
            holder.ItemDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dbHelper.deletWineBottle(arrayaddbottel.get(position).getId());
                    arrayaddbottel.remove(position);
                    notifyDataSetChanged();
                   /* Intent intent=new Intent(context,HomeAct.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);*/


                }
            });
            holder.circularImageView = (CircularImageView) convertView.findViewById(R.id.vine_circleimgview);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }


        holder.ItemName.setText("Drink : " + arrayaddbottel.get(position).getStrwinename());
        Glide.with(context).load(arrayaddbottel.get(position).getByteImage()).into(holder.circularImageView);

        return convertView;
    }


    class HeaderViewHolder {
        TextView ItemName;
        ImageView ItemDelete;
        Button ItemUpdate;
        private CircularImageView circularImageView;
        public LinearLayout llout;


    }
}



