package com.example.cleanapp.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanapp.OwnerHouseInvitationActivity;
import com.example.cleanapp.Model.TenantListModel;
import com.example.cleanapp.R;
import com.example.cleanapp.ViewHolder.OwnerTenantViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TenantListFragment extends Fragment {

    View view;
    protected FloatingActionButton addTenantfloatingActionButton;
    DatabaseReference getHouseTenant;
    String ownerID = getUserKeyFireAuth();
    ArrayList<TenantListModel>Tenants = new ArrayList<>();
    OwnerTenantViewAdapter adapter;
    protected RecyclerView tenantRecyclerView ;

    //constructo
    public TenantListFragment (String houseID){
        Bundle bundle = new Bundle();
        bundle.putString("house", houseID);
      //  bundle.putString("ownerID", userID);

        this.setArguments(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_owner_tenant_list, container,false);

        tenantRecyclerView = (RecyclerView) view.findViewById(R.id.tenantRecyclerView);
        tenantRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        Bundle bundle = getArguments();
        String houseID = bundle.getString("house");


        //get the tenantArrayList of the house
        getHouseTenant = FirebaseDatabase.getInstance().getReference().child("House").child(ownerID).child(houseID).child("Tenant");
        getHouseTenant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Tenants.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String tenantID = dataSnapshot1.getKey();
                    //start model
                    TenantListModel t = new TenantListModel();
                    //fill model
                    t = dataSnapshot1.getValue(TenantListModel.class);
                    t.setIdTenant(tenantID);
                    //add to array
//                    House h = dataSnapshot1.getValue(House.class);
                    Tenants.add(t);
                }
                adapter = new OwnerTenantViewAdapter(TenantListFragment.this, Tenants, houseID);
                tenantRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d( "Failed to read value.", databaseError.toException().toString());

            }
        });

        addTenantfloatingActionButton = view.findViewById(R.id.addTenantfloatingActionButton);
        addTenantfloatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), OwnerHouseInvitationActivity.class);
                i.putExtra("houseID",houseID);
                startActivity(i);
            }
        });

        //listner card tenantArrayList for delet

        return view;
    }

    protected String getUserKeyFireAuth(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fireAuthUserKey="";

        if (user != null) {
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            fireAuthUserKey = user.getUid();
        }

        return fireAuthUserKey;
    }
}
