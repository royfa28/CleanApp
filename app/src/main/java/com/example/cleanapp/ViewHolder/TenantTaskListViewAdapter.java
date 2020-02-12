package com.example.cleanapp.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanapp.Fragment.TenantHomeFragment;
import com.example.cleanapp.Model.TaskAssignCardModel;
import com.example.cleanapp.R;

import java.util.ArrayList;

public class TenantTaskListViewAdapter extends RecyclerView.Adapter<TenantTaskListViewAdapter.TenantListViewHolder> {

    ArrayList<TaskAssignCardModel> taskArrayList;

    public TenantTaskListViewAdapter(TenantHomeFragment mContext, ArrayList<TaskAssignCardModel> tasks) {
        mContext = mContext;
        taskArrayList = tasks;
    }

    @NonNull
    @Override
    public TenantListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listview_rooms, parent, false);
        return new TenantListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TenantListViewHolder holder, int position) {

//        String roomName = taskArrayList.get(position).getRoomName();
//        String room_desc = taskArrayList.get(position).getRoomDescription();

        holder.room_name.setText("ROOM");
        holder.room_description.setText("DESC");

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class TenantListViewHolder extends RecyclerView.ViewHolder {

        public TextView room_name;
        public CardView cardView;
        public TextView room_description;

        public TenantListViewHolder(@NonNull View itemView) {
            super(itemView);

            room_name = (TextView) itemView.findViewById(R.id.room_name_Textview);
            room_description = (TextView) itemView.findViewById(R.id.room_description_TextView);
            cardView = (CardView) itemView.findViewById(R.id.room_cardview);
        }
    }
}
