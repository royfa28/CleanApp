package com.example.cleanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cleanapp.Database.FirebaseDatabaseHelper;
import com.example.cleanapp.Model.Room;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHouseActivity extends AppCompatActivity {

    EditText bedroom_amount, bathroom_amount, room_amount;
    Integer bedroom, bathroom, rooms;

    Room room;
//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        Button saveHouseButton = (Button) findViewById(R.id.save_house_button);
        bedroom_amount = (EditText) findViewById(R.id.bedroom_amount);
        bathroom_amount = (EditText) findViewById(R.id.bathroom_amount);
        room_amount = (EditText) findViewById(R.id.room_amount);

        room = new Room();
//        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        final String bedroom_value = bedroom_amount.getText().toString();

//        bedroom = new Integer(bedroom_value).intValue();
//        bathroom = Integer.parseInt(bathroom_amount.getText().toString());
//        rooms = Integer.parseInt(room_amount.getText().toString());

        saveHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("/House");

                myRef.setValue("Hello, World!");
//                Intent intent = new Intent(AddHouseActivity.this, OwnerMainActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void getValues(){
        room.setRoomName("Bedroom");
        room.setDescription("");

//        String key = databaseReference.push().getKey();
//        databaseReference.child(key).child("roomName").setValue("Bedroom");
//        databaseReference.child(key).child("Description").setValue("Emtpy");
    }
}
