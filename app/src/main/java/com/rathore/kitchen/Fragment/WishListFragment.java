package com.rathore.kitchen.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rathore.kitchen.Activity.DatabaseHalper;
import com.rathore.kitchen.Adapter.WishListAdapter;
import com.rathore.kitchen.Model.WishListModel;
import com.rathore.kitchen.R;

import java.util.ArrayList;

/**
 * Created by Ravi Archi on 1/10/2017------add to cart-----.
 */
public class WishListFragment extends Fragment {

    public ListView mListView;
    public ArrayList<WishListModel> mArrayListAllWine;
    public WishListAdapter adapter;
    private DatabaseHalper helper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        mListView = (ListView) view.findViewById(R.id.list_item_cartadd);
        helper = new DatabaseHalper(getActivity());
//        Gson gson = new Gson();
//        String strObj = getArguments().getString("winelistdetail");
//        AllWineFragment mArrayListAllWine = gson.fromJson(strObj,AllWineFragment.class);


        // Toast.makeText(getActivity(), ""+(helper.getCartData()).size(), Toast.LENGTH_SHORT).show();

       /* if (helper.getCartData().size() > 0) {
            // Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
            mArrayListAllWine = helper.getCartData();
            adapter = new WishListAdapter(mArrayListAllWine, getActivity());
            mListView.setAdapter(adapter);
            //  refreshData();
            //adapter.notifyDataSetChanged();

        }*/


        return view;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            mArrayListAllWine = helper.getCartData();
            adapter = new WishListAdapter(mArrayListAllWine, getActivity());
            mListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


}
