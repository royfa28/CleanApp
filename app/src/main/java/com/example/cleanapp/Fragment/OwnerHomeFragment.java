package com.example.cleanapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanapp.Model.House;
import com.example.cleanapp.OwnerAddHouseActivity;
import com.example.cleanapp.OwnerMainActivity;
import com.example.cleanapp.R;
import com.example.cleanapp.ViewHolder.OwnerHouseViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerHomeFragment extends Fragment {


    private DatabaseReference mDatabase, getHouse;
    private RecyclerView homeListRecyclerList;
    private ArrayList<House> houseArrayList;
    private OwnerHouseViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_owner_homepage, container, false);

        Bundle bundle = getArguments();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final OwnerMainActivity activity = (OwnerMainActivity)getActivity();
        String userID = getUserKeyFireAuth();

        homeListRecyclerList = view.findViewById(R.id.homeRecyclerView);
        homeListRecyclerList.setLayoutManager(new GridLayoutManager(getContext(),3));
        houseArrayList = new ArrayList<>();

        getHouse = FirebaseDatabase.getInstance().getReference().child("House").child(userID);

        getHouse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                houseArrayList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String houseID = dataSnapshot1.getKey();
                    House h = new House();
                    h.setHouseID(houseID);
//                    House h = dataSnapshot1.getValue(House.class);
                    houseArrayList.add(h);
                }
                adapter = new OwnerHouseViewAdapter(houseArrayList);
                homeListRecyclerList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button addHouseButton = view.findViewById(R.id.add_house_button);

        addHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OwnerAddHouseActivity.class);
                startActivity(intent);
            }
        });



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

    @Override
    public void onStart() {
        super.onStart();


    }
}
