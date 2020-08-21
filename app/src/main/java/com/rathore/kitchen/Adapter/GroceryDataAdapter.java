package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathore.kitchen.Activity.DatabaseHalper;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.StoreNameUtility;

import java.util.ArrayList;

/**
 * Created by archi on 1/31/2017.
 */

public class GroceryDataAdapter extends BaseAdapter {

    public ArrayList<StoreNameUtility> array_grocery_Item;
    public LayoutInflater inflater;
    public Context context;
    public DatabaseHalper helper;
    public StoreNameUtility storeNameUtility;


    public GroceryDataAdapter(Context context, ArrayList<StoreNameUtility> array_grocery_Item) {
        this.context = context;
        helper = new DatabaseHalper(context);
        this.array_grocery_Item = array_grocery_Item;
    }

    @Override
    public int getCount() {
        return array_grocery_Item.size();
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

        final GroceryDataAdapter.HeaderViewHolder holder;

        if (convertView == null) {
            holder = new GroceryDataAdapter.HeaderViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.grocery_list_data, parent, false);

            holder.ItemName = (TextView) convertView.findViewById(R.id.grocery_listdata_text);
            holder.img = (ImageView) convertView.findViewById(R.id.grocery_listdata_img);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  Log.e("DDDDATATTAT", "" + array_grocery_Item.get(position).getId());

                    helper.deleteStoreName(array_grocery_Item.get(position).getId());
                    array_grocery_Item.remove(position);
                    notifyDataSetChanged();
                   /* Intent intent=new Intent(context, HomeAct.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);*/

                }
            });
            convertView.setTag(holder);
        } else {
            holder = (GroceryDataAdapter.HeaderViewHolder) convertView.getTag();
        }

        holder.ItemName.setText(array_grocery_Item.get(position).getStorename());
        return convertView;
    }


    class HeaderViewHolder {
        TextView ItemName;
        ImageView img;


    }
}




