package com.example.cleanapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerAddHouseActivity extends AppCompatActivity {

    EditText bathroom_amount, room_amount;
    protected Button saveHouseButton;
    Integer bedroom, bathroom, rooms;
    TextView label;

    DatabaseReference mDatabase;
    DatabaseReference addRoom;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Set House");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveHouseButton = findViewById(R.id.save_house_button);
        bathroom_amount =  findViewById(R.id.bathroom_amount);
        bathroom_amount.setFilters( new InputFilter[]{ new MinMaxInputFilter( "1" , "10" )}) ;
        room_amount = findViewById(R.id.room_amount);
        room_amount.setFilters( new InputFilter[]{ new MinMaxInputFilter( "1" , "10" )}) ;
        label = findViewById(R.id.textView3);

        bathroom_amount.addTextChangedListener(mTextWatcher);
        room_amount.addTextChangedListener(mTextWatcher);

        saveHouseButton.setEnabled(false);

        saveHouseButton.setOnClickListener(v -> {
            context = v.getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            //Dialog box for confirmation
            builder.setMessage("Are you sure with the room amount? \nNote that you cannot change number of rooms after setting it.")
                    .setTitle("Confirmation")
                    .setPositiveButton("OK", (dialog, which) -> {
                        getValues();
                        Intent intent = new Intent(OwnerAddHouseActivity.this, OwnerMainActivity.class);
                        startActivity(intent);
                        Toast.makeText(OwnerAddHouseActivity.this, "House deleted!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", ((dialog, which) -> dialog.dismiss()));
            builder.show();
            builder.create();
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
            checkIsEmpty();
        }
    };

    void checkIsEmpty(){
        String bathroom = bathroom_amount.getText().toString();
        String otherRoom = room_amount.getText().toString();

        if(bathroom.equals("") || otherRoom.equals("")){
            saveHouseButton.setEnabled(false);
        }else{
            saveHouseButton.setEnabled(true);
        }

    }

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
