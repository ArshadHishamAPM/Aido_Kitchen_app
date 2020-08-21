package com.rathore.kitchen.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.rathore.kitchen.Model.AddWineBottleModal;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

public class WineBottelDetailDisplay extends AppCompatActivity {


    public EditText edtBottelVintage, edtwineproducer, edtcomplitwinename, edttype, edtsize,
            edtstartdate, edtenddate, edtprice, edtstored, edtpurchased, edtdate, edttestingnote, edttestedname;
    public ImageView imgbottel;
    public String BottelVintage,wineproducer, complitwinename,type, sizes,
            startdate, enddate, pricep, stored, purchased, dates,testingnote,testedname;;
    public DbHelper dbHelper;
    public ArrayList<AddWineBottleModal> arrayaddbottel;
    public AddWineBottleModal addWineBottleModal;
    public String id;
    public  byte[] img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_bottel_detail_display);
        dbHelper = new DbHelper(this);
//        arrayaddbottel = dbHelper.getVineBottelList();




        if (getIntent().getExtras() != null) {

//            String data = getIntent().getExtras().getString(Constant.Id);
//            Log.d("Rujul", ""+data);
            addWineBottleModal = new AddWineBottleModal();
            Gson gson = new Gson();
            String strObj = getIntent().getExtras().getString("obj");
            addWineBottleModal  = gson.fromJson(strObj,AddWineBottleModal.class);

            BottelVintage =addWineBottleModal.getStrivintage();
            wineproducer =addWineBottleModal.getStrwinery();
            complitwinename =addWineBottleModal.getStrwinename();
            type =addWineBottleModal.getStrtype();
            sizes =addWineBottleModal.getStrsize();
            startdate =addWineBottleModal.getStrstartdate();
            enddate =addWineBottleModal.getStrenddate();
            pricep =addWineBottleModal.getStrstorelocation();
            stored =addWineBottleModal.getStrstorewherepurchased();
            purchased =addWineBottleModal.getStrdate();
            dates =addWineBottleModal.getStrpaidbottle();
            testingnote =addWineBottleModal.getStrEntertestingnotes();
            testedname =addWineBottleModal.getStrEntertestin();
            img=addWineBottleModal.getByteImage();

            //Log.d("vintageDate",addWineBottleModal.getStrdate());


        }


        edtBottelVintage = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_bottle_vintage);
        edtwineproducer = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_wine_producer);
        edtcomplitwinename = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_wine_name);
        edttype = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_wine_type);
        edtsize = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_wine_size);
        edtstartdate = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_startdate);
        edtenddate = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_enddate);
        edtprice = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_bottleprice);
        edtstored = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_bottlestored);
        edtpurchased = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_bottlepurchased);
        edtdate = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_date);
        edttestingnote = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_testnote);
        edttestedname = (EditText) findViewById(R.id.activity_wine_bottel_detail_display_edit_other_testedyou);
        imgbottel = (ImageView) findViewById(R.id.activity_wine_bottel_detail_display_imgdata);


        edtBottelVintage.setText(BottelVintage);
        edtwineproducer.setText(wineproducer);
        edtcomplitwinename.setText(complitwinename);
        edttype.setText(type);
        edtsize.setText(sizes);
        edtstartdate.setText(startdate);
        edtenddate.setText(enddate);
        edtprice.setText(pricep);
        edtstored.setText(stored);
        edtpurchased.setText(purchased);
        edtdate.setText(dates);
        edttestingnote.setText(testingnote);
        edttestedname.setText(testedname);
        Glide.with(getApplicationContext()).load(addWineBottleModal.getByteImage()).into(imgbottel);






    }

}

