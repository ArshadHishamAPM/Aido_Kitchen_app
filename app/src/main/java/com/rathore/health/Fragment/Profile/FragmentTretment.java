package com.rathore.health.Fragment.Profile;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

//import com.example.archi.health.Activity.Profile.ActivityProfileCalender;
//import com.example.archi.health.Activity.Profile.ActivityProfileReportSomethingTab;
//import com.example.archi.health.Adapter.Profile.TreatmentProfileAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.example.archi.health.model.ProfileAddValues;

//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileCalender;
//import com.pierfrancescosoffritti.aytplayersample.Activity.Profile.ActivityProfileReportSomethingTab;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.Profile.TreatmentProfileAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.model.ProfileAddValues;
import com.rathore.health.Activity.Profile.ActivityProfileCalender;
import com.rathore.health.Activity.Profile.ActivityProfileReportSomethingTab;
import com.rathore.health.Adapter.Profile.TreatmentProfileAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;
import com.rathore.health.model.ProfileAddValues;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 12/30/2016.
 */

public class FragmentTretment extends Fragment implements View.OnClickListener {

    private LinearLayout llMedication, llStayingHome, llDoctorVisit, llTreatMent, llVaccine, llOther;
    private DbHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.fragment_profile_tretment, null);
        llMedication = (LinearLayout) rooView.findViewById(R.id.fragment_profile_tretment_ll_medication);
        llStayingHome = (LinearLayout) rooView.findViewById(R.id.fragment_profile_tretment_ll_staying_home);
        llDoctorVisit = (LinearLayout) rooView.findViewById(R.id.fragment_profile_tretment_ll_doctor_visit);
        llTreatMent = (LinearLayout) rooView.findViewById(R.id.fragment_profile_tretment_ll_tretment_speciality);
        llVaccine = (LinearLayout) rooView.findViewById(R.id.fragment_profile_tretment_ll_vcaccine);
        llOther = (LinearLayout) rooView.findViewById(R.id.fragment_profile_tretment_ll_other);
        dbHelper = new DbHelper(getActivity());
        // arrayListillness = new ArrayList<>();
        init();
        return rooView;
    }

    private void init() {
        llMedication.setOnClickListener(this);
        llStayingHome.setOnClickListener(this);
        llDoctorVisit.setOnClickListener(this);
        llTreatMent.setOnClickListener(this);
        llVaccine.setOnClickListener(this);
        llOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_profile_tretment_ll_doctor_visit:
                String strTitle = getString(R.string.doctor_visit);

                List<HashMap<String, String>> listDocVisit = dbHelper.getProfileTreatmentDocotorVisit();
                openAddDialog(strTitle, 1,listDocVisit);
                break;

            case R.id.fragment_profile_tretment_ll_medication:
                String sttTitle = getString(R.string.medication);
                List<HashMap<String, String>> listDocMedication = dbHelper.getProfileTreatmentMedication();
                //openAddDialog(sttTitle, 2, listDocMedication);
                break;

            case R.id.fragment_profile_tretment_ll_other:
                String strOther = getString(R.string.other);
                List<HashMap<String, String>> listDocother = dbHelper.getProfileTreatmentOther();
               // openAddDialog(strOther, 3, listDocother);
                break;

            case R.id.fragment_profile_tretment_ll_staying_home:
                String strHome = getString(R.string.staying_home);
                List<HashMap<String, String>> listDocotorStayHome = dbHelper.getProfileTreatmentStayHome();
                //openAddDialog(strHome, 4, listDocotorStayHome);
                break;

            case R.id.fragment_profile_tretment_ll_tretment_speciality:
                String speciality = getString(R.string.speciality);
                List<HashMap<String, String>> listDocotorSpeciality = dbHelper.getProfileTreatmentSpeciality();
              //  openAddDialog(speciality, 5, listDocotorSpeciality);
                break;

            case R.id.fragment_profile_tretment_ll_vcaccine:
                String strVaccans = getString(R.string.vcaccine);
                List<HashMap<String, String>> listDocotorVaccine = dbHelper.getProfileTreatmentVaccine();
               // openAddDialog(strVaccans, 6, listDocotorVaccine);
                break;

        }
    }

    private void openAddDialog(String strTitle, final int selectedItem, final List<HashMap<String, String>> listDocVisit) {

        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_profile_treatment_medication);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.shape);



ImageView img = (ImageView) dialog.findViewById(R.id.imgad);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.dialog_profile_tretatment_tv_title);
        TextView tvDescription = (TextView) dialog.findViewById(R.id.dialog_profile_tretatment_tv_description);
        final ListView lvDialog = (ListView)dialog.findViewById(R.id.dialog_profile_treatment_medication_lv);
        final TreatmentProfileAdapter treatmentProfileAdapter = new TreatmentProfileAdapter(getActivity(),listDocVisit,selectedItem);
        lvDialog.setAdapter(treatmentProfileAdapter);
        lvDialog.setVisibility(View.VISIBLE);


        final EditText etGetDetail = (EditText) dialog.findViewById(R.id.dialog_profile_treatment_medication_et_new_medication);
        TextView ivCancel = (TextView) dialog.findViewById(R.id.dialog_profile_treatment_iv_cancel);
        TextView btnSave = (TextView) dialog.findViewById(R.id.dialog_profile_treatment_medication_btn_send_list);

        tvTitle.setText(strTitle);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strInstructionName = etGetDetail.getText().toString();
                Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                String currentDate = date + "-" + month + "-" + year;
                ProfileAddValues profileAddValues = new ProfileAddValues();
                profileAddValues.setStrDate(currentDate);
                profileAddValues.setStrName(strInstructionName);
               ActivityProfileCalender.arrayListillness.add(profileAddValues);
               // ActivityProfileCalender.arrayListillness1.put(1,profileAddValues);
                ActivityProfileReportSomethingTab.tvHeaderTotal.setText("Selected item :" + ActivityProfileCalender.arrayListillness.size());
                saveTreatmentToDatabase(selectedItem, currentDate, strInstructionName);
                etGetDetail.setText("");
                String id = null;
                if (listDocVisit.size() > 0)
                {
                       id = listDocVisit.get(listDocVisit.size()-1).get("key_treatment_medi_id");
                }
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("key_treatment_medi_id",id);
                hashMap.put("key_treatment_doc_speciality_date",currentDate);
                hashMap.put("key_treatment_medi_name",strInstructionName);
                hashMap.put("key_treatment_doc_speciality_description","");
                listDocVisit.add(hashMap);
                TreatmentProfileAdapter treatmentProfileAdapter = new TreatmentProfileAdapter(getActivity(),listDocVisit, selectedItem);
                lvDialog.setAdapter(treatmentProfileAdapter);
            }
        });
        dialog.show();
    }

    private void saveTreatmentToDatabase(int vlaue, String currentDate, String strInstructionName) {
        switch (vlaue) {
            case 1:
                dbHelper.addProfileTreatDoctorVisit(currentDate, strInstructionName, "");
                break;
            case 2:
                dbHelper.addProfileTreatMedication(currentDate, strInstructionName, "");
                break;
            case 3:
                dbHelper.addProfileTreatOthers(currentDate, strInstructionName, "");
                break;
            case 4:
                dbHelper.addProfileTreatStayHome(currentDate, strInstructionName, "");
                break;
            case 5:
                dbHelper.addProfileTreatSpeciality(currentDate, strInstructionName, "");
                break;
            case 6:
                dbHelper.addProfileTreatVaccine(currentDate, strInstructionName, "");
                break;
        }
    }
}
