package com.example.cleanapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanapp.Model.Room;
import com.example.cleanapp.OwnerMainActivity;
import com.example.cleanapp.R;
import com.example.cleanapp.ViewHolder.OwnerRoomViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerRoomDetailFragment extends Fragment {

    View view;
    DatabaseReference removeHouse, getRoom;

    private RecyclerView roomListRecycleView;
    Context context;
    OwnerRoomViewAdapter adapter;
    ArrayList<Room> rooms;

    public OwnerRoomDetailFragment(String houseID){
        Bundle bundle = new Bundle();
        bundle.putString("house", houseID);
        this.setArguments(bundle);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_owner_room_detail, container,false);

        rooms = new ArrayList<>();
        roomListRecycleView = view.findViewById(R.id.roomListRecyclerView);
        roomListRecycleView.setLayoutManager(new GridLayoutManager(getContext(),1));

        Bundle bundle = getArguments();
        String houseID = bundle.getString("house");
        String userID = getUserKeyFireAuth();

        FloatingActionButton deleteHouse = view.findViewById(R.id.deleteHouseButton);
        deleteHouse.setOnClickListener( v -> {
            context = v.getContext();

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            //Dialog box for confirmation
            builder.setMessage("Are you sure you want to remove this house? \n\nYou cannot get it back later.")
                    .setTitle("Confirmation")
                    .setPositiveButton("Remove", (dialog, which) -> {
                        removeHouse = FirebaseDatabase.getInstance().getReference().child("House").child(userID).child(houseID);
                        removeHouse.removeValue();
                        Intent intent = new Intent(v.getContext(), OwnerMainActivity.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("Cancel", ((dialog, which) -> dialog.dismiss()));
            builder.show();
            builder.create();
        });

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
                adapter = new OwnerRoomViewAdapter(OwnerRoomDetailFragment.this, rooms);
                roomListRecycleView.setAdapter(adapter);
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
