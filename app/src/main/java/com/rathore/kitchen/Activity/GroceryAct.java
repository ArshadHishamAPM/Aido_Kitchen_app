package com.rathore.kitchen.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rathore.kitchen.Adapter.GroceryDataAdapter;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.GroceryAddItemUtility;
import com.rathore.kitchen.Utils.StoreNameUtility;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class GroceryAct extends AppCompatActivity {

    public Toolbar mToolbar;
    public Button mButtonAddStore, mButtonAddItem;
    public ArrayList<StoreNameUtility> arraystorename;
    public ArrayList<GroceryAddItemUtility> arraygrocerylist;
    DatabaseHalper helper;
    public ArrayList<GroceryAddItemUtility> array_grocery_Item;

    GroceryDataAdapter adapter;
    public String GroceryList;
    public ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        helper = new DatabaseHalper(this);
        arraystorename=helper.getAllStoreName();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        TextView textView = (TextView)myToolbar.findViewById(R.id.toolbar_title);
        // textView.setText("Grocery");


        Log.e("ALLDATA" ,"" +arraystorename);
        // Log.d("DATAID" ,"" +arraystorename.get(id))));

        /*mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        mButtonAddStore = (Button) findViewById(R.id.btn_AddStore);
        // mButtonAddItem = (Button) findViewById(R.id.btn_AddItemtobuy);
        mListView = (ListView) findViewById(R.id.lv_groceryitemlist);

        // MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)findViewById(R.id.spinner_StoreName);
        mButtonAddStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(GroceryAct.this);
//                requestWindowFeature(Window.FEATURE_NO_TITLE);
//                LayoutInflater inflater = getLayoutInflater();
//                final View alertLayout = inflater.inflate(R.layout.customedialog, null);
//                alert.setTitle("AddGrocery");
//                // this is set the view from XML inside AlertDialog
//                alert.setView(alertLayout);
//                // disallow cancel of AlertDialog on click of back button and outside touch
//                alert.setCancelable(false);
//                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        EditText mEditTextAddStoreName = (EditText) alertLayout.findViewById(R.id.edt_AddStoreName);
//                        String StoreName = mEditTextAddStoreName.getText().toString();
//                        arraystorename = new ArrayList<StoreNameUtility>();
//                        recreate();
//                        helper.InsetStoreName(new StoreNameUtility(StoreName));
//
//                    }
//                });
//                AlertDialog dialog = alert.create();
//                dialog.show();
//
//            }
//        });
                final Dialog dialog = new Dialog(GroceryAct.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.customedialog);
                Button cancelbutton = (Button) dialog.findViewById(R.id.cancel);
                Button savebutton = (Button) dialog.findViewById(R.id.save);
                final EditText mEditTextAddStoreName = (EditText)dialog.findViewById(R.id.edt_AddStoreName);
                cancelbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                savebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String StoreName = mEditTextAddStoreName.getText().toString();
                        arraystorename = new ArrayList<StoreNameUtility>();
                        recreate();
                        helper.InsetStoreName(new StoreNameUtility(StoreName));
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });




        if (arraystorename == null) {
            arraystorename = helper.getAllStoreName();
        }

        if (arraystorename != null) {


            adapter = new GroceryDataAdapter(getApplicationContext(),arraystorename);
            mListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();



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
            ArrayAdapter<StoreNameUtility> arrayAdapter = new ArrayAdapter<StoreNameUtility>(GroceryAct.this,
                    android.R.layout.simple_dropdown_item_1line, arraystorename);
            MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                    findViewById(R.id.sp_GroceryList);

            materialDesignSpinner.setAdapter(arrayAdapter);
            materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    GroceryList = parent.getItemAtPosition(position).toString();
                    if(GroceryList != null)
                    {
                        arraygrocerylist = helper.getAllGroceryAddItem(GroceryList);
                    }else{
                        Toast.makeText(GroceryAct.this,"Please select grocery",Toast.LENGTH_SHORT).show();
                    }

                    for(int i = 0; i < arraygrocerylist.size(); i++)
                    {
                        if(arraygrocerylist.get(i).grocery.equalsIgnoreCase(GroceryList))
                        {
                            array_grocery_Item = new ArrayList<GroceryAddItemUtility>();
                            String G_ItemName = arraygrocerylist.get(i).getItemname().toString();
                            String G_ItemPrice = arraygrocerylist.get(i).getItemprice().toString();
                            String G_ItemWeight = arraygrocerylist.get(i).getItemwieght().toString();
                            byte[] G_ItemPic = arraygrocerylist.get(i).getItemproductimage();
                            array_grocery_Item.add(new GroceryAddItemUtility(GroceryList,G_ItemName,G_ItemPrice,G_ItemWeight,G_ItemPic));
                        }

                    }
                    if(GroceryList != null)
                    {
                        if(array_grocery_Item != null)
                        {
                           /* adapter = new GroceryListItemAdapter(GroceryAct.this, R.layout.activity_groceryact_grocerylist_item,array_grocery_Item);
                            mListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();*/
                            Log.v("DepartmentItem", GroceryList);
                            Toast.makeText(GroceryAct.this, GroceryList, Toast.LENGTH_SHORT).show();
                        }else{
                            //        Toast.makeText(GroceryAct.this,"No Items",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(GroceryAct.this," select grocery",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(GroceryAct.this, "No Data", Toast.LENGTH_SHORT).show();
        }
       /* mButtonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntentStoreItem = new Intent(GroceryAct.this, GroceryShopAct.class);
                startActivity(mIntentStoreItem);
            }
        });*/


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {

            Intent mIntentStoreItem = new Intent(GroceryAct.this, GroceryShopAct.class);
            startActivity(mIntentStoreItem);
        }else if(id == R.id.foodtimer){
            Intent mIntenTime = new Intent(GroceryAct.this,TimeWizarAct.class);
            startActivity(mIntenTime);
        }

        return super.onOptionsItemSelected(item);
    }
}

