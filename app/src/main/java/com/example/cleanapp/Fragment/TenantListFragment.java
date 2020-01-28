package com.example.cleanapp.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanapp.HouseInvitationActivity;
import com.example.cleanapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TenantListFragment extends Fragment {

    View view;
    protected FloatingActionButton addTenantfloatingActionButton;

    public TenantListFragment (String houseID){
        Bundle bundle = new Bundle();
        bundle.putString("house", houseID);
        this.setArguments(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tenant_list, container,false);

        Bundle bundle = getArguments();
        String houseID = bundle.getString("house");

        addTenantfloatingActionButton = view.findViewById(R.id.addTenantfloatingActionButton);
        addTenantfloatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), HouseInvitationActivity.class);
                i.putExtra("houseID",houseID);
                startActivity(i);
            }
        });

        return view;
    }
}
