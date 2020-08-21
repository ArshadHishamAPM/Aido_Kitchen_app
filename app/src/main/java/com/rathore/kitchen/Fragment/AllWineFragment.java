package com.rathore.kitchen.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rathore.kitchen.Activity.DatabaseHalper;
import com.rathore.kitchen.Adapter.VirtualAllWineAdapter;
import com.rathore.kitchen.Model.WishListModel;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.Util;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/10/2017.
 */
public class AllWineFragment extends Fragment {
    public ArrayList<WishListModel> mArrayListAllWine;
    public VirtualAllWineAdapter adapter;
    public ListView mListView;
    public ProgressDialog progressDoalog;
    DatabaseHalper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allwine, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_virtualallwine);
        helper = new DatabaseHalper(getActivity());
              /* Gson gson = new Gson();
                 Bundle bundle = new Bundle();
                String jsonCars = gson.toJson(mArrayListAllWine);
                bundle.putString("winelistdetail", gson.toJson(jsonCars));*/

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
           /*     Toast.makeText(getActivity(), "Add To Cart"  , Toast.LENGTH_SHORT).show();
                helper.AddToCart(mArrayListAllWine.get(position));*/
//
           // Toast.makeText(getActivity(), ""+(helper.getCartData()).size(), Toast.LENGTH_SHORT).show();
//                Gson gson = new Gson();
//                Bundle bundle = new Bundle();
//                bundle.putString("winelistdetail", gson.toJson(mAllWine));
//                frg.setArguments(bundle);






              /*  String ItemName = mEditTextItemName.getText().toString();
                String Price = mEditTextItemPrice.getText().toString();
                String Weight = mEditTextItemWeight.getText().toString();
                helper.AddItemToShop(new AddItemToShopUtility(Weight,imagePath));*/
            }
        });

        new getContactUsDetails().execute();


//        grtAllWine();

        return view;
    }



    public class getContactUsDetails extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading");
            pd.setCancelable(false);
            mArrayListAllWine = new ArrayList<>();
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String response = "";
//            response = Utils.getResponseofGet("http://services.wine.com/api/beta/service.svc/JSON/catalog?search=wine&size=1&offset=0&apikey=675912e6792f5bc58f7e179dbc5151bc");
//            HashMap<String, String> hashmap = new HashMap<>();
//            hashmap.put("search", "wine");
//            hashmap.put("size", "1");
//            hashmap.put("offset", "0");
//            hashmap.put("apikey", "675912e6792f5bc58f7e179dbc5151bc");
            response = Util.getResponseofGet("http://services.wine.com/api/beta/service.svc/JSON/catalog?search=wine&offset=0&apikey=675912e6792f5bc58f7e179dbc5151bc");
            Log.d("RESPONSE ", "@@ " + response);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
