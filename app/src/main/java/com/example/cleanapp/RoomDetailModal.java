package com.example.cleanapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.cleanapp.Fragment.TenantHomeFragment;
import com.example.cleanapp.Fragment.TenantProfileFragment;
import com.example.cleanapp.Model.Room;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoomDetailModal extends Activity {

    EditText roomName, roomDescription;
    Button saveButton, cancelButton;
    DatabaseReference updateRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_modal);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Room Name");
        String description = intent.getStringExtra("Room Description");
        String roomID = intent.getStringExtra("Room ID");
        String houseID = intent.getStringExtra("House ID");
        String userID = getUserKeyFireAuth();

        roomName = (EditText)findViewById(R.id.roomNameEditText);
        roomDescription = (EditText)findViewById(R.id.roomDescriptionEditText);
        saveButton = (Button)findViewById(R.id.roomDetailSaveButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);

        roomName.setText(name);
        roomDescription.setText(description);

        updateRoom = FirebaseDatabase.getInstance().getReference().child("House").child(userID).child(houseID).child("Rooms").child(roomID);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room room = new Room(roomName.getText().toString(), roomDescription.getText().toString());
                updateRoom.setValue(room);
                Toast.makeText(v.getContext(),"Room updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int)(width*.8), (int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
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

    public static class TenantMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        private DrawerLayout drawer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tenant_home);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Clean App - Tenant");

            drawer = findViewById(R.id.nav_drawer);
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                    R.string.navigation_drawer_open,R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TenantHomeFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_homepage);
                getSupportActionBar().setTitle("CleanApp - Tenant");
            }
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new TenantProfileFragment()).commit();
                    getSupportActionBar().setTitle("Profile");
                    break;

                case R.id.nav_homepage:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new TenantHomeFragment()).commit();
                    getSupportActionBar().setTitle("CleanApp - Tenant");
                    break;

                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        @Override
        public void onBackPressed() {
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            } else{
                super.onBackPressed();
            }
        }
    }
}
