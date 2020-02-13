package com.example.cleanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.cleanapp.Model.HouseInvitationModel;

public class HouseInvitationActivity extends AppCompatActivity {

    protected Toolbar myToolbar;
    protected EditText phoneNumberTenant;
    protected Button validationBtn;
    protected HouseInvitationModel myHouseInvitation;
    FirebaseAuth myFirebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference getHouseInvitation = FirebaseDatabase.getInstance().getReference().child("Invitation House");
    String houseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i  = getIntent();
        houseID = i.getStringExtra("houseID");

        setContentView(R.layout.activity_house_invitation);

        //myFirebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tenant Invite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phoneNumberTenant = findViewById(R.id.tenantPhoneEditText);
        validationBtn = findViewById(R.id.validBtn);
        myHouseInvitation = new HouseInvitationModel();
        //get n set the info owner to fill house invitation model
        //myHouseInvitation.setIdOwner(myFirebaseAuth.getCurrentUser().getUid());
        //datasnapshot from house .userID

        //get n set info Tenant

        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepHouseInvit();
                addHouseInviteDB();
                returntoTenantList();
            }
        });


    }
    protected  void returntoTenantList(){
        Toast.makeText(HouseInvitationActivity.this, "Your invitation have been sent",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(HouseInvitationActivity.this,HouseActivityTab.class);
        i.putExtra("houseID",houseID);
        startActivity(i);
    }
    protected  void addHouseInviteDB(){
        getHouseInvitation.child(phoneNumberTenant.getText().toString());
        getHouseInvitation.child(phoneNumberTenant.getText().toString()).child("House id").setValue(myHouseInvitation.getIdHouse());
        getHouseInvitation.child(phoneNumberTenant.getText().toString()).child("Owner Key").setValue(myHouseInvitation.getIdOwner());
        getHouseInvitation.child(phoneNumberTenant.getText().toString()).child("isRead").setValue(myHouseInvitation.getisRead());

    }
    protected void prepHouseInvit(){
        myHouseInvitation.setIdOwner(myFirebaseAuth.getCurrentUser().getUid());
        myHouseInvitation.setIdHouse(houseID);

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
