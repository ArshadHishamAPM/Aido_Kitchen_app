package com.rathore.health.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

//import androidx.appcompat.app.AppCompatActivity;

//import com.pierfrancescosoffritti.aytplayersample.Adapter.PurposeAdapter;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.model.PurposeModel;

import com.rathore.health.Adapter.PurposeAdapter;
import com.rathore.health.R;
import com.rathore.health.model.PurposeModel;
import com.rathore.health.utilities.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDrugInformation extends AppCompatActivity
{

    private EditText etSearch;
    private String apiUrl = "https://api.fda.gov/drug/label.json?api_key=kv3pWBNCnm3MUSyr6zw6VzlcooVYkCGvzTPFo04H&search=ibuprofen";
    private Utils utils;
    private ArrayList<PurposeModel> arrayListPurpose;
    private ListView lvPurpose;
    private Button btnSearch;
    InputMethodManager inputManager;
    String value;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_information);

//        Intent intentbr = getIntent();
//        if(intentbr!=null){
//            if(intentbr.getAction().equals("com.example.archi.health.Activity.ActivityDrugInformation"));
//
//            String data = intentbr.getStringExtra("data");
//
//            if(data!=null){
//                Log.i("medicine search log",data);
//            if(data.equals("close it")){
//                this.finish();
//               // finishAndRemoveTask();
//                this.onBackPressed();
//            }
//        }}

        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        etSearch = (EditText) findViewById(R.id.act_drug_information_et_search);
        lvPurpose = (ListView) findViewById(R.id.act_drug_information_lv);
       // btnSearch = (Button) findViewById(R.id.act_drug_information_btn_search);
        setSupportActionBar(toolbar);
        arrayListPurpose=new ArrayList<>();
        inputManager =
                (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);




        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i("pressed","Enter pressed");
                    inputManager.hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    String name = etSearch.getText().toString();
                    if (name.length() <= 0) {
                        if(arrayListPurpose!=null){
                            arrayListPurpose.clear();
                            PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
                            lvPurpose.setAdapter(purposeAdapter);
                        }
                        Toast.makeText(getApplicationContext(),"Enter Something",Toast.LENGTH_SHORT).show();
                    } else {
                        new GetApiData(name.toString()).execute();
                    }
                    Log.i("typed String",name.toString());


                }

                return true;
            }
        });
        init();
    }

        private void init() {
        utils = new Utils(ActivityDrugInformation.this);
        // etSearch.addTextChangedListener(this);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityDrugInformation.this, R.drawable.ic_toolbar_back));
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Drug information");
        toolbar.setTitleTextColor(ContextCompat.getColor(ActivityDrugInformation.this, R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // btnSearch.setOnClickListener(this);
    }

        @Override
        public void onBackPressed() {
        super.onBackPressed();

    }

//    @Override
//    public void onClick(View v) {
//        String searchValue = etSearch.getText().toString();
//        Log.i("searchbutton",searchValue);
//        if (searchValue.length() <= 0) {
////            if(arrayListPurpose!=null){
////            arrayListPurpose.clear();
////            PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
////            lvPurpose.setAdapter(purposeAdapter);
//        //}
//Toast.makeText(getApplicationContext(),"Enter Something",Toast.LENGTH_SHORT).show();
//        } else {
//            new GetApiData(searchValue.toString()).execute();
//        }
//        Log.i("typed String",searchValue.toString());
//    }





//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
////        if (s.length() <= 0) {
////            arrayListPurpose.clear();
////            PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
////            lvPurpose.setAdapter(purposeAdapter);
////        } else {
////            new GetApiData(s.toString()).execute();
////        }
//        Log.i("typed String",s.toString());
//    }

        private class GetApiData extends AsyncTask<String, String, String> {
            String value;
            ProgressDialog pd;

            public GetApiData(String s) {
                this.value = String.valueOf(s);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd = new ProgressDialog(ActivityDrugInformation.this);
                pd.setMessage("please wait");
                pd.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String url = "https://api.fda.gov/drug/label.json?api_key=kv3pWBNCnm3MUSyr6zw6VzlcooVYkCGvzTPFo04H&search=" + value;
                Log.i("url",url);
                Log.d("jai", "url:" + url);
                String response = utils.MakeServiceCall(url);
                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("druginfo",s.toString());
                try {
                    JSONObject jsonObject = new JSONObject(s.toString());

                    JSONObject jsonObjectMain = jsonObject.getJSONObject("meta");
                    if (!jsonObjectMain.equals("")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObjectResult = jsonArray.getJSONObject(i);

                            if (jsonObjectResult.has("inactive_ingredient")) {
                                String strPurpose = null, strWarnning = null, strKeepAwayFromChild = null, strDoses = null;
                                arrayListPurpose = new ArrayList<>();
                                JSONArray jsonArryPurpose = jsonObjectResult.getJSONArray("purpose");
                                for (int purpose = 0; purpose < jsonArryPurpose.length(); purpose++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strPurpose = jsonArryPurpose.getString(purpose);
                                    purposeModel.setPurpose(strPurpose);
                                    purposeModel.setStrTitle(getString(R.string.purpose));
                                    arrayListPurpose.add(purposeModel);
                                }
                                JSONArray jsonArrayWarnning = jsonObjectResult.getJSONArray("warnings");
                                for (int warning = 0; warning < jsonArrayWarnning.length(); warning++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strWarnning = jsonArrayWarnning.getString(warning);
                                    purposeModel.setPurpose(strWarnning);
                                    purposeModel.setStrTitle(getString(R.string.warning));
                                    arrayListPurpose.add(purposeModel);
                                }
                                JSONArray jsonArryChildren = jsonObjectResult.getJSONArray("keep_out_of_reach_of_children");
                                for (int child = 0; child < jsonArryChildren.length(); child++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strKeepAwayFromChild = jsonArryChildren.getString(child);
                                    purposeModel.setPurpose(strKeepAwayFromChild);
                                    purposeModel.setStrTitle(getString(R.string.children));
                                    arrayListPurpose.add(purposeModel);
                                }
                                JSONArray jsonArrayDosage = jsonObjectResult.getJSONArray("dosage_and_administration");
                                for (int dosage = 0; dosage < jsonArrayDosage.length(); dosage++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strDoses = jsonArrayDosage.getString(dosage);
                                    purposeModel.setPurpose(strDoses);
                                    purposeModel.setStrTitle(getString(R.string.medicine_dosage));
                                    arrayListPurpose.add(purposeModel);
                                }
                                PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
                                lvPurpose.setAdapter(purposeAdapter);

                            } else if (jsonObjectResult.has("precautions")) {
                                //inactive gradiat
                                String strPurpose = null, strWarnning = null, strKeepAwayFromChild = null, strDoses = null;
                                arrayListPurpose = new ArrayList<>();


                                if (jsonObjectResult.has("geriatric_use")) {
                                    JSONArray jsonArrayPurpose = jsonObjectResult.getJSONArray("geriatric_use");
                                    for (int purpose1 = 0; purpose1 < jsonArrayPurpose.length(); purpose1++) {
                                        PurposeModel purposeModel = new PurposeModel();
                                        strPurpose = jsonArrayPurpose.getString(purpose1);
                                        purposeModel.setPurpose(strPurpose);
                                        purposeModel.setStrTitle(getString(R.string.purpose));
                                        arrayListPurpose.add(purposeModel);
                                    }
                                } else {
                                    JSONArray jsonArrayPurpose = jsonObjectResult.getJSONArray("indications_and_usage");
                                    for (int purpose1 = 0; purpose1 < jsonArrayPurpose.length(); purpose1++) {
                                        PurposeModel purposeModel = new PurposeModel();
                                        strPurpose = jsonArrayPurpose.getString(purpose1);
                                        purposeModel.setPurpose(strPurpose);
                                        purposeModel.setStrTitle(getString(R.string.indications));
                                        arrayListPurpose.add(purposeModel);
                                    }

                                }
                                JSONArray jsonArrayWarnning = jsonObjectResult.getJSONArray("warnings");
                                for (int intWar = 0; intWar < jsonArrayWarnning.length(); intWar++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strPurpose = jsonArrayWarnning.getString(intWar);
                                    purposeModel.setPurpose(strPurpose);
                                    purposeModel.setStrTitle(getString(R.string.warning));
                                    arrayListPurpose.add(purposeModel);
                                }


                                if (jsonObjectResult.has("overdosage")) {
                                    JSONArray jsonDoses = jsonObjectResult.getJSONArray("overdosage");
                                    for (int overDose = 0; overDose < jsonDoses.length(); overDose++) {
                                        PurposeModel purposeModel = new PurposeModel();
                                        strDoses = jsonDoses.getString(overDose);
                                        purposeModel.setPurpose(strDoses);
                                        purposeModel.setStrTitle(getString(R.string.overDoses));
                                        arrayListPurpose.add(purposeModel);
                                    }
                                } else {
                                    JSONArray jsonArrayPurpose = jsonObjectResult.getJSONArray("adverse_reactions");
                                    for (int purpose1 = 0; purpose1 < jsonArrayPurpose.length(); purpose1++) {
                                        PurposeModel purposeModel = new PurposeModel();
                                        strPurpose = jsonArrayPurpose.getString(purpose1);
                                        purposeModel.setPurpose(strPurpose);
                                        purposeModel.setStrTitle(getString(R.string.reactions));
                                        arrayListPurpose.add(purposeModel);
                                    }

                                }
                                JSONArray jsonArrayDescription = jsonObjectResult.getJSONArray("description");
                                for (int description = 0; description < jsonArrayDescription.length(); description++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    String strDescription = jsonArrayDescription.getString(description);
                                    purposeModel.setPurpose(strDescription);
                                    purposeModel.setStrTitle(getString(R.string.description));
                                    arrayListPurpose.add(purposeModel);
                                }
                                PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
                                lvPurpose.setAdapter(purposeAdapter);
                            } else if (jsonObjectResult.has("geriatric_use")) {
                                String strPurpose = null, strWarnning = null, strKeepAwayFromChild = null, strDoses = null;
                                arrayListPurpose = new ArrayList<>();

                                if (jsonObjectResult.has("instructions_for_use")) {
                                    JSONArray jsonArrayPurpose = jsonObjectResult.getJSONArray("instructions_for_use");
                                    for (int purpose1 = 0; purpose1 < jsonArrayPurpose.length(); purpose1++) {
                                        PurposeModel purposeModel = new PurposeModel();
                                        strPurpose = jsonArrayPurpose.getString(purpose1);
                                        purposeModel.setPurpose(strPurpose);
                                        purposeModel.setStrTitle(getString(R.string.use));
                                        arrayListPurpose.add(purposeModel);
                                    }

                                } else {
                                    JSONArray jsonArrayPurpose = jsonObjectResult.getJSONArray("description");
                                    for (int purpose1 = 0; purpose1 < jsonArrayPurpose.length(); purpose1++) {
                                        PurposeModel purposeModel = new PurposeModel();
                                        strPurpose = jsonArrayPurpose.getString(purpose1);
                                        purposeModel.setPurpose(strPurpose);
                                        purposeModel.setStrTitle(getString(R.string.description));
                                        arrayListPurpose.add(purposeModel);
                                    }

                                }


                                JSONArray jsonArrayWarnning = jsonObjectResult.getJSONArray("warnings_and_cautions");
                                for (int intWar = 0; intWar < jsonArrayWarnning.length(); intWar++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strPurpose = jsonArrayWarnning.getString(intWar);
                                    purposeModel.setPurpose(strPurpose);
                                    purposeModel.setStrTitle(getString(R.string.warning));
                                    arrayListPurpose.add(purposeModel);
                                }

                                JSONArray jsonDoses = jsonObjectResult.getJSONArray("indications_and_usage");
                                for (int overDose = 0; overDose < jsonDoses.length(); overDose++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strDoses = jsonDoses.getString(overDose);
                                    purposeModel.setPurpose(strDoses);
                                    purposeModel.setStrTitle(getString(R.string.indications));
                                    arrayListPurpose.add(purposeModel);
                                }
                                JSONArray jsonArrayDescription = jsonObjectResult.getJSONArray("overdosage");
                                for (int description = 0; description < jsonArrayDescription.length(); description++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    String strDescription = jsonDoses.getString(description);
                                    purposeModel.setPurpose(strDescription);
                                    purposeModel.setStrTitle(getString(R.string.overDoses));
                                    arrayListPurpose.add(purposeModel);
                                }
                                PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
                                lvPurpose.setAdapter(purposeAdapter);

                            } else if (jsonObjectResult.has("warnings_and_cautions_table")) {

                                String strPurpose = null, strWarnning = null, strKeepAwayFromChild = null, strDoses = null;
                                arrayListPurpose = new ArrayList<>();
                                JSONArray jsonArrayPurpose = jsonObjectResult.getJSONArray("indications_and_usage");
                                for (int purpose1 = 0; purpose1 < jsonArrayPurpose.length(); purpose1++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strPurpose = jsonArrayPurpose.getString(purpose1);
                                    purposeModel.setPurpose(strPurpose);
                                    purposeModel.setStrTitle(getString(R.string.use));
                                    arrayListPurpose.add(purposeModel);
                                }
                                JSONArray jsonArrayWarnning = jsonObjectResult.getJSONArray("warnings_and_cautions");
                                for (int intWar = 0; intWar < jsonArrayWarnning.length(); intWar++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strPurpose = jsonArrayPurpose.getString(intWar);
                                    purposeModel.setPurpose(strPurpose);
                                    purposeModel.setStrTitle(getString(R.string.warning));
                                    arrayListPurpose.add(purposeModel);
                                }

                                JSONArray jsonDoses = jsonObjectResult.getJSONArray("adverse_reactions");
                                for (int overDose = 0; overDose < jsonDoses.length(); overDose++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strDoses = jsonDoses.getString(overDose);
                                    purposeModel.setPurpose(strDoses);
                                    purposeModel.setStrTitle(getString(R.string.reactions));
                                    arrayListPurpose.add(purposeModel);
                                }
                                JSONArray jsonArrayDescription = jsonObjectResult.getJSONArray("overdosage");
                                for (int description = 0; description < jsonArrayDescription.length(); description++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    String strDescription = jsonDoses.getString(description);
                                    purposeModel.setPurpose(strDescription);
                                    purposeModel.setStrTitle(getString(R.string.overDoses));
                                    arrayListPurpose.add(purposeModel);
                                }
                                PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
                                lvPurpose.setAdapter(purposeAdapter);

                            } else if (jsonObjectResult.has("clinical_studies_table")) {
                                String strPurpose = null, strWarnning = null, strKeepAwayFromChild = null, strDoses = null;
                                arrayListPurpose = new ArrayList<>();
                                JSONArray jsonArrayPurpose = jsonObjectResult.getJSONArray("indications_and_usage");
                                for (int purpose1 = 0; purpose1 < jsonArrayPurpose.length(); purpose1++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strPurpose = jsonArrayPurpose.getString(purpose1);
                                    purposeModel.setPurpose(strPurpose);
                                    purposeModel.setStrTitle(getString(R.string.use));
                                    arrayListPurpose.add(purposeModel);
                                }

                                JSONArray jsonArrayWarnning = jsonObjectResult.getJSONArray("warnings_and_cautions");
                                for (int intWar = 0; intWar < jsonArrayWarnning.length(); intWar++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strPurpose = jsonArrayPurpose.getString(intWar);
                                    purposeModel.setPurpose(strPurpose);
                                    purposeModel.setStrTitle(getString(R.string.warning));
                                    arrayListPurpose.add(purposeModel);
                                }

                                JSONArray jsonDoses = jsonObjectResult.getJSONArray("adverse_reactions");
                                for (int overDose = 0; overDose < jsonDoses.length(); overDose++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    strDoses = jsonDoses.getString(overDose);
                                    purposeModel.setPurpose(strDoses);
                                    purposeModel.setStrTitle(getString(R.string.reactions));
                                    arrayListPurpose.add(purposeModel);
                                }
                                JSONArray jsonArrayDescription = jsonObjectResult.getJSONArray("overdosage");
                                for (int description = 0; description < jsonArrayDescription.length(); description++) {
                                    PurposeModel purposeModel = new PurposeModel();
                                    String strDescription = jsonDoses.getString(description);
                                    purposeModel.setPurpose(strDescription);
                                    purposeModel.setStrTitle(getString(R.string.overDoses));
                                    arrayListPurpose.add(purposeModel);
                                }
                                PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
                                lvPurpose.setAdapter(purposeAdapter);
                            } else {
                                arrayListPurpose.clear();
                                PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
                                lvPurpose.setAdapter(purposeAdapter);
                            }

                        }
                    } else {
                        Toast.makeText(ActivityDrugInformation.this, "no data find", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
//                if(arrayListPurpose!=null){
//                    arrayListPurpose.clear();
//                PurposeAdapter purposeAdapter = new PurposeAdapter(ActivityDrugInformation.this, arrayListPurpose);
//                lvPurpose.setAdapter(purposeAdapter);
//            }
                }
                pd.dismiss();
            }
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if(intent.getAction().equals("com.example.archi.health.Activity.ActivityDrugInformation"));
//
//        String data = intent.getStringExtra("data");
//
//        if(data!=null){
//            Log.i("medicine search log",data);
//            if(data.equals("close it")){
//                this.finish();
//                // finishAndRemoveTask();
//               // this.onBackPressed();
//            }
//    }}
    }
