package com.rathore.kitchen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathore.kitchen.Model.DrinkAllDetailModal;
import com.rathore.kitchen.Model.ShoppingModal;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/9/2017.
 */
public class MyBarShelfAllDetailsAct extends AppCompatActivity {
    public TextView tvidDrink, tvstrDrink, tvstrCategory, tvstrAlcoholic, tvstrGlass, tvstrInstructions, tvstrIngredient1, tvstrIngredient2,
            tvstrIngredient3, tvstrIngredient4, tvstrIngredient5, tvstrIngredient6, tvstrIngredient7, tvstrIngredient8, tvstrIngredient9, tvstrIngredient10, tvstrIngredient11,
            tvstrIngredient12, tvstrIngredient13, tvstrIngredient14, tvstrIngredient15, tvstrMeasure1, tvstrMeasure2, tvstrMeasure3, tvstrMeasure4, tvstrMeasure5, tvstrMeasure6,
            tvstrMeasure7, tvstrMeasure8, tvstrMeasure9, tvstrMeasure10, tvstrMeasure11, tvstrMeasure12, tvstrMeasure13, tvstrMeasure14, tvstrMeasure15, tvdateModified;
    public ImageView img_strDrinkThumb, img_shopping;
    public String status = "0";
    DbHelper helper;
    int itemid;
    ArrayList<DrinkAllDetailModal> array_drink;
    String idDrink, strDrink, strCategory, strGlass, strInstructions, strDrinkThumb, strIngredient1, strIngredient2, strIngredient3, strIngredient4,
            strIngredient5, strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10, strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
            strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5, strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10, strMeasure11, strMeasure12,
            strMeasure13, strMeasure14, strMeasure15, dateModified, strAlcoholic;
    ArrayList<ShoppingModal> arrayshopping = new ArrayList<>();
    private String statuskey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybarshelfalldetails);
        helper = new DbHelper(this);
        Intent intent = getIntent();
        itemid = intent.getIntExtra("drinkcode", 0);



        array_drink = helper.getSingleRecordsdrink(itemid);
        DrinkAllDetailModal dm = new DrinkAllDetailModal();
        tvstrDrink = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrDrink);
        tvstrCategory = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrCategory);
        // tvstrAlcoholic = (TextView)findViewById(R.id.txt_strAlcoholic);
        tvstrGlass = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrGlass);
        tvstrInstructions = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrInstructions);
        img_strDrinkThumb = (ImageView) findViewById(R.id.img_mybarshelfalldetailsimage);
        img_shopping = (ImageView) findViewById(R.id.img_mybarshelf_shopping);
        tvstrIngredient1 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient1);
        tvstrIngredient2 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient2);
        tvstrIngredient3 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient3);
        tvstrIngredient4 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient4);
        tvstrIngredient5 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient5);
        tvstrIngredient6 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient6);
        tvstrIngredient7 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient7);
        tvstrIngredient8 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient8);
        tvstrIngredient9 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient9);
        tvstrIngredient10 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient10);
        tvstrIngredient11 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient11);
        tvstrIngredient12 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient12);
        tvstrIngredient13 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient13);
        tvstrIngredient14 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient14);
        tvstrIngredient15 = (TextView) findViewById(R.id.txt_mybarshelfalldetailsstrIngredient15);



        Bundle bundle = intent.getExtras();
        if (bundle.containsKey("status")) {
            statuskey = bundle.getString("status");
        }
        //code = Integer.parseInt(id);


        if (statuskey.equalsIgnoreCase("0")) {
            img_shopping.setVisibility(View.GONE);

        }

        for (int i = 0; i < array_drink.size(); i++) {


            strDrink = array_drink.get(i).getStrDrink().toString();
            strCategory = array_drink.get(i).getStrCategory().toString();
            strGlass = array_drink.get(i).getStrGlass().toString();
            strInstructions = array_drink.get(i).getStrInstructions();
            strDrinkThumb = array_drink.get(i).getByteImage().toString();
            strIngredient1 = array_drink.get(i).getStrIngredient1().toString();
            strIngredient2 = array_drink.get(i).getStrIngredient2().toString();
            strIngredient3 = array_drink.get(i).getStrIngredient3().toString();
            strIngredient4 = array_drink.get(i).getStrIngredient4().toString();
            strIngredient5 = array_drink.get(i).getStrIngredient5().toString();
            strIngredient6 = array_drink.get(i).getStrIngredient6().toString();
            strIngredient7 = array_drink.get(i).getStrIngredient7().toString();
            strIngredient8 = array_drink.get(i).getStrIngredient8().toString();
            strIngredient9 = array_drink.get(i).getStrIngredient9().toString();
            strIngredient10 = array_drink.get(i).getStrIngredient10().toString();
            strIngredient11 = array_drink.get(i).getStrIngredient11().toString();
            strIngredient12 = array_drink.get(i).getStrIngredient12().toString();
            strIngredient13 = array_drink.get(i).getStrIngredient13().toString();
            strIngredient14 = array_drink.get(i).getStrIngredient14().toString();
            strIngredient15 = array_drink.get(i).getStrIngredient15().toString();

            tvstrDrink.setText(strDrink);
            tvstrCategory.setText("Category :" + strCategory);
            //  tvstrAlcoholic.setText("Alcoholic: " +strAlcoholic);
            tvstrGlass.setText("Glass: " + strGlass);
            tvstrInstructions.setText(strInstructions);
            tvstrIngredient1.setText("1) " + strIngredient1);
            tvstrIngredient2.setText("2) " + strIngredient2);
            tvstrIngredient3.setText("3) " + strIngredient3);
            tvstrIngredient4.setText("4) " + strIngredient4);
            tvstrIngredient5.setText("5) " + strIngredient5);
            tvstrIngredient6.setText("6) " + strIngredient6);
            tvstrIngredient7.setText("7) " + strIngredient7);
            tvstrIngredient8.setText("8) " + strIngredient8);
            tvstrIngredient9.setText("9) " + strIngredient9);
            tvstrIngredient10.setText("10) " + strIngredient10);
            tvstrIngredient11.setText("11) " + strIngredient11);
            tvstrIngredient12.setText("12) " + strIngredient12);
            tvstrIngredient13.setText("13) " + strIngredient13);
            tvstrIngredient14.setText("14) " + strIngredient14);
            tvstrIngredient15.setText("15): " + strIngredient15);

            Glide.with(MyBarShelfAllDetailsAct.this).load(strDrinkThumb).placeholder(R.drawable.loading).into(img_strDrinkThumb);


        }
        img_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*helper.InsertShoppingRecord(new DrinkAllDetailModal(strDrink, strCategory, strGlass, strInstructions, strDrinkThumb, strIngredient1, strIngredient2,
                        strIngredient3, strIngredient4,strIngredient5, strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
                        strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15));*/


                DbHelper dbHelper = new DbHelper(getApplicationContext());
                dbHelper.updatedDetailDrinkStatus(itemid);


            }
        });


    }
}
