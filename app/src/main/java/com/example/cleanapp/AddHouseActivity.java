package com.example.cleanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cleanapp.Model.Room;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddHouseActivity extends AppCompatActivity {

    EditText bedroom_amount, bathroom_amount, room_amount;
    Integer bedroom, bathroom, rooms;
    TextView label;

    DatabaseReference mDatabase;
    DatabaseReference addRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        Button saveHouseButton = (Button) findViewById(R.id.save_house_button);
        bedroom_amount = (EditText) findViewById(R.id.bedroom_amount);
        bathroom_amount = (EditText) findViewById(R.id.bathroom_amount);
        room_amount = (EditText) findViewById(R.id.room_amount);
        label = (TextView) findViewById(R.id.textView3);

        saveHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
                // Write a message to the database
                //DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
//                mDatabase = FirebaseDatabase.getInstance().getReference();
//                String userID = getUserKeyFireAuth();
//
//                String houseID = mDatabase.push().getKey();
//                mDatabase.child("House").child(userID).child(houseID).child("Rooms").child("Room 1").setValue("Bedroom");

//                mDatabase.child("House").child(userID).child(houseID).child("Room_1").child("roomName").setValue("Bedroom 1");
//                mDatabase.child("House").child(userID).child(houseID).child("Room_1").child("Description").setValue("Clean the sheet");
//                mDatabase.child("House").child(userID).child(houseID).child("Room_2").child("roomName").setValue("Bedroom 2");;
//                mDatabase.child("House").child(userID).child(houseID).child("Room_2").child("Description").setValue("Clean the sheet");;

                Intent intent = new Intent(AddHouseActivity.this, OwnerMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getValues(){
        int count = 1;
        bedroom = Integer.parseInt(bedroom_amount.getText().toString());
        bathroom = Integer.parseInt(bathroom_amount.getText().toString());
        rooms = Integer.parseInt(room_amount.getText().toString());

        String userID = getUserKeyFireAuth();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String houseID = mDatabase.push().getKey();
        addRoom = mDatabase.child("House").child(userID).child(houseID).child("Rooms");

        for(int i = 1; i <= bedroom ; i++){
            addRoom.child("Room ").child("Room name").setValue("Bedroom " + i);
            addRoom.child("Room ").child("Description").setValue("Please fill in task");
            count++;
        }

        for(int j = 1 ; j <= bathroom ; j++){
            addRoom.child("Room " + count).child("Room name").setValue("Bathroom " + j);
            addRoom.child("Room " + count).child("Description").setValue("Please fill in task");
            count++;
        }

        for(int k = 1 ; k <= rooms ; k++){
            addRoom.child("Room " + count).child("Room name").setValue("Other room " + k);
            addRoom.child("Room " + count).child("Description").setValue("Please fill in task");
            count++;
        }
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
