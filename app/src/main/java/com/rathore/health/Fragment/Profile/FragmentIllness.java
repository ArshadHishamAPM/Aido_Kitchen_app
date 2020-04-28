package com.rathore.health.Fragment.Profile;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.archi.health.Adapter.Profile.illnessDataAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.example.archi.health.model.IllnessData;

//import com.pierfrancescosoffritti.aytplayersample.Adapter.Profile.illnessDataAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.model.IllnessData;
import com.rathore.health.Adapter.Profile.illnessDataAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;
import com.rathore.health.model.IllnessData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by archi on 12/30/2016.
 */

public class FragmentIllness extends Fragment implements View.OnClickListener {
    private String strIllnessData;
    private DbHelper dbHelper;
    public static ArrayList<IllnessData> arrayIlnessData,arrayIllnessSearchData;
    private RecyclerView recyclerviewData;
    private EditText etillnessSearch;
    private Button btnAddNewIllnsess;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_illness, null);
        dbHelper = new DbHelper(getActivity());
        arrayIlnessData =  new ArrayList<>();
        recyclerviewData = (RecyclerView)rootView.findViewById(R.id.fargment_profile_illness_recycle);
        etillnessSearch = (EditText)rootView.findViewById(R.id.fragment_profile_illness_search);
        btnAddNewIllnsess = (Button)rootView.findViewById(R.id.fragment_profile_btn_add_illness);
        init();
        return rootView;
    }

    private void init() {
        illNessData();
        btnAddNewIllnsess.setOnClickListener(this);

        etillnessSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              arrayIllnessSearchData = new ArrayList<IllnessData>();
                 for (int i=0;i<arrayIlnessData.size();i++)
                 {
                     if (arrayIlnessData.get(i).getStrName().toLowerCase().contains(s.toString().toLowerCase()))
                     {
                         arrayIllnessSearchData.add(arrayIlnessData.get(i));
                     }
                 }
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
                recyclerviewData.setLayoutManager(mLayoutManager);
                recyclerviewData.setItemAnimator(new DefaultItemAnimator());
                recyclerviewData.setAdapter(new illnessDataAdapter(getActivity(),arrayIllnessSearchData));
            }
        });


        HashMap<String, String> array = new HashMap<>();
//        array = (HashMap<String, String>) dbHelper.getIlnessData();
//
//        Log.d("jai","array size :"+array.size());

        // array = dbHelper.getIlnessData();
    }

    private void illNessData() {
        try {
            InputStream is = getResources().getAssets().open("ilness_data.txt");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            strIllnessData = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArryMain = new JSONArray(strIllnessData.toString());
            for (int i = 0; i < jsonArryMain.length(); i++) {
                IllnessData illnessData = new IllnessData();
                JSONObject jsonObject = jsonArryMain.getJSONObject(i);
                String id = jsonObject.getString("id");
                illnessData.setStrId(id);
                String name = jsonObject.getString("name");
                illnessData.setStrName(name);
                String category = jsonObject.getString("category");
                illnessData.setStrCategory(category);
                String sexFilter = jsonObject.getString("sex_filter");
                illnessData.setSexFilter(sexFilter);
                String strImage = jsonObject.getString("image_url");
                String strParentRelation = jsonObject.getString("parent_relation");
                arrayIlnessData.add(illnessData);
            }

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
            recyclerviewData.setLayoutManager(mLayoutManager);
            recyclerviewData.setItemAnimator(new DefaultItemAnimator());
            recyclerviewData.setAdapter(new illnessDataAdapter(getActivity(),arrayIlnessData));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.fragment_profile_btn_add_illness:
                final Dialog dialog = new Dialog(getActivity());
//                dialog.setContentView(R.layout.dialog_profile_treatment_medication);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_profile_treatment_medication);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.shape);

                final EditText etIllness = (EditText)dialog.findViewById(R.id.dialog_profile_treatment_medication_et_new_medication);
                TextView ivCancel = (TextView)dialog.findViewById(R.id.dialog_profile_treatment_iv_cancel);
                TextView btnSave = (TextView)dialog.findViewById(R.id.dialog_profile_treatment_medication_btn_send_list);
                TextView tvAddInfo = (TextView)dialog.findViewById(R.id.dialog_profile_tretatment_tv_description);
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etIllness.equals(""))
                        {
                            Toast.makeText(getActivity(),"please enter the value", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(arrayIlnessData.size()>0){
                               String name = etIllness.getText().toString();
                                IllnessData illnessData = new IllnessData();
                                illnessData.setStrName(name);
                                arrayIlnessData.add(illnessData);
                                dialog.dismiss();
                            }
                         }
                    }
                });
                dialog.show();
                break;
        }

    }
}
