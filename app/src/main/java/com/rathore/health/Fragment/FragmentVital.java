package com.rathore.health.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.archi.health.Activity.ActivityAddVital;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDiaplayAlcohol;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayBloodPressure;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayBurnCalories;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayCaffeine;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayHeight;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayIntakeCalories;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayPulse;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayTemperature;
//import com.example.archi.health.Activity.VitalDisplay.ActivityVitalDisplayWeight;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddVital;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDiaplayAlcohol;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayBloodPressure;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayBurnCalories;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayCaffeine;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayHeight;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayIntakeCalories;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayPulse;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayTemperature;
//import com.pierfrancescosoffritti.aytplayersample.Activity.VitalDisplay.ActivityVitalDisplayWeight;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddVital;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDiaplayAlcohol;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayBloodPressure;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayBurnCalories;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayCaffeine;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayHeight;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayIntakeCalories;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayPulse;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayTemperature;
import com.rathore.health.Activity.VitalDisplay.ActivityVitalDisplayWeight;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 12/28/2016.
 */

public class FragmentVital extends Fragment implements View.OnClickListener {

    private Button btnAddVital;
    private LinearLayout llTemprature, llPulse, llBloodPressure, llHeight, llWeight, llintCalories, llbrnCalories, llAlcohol, llCoffies;
    List<HashMap<String, String>> alcoholArry = new ArrayList<>();
    List<HashMap<String, String>> BpressureArray = new ArrayList<>();
    List<HashMap<String, String>> BurnCaloryArry = new ArrayList<>();
    List<HashMap<String, String>> CaffeineArray = new ArrayList<>();
    List<HashMap<String, String>> HeightArry = new ArrayList<>();
    List<HashMap<String, String>> IntakeCaloryArry = new ArrayList<>();
    List<HashMap<String, String>> PulseArray = new ArrayList<>();
    List<HashMap<String, String>> TempArray = new ArrayList<>();
    List<HashMap<String, String>> WeightArray = new ArrayList<>();
    private DbHelper dbHelper;
    private TextView tempdate,temptime,temptemp,tempunit;
    private TextView pulsedate,pulsetime,pulsetemp,pulseunit;
    private TextView bpdate,bptime,bpsos,bpdis;
    private TextView hdate,htime,hheight,hunit;
    private TextView wdate,wtime,wweight,wunit;
    private TextView icdate,ictime,icintakec,icunit;
    private TextView bcdate,bctime,bcburnc,bcunit;
    private TextView alcoldate,alcotime,alcoalcohol,alcounit;
    private TextView coffinedate,coffinetime,coffinecoffine,coffineunit;
    private View tempView,pulseView,bpView,heightView,weightView,intakeView,burnView,alchoholView,coffineView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vital, null);
        btnAddVital = (Button) rootView.findViewById(R.id.frg_vital_btn_add);
        llTemprature = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llfortemp);
        llPulse = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llforpulse);
        llBloodPressure = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llforbloodpressure);
        llHeight = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llforheight);
        llWeight = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llforweight);
        llintCalories = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llforintake_calories);
        llbrnCalories = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llforaburn_calories);
        llAlcohol = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llforalcohol);
        llCoffies = (LinearLayout) rootView.findViewById(R.id.fragment_vital_llforcaffeinr);
        tempdate = (TextView) rootView.findViewById(R.id.fragment_vital_temp_txtdate);
        temptime = (TextView) rootView.findViewById(R.id.fragment_vital_temp_txttime);
        temptemp = (TextView) rootView.findViewById(R.id.fragment_vital_temp_txttemp);
        tempunit = (TextView) rootView.findViewById(R.id.fragment_vital_temp_txtunit);

        pulsedate = (TextView) rootView.findViewById(R.id.fragment_vital_pulse_txtdate);
        pulsetime = (TextView) rootView.findViewById(R.id.fragment_vital_pulse_txttime);
        pulsetemp = (TextView) rootView.findViewById(R.id.fragment_vital_pulse_txtpulse);
        pulseunit = (TextView) rootView.findViewById(R.id.fragment_vital_pulse_txtunit);

        bpdate = (TextView) rootView.findViewById(R.id.fragment_vital_bp_txtdate);
        bptime = (TextView) rootView.findViewById(R.id.fragment_vital_bp_txttime);
        bpsos = (TextView) rootView.findViewById(R.id.fragment_vital_bp_txtsos);
        bpdis = (TextView) rootView.findViewById(R.id.fragment_vital_bp_txtdis);


        hdate = (TextView) rootView.findViewById(R.id.fragment_vital_height_txtdate);
        htime = (TextView) rootView.findViewById(R.id.fragment_vital_height_txttime);
        hheight = (TextView) rootView.findViewById(R.id.fragment_vital_height_txtheight);
        hunit = (TextView) rootView.findViewById(R.id.fragment_vital_height_txtunit);


        wdate = (TextView) rootView.findViewById(R.id.fragment_vital_weight_txtdate);
        wtime = (TextView) rootView.findViewById(R.id.fragment_vital_weight_txttime);
        wweight = (TextView) rootView.findViewById(R.id.fragment_vital_weight_txtweight);
        wunit = (TextView) rootView.findViewById(R.id.fragment_vital_weight_txtunit);



        icdate= (TextView) rootView.findViewById(R.id.fragment_vital_intakec_txtdate);
        ictime = (TextView) rootView.findViewById(R.id.fragment_vital_intakec_txttime);
        icintakec = (TextView) rootView.findViewById(R.id.fragment_vital_intakec_txtintakecal);
        icunit = (TextView) rootView.findViewById(R.id.fragment_vital_intakec_txtunit);


        bcdate = (TextView) rootView.findViewById(R.id.fragment_vital_burnc_txtdate);
        bctime = (TextView) rootView.findViewById(R.id.fragment_vital_burnc_txttime);
        bcburnc = (TextView) rootView.findViewById(R.id.fragment_vital_burnc_txtburncal);
        bcunit = (TextView) rootView.findViewById(R.id.fragment_vital_burnc_txtunit);



        alcoldate = (TextView) rootView.findViewById(R.id.fragment_vital_alolcohol_txtdate);
        alcotime = (TextView) rootView.findViewById(R.id.fragment_vital_alolcohol_txttime);
        alcoalcohol = (TextView) rootView.findViewById(R.id.fragment_vital_alolcohol_txtalcohol);
        alcounit = (TextView) rootView.findViewById(R.id.fragment_vital_alolcohol_txtunit);



        coffinedate = (TextView) rootView.findViewById(R.id.fragment_vital_coffine_txtdate);
        coffinetime = (TextView) rootView.findViewById(R.id.fragment_vital_coffine_txttime);
        coffinecoffine = (TextView) rootView.findViewById(R.id.fragment_vital_coffine_txtcoffine);
        coffineunit = (TextView) rootView.findViewById(R.id.fragment_vital_coffine_txtunit);

        tempView = (View) rootView.findViewById(R.id.tempunderline);
        pulseView = (View) rootView.findViewById(R.id.pilseunderline);
        bpView = (View) rootView.findViewById(R.id.bpunderline);
        heightView = (View) rootView.findViewById(R.id.heightunderline);
        weightView = (View) rootView.findViewById(R.id.weightunderline);
        intakeView = (View) rootView.findViewById(R.id.intakeunderline);
        burnView = (View) rootView.findViewById(R.id.burnunderline);
        alchoholView = (View) rootView.findViewById(R.id.alchoholunderline);
        coffineView = (View) rootView.findViewById(R.id.coffineunderline);





        dbHelper = new DbHelper(getContext());
        init();
        return rootView;
    }

    private void init() {
        btnAddVital.setOnClickListener(this);

        llAlcohol.setOnClickListener(this);
        llBloodPressure.setOnClickListener(this);
        llbrnCalories.setOnClickListener(this);
        llCoffies.setOnClickListener(this);
        llHeight.setOnClickListener(this);
        llintCalories.setOnClickListener(this);
        llPulse.setOnClickListener(this);
        llTemprature.setOnClickListener(this);
        llWeight.setOnClickListener(this);


        List<HashMap<String, String>> alcoholArry =dbHelper.getVAlcohol();
        List<HashMap<String, String>> BpressureArray =dbHelper.getVBloodPressure();
        List<HashMap<String, String>> BurnCaloryArry =dbHelper.getVBurnCalories();
        List<HashMap<String, String>> CaffeineArray =dbHelper.getVCoffine();
        List<HashMap<String, String>> HeightArry =dbHelper.getVHeight();
        List<HashMap<String, String>> IntakeCaloryArry =dbHelper.getVIntakeCalories();
        List<HashMap<String, String>> PulseArray =dbHelper.getVPulse();
        List<HashMap<String, String>> TempArray =dbHelper.getVTemperature();
        List<HashMap<String, String>> WeightArray =dbHelper.getVWeight();




        if (alcoholArry.size() > 0)
        {
            llAlcohol.setVisibility(View.VISIBLE);
            HashMap<String, String> map = alcoholArry.get(0);
            String adate = map.get("v_alcohol_date");
            String atime = map.get("v_alcohol_time");
            String aalcohol = map.get("v_alcohol_alcohol");
            String aunit = map.get("v_alcohol_unit");
            alcoldate.setText(adate);
            alcotime.setText(atime);
            alcoalcohol.setText(aalcohol);
            alcounit.setText(aunit);
            alchoholView.setVisibility(View.VISIBLE);


        } else
        {
            llAlcohol.setVisibility(View.GONE);
            alchoholView.setVisibility(View.GONE);
        }

        if (PulseArray.size() > 0)
        {
            llPulse.setVisibility(View.VISIBLE);

            HashMap<String, String> map = PulseArray.get(0);
            String pdate = map.get("v_pulse_date");
            String ptime = map.get("v_pulse_time");
            String psts = map.get("v_pulse_pulse");
            String pdis = map.get("v_pulse_unit");
            pulsedate.setText(pdate);
            pulsetime.setText(ptime);
            pulsetemp.setText(psts);
            pulseunit.setText(pdis);
            pulseView.setVisibility(View.VISIBLE);
        } else
        {
            llPulse.setVisibility(View.GONE);
            pulseView.setVisibility(View.GONE);
        }



        if (BpressureArray.size() > 0)
        {
            llBloodPressure.setVisibility(View.VISIBLE);

            HashMap<String, String> map = BpressureArray.get(0);
            String bpredate = map.get("v_bloodpressure_date");
            String bpreptime = map.get("v_bloodpressure_time");
            String bprepsts = map.get("v_bloodpressure_ststolic");
            String bprepdis = map.get("v_bloodpressure_diastolic");
            bpdate.setText(bpredate);
            bptime.setText(bpreptime);
            bpsos.setText(bprepsts);
            bpdis.setText(bprepdis);
            bpView.setVisibility(View.VISIBLE);

        } else
        {
            llBloodPressure.setVisibility(View.GONE);
            bpView.setVisibility(View.GONE);
        }



        if (BurnCaloryArry.size() > 0)
        {
            llbrnCalories.setVisibility(View.VISIBLE);

            HashMap<String, String> map = BurnCaloryArry.get(0);
            String adate = map.get("v_burn_calories_date");
            String atime = map.get("v_burn_calories_time");
            String aalcohol = map.get("v_burn_calories_burn_calories");
            String aunit = map.get("v_burn_calories_unit");
            bcdate.setText(adate);
            bctime.setText(atime);
            bcburnc.setText(aalcohol);
            bcunit.setText(aunit);
            burnView.setVisibility(View.VISIBLE);


        } else
        {
            llbrnCalories.setVisibility(View.GONE);
            burnView.setVisibility(View.GONE);
        }



        if (CaffeineArray.size() > 0)
        {
            llCoffies.setVisibility(View.VISIBLE);

            HashMap<String, String> map = CaffeineArray.get(0);
            String codate = map.get("v_caffeine_date");
            String cotime = map.get("v_caffeine_time");
            String cocoffin = map.get("v_caffeine_caffeine");
            String counit = map.get("v_caffeine_unit");
            coffinedate.setText(codate);
            coffinetime.setText(cotime);
            coffinecoffine.setText(cocoffin);
            coffineunit.setText(counit);
            coffineView.setVisibility(View.VISIBLE);

        } else
        {
            llCoffies.setVisibility(View.GONE);
            coffineView.setVisibility(View.GONE);
        }

        if (HeightArry.size() > 0)
        {
            llHeight.setVisibility(View.VISIBLE);
            HashMap<String, String> map = HeightArry.get(0);
            String hidate = map.get("v_height_date");
            String hitime = map.get("v_height_time");
            String hiheight = map.get("v_height_height");
            String hiunit = map.get("v_height_unit");
            hdate.setText(hidate);
            htime.setText(hitime);
            hheight.setText(hiheight);
            hunit.setText(hiunit);
            heightView.setVisibility(View.VISIBLE);

        } else
        {
            llHeight.setVisibility(View.GONE);
            heightView.setVisibility(View.GONE);
        }



        if (IntakeCaloryArry.size() > 0)
        {
            llintCalories.setVisibility(View.VISIBLE);

            HashMap<String, String> map = IntakeCaloryArry.get(0);
            String incdate = map.get("v_intake_calories_date");
            String inctime = map.get("v_intake_calories_time");
            String incintakecal = map.get("v_intake_calories_intake_calories");
            String incunit = map.get("v_intake_calories_unit");
            icdate.setText(incdate);
            ictime.setText(inctime);
            icintakec.setText(incintakecal);
            icunit.setText(incunit);
            intakeView.setVisibility(View.VISIBLE);
        } else
        {
            llintCalories.setVisibility(View.GONE);
            intakeView.setVisibility(View.GONE);
        }


        if (TempArray.size() > 0)
        {
            llTemprature.setVisibility(View.VISIBLE);

            HashMap<String, String> map = TempArray.get(0);
            String tdate = map.get("v_temp_date");
            String ttime = map.get("v_temp_time");
            String theight = map.get("v_temp_temperature");
            String tunit = map.get("v_temp_unit");
            tempdate.setText(tdate);
            temptime.setText(ttime);
            temptemp.setText(theight);
            tempunit.setText(tunit);
            tempView.setVisibility(View.VISIBLE);
        } else
        {
            llTemprature.setVisibility(View.GONE);
            tempView.setVisibility(View.GONE);
        }


        if (WeightArray.size() > 0)
        {
            llWeight.setVisibility(View.VISIBLE);

            HashMap<String, String> map = WeightArray.get(0);
            String widate = map.get("v_weight_date");
            String witime = map.get("v_weight_time");
            String wiheight = map.get("v_weight_weight");
            String wiunit = map.get("v_weight_unit");
            wdate.setText(widate);
            wtime.setText(witime);
            wweight.setText(wiheight);
            wunit.setText(wiunit);
            weightView.setVisibility(View.VISIBLE);

        } else
        {
            llWeight.setVisibility(View.GONE);
            weightView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.frg_vital_btn_add:
                Intent vaddbtn = new Intent(getActivity(), ActivityAddVital.class);
                startActivity(vaddbtn);
                break;

            case R.id.fragment_vital_llfortemp:
                Intent vlltemp = new Intent(getActivity(), ActivityVitalDisplayTemperature.class);
                startActivity(vlltemp);
                break;
            case R.id.fragment_vital_llforpulse:
                Intent vllpulse = new Intent(getActivity(), ActivityVitalDisplayPulse.class);
                startActivity(vllpulse);
                break;
            case R.id.fragment_vital_llforbloodpressure:
                Intent vllbloodpre = new Intent(getActivity(), ActivityVitalDisplayBloodPressure.class);
                startActivity(vllbloodpre);
                break;

            case R.id.fragment_vital_llforheight:
                Intent vllheight = new Intent(getActivity(), ActivityVitalDisplayHeight.class);
                startActivity(vllheight);
                break;

            case R.id.fragment_vital_llforweight:
                Intent vllweight = new Intent(getActivity(), ActivityVitalDisplayWeight.class);
                startActivity(vllweight);
                break;

            case R.id.fragment_vital_llforintake_calories:
                Intent vllintakecalories = new Intent(getActivity(), ActivityVitalDisplayIntakeCalories.class);
                startActivity(vllintakecalories);
                break;

            case R.id.fragment_vital_llforaburn_calories:
                Intent vllburncalories = new Intent(getActivity(), ActivityVitalDisplayBurnCalories.class);
                startActivity(vllburncalories);
                break;

            case R.id.fragment_vital_llforalcohol:
                Intent vllalcohol = new Intent(getActivity(), ActivityVitalDiaplayAlcohol.class);
                startActivity(vllalcohol);
                break;

            case R.id.fragment_vital_llforcaffeinr:
                Intent vllcaffeinr = new Intent(getActivity(), ActivityVitalDisplayCaffeine.class);
                startActivity(vllcaffeinr);
                break;

        }


    }







}
