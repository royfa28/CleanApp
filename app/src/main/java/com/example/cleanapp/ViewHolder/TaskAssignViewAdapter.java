package com.example.cleanapp.ViewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAssignViewAdapter {
    private static final String TAG = "TaskAssignViewAdapter";

    public class TaskAssignViewHolder extends RecyclerView.ViewHolder{
        //hold the widget => declare all elemtents of assign task layout


        CardView cardview_TaskAssign_Parent;
        LinearLayout linearLayout_TaskAssign,linearLayout_Tenant_Child,linearLayout_Task_child;
        TextView textView_TenantName,textView_TenantNumber,textView_roomName,textView_TaskDescription;
        ImageView imageView_ProfilePicTenant;


        public TaskAssignViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview_TaskAssign_Parent = itemView.findViewById(R.id.cardview_TaskAssign_Parent);
        }
    }
}
