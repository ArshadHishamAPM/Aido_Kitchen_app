package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathore.kitchen.Activity.DatabaseHalper;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.GroceryAddItemUtility;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/3/2017.
 */
public class GroceryListItemAdapter extends ArrayAdapter<GroceryAddItemUtility> {

    public Context mContext;
    public int ResourceId;
    public ArrayList<GroceryAddItemUtility> arraydata;
    public LayoutInflater inflater;
    public String grocery;
    public GroceryAddItemUtility itemtoshop;
    DatabaseHalper helper;
    public GroceryListItemAdapter(Context groceryshop, int groceryshop_item, ArrayList<GroceryAddItemUtility> arraydietdata) {
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
            holder.Itemname = (TextView) convertView.findViewById(R.id.txt_GroceryListItemName);
            holder.ItenPrice = (TextView) convertView.findViewById(R.id.txt_GroceryListItemPrice);
            holder.Itemwieght = (TextView) convertView.findViewById(R.id.txt_GroceryListItemweight);
            holder.imgProduct = (ImageView) convertView.findViewById(R.id.img_GroceryListItemPic);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        holder.Itemname.setText("ProductName : " +arraydata.get(position).getG_itemname());
        holder.ItenPrice.setText("Price: " +arraydata.get(position).getG_itemprice());
        holder.Itemwieght.setText("Wieght: " +arraydata.get(position).getG_itemweight());

        byte[] outImage=arraydata.get(position).getG_itemppic();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgProduct.setImageBitmap(theImage);
        return convertView;
    }

    class HeaderViewHolder {
        TextView Itemname,ItenPrice,Itemwieght;
        ImageView imgProduct;

    }
}
