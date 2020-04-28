package com.rathore.health.Fragment.Profile;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

//import com.example.archi.health.Adapter.Profile.SymptomMainListAdapter;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.Profile.SymptomMainListAdapter;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Adapter.Profile.SymptomMainListAdapter;
import com.rathore.health.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by archi on 12/30/2016.
 */

public class FragmentSymptom extends Fragment implements View.OnClickListener {
    private TextView tvHeadAndNeck, tvChest, tvStomachANdBack, tvPalaricAre, tvLeftHand, tvRightHand, tvLeftLag, tvRightLag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.fragment_profile_symptom, null);
        tvHeadAndNeck = (TextView) rooView.findViewById(R.id.fragment_profile_symptom_tv_head_and_neck);
        tvChest = (TextView) rooView.findViewById(R.id.fragment_profile_symptom_tv_chest);
        tvStomachANdBack = (TextView) rooView.findViewById(R.id.fragment_profile_symptom_tv_stomach_back);
        tvPalaricAre = (TextView) rooView.findViewById(R.id.fragment_profile_symptom_tv_symptom_paralic_area);
        tvLeftHand = (TextView) rooView.findViewById(R.id.fragment_profile_symptom_left_hand);
        tvRightHand = (TextView) rooView.findViewById(R.id.fragment_profile_symptom_right_hand);
        tvLeftLag = (TextView) rooView.findViewById(R.id.fragment_profile_symtom_tv_left_lag);
        tvRightLag = (TextView) rooView.findViewById(R.id.fragment_profile_symptom_tv_right_leg);

        init();
        return rooView;
    }

    private void init() {

        tvHeadAndNeck.setOnClickListener(this);
        tvChest.setOnClickListener(this);
        tvStomachANdBack.setOnClickListener(this);
        tvPalaricAre.setOnClickListener(this);
        tvLeftHand.setOnClickListener(this);
        tvRightLag.setOnClickListener(this);
        tvRightHand.setOnClickListener(this);
        tvLeftLag.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_profile_symptom_tv_chest:
                String arrayChest[] = getResources().getStringArray(R.array.profile_symptom_chest);
                openMainListDialog(arrayChest);
                break;
            case R.id.fragment_profile_symptom_left_hand:
                String arrayhand[] = getResources().getStringArray(R.array.profile_symptom_hand);
                openMainListDialog(arrayhand);
                break;
            case R.id.fragment_profile_symptom_tv_head_and_neck:
                String arrayNeck[] = getResources().getStringArray(R.array.profile_symptom_head_and_neck);
                openMainListDialog(arrayNeck);
                break;
            case R.id.fragment_profile_symptom_tv_right_leg:
                String arrayLeg[] = getResources().getStringArray(R.array.profile_symptom_leg);
                openMainListDialog(arrayLeg);
                break;
            case R.id.fragment_profile_symptom_tv_symptom_paralic_area:
                String arrayParelic[] = getResources().getStringArray(R.array.profile_symptom_palvik_area);
                openMainListDialog(arrayParelic);
                break;
            case R.id.fragment_profile_symptom_tv_stomach_back:
                String stomachback[] = getResources().getStringArray(R.array.profile_symptom_stomach_and_back);
                openMainListDialog(stomachback);
                break;
            case R.id.fragment_profile_symptom_right_hand:
                String rightHand[] = getResources().getStringArray(R.array.profile_symptom_hand);
                openMainListDialog(rightHand);
                break;
            case R.id.fragment_profile_symtom_tv_left_lag:
                String leftLeg[] = getResources().getStringArray(R.array.profile_symptom_leg);
                openMainListDialog(leftLeg);
                break;

        }
    }

    private void openMainListDialog(String[] array) {

        List<String> stringList = new ArrayList<String>(Arrays.asList(array));
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_profile_symptom_display_main_array);
        ListView lvView = (ListView) dialog.findViewById(R.id.dialog_profile_symptom_main_array_lv);
        SymptomMainListAdapter symptomMainListAdapter = new SymptomMainListAdapter(getActivity(), stringList,dialog);
        lvView.setAdapter(symptomMainListAdapter);
        dialog.show();
    }

    private void dialogGetItem(String first, String second) {

    }
}
