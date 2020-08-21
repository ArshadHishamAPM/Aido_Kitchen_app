package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.archirayan.kitchen.Activity.RecipesDetailsAct;
//import com.archirayan.kitchen.Model.RecipesListModel;
//import com.archirayan.kitchen.R;
import com.bumptech.glide.Glide;
import com.rathore.kitchen.Activity.RecipesDetailsAct;
import com.rathore.kitchen.Model.RecipesListModel;
import com.rathore.kitchen.R;

import java.util.ArrayList;

/**
 * Created by archirayan on 23/12/16.
 */

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.MyViewHolder> {

    public ArrayList<RecipesListModel> recipesListModelsArray;
    public Context context;

    public RecipesListAdapter(Context context, ArrayList<RecipesListModel> recipesListModelsArray) {
        this.recipesListModelsArray = recipesListModelsArray;
        this.context = context;
//        RoundedImageView riv = new RoundedImageView(context);
//        riv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        riv.setCornerRadius((float) 10);
//        riv.setBorderWidth((float) 2);
//        riv.setBorderColor(Color.DKGRAY);
//        riv.mutateBackground(true);
//
//        riv.setOval(true);
//        riv.setTileModeX(Shader.TileMode.REPEAT);
//       // riv.setImageDrawable();

//        riv.setBackground(backgroundDrawable);


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_item_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final RecipesListModel recipesListModel = recipesListModelsArray.get(position);
        holder.title.setText(recipesListModel.getTitle());
        Log.i("titlee",recipesListModel.getTitle());

        holder.liRecipeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String rid = recipesListModelsArray.get(position).getRecipe_id();
                Intent intentRecipeDetails = new Intent(context, RecipesDetailsAct.class);
                intentRecipeDetails.putExtra("rid", rid);
                intentRecipeDetails.putExtra("title",recipesListModel.getTitle());
                intentRecipeDetails.putExtra("status", "0");
                context.startActivity(intentRecipeDetails);

            }
        });


        String imgUrl = recipesListModel.getImage_url();
        if (!imgUrl.equalsIgnoreCase("")) {
            Glide.with(context).load(imgUrl).fitCenter().placeholder(R.drawable.image_loader).into(holder.ivRecipe);

        }


    }

    @Override
    public int getItemCount() {
        return recipesListModelsArray.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView ivRecipe;
        public LinearLayout liRecipeItem;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tvNameRecipesAdapter);
            ivRecipe = (ImageView) view.findViewById(R.id.ivRecipesRecipesAdapter);
            liRecipeItem = (LinearLayout) view.findViewById(R.id.lirecipesitem);


        }
    }

}
