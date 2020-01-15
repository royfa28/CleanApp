package com.example.cleanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.cleanapp.Model.HouseInvitationModel;

public class HouseInvitation extends AppCompatActivity {

    protected Toolbar myToolbar;
    protected EditText phoneNumberTenant;
    protected Button validationBtn;
    protected HouseInvitationModel myHouseInvitation;
    FirebaseAuth myFirebaseAuth;
    DatabaseReference myDataBase= FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_invitation);

        myToolbar = findViewById(R.id.toolbar2);
        phoneNumberTenant = findViewById(R.id.editText);
        validationBtn = findViewById(R.id.valdBtn);
        myHouseInvitation = new HouseInvitationModel();
        //get n set the info owner to fill house invitation model
        myHouseInvitation.setIdOwner(myFirebaseAuth.getCurrentUser().getUid());

        //get n set info Tenant


    }

    protected void getOwnerID()
    {


    }
}
