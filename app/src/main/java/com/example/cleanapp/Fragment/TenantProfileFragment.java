package com.example.cleanapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cleanapp.R;

public class TenantProfileFragment extends Fragment {

    String userid, userName, userNumber, userAddress, userPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final TenantMainFragment activity = (TenantMainFragment) getActivity();

        //Bundle results = activity.getUserID();            Getting the user ID
        //userid = results.getString("userID");

        return view;
    }
}
