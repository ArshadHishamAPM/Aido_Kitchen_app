package com.rathore.health.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.example.archi.health.Activity.ActivityAddInvestigation;
//import com.example.archi.health.Adapter.InvestigationAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Activity.ActivityAddInvestigation;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.InvestigationAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Activity.ActivityAddInvestigation;
import com.rathore.health.Adapter.InvestigationAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 12/28/2016.
 */

public class FragmentInvestigation extends Fragment implements View.OnClickListener {

    private Button btnAddInvestigation;
    private RecyclerView investigationrecycle;
    DbHelper helper ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_investigation,null);
        btnAddInvestigation = (Button) rootView.findViewById(R.id.frg_investigation_btn_add);
        investigationrecycle = (RecyclerView) rootView.findViewById(R.id.frg_investigation_rcyv);
        helper = new DbHelper(getActivity());
        init();
        return rootView;
    }

    private void init()
    {
        btnAddInvestigation.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<HashMap<String, String>> users = helper.getInvestigation();
        investigationrecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        investigationrecycle.setAdapter(new InvestigationAdapter(getContext(), users));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frg_investigation_btn_add:
                Intent i = new Intent(getActivity(), ActivityAddInvestigation.class);
                startActivity(i);
                break;
        }
    }
}
