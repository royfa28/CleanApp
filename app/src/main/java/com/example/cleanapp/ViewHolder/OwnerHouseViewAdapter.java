package com.example.cleanapp.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanapp.Fragment.OwnerRoomDetailFragment;
import com.example.cleanapp.Fragment.OwnerHomeFragment;
import com.example.cleanapp.OwnerHouseActivityTab;
import com.example.cleanapp.Model.House;
import com.example.cleanapp.R;

import java.util.ArrayList;

public class OwnerHouseViewAdapter extends RecyclerView.Adapter<OwnerHouseViewAdapter.HomeViewHolder> {

    private Context mContext;
    ArrayList<House> houses;

    public OwnerHouseViewAdapter(OwnerHomeFragment mContext, ArrayList<House> h){
        mContext = mContext;
        houses = h;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listview_house, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        String houseID = houses.get(position).getHouseID();
        holder.house_Name.setText(houseID);
        Bundle bundle = new Bundle();

        // Clicking the house will bring it to house view, where user can see rooms inside the house.

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OwnerHouseActivityTab.class);
                intent.putExtra("houseID", houseID);
                bundle.putString("house",houseID);
                OwnerRoomDetailFragment roomDetailFragment = new OwnerRoomDetailFragment(houseID);
                roomDetailFragment.setArguments(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public class HomeViewHolder extends  RecyclerView.ViewHolder{

        public TextView house_Name;
        public CardView cardView;
        public ImageView house_image;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            house_Name = (TextView) itemView.findViewById(R.id.house_name_id);
            house_image = (ImageView) itemView.findViewById(R.id.house_image_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }
}
