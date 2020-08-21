package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathore.kitchen.Activity.DatabaseHalper;
import com.rathore.kitchen.Activity.HomeAct;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DietItemsUtility;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Ravi Archi on 12/27/2016.
 */
public class DietAdapter extends ArrayAdapter<DietItemsUtility> {

    public Context mContext;
    public int ResourceId;
    public ArrayList<DietItemsUtility> arraydata;
    public LayoutInflater inflater;
    DatabaseHalper helper;
    public DietAdapter(Context dietwhizAct, int dietwhizlogbook_item, ArrayList<DietItemsUtility> arraydietdata) {
        super(dietwhizAct,dietwhizlogbook_item,arraydietdata);
        this.mContext = dietwhizAct;
        this.ResourceId = dietwhizlogbook_item;
        this.arraydata = arraydietdata;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        helper = new DatabaseHalper(mContext);

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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(ResourceId, null);
        DietItemsUtility dietitem = new DietItemsUtility();
        final int _id = arraydata.get(position).getId();
        TextView tvTitle = (TextView) convertView.findViewById(R.id.txt_logbooktitle_item);
        TextView tvDate = (TextView) convertView.findViewById(R.id.txt_logbooktitle_date);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.delete);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteitem(_id);
            }
        });
        tvDate.setText(arraydata.get(position).getsDate().toString());
        ImageView img_itemPic = (ImageView)convertView.findViewById(R.id.img_logbookItem);
        tvTitle.setText(arraydata.get(position).getTitle().toString());
        byte[] outImage=arraydata.get(position).getBytearray();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        img_itemPic.setImageBitmap(theImage);
        return convertView;
    }
    public void deleteitem(int id){
        helper.deleteContact(id);
//               /* onBackPressed();
//                arraydata.remove(position);
//                notifyDataSetChanged();*/
        Intent intent=new Intent(mContext,HomeAct.class);
        mContext.startActivity(intent);
    }

}
