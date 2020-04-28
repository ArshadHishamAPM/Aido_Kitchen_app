package com.rathore.health.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.example.archi.health.Activity.ActivityAddMedications;
//import com.example.archi.health.Adapter.MedicancesAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddMedications;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.MedicancesAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddMedications;
import com.rathore.health.Adapter.MedicancesAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 12/22/2016.
 */

public class FragmentMedications extends Fragment implements View.OnClickListener {
    private Button btnAddmedications;
    private RecyclerView medicancesrecycle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_medications, null);
        btnAddmedications = (Button) rootview.findViewById(R.id.frg_medication_btn_add);
        medicancesrecycle = (RecyclerView) rootview.findViewById(R.id.frg_medications_rcyv);

        init();
        return rootview;
    }

    private void init() {
        btnAddmedications.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(getActivity());
        List<HashMap<String, String>> users = helper.getMedicances();
        medicancesrecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        medicancesrecycle.setAdapter(new MedicancesAdapter(getContext(), users));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frg_medication_btn_add:
                Log.i("addButton","clickd");
                Intent i = new Intent(getActivity(), ActivityAddMedications.class);
                startActivity(i);
                break;
        }
    }
}
