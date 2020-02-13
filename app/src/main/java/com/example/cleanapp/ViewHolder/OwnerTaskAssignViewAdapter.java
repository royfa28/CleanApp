package com.example.cleanapp.ViewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanapp.Fragment.OwnerCalendarFragment;
import com.example.cleanapp.Model.TaskAssignCardModel;
import com.example.cleanapp.R;

import java.util.ArrayList;

public class OwnerTaskAssignViewAdapter extends RecyclerView.Adapter<OwnerTaskAssignViewAdapter.TaskAssignViewHolder> {
    private static final String TAG = "OwnerTaskAssignViewAdapter";

    private ArrayList<TaskAssignCardModel> myTaskAssignCardModelArrayList;
    private Context context;

    public OwnerTaskAssignViewAdapter(ArrayList<TaskAssignCardModel> assignArray, OwnerCalendarFragment myContext) {
        myTaskAssignCardModelArrayList = assignArray;
        myContext = myContext;
    }

    @NonNull
    @Override
    public TaskAssignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_assign_task,parent,false);
        TaskAssignViewHolder mytaskAssignViewHolder = new TaskAssignViewHolder(view);
        return mytaskAssignViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAssignViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: CALLED");

        String roomName = myTaskAssignCardModelArrayList.get(position).getRoomName();
        String tenantName = myTaskAssignCardModelArrayList.get(position).getTenantName();
        String room_desc = myTaskAssignCardModelArrayList.get(position).getRoomDescription();
        Boolean isDone = myTaskAssignCardModelArrayList.get(position).getisDone();

        holder.textView_TenantName.setText(tenantName);
        holder.textView_roomName.setText(roomName);
        holder.textView_isDone.setText("Task: " + isDone.toString());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();

                // Make a dialog box to show more detail
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(roomName + "\n\nTask to do: \n" + room_desc + "\n\nTenant: " + tenantName + "\nCompletion: " + isDone.toString())
                        .setTitle("Information")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.show();
                builder.create();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myTaskAssignCardModelArrayList.size();
    }

    public class TaskAssignViewHolder extends RecyclerView.ViewHolder{
        //hold the widget => declare all elemtents of assign task layout

        CardView cardView;
        TextView textView_TenantName,textView_roomName,textView_isDone;
        ImageView imageView_ProfilePicTenant;


        public TaskAssignViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_TaskAssign_Parent);

            textView_TenantName = itemView.findViewById(R.id.textView_TenantName);
            textView_roomName = itemView.findViewById(R.id.textView_roomName);
            textView_isDone = itemView.findViewById(R.id.textView_isDone);
            imageView_ProfilePicTenant = itemView.findViewById(R.id.imageView_ProfilePicTenant);
        }
    }
}
