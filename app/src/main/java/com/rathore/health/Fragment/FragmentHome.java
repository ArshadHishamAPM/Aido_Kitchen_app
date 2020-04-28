package com.rathore.health.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.archi.health.Adapter.MedicancesAdapter;
//import com.example.archi.health.Adapter.ReminderAdapter;
//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.MedicancesAdapter;
//import com.pierfrancescosoffritti.aytplayersample.Adapter.ReminderAdapter;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.Adapter.MedicancesAdapter;
import com.rathore.health.Adapter.ReminderAdapter;
import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 12/28/2016.
 */

public class FragmentHome extends Fragment implements View.OnClickListener
{


    private RecyclerView medicanhomesrecycle;
    private RecyclerView reminderhomesrecycle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home, null);

        medicanhomesrecycle = (RecyclerView) rootview.findViewById(R.id.frg_homem_recycleview);
        reminderhomesrecycle = (RecyclerView) rootview.findViewById(R.id.frg_homer_recycleview);

        return rootview;
    }



    @Override
    public void onResume() {
        super.onResume();
        DbHelper helper = new DbHelper(getActivity());
        List<HashMap<String, String>> parents = helper.getMedicances();
        medicanhomesrecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        medicanhomesrecycle.setAdapter(new MedicancesAdapter(getContext(), parents));
        List<HashMap<String, String>> users = helper.getReminder();
        reminderhomesrecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        reminderhomesrecycle.setAdapter(new ReminderAdapter(getContext(), users));

    }

    @Override
    public void onClick(View v)
    {
    }
}


