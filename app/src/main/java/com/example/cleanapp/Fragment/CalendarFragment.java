package com.example.cleanapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cleanapp.Model.Room;
import com.example.cleanapp.Model.TaskAssignCardModel;
import com.example.cleanapp.Model.TenantListModel;
import com.example.cleanapp.R;
import com.example.cleanapp.ViewHolder.TaskAssignViewAdapter;
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
    DatabaseReference getRoom, getTenant;


    //prep array for assignation
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<TenantListModel> tenant = new ArrayList<>();
    ArrayList<TaskAssignCardModel> taskAssignCard = new ArrayList<>();
    ArrayList<Integer> garbage = new ArrayList<Integer>();


    String houseID;
    String userID;

    View view;
    //recycler
    TaskAssignViewAdapter adapter;
    RecyclerView TaskAssignRecycler;


    public CalendarFragment(String houseID) {
        Bundle bundle = new Bundle();
        bundle.putString("house", houseID);
        this.setArguments(bundle);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        TaskAssignRecycler = (RecyclerView) view.findViewById(R.id.recyclerViewAssignTask);
        TaskAssignRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));

        Bundle bundle = getArguments();
        houseID = bundle.getString("house");
        userID = getUserKeyFireAuth();

        adapter = new TaskAssignViewAdapter(taskAssignCard, CalendarFragment.this);
        TaskAssignRecycler.setAdapter(adapter);

        //prep arrays

        //btn
        Button btnRandom = view.findViewById(R.id.button);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepArrayRoomAndRand();
            }
        });

        //prep array

        //load Tenant array on create
        getTenant = FirebaseDatabase.getInstance().getReference().child("House").child(userID).child(houseID).child("Tenant");
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
                    t.setIdTenant(tenantID);
                    //add to array
                    tenant.add(t);
                }
                Log.d("TENANT LIST", "onDataChange: " + tenant);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //load Room Array
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
                }
                Log.d("TAG", "onDataChange: " + rooms);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

    protected String getUserKeyFireAuth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fireAuthUserKey = "";

        if (user != null) {
            fireAuthUserKey = user.getUid();
        }

        return fireAuthUserKey;
    }

    protected void prepArrayRoomAndRand() {

        int totalTask = rooms.size();
        int count = 1;
        while (rooms.size() > 0) {
            TaskAssignCardModel myTaskCard = new TaskAssignCardModel();

            int randNumGarb = (int) (Math.random() * ((rooms.size() - 1) + 1)) + 0;

            int tenantIndex = count % tenant.size();

            myTaskCard.setRoomName(rooms.get(randNumGarb).getRoomName());
            myTaskCard.setRoomTaskDescription(rooms.get(randNumGarb).getRoomdescription());
            myTaskCard.setTenantName(tenant.get(tenantIndex).getName());
            myTaskCard.setTenantNumber(tenant.get(tenantIndex).getNumber());

            taskAssignCard.add(myTaskCard);
            //need to track in db
            String roomID = rooms.get(randNumGarb).getRoomID();
            String tenantID = tenant.get(tenantIndex).getIdTenant();

            //Write to database
            //write on House....OWNERID...HouseID ... tenant...TenanID
            getRoom = FirebaseDatabase.getInstance().getReference().child("House").child(userID).child(houseID).child("TaskAssign");
            //add to house task assign
            getRoom.child(roomID).child("TenanName").setValue(myTaskCard.getTenantName());
            getRoom.child(roomID).child("TenantNumber").setValue(myTaskCard.getTenantNumber());
            getRoom.child(roomID).child("isDone").setValue(false);
            getRoom.child(roomID).child("roomDescription").setValue(myTaskCard.getRoomTaskDescription());
            getRoom.child(roomID).child("roomName").setValue(myTaskCard.getRoomName());
            //write on Tenant....OWNERID...HouseID...TaskAssign...RoomID
            getTenant = FirebaseDatabase.getInstance().getReference().child("House").child(userID).child(houseID).child("Tenant").child(tenantID);
            //add to the tenant Task
            getTenant.child("Task").child(roomID).child("roomDesc").setValue(myTaskCard.getRoomTaskDescription());
            getTenant.child("Task").child(roomID).child("roomName").setValue(myTaskCard.getRoomName());
            //
            count++;
            rooms.remove(randNumGarb);
        }
    }

}

