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

import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.AddItemToShopUtility;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/2/2017.
 */
public class GroceryShopAdapter extends ArrayAdapter<AddItemToShopUtility> {

    public Context mContext;
    public int ResourceId;
    public ArrayList<AddItemToShopUtility> arraydata;
    public LayoutInflater inflater;

    public GroceryShopAdapter(Context groceryshop, int groceryshop_item, ArrayList<AddItemToShopUtility> arraydietdata) {
        super(groceryshop, groceryshop_item, arraydietdata);
        this.mContext = groceryshop;
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
            holder.Itemname = (TextView) convertView.findViewById(R.id.txt_ShopItemName);
            holder.ItenPrice = (TextView) convertView.findViewById(R.id.txt_ShopItemPrice);
            holder.Itemwieght = (TextView) convertView.findViewById(R.id.txt_ShopItemweight);
            holder.imgProduct = (ImageView) convertView.findViewById(R.id.img_shopProductItem_pic);

            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.Itemname.setText("ProductName : " + arraydata.get(position).getItemname());
        holder.ItenPrice.setText("Price: " + arraydata.get(position).getPrice());
        holder.Itemwieght.setText("Wieght: " + arraydata.get(position).getWeight());
        Bitmap bitmap = BitmapFactory.decodeFile(arraydata.get(position).getImageinByte());
        holder.imgProduct.setImageBitmap(bitmap);


       /* ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);*/


        //    holder.imgProduct.setImageURI(Uri.parse(outImage));
        return convertView;
    }

    class HeaderViewHolder {
        TextView Itemname, ItenPrice, Itemwieght;
        ImageView imgProduct;
    }
}