//            pd.dismiss();
            super.onPostExecute(s);
            pd.dismiss();
            Log.d("Response", "" + s);
            try {
//                JSONObject object = new JSONObject(s.toString());
//                if (object.getString("status").equalsIgnoreCase("true")) {
//
//                    JSONArray arry = object.getJSONArray("data");
//                    for (int i = 0; i < arry.length(); i++) {
//                        JSONObject obj = arry.getJSONObject(i);
//                        ContactUs contact = new ContactUs();
//                        contact.setContactus_id(obj.get("contactus_id").toString());
//
//                        contact.setName(obj.get("name").toString());
//                        tvName.setText(obj.get("name").toString());
////                        setData(obj.get("name").toString());
//                        contact.setAddress(obj.get("address").toString());
//                        tvAddess.setText(obj.get("address").toString());
////                        setData(obj.get("address").toString());
//                        contact.setPhone(obj.get("phone").toString());
//                        tvContact.setText(obj.get("phone").toString());
////                        setData(obj.get("phone").toString());
//                        contact.setEmail(obj.get("email").toString());
//                        tvEmail.setText(obj.get("email").toString());
////                        setData(obj.get("email").toString());
//
//                        contact.setFacebook(obj.get("facebook_account").toString());
//                        tvFb.setText(obj.get("facebook_account").toString());
//
//                        contact.setTwitter(obj.get("twitter_account").toString());
//                        tvTwitt.setText(obj.get("twitter_account").toString());
//
//                        contact.setInstagram(obj.get("instagram_account").toString());
//                        tvInsta.setText(obj.get("instagram_account").toString());

                JSONObject jsonObjectMain = new JSONObject(s.toString());
                JSONObject jsonObjectPRO = jsonObjectMain.getJSONObject("Products");
                JSONArray jsonArryList = jsonObjectPRO.getJSONArray("List");
                for (int i = 0; i < jsonArryList.length(); i++) {
                    JSONObject obj = jsonArryList.getJSONObject(i);
                    Log.d("PRODUCT", "obj " + obj);
                    Log.d("Id ", "Id " + obj.get("Id"));
                    Log.d("NAME ", "Name " + obj.get("Name"));
                    String drinkid = obj.get("Id").toString();
                    String strdrink = obj.get("Name").toString();
                    JSONArray jsonArryLabel = obj.getJSONArray("Labels");
                    String strdrinkthumb = jsonArryLabel.getJSONObject(0).get("Url").toString();
                    Log.d("NAME ", "Url " + strdrinkthumb);

                    mArrayListAllWine.add(new WishListModel(drinkid,strdrink, strdrinkthumb));
                }

                adapter = new VirtualAllWineAdapter(getActivity(), R.layout.virtualallwine_item_field, mArrayListAllWine);
                mListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

//                for (int i = 0; i < jsonObjectRecipes.length(); i++) {
//                    JSONObject obj = jsonObjectRecipes.getJSONObject(i);
//                    String strdrink = obj.getString("strDrink");
//                    String strdrinkthumb = obj.getString("strDrinkThumb");
//                    String drinkid = obj.getString("idDrink");
//                    mArrayListAllWine.add(new VirtualAllWineModal(strdrink, strdrinkthumb, drinkid));
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void grtAllWine() {
        mArrayListAllWine = new ArrayList<>();
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();

//        http://services.wine.com/api/beta/service.svc/JSON/catalog?search=wine&size=1&offset=0&apikey=675912e6792f5bc58f7e179dbc5151bc
        Ion.with(getActivity())
                .load("http://services.wine.com/api/beta/service.svc/JSON/catalog?search=wine&size=1&offset=0&apikey=675912e6792f5bc58f7e179dbc5151bc")
                .setMultipartParameter("url", "")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    public String rank;
                    public String url;

                    @Override
                    public void onCompleted(Exception e, String result) {
                        Log.d("@@@@@@", "@@@@@@@@@@@@@@@@@@@@@@");
                        Log.d("RESPONSE", "RESPO@@  " + result);
                        try {

                            JSONObject jsonObjectMain = new JSONObject(result);
                            JSONArray jsonObjectRecipes = jsonObjectMain.getJSONArray("drinks");
                            Log.e("array","" +jsonObjectRecipes.length());

                            for (int i = 0; i < jsonObjectRecipes.length(); i++) {
                                JSONObject obj = jsonObjectRecipes.getJSONObject(i);
                                String strdrink = obj.getString("strDrink");
                                String strdrinkthumb = obj.getString("strDrinkThumb");
                                String drinkid = obj.getString("idDrink");
                                /*Intent intent =new Intent(getActivity(),WishListFragment.class);
                                intent.putExtra("id",drinkid);
                                intent.putExtra("thumb",strdrinkthumb);
                                intent.putExtra("name",strdrink);
                                startActivity(intent);*/

                                mArrayListAllWine.add(new WishListModel(drinkid, strdrink, strdrinkthumb));
                            }

                            adapter = new VirtualAllWineAdapter(getActivity(), R.layout.virtualallwine_item_field, mArrayListAllWine);
                            mListView.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }


                    }
                });
        progressDoalog.dismiss();

    }

}

