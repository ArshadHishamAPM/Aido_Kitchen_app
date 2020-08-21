package com.rathore.kitchen.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rathore.kitchen.*;
import com.rathore.kitchen.Adapter.DietAdapter;
import com.rathore.kitchen.Utils.DietItemsUtility;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

//import com.github.mikephil.charting.data.PieEntry;

/**
 * Created by Ravi Archi on 12/28/2016.
 */
public class DietSingleRecordAct extends AppCompatActivity {
    DietAdapter dietadapter;
    DatabaseHalper helper;
    public ArrayList<DietItemsUtility> arrayrecords;
    public ImageView mImageviewProduct,mImageviewDelete;
    public TextView tv_CurrentDate, tv_CurrentTime, tvMealType, tvTitle, tv_calories, tvingredients1, tvingredients2, tvingredients3, tvingredients4, tvingredients5, tvingredients6, tvingredients7, tvingredients8, tvingredients9, tvingredients10,
            carbohydrates, fat, saturatedfat, protein, fiber, sugars, sodium, note;
    private LinearLayout mainLayout;
    private PieChart pieChart;
    // we're going to display pie chart for smartphones martket shares
    private float[] yData = {5, 10, 15, 30, 40};
    private String[] xData = {"A", "B", "C", "D", "E"};
    public DietItemsUtility dietItemsUtility;
    public  int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietsinglerecords);
        helper = new DatabaseHalper(this);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("ItemId");
        Log.e("IDDDD","" +id);
        DietItemsUtility dietitem = new DietItemsUtility();
        dietitem = helper.getRecords(id);
        //  arrayrecords.add(dietitem);
        pieChart = (PieChart) findViewById(R.id.chart);
        mainLayout = (LinearLayout) findViewById(R.id.ly_chart);
        mImageviewProduct = (ImageView) findViewById(R.id.img_dietsinglerecord_image);
        //mImageviewDelete = (ImageView) findViewById(R.id.img_mydietitem_deletest);
        tv_CurrentDate = (TextView) findViewById(R.id.txt_singlerecord_date);
        tv_CurrentTime = (TextView) findViewById(R.id.txt_singlerecord_time);
        tvMealType = (TextView) findViewById(R.id.txt_singlerecord_mealtype);
        tvTitle = (TextView) findViewById(R.id.txt_dietsinglerecord_title);
        tv_calories = (TextView) findViewById(R.id.txt_singlerecord_calories);
        tvingredients1 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients1);
        tvingredients2 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients2);
        tvingredients3 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients3);
        tvingredients4 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients4);
        tvingredients5 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients5);
        tvingredients6 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients6);
        tvingredients7 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients7);
        tvingredients8 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients8);
        tvingredients9 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients9);
        tvingredients10 = (TextView) findViewById(R.id.txt_dietsinglerecord_ingredients10);
        carbohydrates = (TextView) findViewById(R.id.txt_dietsinglerecord_Carbohydrates);
        fat = (TextView) findViewById(R.id.txt_dietsinglerecord_totalfat);
        saturatedfat = (TextView) findViewById(R.id.txt_dietsinglerecord_saturatedfat);
        protein = (TextView) findViewById(R.id.txt_dietsinglerecord_protine);
        fiber = (TextView) findViewById(R.id.txt_dietsinglerecord_fiber);
        sugars = (TextView) findViewById(R.id.txt_dietsinglerecord_sugars);
        sodium = (TextView) findViewById(R.id.txt_dietsinglerecord_sodium);
        note = (TextView) findViewById(R.id.txt_dietsinglerecord_Note);

        tv_CurrentDate.setText(dietitem.getsDate());
        tv_CurrentTime.setText(dietitem.getmCurrentTime());
        tvMealType.setText(dietitem.getSelectMealType());
        tvTitle.setText(dietitem.getTitle());
        tv_calories.setText(dietitem.getCalories());
        tvingredients1.setText(dietitem.getIngredients1());
        tvingredients2.setText(dietitem.getIngredients2());
        tvingredients3.setText(dietitem.getIngredients3());
        tvingredients4.setText(dietitem.getIngredients4());
        tvingredients5.setText(dietitem.getIngredients5());
        tvingredients6.setText(dietitem.getIngredients6());
        tvingredients7.setText(dietitem.getIngredients7());
        tvingredients8.setText(dietitem.getIngredients8());
        tvingredients9.setText(dietitem.getIngredients9());
        tvingredients10.setText(dietitem.getIngredients10());
        carbohydrates.setText(dietitem.getCarbohydrates());
        fat.setText(dietitem.getFat());
        saturatedfat.setText(dietitem.getSaturatedfat());
        protein.setText(dietitem.getProtein());
        fiber.setText(dietitem.getFiber());
        sugars.setText(dietitem.getSugars());
        sodium.setText(dietitem.getSodium());
        note.setText(dietitem.getNote());
        dietitem.getBytearray();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(dietitem.getBytearray());
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        mImageviewProduct.setImageBitmap(theImage);
        openChart();


//        mImageviewDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//               helper.deleteContact(id);
//               /* onBackPressed();
//                arraydata.remove(position);
//                notifyDataSetChanged();*/
//                Intent intent=new Intent(DietSingleRecordAct.this,HomeAct.class);
//                startActivity(intent);
//
//
//            }
//        });
    }

    public void openChart() {

        //pieChart = new PieChart(this);
        // add pie chart to main layout
//        mainLayout.addView(pieChart);
        mainLayout.setBackgroundColor(Color.parseColor("#55656C"));

        // configure pie chart
        pieChart.setUsePercentValues(true);
       // mChart.setDescription("Smartphones Market Share");

        // enable hole and configure
        pieChart.setDrawHoleEnabled(true);
        //mChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(5);
        pieChart.setTransparentCircleRadius(15);

        // enable rotation of the chart by touch
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);

        // set a chart value selected listener
       /* mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(MainActivity.this,
                        xData[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });*/

        // add data
        addData();

        // customize legends
        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xData.length; i++) {
            xVals.add(xData[i]);
        }
        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1,"PieChat");
        dataSet.setSliceSpace(3);
       dataSet.setSelectionShift(5);
        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
       data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);
        pieChart.setData(data);
      // undo all highlights
        pieChart.highlightValues(null);
       // update pie chart
        pieChart.invalidate();
    }

}
