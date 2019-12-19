package com.example.cleanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OwnerHomeFragment extends Fragment {


    DatabaseReference mDatabase;
    private RecyclerView homeListRecyclerList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_owner_homepage, container, false);

        Bundle bundle = getArguments();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final OwnerMainActivity activity = (OwnerMainActivity)getActivity();
        String userID = getUserKeyFireAuth();

        Button addHouseButton = (Button) view.findViewById(R.id.add_house_button);

        //Query ownerHouse = mDatabase.child("House").child("UserID").equalTo(userID);

        addHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String houseID = mDatabase.push().getKey();
                //mDatabase.child("House").child(houseID).child("UserID").setValue(userID);
//                mDatabase.child("House").child(userID).child(houseID).child("Room_1").child("roomName").setValue("Bedroom 1");
//                mDatabase.child("House").child(userID).child(houseID).child("Room_1").child("Description").setValue("Clean the sheet");
//                mDatabase.child("House").child(userID).child(houseID).child("Room_2").child("roomName").setValue("Bedroom 2");;
//                mDatabase.child("House").child(userID).child(houseID).child("Room_2").child("Description").setValue("Clean the sheet");;

                Intent intent = new Intent(v.getContext(), AddHouseActivity.class);
                startActivity(intent);

            }
        });

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
