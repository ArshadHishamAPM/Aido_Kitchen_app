package com.rathore.kitchen.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rathore.kitchen.Adapter.MyCallerAdapter;
import com.rathore.kitchen.Model.AddWineBottleModal;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/10/2017//////bottle list fragment/////.
 */
public class MyCellarFragment extends Fragment {
    public ListView mListView;
    public MyCellarFragment(){}
    public ArrayList<AddWineBottleModal> arrayaddbottel;
    public MyCallerAdapter adapter;
    public DbHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mtcellar, container, false);
        helper = new DbHelper(getActivity());

        mListView = (ListView) view.findViewById(R.id.lv_mycellar);

        if (helper.getVineBottelList().size() > 0) {
            // Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
            arrayaddbottel =helper.getVineBottelList();

            adapter = new MyCallerAdapter(arrayaddbottel, getActivity());
            mListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //  refreshData();
            //adapter.notifyDataSetChanged();

        }





        return view;
    }


}
