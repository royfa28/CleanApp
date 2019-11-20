package com.example.cleanapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OwnerHomeFragment extends Fragment {

    public String userid;
    TextView userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_owner_homepage, container, false);

        Bundle bundle = getArguments();

        final OwnerMainActivity activity = (OwnerMainActivity)getActivity();

        /*
        Bundle results = activity.getUserID();
        userid = results.getString("val1");

        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerView_id);
        final ProductsAdapter listAdapter = new ProductsAdapter(this, activity.getAllProducts());

        myrv.setLayoutManager(new GridLayoutManager(getContext(),3));
        myrv.setAdapter(listAdapter);
        */

        return view;
    }
}
