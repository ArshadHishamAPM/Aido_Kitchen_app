package com.rathore.kitchen.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.rathore.kitchen.Adapter.DietAdapter;
import com.rathore.kitchen.Adapter.LogBookAdapter;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DietItemsUtility;

import java.util.ArrayList;

public class DietwhizAct extends AppCompatActivity {

    public Toolbar mtToolbar;
    public Button mButtonCreate;
    // public EditText mEditTextLogbookTitle;
    public LogBookAdapter adapter;
    DatabaseHalper helper;
    public ListView mListView;
    ArrayList<String> array;
    public ArrayList<DietItemsUtility> arraydietdata;
    public ArrayList<DietItemsUtility> arraySinglecontact;
    DietAdapter dietadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietwhiz);
        arraydietdata = new ArrayList<>();
        helper = new DatabaseHalper(this);
        if (arraydietdata.size() == 0) {

            arraydietdata = helper.getAllRecords();
            Log.i("dietlist",arraydietdata.toString());
        }


        mButtonCreate = (Button) findViewById(R.id.btn_logbook);
        //  mEditTextLogbookTitle = (EditText)findViewById(R.id.edt_logbookTitle);
        mListView = (ListView) findViewById(R.id.listview_logbookitem);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // arraySinglecontact = new ArrayList<DietItemsUtility>();
                DietItemsUtility dietitem = new DietItemsUtility();
                int _id = arraydietdata.get(position).getId();

                //dietitem = helper.getRecords(_id);
                Intent mIntent = new Intent(DietwhizAct.this,DietSingleRecordAct.class);
                mIntent.putExtra("ItemId",_id);
                startActivity(mIntent);
            }
        });

        array = new ArrayList<String>();
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mDietIntent = new Intent(DietwhizAct.this, AddDietAct.class);
                startActivity(mDietIntent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        arraydietdata = new ArrayList<>();
        if (arraydietdata.size() == 0) {

            arraydietdata = helper.getAllRecords();

        }

        if (arraydietdata.size() != 0)
        {
            dietadapter = new DietAdapter(DietwhizAct.this, R.layout.dietwhizlogbook_item, arraydietdata);
            mListView.setAdapter(dietadapter);
            dietadapter.notifyDataSetChanged();
            // recreate();


        }
    }

}
