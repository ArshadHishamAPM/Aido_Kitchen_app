package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathore.kitchen.Activity.DatabaseHalper;
import com.rathore.kitchen.Model.WishListModel;
import com.rathore.kitchen.R;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Created by archi on 1/19/2017-----ADD to Cart-----.
 */

public class WishListAdapter extends BaseAdapter {

    public Context mContext;
    public ArrayList<WishListModel> arraydata;
    public LayoutInflater inflater;
    public DatabaseHalper halper;


    public WishListAdapter(ArrayList<WishListModel> cartModels, Context context) {

        this.arraydata = cartModels;
        this.mContext = context;
        halper=new DatabaseHalper(context);
    }



    @Override
    public int getCount() {
        return arraydata.size();
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
        HeaderViewHolder holder;
        if (arraydata.get(position) == null) {
            arraydata.clear();
        }

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_cartadd_data, parent, false);
            holder.ItemName = (TextView) convertView.findViewById(R.id.iv_addtocart_name);
            holder.img_pic = (ImageView) convertView.findViewById(R.id.iv_addtocart_delete);
            holder.circularImageView = (CircularImageView) convertView.findViewById(R.id.iv_addtocart_img);
            holder.img_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
/*

                    Gson gson = new Gson();
                    arraydata = gson.fromJson(strObj, WishListFragment.class);
                      String strObj = getArguments().getString("winelistdetail");
                    WishListAdapter mArrayListAllWine = gson.fromJson(strObj,WishListFragment.class);


*/

                    Log.e("ArrayList","" +arraydata);
                    halper.deleteCartData(arraydata.get(position).getId());
                    arraydata.remove(position);
                    notifyDataSetChanged();


                }
            });

            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }


       /* if (arraydata.size() == 0) {
            arraydata.clear();
        }*/
        holder.ItemName.setText(arraydata.get(position).getWlistname());
        //  holder.ItemId.setText("DrinkId: " +arraydata.get(position).getDrinkid());
        if(arraydata.get(position).getWlistimg().length()>0){
            Glide.with(mContext).load(arraydata.get(position).getWlistimg()).into(holder.circularImageView);
        }
        return convertView;
    }

    class HeaderViewHolder {
        TextView ItemName;
        ImageView img_pic;
        private CircularImageView circularImageView;


    }
}

