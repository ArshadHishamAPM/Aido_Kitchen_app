package com.rathore.kitchen.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rathore.kitchen.R;

public class HomeAct extends AppCompatActivity implements View.OnClickListener {

    LinearLayout liRecipes, liDiet;
    ImageView ivgrocery,ivbartendar;
    private ImageView ivVirtualWine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        ivgrocery = (ImageView) findViewById(R.id.ivgrocery);
        ivgrocery.setOnClickListener(this);
        ivbartendar= (ImageView)findViewById(R.id.bartendar);
        ivbartendar.setOnClickListener(this);
        liRecipes = (LinearLayout) findViewById(R.id.linearRecipes);
        liRecipes.setOnClickListener(this);
        liDiet = (LinearLayout) findViewById(R.id.Img_Dietwhiz);
        liDiet.setOnClickListener(this);
       /* ivVirtualWine= (ImageView) findViewById(R.id.virtualwine);
        ivVirtualWine.setOnClickListener(this);*/


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.linearRecipes:
                Intent intentRecipesList = new Intent(HomeAct.this, RecipesItemAct.class);
                startActivity(intentRecipesList);
                break;


            case R.id.Img_Dietwhiz:
                Intent intentdietwhiz = new Intent(HomeAct.this, DietwhizAct.class);
                startActivity(intentdietwhiz);
                break;

            case R.id.ivgrocery:
                Intent intentgrocery = new Intent(HomeAct.this, GroceryAct.class);
                startActivity(intentgrocery);
                break;

            case  R.id.bartendar:
                Intent intentBarTendar = new Intent(HomeAct.this, BarTanderVitualWineAct.class);
                startActivity(intentBarTendar);
                break;

           /* case  R.id.virtualwine:
                Intent intentvirtualwine = new Intent(HomeAct.this, VirtualWineManager.class);
                startActivity(intentvirtualwine);
                break;*/

        }

    }

}
