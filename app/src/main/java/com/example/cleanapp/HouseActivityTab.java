package com.example.cleanapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.cleanapp.Fragment.CalendarFragment;
import com.example.cleanapp.Fragment.RoomDetailFragment;
import com.example.cleanapp.Fragment.TenantListFragment;
//import com.example.cleanapp.ui.main.PageViewModel;
//import com.example.cleanapp.ui.main.PlaceholderFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HouseActivityTab extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    SectionsPagerAdapter pagerAdapter;
    FragmentManager fragmentManager;
    TabItem tabRoom, tabTask, tabTenant;
    String houseID, ownerID;

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
        tabTask = findViewById(R.id.tabCalendar);
        tabTenant = findViewById(R.id.tabTenant);
        viewPager = findViewById(R.id.view_pager);

        Intent intent = getIntent();
        houseID = intent.getStringExtra("houseID");
        Bundle bundle = new Bundle();
        bundle.putString("house", houseID);
//        RoomDetailFragment houseDetailFragment = new RoomDetailFragment();
        Log.d("C", houseID);
//        houseDetailFragment.setArguments(bundle);

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 1){
                    new RoomDetailFragment(houseID);
                }else if(tab.getPosition() == 2){

                }else{
                    new TenantListFragment(houseID);
                }
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
                    return new RoomDetailFragment(houseID);
                case 1:
                    return new CalendarFragment(houseID);
                case 2:
                    return new TenantListFragment(houseID);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return numOfTabs;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment(){

        }

        public static PlaceholderFragment newInstance(int sectionNumber){
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_room_detail,container, false);
            return rootView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}