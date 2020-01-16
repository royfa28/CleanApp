package com.example.cleanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TenantList extends AppCompatActivity {

    protected FloatingActionButton addTenantfloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_list);

        Intent i = getIntent();
        String houseID = i.getStringExtra("houseID");


        addTenantfloatingActionButton = findViewById(R.id.addTenantfloatingActionButton);
        addTenantfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TenantList.this, HouseInvitation.class);
                i.putExtra("houseId",houseID);
                startActivity(i);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tenant list");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
