package com.rathore.kitchen.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rathore.kitchen.Activity.DringAllDetailsAct;
import com.rathore.kitchen.Adapter.BarTanderAdapter;
import com.rathore.kitchen.Model.BarTanderModal;
import com.rathore.kitchen.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/6/2017.
 */
public class ManageBarShelfFragment extends Fragment {

//    ListView mListView;
    GridView mListView;
    ArrayList<BarTanderModal> arraybartander = new ArrayList<BarTanderModal>();
    BarTanderModal bartandermodal;
    BarTanderAdapter adapter;
    public int d_code;
    private RequestQueue requestQueue;
    public ManageBarShelfFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("oncreate","called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mybarshelf, container, false);
        Log.i("oncreateview","called");
        requestQueue = Volley.newRequestQueue(getContext());
        DrinkRecips();
       // mListView = (ListView)view.findViewById(R.id.lv_bartander);
        mListView = (GridView)view.findViewById(R.id.lv_bartander);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String idDrinks = String.valueOf(parent.getItemIdAtPosition(position));
                int drinkid = Integer.parseInt(idDrinks);
                String drink_code = arraybartander.get(drinkid).getDrinkid().toString();
                d_code = Integer.parseInt(drink_code);
                Intent DrinkDetailIntent = new Intent(getActivity(),DringAllDetailsAct.class);
                DrinkDetailIntent.putExtra("drinkcode",drink_code);
                startActivity(DrinkDetailIntent);

                // getAllDrinkRecipsDetals(d_code);
                //Toast.makeText(BarTanderAct.this,drink_code,Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void DrinkRecips()
    {
//        final ProgressDialog pd;
//        pd = new ProgressDialog(getActivity());
//        pd.setMessage("Loading");
//        pd.setCancelable(false);
//        pd.show();

//        Ion.with(getContext())
//                .load("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink")
//                .setMultipartParameter("url", "")
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    public String rank;
//                    public String url;
//
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        Log.d("msg", "new res " + result);
//                        pd.dismiss();
//
//                        try {
//                            JSONObject jsonObjectMain = new JSONObject(result);
//                            JSONArray jsonObjectRecipes = jsonObjectMain.getJSONArray("drinks");
//
//                            for (int i = 0; i < jsonObjectRecipes.length(); i++) {
//                                JSONObject obj = jsonObjectRecipes.getJSONObject(i);
//                                String strdrink = obj.getString("strDrink");
//                                String strdrinkthumb = obj.getString("strDrinkThumb");
//                                String drinkid = obj.getString("idDrink");
//                                arraybartander.add(new BarTanderModal(strdrink, strdrinkthumb, drinkid));
//                            }
//                            adapter = new BarTanderAdapter(getActivity(), R.layout.bartander_item_field, arraybartander);
//                            mListView.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
//
//                        } catch (Exception e1) {
//                            e1.printStackTrace();
//                        }
//
//
//                    }
//                });

        final String url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d("msg..................................", "new res " + response);
                //pd.dismiss();
                try {
                    JSONArray jsonArray = response.getJSONArray("drinks");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String strdrink = obj.getString("strDrink");
                        String strdrinkthumb = obj.getString("strDrinkThumb");
                        String drinkid = obj.getString("idDrink");
                        Log.d("...........drink................",  strdrink);
                        Log.d("...........drink.thumb...............",  strdrinkthumb);
                        Log.d("...........drink.id...............",  drinkid);
                        arraybartander.add(new BarTanderModal(strdrink, strdrinkthumb, drinkid));
                    }
                    adapter = new BarTanderAdapter(getContext(), R.layout.bartander_item_field, arraybartander);
                    mListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

    }
}