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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddHouseActivity extends AppCompatActivity {

    EditText bedroom_amount, bathroom_amount, room_amount;
    Integer bedroom, bathroom, rooms;
    TextView label;

    Room room;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        Button saveHouseButton = (Button) findViewById(R.id.save_house_button);
        bedroom_amount = (EditText) findViewById(R.id.bedroom_amount);
        bathroom_amount = (EditText) findViewById(R.id.bathroom_amount);
        room_amount = (EditText) findViewById(R.id.room_amount);
        label = (TextView) findViewById(R.id.textView3);

        room = new Room();

//        bathroom = Integer.parseInt(bathroom_amount.getText().toString());
//        rooms = Integer.parseInt(room_amount.getText().toString());

        saveHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                //DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                mDatabase = FirebaseDatabase.getInstance().getReference("Test").child("u2").child("Child");
                String myID = mDatabase.getKey();

                mDatabase.setValue("ABC");

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        String value = databaseError.getMessage();
                    }
                });

                //myRef.setValue("Hello, World!");

//                bedroom = Integer.parseInt(bedroom_amount.getText().toString());
//                getValues();
//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        databaseReference.child("User01").setValue("Testing");
//                        Toast.makeText(AddHouseActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
//                        Log.println('1',"Check", "It work");
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
                Intent intent = new Intent(AddHouseActivity.this, OwnerMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getValues(){
        room.setRoomName("Bedroom");
        room.setDescription("");

        for(int i = 1; i <= bedroom ; i++){
            room.setRoomName("Bedroom " + i);
            room.setDescription("Empty");
        }
    }
}
