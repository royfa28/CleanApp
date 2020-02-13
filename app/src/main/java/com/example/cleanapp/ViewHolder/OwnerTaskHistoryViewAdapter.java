package com.example.cleanapp.ViewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanapp.Model.TaskHistoryModel;
import com.example.cleanapp.OwnerTaskHistory;
import com.example.cleanapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerTaskHistoryViewAdapter extends RecyclerView.Adapter<OwnerTaskHistoryViewAdapter.TaskHistoryViewHolder> {

    ArrayList<TaskHistoryModel> taskHistoryArrayList;
    DatabaseReference getHistoryDetail;
    String UserID, HouseID;
    Context context;

    public OwnerTaskHistoryViewAdapter(ArrayList<TaskHistoryModel> historyModelArrayList, OwnerTaskHistory myContext, String userID, String houseID) {
        taskHistoryArrayList = historyModelArrayList;
        myContext = myContext;
        UserID = userID;
        HouseID = houseID;
    }

    @NonNull
    @Override
    public TaskHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listview_owner_task_history, parent, false);
        return new TaskHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHistoryViewHolder holder, int position) {
        String date = taskHistoryArrayList.get(position).getHistoryID();

        holder.dateTextView.setText("Date: " + date);

        holder.cardView.setOnClickListener(v -> {
            context = v.getContext();

            getHistoryDetail = FirebaseDatabase.getInstance().getReference().child("House").child(UserID).child(HouseID).child("TaskHistory").child(date);
            getHistoryDetail.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String message = "";
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String tenantName = ds.getKey();
                        String taskNotComplete = ds.getValue().toString();

                        message = "Tenant Name: " + tenantName + "\n" + taskNotComplete + "\n\n" + message;
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage(message)
                            .setTitle("Task history for " + date)
                            .setPositiveButton("Ok", ((dialog, which) -> dialog.dismiss()));

                    builder.show();
                    builder.create();

                    Log.d("Tenant", "Name: " + message );
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return taskHistoryArrayList.size();
    }

    public class TaskHistoryViewHolder extends RecyclerView.ViewHolder {

        public TextView dateTextView;
        public CardView cardView;

        public TaskHistoryViewHolder(@NonNull View v) {
            super(v);

            dateTextView = (TextView) v.findViewById(R.id.historyDate_TextView);
            cardView = (CardView) v.findViewById(R.id.taskHistory_cardview);
        }
    }
}
