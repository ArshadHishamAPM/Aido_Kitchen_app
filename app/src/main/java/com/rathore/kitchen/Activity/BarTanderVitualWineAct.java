package com.rathore.kitchen.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rathore.kitchen.Fragment.ManageBarShelfFragment;
import com.rathore.kitchen.Fragment.MyBarShelfFragment;
import com.rathore.kitchen.R;

import java.util.ArrayList;
import java.util.List;

public class BarTanderVitualWineAct extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView bottelglasss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bartandervitualwine);
        viewPager = (ViewPager) findViewById(R.id.bartandervitualwineviewpager);
        setupViewPager(viewPager);
        bottelglasss = (ImageView)findViewById(R.id.bottleglass1);
        bottelglasss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntentStoreItem = new Intent(BarTanderVitualWineAct.this, VirtualWineManager.class);
                startActivity(mIntentStoreItem);
            }
        });
        tabLayout = (TabLayout) findViewById(R.id.bartandervitualwinetabs);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, tabLayout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setText(tab.getText());
            tab.setCustomView(relativeLayout);
            tab.select();
            if(i==2){
                View view = (View) relativeLayout.findViewById(R.id.tabdivider);
                view.setVisibility(View.GONE);
            }
        }
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ManageBarShelfFragment(), "MANAGE BAR SHELF");
        adapter.addFragment(new MyBarShelfFragment(), "MY BAR SHELF CART");
        // adapter.addFragment(new ShoppingFragment(), "SHOPPING");
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
        getMenuInflater().inflate(R.menu.bartendar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bottleglass) {

           /* Intent mIntentStoreItem = new Intent(BarTanderVitualWineAct.this, GroceryShopAct.class);
            startActivity(mIntentStoreItem);*/
            Intent mIntentStoreItem = new Intent(BarTanderVitualWineAct.this, VirtualWineManager.class);
            startActivity(mIntentStoreItem);

        }/*else if(id == R.id.foodtimer){
            Intent mIntenTime = new Intent(BarTanderVitualWineAct.this,TimeWizarAct.class);
            startActivity(mIntenTime);
        }*/

        return super.onOptionsItemSelected(item);
    }
}
