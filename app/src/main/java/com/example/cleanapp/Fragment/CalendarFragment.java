package com.example.cleanapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanapp.Model.Room;
import com.example.cleanapp.Model.TenantListModel;
import com.example.cleanapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class CalendarFragment extends Fragment {

    //retrieve the info from db
    DatabaseReference getRoom,getTenant;
    ArrayList roomAssign = new ArrayList();
    View view;
    ArrayList<Room>rooms = new ArrayList<>();
    ArrayList<TenantListModel> tenant = new ArrayList<>();



    public CalendarFragment(String houseID) {
        Bundle bundle = new Bundle();
        bundle.putString("house", houseID);
        this.setArguments(bundle);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        Bundle bundle = getArguments();
        String houseID = bundle.getString("house");
        String userID = getUserKeyFireAuth();

        //prep the array of room
        getRoom = FirebaseDatabase.getInstance().getReference().child("House").child(userID).child(houseID).child("Rooms");
        getRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rooms.clear();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot ds : children) {
                    String roomID = ds.getKey();
                    Room room = new Room();
                    room = ds.getValue(Room.class);
                    room.setRoomID(roomID);
                    room.setHouseID(houseID);
                    rooms.add(room);
            }}


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
                {

            }
        });

        //prep array tenant
        getTenant = FirebaseDatabase.getInstance().getReference().child("House").child(houseID).child("Tenant");
        getTenant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tenant.clear();
                Iterable<DataSnapshot> childrenTenant = dataSnapshot.getChildren();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String tenantID = dataSnapshot1.getKey();
                    //start model
                    TenantListModel t = new TenantListModel();
                    //fill model
                    t = dataSnapshot1.getValue(TenantListModel.class);
                    //add to array
                    tenant.add(t);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

return view;
}

    protected String getUserKeyFireAuth(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fireAuthUserKey="";

        if (user != null) {
            fireAuthUserKey = user.getUid();
        }

        return fireAuthUserKey;
    }
}
