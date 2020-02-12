package com.example.cleanapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanapp.R;


public class FirebaseCalendarFragment extends Fragment {



    public FirebaseCalendarFragment(String houseID) {
        Bundle bundle = new Bundle();
        bundle.putString("house", houseID);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_firebase_calendar, container, false);
    }


}
