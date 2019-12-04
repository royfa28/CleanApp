package com.example.cleanapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddHouseFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);


        final OwnerMainActivity activity = (OwnerMainActivity)getActivity();

        //Bundle results = activity.getUserID();            Getting the user ID
        //userid = results.getString("userID");

        return view;
    }
}
