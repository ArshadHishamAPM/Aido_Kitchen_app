package com.rathore.kitchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.archirayan.kitchen.Activity.RecipesDetailsAct;
//import com.archirayan.kitchen.Model.BookmarkRecipesModel;
//import com.archirayan.kitchen.R;
import com.bumptech.glide.Glide;
import com.rathore.kitchen.Activity.RecipesDetailsAct;
import com.rathore.kitchen.Model.BookmarkRecipesModel;
import com.rathore.kitchen.R;

import java.util.List;

/**
 * Created by archirayan on 26/12/16.
 */


public class RecipeItemAdapter extends RecyclerView.Adapter<RecipeItemAdapter.MyViewHolder> {


    public Context context;

    public List<BookmarkRecipesModel> bookmarkRecipesModels;

    public RecipeItemAdapter(Context context, List<BookmarkRecipesModel> bookmarkRecipesModels) {


        this.bookmarkRecipesModels = bookmarkRecipesModels;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.dietwhizlogbook_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final BookmarkRecipesModel bookmarkRecipesModel = bookmarkRecipesModels.get(position);

        holder.title.setText(bookmarkRecipesModel.getTitle());
        String img = bookmarkRecipesModel.getImage();
        if (!img.equalsIgnoreCase(""))
            Glide.with(context).load(img).placeholder(R.drawable.image_loader).into(holder.ivRecipe);


        holder.liRecipeitemAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BookmarkRecipesModel bookmarkRecipesModelClick = bookmarkRecipesModels.get(position);


                Intent intentDetail = new Intent(context, RecipesDetailsAct.class);

                intentDetail.putExtra("recipedetails", bookmarkRecipesModelClick);
                intentDetail.putExtra("status", "1");

                context.startActivity(intentDetail);

            }
        });
    }

    @Override
    public int getItemCount() {
        return bookmarkRecipesModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tvdate;
        public ImageView ivRecipe;
        public LinearLayout liRecipeitemAdapter;
        ImageView delete,deleteellipse;



        public MyViewHolder(View view) {
            super(view);
            delete = (ImageView) view.findViewById(R.id.delete);
            deleteellipse = (ImageView) view.findViewById(R.id.vine_delete);
            delete.setVisibility(View.INVISIBLE);
            deleteellipse.setVisibility(View.INVISIBLE);

            title = (TextView) view.findViewById(R.id.txt_logbooktitle_item);
            liRecipeitemAdapter = (LinearLayout) view.findViewById(R.id.liRecipeItemAdapter);

            tvdate = (TextView) view.findViewById(R.id.txt_logbooktitle_date);
            tvdate.setVisibility(View.GONE);
            ivRecipe = (ImageView) view.findViewById(R.id.img_logbookItem);

        }
    }

}
