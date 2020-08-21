package com.rathore.kitchen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.rathore.kitchen.Adapter.GroceryListItemAdapter;
import com.rathore.kitchen.Adapter.GroceryShopAdapter;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.AddItemToShopUtility;
import com.rathore.kitchen.Utils.GroceryAddItemUtility;
import com.rathore.kitchen.Utils.StoreNameUtility;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 12/31/2016.
 */
public class GroceryShopAct extends AppCompatActivity {

    public Button mButtonItemtoBuy;
    public ListView mListView;
    public ArrayList<AddItemToShopUtility> arraydietdata;
    public ArrayList<AddItemToShopUtility> arraydietdataFilter;
    public ArrayList<GroceryAddItemUtility> arraygrocerylist;
    public ArrayList<GroceryAddItemUtility> array_grocery_Item;
    public String GroceryList;
    DatabaseHalper helper;
    GroceryListItemAdapter adapter;
    GroceryShopAdapter adapterg;
    private ArrayList<StoreNameUtility> arraystorename;
    public ArrayAdapter<StoreNameUtility> arrayAdapter;
    public MaterialBetterSpinner materialDesignSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mButtonItemtoBuy = (Button) findViewById(R.id.btn_AddItem);
        mListView = (ListView) findViewById(R.id.lv_ItemtoBuy);
        materialDesignSpinner = (MaterialBetterSpinner) findViewById(R.id.sp_GroceryListShop);
        arraydietdataFilter = new ArrayList<>();
        arraydietdata = new ArrayList<>();
        helper = new DatabaseHalper(this);
        arraystorename = helper.getAllStoreName();
        Log.e("Arraysss", "" + arraystorename.toString());

        arrayAdapter = new ArrayAdapter<StoreNameUtility>(GroceryShopAct.this, android.R.layout.simple_dropdown_item_1line, arraystorename);
        materialDesignSpinner.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();


        mButtonItemtoBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(GroceryShopAct.this, AddItemToBuyAct.class);
                startActivity(mIntent);

            }
        });

        // if (arraystorename == null) {


        //  }

        if (arraystorename == null) {
            Log.e("Arraysssssssss", "" + arraystorename.toString());

        }
        if (arraystorename != null) {

           /* mspSpinner = (Spinner) findViewById(R.id.sp_StoreName);
            ArrayAdapter<StoreNameUtility> arrayAdapter = new ArrayAdapter<StoreNameUtility>(GroceryAct.this,
                    android.R.layout.simple_dropdown_item_1line, arraystorename);
            mspSpinner.setAdapter(arrayAdapter);
            mspSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectMealType = parent.getItemAtPosition(position).toString();
                    Log.v("DepartmentItem", selectMealType);
                    arraygrocerylist = helper.getAllGroceryAddItem(selectMealType);
                    Toast.makeText(GroceryAct.this, selectMealType, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/


            /*Log.e("Arraysssssssss", "" + arraystorename.toString());
            arrayAdapter = new ArrayAdapter<StoreNameUtility>(GroceryShopAct.this, android.R.layout.simple_dropdown_item_1line, arraystorename);
            materialDesignSpinner.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();*/

           /* if (arraydietdataFilter == null) {
                materialDesignSpinner.setEnabled(false);
            } else {
                materialDesignSpinner.setEnabled(true);*/

            materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    GroceryList = parent.getItemAtPosition(position).toString();

                    if (GroceryList != null) {

                        if (arraydietdataFilter != null) {
                            arraydietdataFilter.clear();
                        }

                        if (arraydietdata != null) {
                            arraydietdata.clear();
                            arraydietdata = helper.getAllRecordsAddItem();
                            Log.i("arraysize", String.valueOf(arraydietdata.size()));
                           if(arraydietdata.size()>0){
                            Log.d("msg", "dd " + arraydietdata.get(position).getSelectitem());
                               Log.i("dataaaposition", String.valueOf(position));
                           Log.i("dataaa",arraydietdata.get(position).getSelectitem());

                           }


                            for (int i = 0; i < arraydietdata.size(); i++) {
                                String selectedd = arraydietdata.get(i).getSelectitem();
                                if (GroceryList.equalsIgnoreCase(selectedd)) {
                                    arraydietdataFilter.add(arraydietdata.get(i));
                                }
                            }


                            if (arraydietdataFilter.size() > 0) {

                                adapterg = new GroceryShopAdapter(GroceryShopAct.this, R.layout.shops_item_field, arraydietdataFilter);
                                mListView.setAdapter(adapterg);
                                adapterg.notifyDataSetChanged();

                                //arraydietdata = new ArrayList<AddItemToShopUtility>();
                                //arraydietdata = helper.getAllRecordsAddItem();


                                //Log.e("DataBaseData", "" + arraydietdata);
                                // adapterg = new GroceryShopAdapter(GroceryShopAct.this, R.layout.shops_item_field, arraydietdata);
                                // mListView.setAdapter(adapterg);
                                // adapterg.notifyDataSetChanged();

                                // }

                            } else {
                                arraydietdataFilter.clear();
                          //      adapterg.notifyDataSetChanged();


                                Toast.makeText(GroceryShopAct.this, "please add to all information", Toast.LENGTH_SHORT).show();
                            }
                        }


                    } else {
                        Toast.makeText(GroceryShopAct.this, "Please select grocery", Toast.LENGTH_SHORT).show();
                    }

                  /*  for (int i = 0; i < arraygrocerylist.size(); i++) {
                        if (arraygrocerylist.get(i).grocery.equalsIgnoreCase(GroceryList)) {
                            array_grocery_Item = new ArrayList<GroceryAddItemUtility>();
                            String G_ItemName = arraygrocerylist.get(i).getItemname().toString();
                            String G_ItemPrice = arraygrocerylist.get(i).getItemprice().toString();
                            String G_ItemWeight = arraygrocerylist.get(i).getItemwieght().toString();
                            byte[] G_ItemPic = arraygrocerylist.get(i).getItemproductimage();
                            array_grocery_Item.add(new GroceryAddItemUtility(GroceryList, G_ItemName, G_ItemPrice, G_ItemWeight, G_ItemPic));
                        }

                    }*/


                   /* if (GroceryList != null) {
                        if (array_grocery_Item != null) {
                            adapter = new GroceryListItemAdapter(GroceryShopAct.this, R.layout.activity_groceryact_grocerylist_item, array_grocery_Item);
                            mListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Log.v("DepartmentItem", GroceryList);
                            Toast.makeText(GroceryShopAct.this, GroceryList, Toast.LENGTH_SHORT).show();
                        } else {
                            //     Toast.makeText(GroceryShopAct.this,"No Items",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(GroceryShopAct.this, " select grocery", Toast.LENGTH_SHORT).show();
                    }
*/
                }
            });
        }
        //}
        else {
            Toast.makeText(GroceryShopAct.this, "No Data", Toast.LENGTH_SHORT).show();
        }

    }

   /* @Override
    public void onResume() {
        super.onResume();

        arraydietdata = new ArrayList<AddItemToShopUtility>();
        arraydietdata = helper.getAllRecordsAddItem();


        Log.e("DataBaseData", "" + arraydietdata);
        adapterg = new GroceryShopAdapter(GroceryShopAct.this, R.layout.shops_item_field, arraydietdata);
        mListView.setAdapter(adapterg);
        adapterg.notifyDataSetChanged();

    }*/


}
