package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathore.kitchen.Activity.DatabaseHalper;
import com.rathore.kitchen.Model.WishListModel;
import com.rathore.kitchen.R;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/10/2017.
 */
public class VirtualAllWineAdapter extends BaseAdapter {

    public Context mContext;
    public int ResourceId;
    public ArrayList<WishListModel> arraydata;
    public LayoutInflater inflater;
    public String grocery;
    public WishListAdapter itemtoshop;
    public WishListModel wishListModel;
    public DatabaseHalper helper;

    public VirtualAllWineAdapter(Context activity, int bartander_item_field, ArrayList<WishListModel> mArrayListAllWine) {

        this.mContext = activity;
        helper=new DatabaseHalper(activity);
        this.ResourceId = bartander_item_field;
        this.arraydata = mArrayListAllWine;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        notifyDataSetChanged();
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

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(ResourceId, parent, false);
            holder.btnaddtocart = (Button) convertView.findViewById(R.id.txt_btnaddtocart);
            holder.ItemName = (TextView) convertView.findViewById(R.id.txt_virtualallwinestrdrink);
            holder.circularImageView = (CircularImageView) convertView.findViewById(R.id.img_virtualallwineimage);


            holder.btnaddtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    helper.AddToCart(arraydata.get(position));


                 // if(rawQuery("SELECT * FROM TABLE_ADDTOCART WHERE product_id = ? ", new String[] {wishListModel.getProductid()});)
/*
                    if(wishListModel.getProductid() == helper.getCartData(wishListModel.getProductid()))
                    {
                        Toast.makeText(mContext, "Added in Cart"  , Toast.LENGTH_SHORT).show();
                        helper.AddToCart(arraydata.get(position));
                    }
                    else {
                        Toast.makeText(mContext, "Already in Cart"  , Toast.LENGTH_SHORT).show();
                    }*/


                }
            });
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        holder.ItemName.setText(arraydata.get(position).getWlistname());
      //  holder.ItemId.setText("DrinkId: " +arraydata.get(position).getDrinkid());
        Glide.with(mContext).load(arraydata.get(position).getWlistimg()).into(holder.circularImageView);
        return convertView;
    }

    class HeaderViewHolder {
        TextView ItemName;
        ImageView img_pic;
        Button btnaddtocart;
        private CircularImageView circularImageView;;

    }
}


