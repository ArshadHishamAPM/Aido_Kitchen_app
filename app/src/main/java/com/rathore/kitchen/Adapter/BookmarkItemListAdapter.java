package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.archirayan.kitchen.Activity.RecipesDetailsAct;
//import com.archirayan.kitchen.R;

import com.rathore.kitchen.Activity.RecipesDetailsAct;
import com.rathore.kitchen.R;

import java.util.ArrayList;

/**
 * Created by archirayan on 26/12/16.
 */

public class BookmarkItemListAdapter extends RecyclerView.Adapter<BookmarkItemListAdapter.MyViewHolder> {

    String imagelink;
    public Context context;
    public ArrayList<String> urlArray;
    public ArrayList<String> arrayListTitle;
    public ArrayList<Bitmap> bitmapimages;

    public BookmarkItemListAdapter(Context context, ArrayList<String> arrayListTitle, ArrayList<String> urlArray, String imagelink) {

        this.context = context;
        this.bitmapimages = bitmapimages;
        this.arrayListTitle = arrayListTitle;
        this.urlArray = urlArray;
        this.imagelink=imagelink;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.bookmark_item_list_adapter, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        String title = arrayListTitle.get(position);
        holder.title.setText(title);
        String urlTitle = urlArray.get(position);
        holder.titleURl.setText("- "+urlTitle);






        holder.liBookmarkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRecipesDetails = new Intent(context, RecipesDetailsAct.class);
                Log.i("bookmarkurl",urlArray.get(position));

                intentRecipesDetails.putExtra("bookmarkurl",urlArray.get(position));
                intentRecipesDetails.putExtra("imageurl",imagelink);
                intentRecipesDetails.putExtra("status","0");
                context.startActivity(intentRecipesDetails);


            }
        });

    }

    @Override
    public int getItemCount() {
        return urlArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, titleURl;
        public LinearLayout liBookmarkItem;
        public ImageView ivuRL;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tvTitleBookmarkitemList);
            titleURl = (TextView) view.findViewById(R.id.tvTitleUrlBookmarkitemList);
            liBookmarkItem = (LinearLayout) view.findViewById(R.id.liBookmarklist);



        }
    }
}
