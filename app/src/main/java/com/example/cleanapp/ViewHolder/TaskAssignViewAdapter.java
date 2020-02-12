package com.example.cleanapp.ViewHolder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanapp.Fragment.CalendarFragment;
import com.example.cleanapp.Model.Room;
import com.example.cleanapp.Model.TaskAssignCardModel;
import com.example.cleanapp.Model.UserModel;
import com.example.cleanapp.R;

import java.util.ArrayList;

public class TaskAssignViewAdapter extends RecyclerView.Adapter<TaskAssignViewAdapter.TaskAssignViewHolder> {
    private static final String TAG = "TaskAssignViewAdapter";


    ArrayList<TaskAssignCardModel> myTaskAssignCardModelArrayList;
    private Context myContext;

    public TaskAssignViewAdapter(ArrayList<TaskAssignCardModel> assignArray,  CalendarFragment myContext) {
        myTaskAssignCardModelArrayList = assignArray;
        myContext = myContext;
    }

    @NonNull
    @Override
    public TaskAssignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assign_task,parent,false);
        TaskAssignViewHolder mytaskAssignViewHolder = new TaskAssignViewHolder(view);
        return mytaskAssignViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAssignViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: CALLED");

        String roomName = myTaskAssignCardModelArrayList.get(position).getRoomName();
        String room_desc = myTaskAssignCardModelArrayList.get(position).getRoomDescription();
        String tenantName = myTaskAssignCardModelArrayList.get(position).getTenantName();
        String tenantNumber = myTaskAssignCardModelArrayList.get(position).getTenantNumber();

        holder.textView_TenantName.setText(tenantName);
        holder.textView_TenantNumber.setText(tenantNumber);
        holder.textView_roomName.setText(roomName);
        //holder.textView_TaskDescription.setText(room_desc);

    }

    @Override
    public int getItemCount() {
        return myTaskAssignCardModelArrayList.size();
    }

    public class TaskAssignViewHolder extends RecyclerView.ViewHolder{
        //hold the widget => declare all elemtents of assign task layout

        CardView cardview_TaskAssign_Parent;
        ConstraintLayout ConstraintLayout_TaskAssign;
        TextView textView_TenantName,textView_TenantNumber,textView_roomName,textView_TaskDescription;
        ImageView imageView_ProfilePicTenant;


        public TaskAssignViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview_TaskAssign_Parent = itemView.findViewById(R.id.cardview_TaskAssign_Parent);
            ConstraintLayout_TaskAssign = itemView.findViewById(R.id.linearLayout_TaskAssign);

            textView_TenantName = itemView.findViewById(R.id.textView_TenantName);
            textView_TenantNumber = itemView.findViewById(R.id.textView_TenantNumber);
            textView_roomName = itemView.findViewById(R.id.textView_roomName);
            textView_TaskDescription = itemView.findViewWithTag(R.id.textView_TaskDescription);
            imageView_ProfilePicTenant = itemView.findViewById(R.id.imageView_ProfilePicTenant);
        }
    }
}
