package com.example.cleanapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.cleanapp.Fragment.HouseDetailFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

public class HouseActivityTab extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    SectionsPagerAdapter pagerAdapter;
    FragmentManager fragmentManager;
    TabItem tabRoom, tabTask, tabTenant;
    String houseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_tab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("House Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tabLayout);
        tabRoom = findViewById(R.id.tabRoom);
        tabTask = findViewById(R.id.tabTask);
        tabTenant = findViewById(R.id.tabTenant);
        viewPager = findViewById(R.id.view_pager);

        Intent intent = getIntent();
        houseID = intent.getStringExtra("houseID");
        Bundle bundle = new Bundle();
        bundle.putString("house", houseID);
//        HouseDetailFragment houseDetailFragment = new HouseDetailFragment();
        Log.d("C", houseID);
//        houseDetailFragment.setArguments(bundle);

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private int numOfTabs;

        public SectionsPagerAdapter(FragmentManager fm, int numofTabs) {
            super(fm);
            this.numOfTabs = numofTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new HouseDetailFragment(houseID);
                case 1:
                    return new HouseDetailFragment(houseID);
                case 2:
                    return new HouseDetailFragment(houseID);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return numOfTabs;
        }
    }
}