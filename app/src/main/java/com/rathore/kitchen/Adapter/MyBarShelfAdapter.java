package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rathore.kitchen.Model.DrinkAllDetailModal;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/9/2017.
 */
public class MyBarShelfAdapter extends ArrayAdapter<DrinkAllDetailModal> {

    public Context mContext;
    public int ResourceId;
    public ArrayList<DrinkAllDetailModal> arraydata;
    public LayoutInflater inflater;
    public String grocery;
    public DrinkAllDetailModal itemtoshop;
    DbHelper helper;
    public GridView listVIew;
    public MyBarShelfAdapter(Context groceryshop, int groceryshop_item, ArrayList<DrinkAllDetailModal> arraydietdata, GridView mListView) {
        super(groceryshop,groceryshop_item,arraydietdata);
        this.mContext = groceryshop;
        this.ResourceId = groceryshop_item;
        this.listVIew = mListView;
        this.arraydata = arraydietdata;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            holder.vtstrdrink = (TextView) convertView.findViewById(R.id.txtmybarshelf_strdrink);
            // holder.drinhid = (TextView) convertView.findViewById(R.id.txt_bartander_drinkid);
            holder.imgThub = (ImageView) convertView.findViewById(R.id.img_barshelfimage);
            holder.delete = (ImageView) convertView.findViewById(R.id.img_mybarshelf_delete);


            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        itemtoshop = arraydata.get(position);
       // String ss =   arraydata.get(position).getStrDrink();
        holder.vtstrdrink.setText(arraydata.get(position).getStrDrink());
        // holder.drinhid.setText("DrinkCode: " +itemtoshop.getDrinkid());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper = new DbHelper(mContext);
                int rid = arraydata.get(position).getId();
                Log.d("id...", String.valueOf(rid));
                int dleresponse =  helper.DeleteMyShelfItem(rid);
                Toast.makeText(v.getContext(),"Deleted" +dleresponse, Toast.LENGTH_SHORT).show();
                arraydata.remove(position);
                notifyDataSetChanged();

            }
        });
        Glide.with(mContext).load(itemtoshop.getByteImage()).placeholder(R.drawable.loading).into(holder.imgThub);
        return convertView;
    }


    class HeaderViewHolder {
        TextView vtstrdrink,drinhid;
        ImageView imgThub,delete;


    }

}

