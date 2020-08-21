package com.rathore.kitchen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rathore.kitchen.Activity.MyBarShelfAllDetailsAct;
import com.rathore.kitchen.Adapter.MyBarShelfAdapter;
import com.rathore.kitchen.Model.BarTanderModal;
import com.rathore.kitchen.Model.DrinkAllDetailModal;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/6/2017.
 */
public class MyBarShelfFragment extends Fragment {
   // public static ListView mListView;
    public static GridView mListView;
    ArrayList<BarTanderModal> arraybartander = new ArrayList<BarTanderModal>();
    ArrayList<DrinkAllDetailModal> arraygetdatabase = new ArrayList<>();
    BarTanderModal bartandermodal;
    public static MyBarShelfAdapter adapter;
    public int d_code;
    DbHelper helper;
    static int i =0;

    public MyBarShelfFragment() {

    }
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        helper = new DbHelper(activity);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("oncreate","called");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mybarshelf, container, false);
        helper = new DbHelper(getActivity());
      //  mListView = (ListView) view.findViewById(R.id.lv_bartander);
        mListView = (GridView) view.findViewById(R.id.lv_bartander);

        Log.i("oncreateview","called");
        arraygetdatabase = new ArrayList<>();
        arraygetdatabase = helper.getAllRcordofDrink();
        adapter = new MyBarShelfAdapter(getActivity(), R.layout.mybarshelf_item_field, arraygetdatabase, mListView);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idDrinks = arraygetdatabase.get(position).getId();
                //String drink_code = arraygetdatabase.get(drinkid).getIdDrink().toString();
                //  d_code = Integer.parseInt(drink_code);
                Intent DrinkDetailIntent = new Intent(getActivity(), MyBarShelfAllDetailsAct.class);
                DrinkDetailIntent.putExtra("drinkcode", idDrinks);
                //        DrinkDetailIntent.putExtra("status","0");
                startActivity(DrinkDetailIntent);

                // getAllDrinkRecipsDetals(d_code);
                //Toast.makeText(BarTanderAct.this,drink_code,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void dataupdatetion(){
        if(arraygetdatabase!=null&&helper!=null){
        arraygetdatabase = helper.getAllRcordofDrink();
        adapter = new MyBarShelfAdapter(getActivity(), R.layout.mybarshelf_item_field, arraygetdatabase, mListView);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
            Log.i("dataupdation","called");
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                int idDrinks = arraygetdatabase.get(position).getId();
//                //String drink_code = arraygetdatabase.get(drinkid).getIdDrink().toString();
//                //  d_code = Integer.parseInt(drink_code);
//                Intent DrinkDetailIntent = new Intent(getActivity(), MyBarShelfAllDetailsAct.class);
//                DrinkDetailIntent.putExtra("drinkcode", idDrinks);
//                //        DrinkDetailIntent.putExtra("status","0");
//                startActivity(DrinkDetailIntent);
//
//                // getAllDrinkRecipsDetals(d_code);
//                //Toast.makeText(BarTanderAct.this,drink_code,Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    else {
            Log.i("dataupdation","not called");
        }
    }

//    @Override
//    public void setMenuVisibility(final boolean visible) {
//        super.setMenuVisibility(visible);
//
//        if (visible) {
//            if(arraygetdatabase!=null){
//            if (arraygetdatabase.size() == 0) {
//                arraygetdatabase = helper.getAllRcordofDrink();
//                adapter = new MyBarShelfAdapter(getActivity(), R.layout.mybarshelf_item_field, arraygetdatabase, mListView);
//                mListView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//            }}
//        }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            Log.i("visible","called");
            i=i+1;
            if(i==0){
            dataupdatetion();

        }
            Log.i("value of i", String.valueOf(i));
        }
        else {
           i= i-1;
            Log.i("visible","not called");
            Log.i("value of i", String.valueOf(i));
        }
    }
}


//}
