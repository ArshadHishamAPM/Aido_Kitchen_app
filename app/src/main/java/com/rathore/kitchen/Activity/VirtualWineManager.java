package com.rathore.kitchen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.rathore.kitchen.Fragment.AllWineFragment;
import com.rathore.kitchen.Fragment.MyCellarFragment;
import com.rathore.kitchen.Fragment.WishListFragment;
import com.rathore.kitchen.R;

import java.util.ArrayList;
import java.util.List;

public class VirtualWineManager extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView addbarbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_wine_manager);
        viewPager = (ViewPager) findViewById(R.id.virtualwineViewPage);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.virtualwinetabs);
        tabLayout.setupWithViewPager(viewPager);
        addbarbutton = (ImageView)findViewById(R.id.addbar);
        addbarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntentStoreItem = new Intent(VirtualWineManager.this, AddVirtualWineManagerAct.class);
                startActivity(mIntentStoreItem);
            }
        });

    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment(new MyCellarFragment(), "MyCellar");
        adapter.addFragment(new AllWineFragment(), "AllWine");
        adapter.addFragment(new WishListFragment(), "Cart");
        adapter.addFragment(new MyCellarFragment(), "Bottel List");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addvirtualwine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.AddVirtualWinebottle) {

           /* Intent mIntentStoreItem = new Intent(BarTanderVitualWineAct.this, GroceryShopAct.class);
            startActivity(mIntentStoreItem);*/
            Intent mIntentStoreItem = new Intent(VirtualWineManager.this, AddVirtualWineManagerAct.class);
            startActivity(mIntentStoreItem);

        }/*else if(id == R.id.foodtimer){
            Intent mIntenTime = new Intent(BarTanderVitualWineAct.this,TimeWizarAct.class);
            startActivity(mIntenTime);
        }*/

        return super.onOptionsItemSelected(item);
    }

}
