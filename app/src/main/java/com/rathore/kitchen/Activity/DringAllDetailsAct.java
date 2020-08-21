package com.rathore.kitchen.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rathore.kitchen.Model.DrinkAllDetailModal;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;
import com.bumptech.glide.Glide;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/5/2017.
 */
public class DringAllDetailsAct extends AppCompatActivity {
    public TextView tvidDrink, tvstrDrink, tvstrCategory, tvstrAlcoholic, tvstrGlass, tvstrInstructions, tvstrIngredient1, tvstrIngredient2,
            tvstrIngredient3,tvstrIngredient4,tvstrIngredient5,tvstrIngredient6,tvstrIngredient7,tvstrIngredient8,tvstrIngredient9,tvstrIngredient10,tvstrIngredient11,
            tvstrIngredient12,tvstrIngredient13,tvstrIngredient14,tvstrIngredient15,tvstrMeasure1,tvstrMeasure2,tvstrMeasure3,tvstrMeasure4,tvstrMeasure5,tvstrMeasure6,
            tvstrMeasure7,tvstrMeasure8,tvstrMeasure9,tvstrMeasure10,tvstrMeasure11,tvstrMeasure12, tvstrMeasure13,tvstrMeasure14,tvstrMeasure15,tvdateModified;
    public ImageView img_strDrinkThumb;
   public int code;
   public byte[] byteImage;
    DbHelper helper;
    public Button btn_btn_savedrink;
    ArrayList<DrinkAllDetailModal> array_drink;
    String idDrink,strDrink,strCategory,strGlass,strInstructions,strDrinkThumb,strIngredient1,strIngredient2,strIngredient3,strIngredient4,
            strIngredient5,strIngredient6,strIngredient7,strIngredient8,strIngredient9,strIngredient10,strIngredient11,strIngredient12,strIngredient13,strIngredient14,strIngredient15,
            strMeasure1,strMeasure2,strMeasure3,strMeasure4,strMeasure5,strMeasure6,strMeasure7,strMeasure8,strMeasure9,strMeasure10,strMeasure11,strMeasure12,
            strMeasure13,strMeasure14,strMeasure15,dateModified,strAlcoholic;
    public Bitmap bm;
    ByteArrayOutputStream baos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dringalldetails);
      //  tvidDrink = (TextView)findViewById(R.id.txt_i);
        helper = new DbHelper(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("drinkcode");
        code = Integer.parseInt(id);
        getAllDrinkRecipsDetals(code);

        tvstrDrink = (TextView)findViewById(R.id.txt_strDrink);
        tvstrCategory = (TextView)findViewById(R.id.txt_strCategory);
        tvstrAlcoholic = (TextView)findViewById(R.id.txt_strAlcoholic);
      //  tvstrGlass = (TextView)findViewById(R.id.txt_strGlass);
        tvstrInstructions = (TextView)findViewById(R.id.txt_strInstructions);
        img_strDrinkThumb = (ImageView)findViewById(R.id.img_bartanderimage);
        tvstrIngredient1 = (TextView)findViewById(R.id.txt_strIngredient1);
        tvstrIngredient2 = (TextView)findViewById(R.id.txt_strIngredient2);
        tvstrIngredient3 = (TextView)findViewById(R.id.txt_strIngredient3);
        tvstrIngredient4 = (TextView)findViewById(R.id.txt_strIngredient4);
        tvstrIngredient5 = (TextView)findViewById(R.id.txt_strIngredient5);
        tvstrIngredient6 = (TextView)findViewById(R.id.txt_strIngredient6);
        tvstrIngredient7 = (TextView)findViewById(R.id.txt_strIngredient7);
        tvstrIngredient8 = (TextView)findViewById(R.id.txt_strIngredient8);
        tvstrIngredient9 = (TextView)findViewById(R.id.txt_strIngredient9);
        tvstrIngredient10 = (TextView)findViewById(R.id.txt_strIngredient10);
        tvstrIngredient10 = (TextView)findViewById(R.id.txt_strIngredient10);
        tvstrIngredient10 = (TextView)findViewById(R.id.txt_strIngredient10);
        tvstrIngredient10 = (TextView)findViewById(R.id.txt_strIngredient10);
        tvstrIngredient10 = (TextView)findViewById(R.id.txt_strIngredient10);
        tvstrIngredient11 = (TextView)findViewById(R.id.txt_strIngredient11);
        tvstrIngredient12 = (TextView)findViewById(R.id.txt_strIngredient12);
        tvstrIngredient13 = (TextView)findViewById(R.id.txt_strIngredient13);
        tvstrIngredient14 = (TextView)findViewById(R.id.txt_strIngredient14);
        tvstrIngredient15 = (TextView)findViewById(R.id.txt_strIngredient15);
       /* tvstrMeasure1 = (TextView)findViewById(R.id.txt_strMeasure1);
        tvstrMeasure2 = (TextView)findViewById(R.id.txt_strMeasure2);
        tvstrMeasure3 = (TextView)findViewById(R.id.txt_strMeasure3);
        tvstrMeasure4 = (TextView)findViewById(R.id.txt_strMeasure4);
        tvstrMeasure5 = (TextView)findViewById(R.id.txt_strMeasure5);
        tvstrMeasure6 = (TextView)findViewById(R.id.txt_strMeasure6);
        tvstrMeasure7 = (TextView)findViewById(R.id.txt_strMeasure7);
        tvstrMeasure8 = (TextView)findViewById(R.id.txt_strMeasure8);
        tvstrMeasure9 = (TextView)findViewById(R.id.txt_strMeasure9);
        tvstrMeasure10 = (TextView)findViewById(R.id.txt_strMeasure10);
        tvstrMeasure11 = (TextView)findViewById(R.id.txt_strMeasure11);
        tvstrMeasure12 = (TextView)findViewById(R.id.txt_strMeasure12);
        tvstrMeasure13 = (TextView)findViewById(R.id.txt_strMeasure13);
        tvstrMeasure14 = (TextView)findViewById(R.id.txt_strMeasure14);
        tvstrMeasure15 = (TextView)findViewById(R.id.txt_strMeasure15);
        tvdateModified = (TextView)findViewById(R.id.txt_stdateModified);*/
        btn_btn_savedrink = (Button) findViewById(R.id.btn_savedrink);
        btn_btn_savedrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int drinkrecord =  helper.InsertDetailsDrink(new DrinkAllDetailModal(idDrink,strDrink,strCategory,strGlass,strInstructions,strIngredient1,strIngredient2,strIngredient3,strIngredient4,
                        strIngredient5,strIngredient6,strIngredient7,strIngredient8,strIngredient9,strIngredient10,strIngredient11,strIngredient12,strIngredient13,strIngredient14,strIngredient15,
                        strMeasure1,strMeasure2,strMeasure3,strMeasure4,strMeasure5,strMeasure6,strMeasure7,strMeasure8,strMeasure9,strMeasure10,strMeasure11,strMeasure12,
                        strMeasure13,strMeasure14,strMeasure15,dateModified,strDrinkThumb,strAlcoholic,"0"));
                String result = String.valueOf(drinkrecord);
                if(drinkrecord != -1)
                {
                    Toast.makeText(DringAllDetailsAct.this,"Save" , Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else{
                    Toast.makeText(DringAllDetailsAct.this,"Something Wrong Please Try Again" , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void getAllDrinkRecipsDetals(int d_code) {
        Ion.with(DringAllDetailsAct.this)
                .load("http://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + d_code)
                .setMultipartParameter("url", "")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    public String rank;
                    public String url;

                    @Override
                    public void onCompleted(Exception e, String result) {
                        Log.d("msg", "new res " + result);
                        try {

                            JSONObject jsonObjectMain = new JSONObject(result);
                            JSONArray jsonObjectRecipes = jsonObjectMain.getJSONArray("drinks");

                            for (int i = 0; i < jsonObjectRecipes.length(); i++) {
                                JSONObject obj = jsonObjectRecipes.getJSONObject(i);
                                    idDrink = obj.getString("idDrink");
                                strDrink = obj.getString("strDrink");
                                strCategory = obj.getString("strCategory");
                                strAlcoholic = obj.getString("strAlcoholic");
                                strGlass = obj.getString("strGlass");
                                strInstructions = obj.getString("strInstructions");
                                strDrinkThumb = obj.getString("strDrinkThumb");
                                strIngredient1 = obj.getString("strIngredient1");
                                strIngredient2 = obj.getString("strIngredient2");
                                strIngredient3 = obj.getString("strIngredient3");
                                strIngredient4 = obj.getString("strIngredient4");
                                strIngredient5 = obj.getString("strIngredient5");
                                strIngredient6 = obj.getString("strIngredient6");
                                strIngredient7 = obj.getString("strIngredient7");
                                strIngredient8 = obj.getString("strIngredient8");
                                strIngredient9 = obj.getString("strIngredient9");
                                strIngredient10 = obj.getString("strIngredient10");
                                strIngredient11 = obj.getString("strIngredient11");
                                strIngredient12 = obj.getString("strIngredient12");
                                strIngredient13 = obj.getString("strIngredient13");
                                strIngredient14 = obj.getString("strIngredient14");
                                strIngredient15 = obj.getString("strIngredient15");
                                strMeasure1 = obj.getString("strMeasure1");
                                 strMeasure2 = obj.getString("strMeasure2");
                                 strMeasure3 = obj.getString("strMeasure3");
                                 strMeasure4 = obj.getString("strMeasure4");
                                 strMeasure5 = obj.getString("strMeasure5");
                                 strMeasure6 = obj.getString("strMeasure6");
                                 strMeasure7 = obj.getString("strMeasure7");
                                 strMeasure8 = obj.getString("strMeasure8");
                                 strMeasure9 = obj.getString("strMeasure9");
                                 strMeasure10 = obj.getString("strMeasure10");
                                 strMeasure11 = obj.getString("strMeasure11");
                                 strMeasure12 = obj.getString("strMeasure12");
                                 strMeasure13 = obj.getString("strMeasure13");
                                 strMeasure14 = obj.getString("strMeasure14");
                                 strMeasure15 = obj.getString("strMeasure15");
                                 dateModified = obj.getString("dateModified");

                                tvstrDrink.setText(strDrink);
                                tvstrCategory.setText(strCategory);
                                tvstrAlcoholic.setText("Alcoholic: " +strAlcoholic);
                               // tvstrGlass.setText("Glass :"+strGlass);
                                tvstrInstructions.setText(strInstructions);
                                tvstrIngredient1.setText("1) " +strIngredient1);
                                tvstrIngredient2.setText("2) " +strIngredient2);
                                tvstrIngredient3.setText("3) " +strIngredient3);
                                tvstrIngredient4.setText("4) " +strIngredient4);
                                tvstrIngredient5.setText("5) " +strIngredient5);
                                tvstrIngredient6.setText("6) " +strIngredient6);
                                tvstrIngredient7.setText("7) " +strIngredient7);
                                tvstrIngredient8.setText("8) " +strIngredient8);
                                tvstrIngredient9.setText("9) " +strIngredient9);
                                tvstrIngredient10.setText("10) " +strIngredient10);
                                tvstrIngredient11.setText("11) " +strIngredient11);
                                tvstrIngredient12.setText("12) " +strIngredient12);
                                tvstrIngredient13.setText("13) " +strIngredient13);
                                tvstrIngredient14.setText("14) " +strIngredient14);
                                tvstrIngredient15.setText("15): " +strIngredient15);
                               /* tvstrMeasure1.setText("1) " +strMeasure1);
                                tvstrMeasure2.setText("2) " +strMeasure2);
                                tvstrMeasure3.setText("3) " +strMeasure3);
                                tvstrMeasure4.setText("4) " +strMeasure4);
                                tvstrMeasure5.setText("5) " +strMeasure5);
                                tvstrMeasure6.setText("6) " +strMeasure6);
                                tvstrMeasure7.setText("7) " +strMeasure7);
                                tvstrMeasure8.setText("8) " +strMeasure8);
                                tvstrMeasure9.setText("9) " +strMeasure9);
                                tvstrMeasure10.setText("10) " +strMeasure10);
                                tvstrMeasure11.setText("11) " +strMeasure11);
                                tvstrMeasure12.setText("12) " +strMeasure12);
                                tvstrMeasure13.setText("13) " +strMeasure13);
                                tvstrMeasure14.setText("14) " +strMeasure14);
                                tvstrMeasure15.setText("15) " +strMeasure15);*/
                               // tvdateModified.setText(dateModified);
                                Glide.with(DringAllDetailsAct.this).load(strDrinkThumb).placeholder(R.drawable.loading).into(img_strDrinkThumb);

                              /*  bm = BitmapFactory.decodeFile(strDrinkThumb);
                                baos = new ByteArrayOutputStream();
                                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                                byteImage = baos.toByteArray();*/
                               /* helper.InsertDetailsDrink(new DrinkAllDetailModal(idDrink,strDrink,strCategory,strGlass,strInstructions,strIngredient1,strIngredient2,strIngredient3,strIngredient4,
                                        strIngredient5,strIngredient6,strIngredient7,strIngredient8,strIngredient9,strIngredient10,strIngredient11,strIngredient12,strIngredient13,strIngredient14,strIngredient15,
                                        strMeasure1,strMeasure2,strMeasure3,strMeasure4,strMeasure5,strMeasure6,strMeasure7,strMeasure8,strMeasure9,strMeasure10,strMeasure11,strMeasure12,
                                        strMeasure13,strMeasure14,strMeasure15,dateModified,byteImage,strAlcoholic));*/

                            }
                           /* adapter = new BarTanderAdapter(BarTanderAct.this, R.layout.bartander_item_field, arraybartander);
                            mListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();*/

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }


                    }
                });
    }

}
