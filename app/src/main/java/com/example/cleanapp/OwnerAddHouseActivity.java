package com.example.cleanapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerAddHouseActivity extends AppCompatActivity {

    EditText bathroom_amount, room_amount;
    Integer bedroom, bathroom, rooms;
    TextView label;

    DatabaseReference mDatabase;
    DatabaseReference addRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Set House");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button saveHouseButton = (Button) findViewById(R.id.save_house_button);
        bathroom_amount = (EditText) findViewById(R.id.bathroom_amount);
        bathroom_amount.setFilters( new InputFilter[]{ new MinMaxInputFilter( "1" , "10" )}) ;
        room_amount = (EditText) findViewById(R.id.room_amount);
        room_amount.setFilters( new InputFilter[]{ new MinMaxInputFilter( "1" , "10" )}) ;
        label = (TextView) findViewById(R.id.textView3);

        saveHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();

                Intent intent = new Intent(OwnerAddHouseActivity.this, OwnerMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getValues(){
        int count = 1;
        bathroom = Integer.parseInt(bathroom_amount.getText().toString());
        rooms = Integer.parseInt(room_amount.getText().toString());

        String userID = getUserKeyFireAuth();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String houseID = mDatabase.push().getKey();
        addRoom = mDatabase.child("House").child(userID).child(houseID).child("Rooms");

//        for(int i = 1; i <= bedroom ; i++){
//            addRoom.child("Room " + count).child("roomName").setValue("Bedroom " + i);
//            addRoom.child("Room " + count).child("roomDescription").setValue("Please fill in task");
//            count++;
//        }

        for(int j = 1 ; j <= bathroom ; j++){
            addRoom.child("Room " + count).child("roomName").setValue("Bathroom " + j);
            addRoom.child("Room " + count).child("roomDescription").setValue("Please fill in task");
            count++;
        }

        for(int k = 1 ; k <= rooms ; k++){
            addRoom.child("Room " + count).child("roomName").setValue("Other room " + k);
            addRoom.child("Room " + count).child("roomDescription").setValue("Please fill in task");
            count++;
        }
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    protected String getUserKeyFireAuth(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fireAuthUserKey="";

        if (user != null) {
            fireAuthUserKey = user.getUid();
        }
        return fireAuthUserKey;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
