package com.example.cleanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.cleanapp.Model.TaskHistoryModel;
import com.example.cleanapp.ViewHolder.OwnerTaskHistoryViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerTaskHistory extends AppCompatActivity {

    DatabaseReference getTaskHistory, getHistoryDetail;

    ArrayList<TaskHistoryModel> taskHistoryModelArrayList;

    OwnerTaskHistoryViewAdapter adapter;
    RecyclerView taskHistoryRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_task_history);

        // Set the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Task History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taskHistoryRecycler = (RecyclerView) findViewById(R.id.taskHistoryRecylerView);
        taskHistoryRecycler.setLayoutManager(new GridLayoutManager(this, 1));

        Intent intent = getIntent();
        String userID = intent.getStringExtra("UserID");
        String houseID = intent.getStringExtra("HouseID");

        taskHistoryModelArrayList = new ArrayList<>();

        getTaskHistory = FirebaseDatabase.getInstance().getReference().child("House").child(userID).child(houseID).child("TaskHistory");
        getTaskHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                taskHistoryModelArrayList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String historyID = ds.getKey();
                    TaskHistoryModel historyModel = new TaskHistoryModel();
                    historyModel.setHistoryID(historyID);
                    taskHistoryModelArrayList.add(historyModel);
                }
                adapter = new OwnerTaskHistoryViewAdapter(taskHistoryModelArrayList, OwnerTaskHistory.this, userID, houseID);
                taskHistoryRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
