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

import com.example.cleanapp.Fragment.RoomDetailFragment;
import com.example.cleanapp.Fragment.TenantListFragment;
import com.example.cleanapp.HouseActivityTab;
import com.example.cleanapp.Model.TenantListModel;
import com.example.cleanapp.Model.UserModel;
import com.example.cleanapp.R;

import java.util.ArrayList;

public class TenantViewAdapter extends RecyclerView.Adapter<TenantViewAdapter.TenantViewHolder> {

    private Context mContext;
    ArrayList<TenantListModel> userTenant;

    public TenantViewAdapter(TenantListFragment mContext, ArrayList<TenantListModel> t){
        mContext = mContext;
        userTenant = t;
    }


    @NonNull
    @Override
    public TenantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listview_tenant, parent, false);
        return new TenantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TenantViewAdapter.TenantViewHolder holder, int position) {
//        String houseID = houses.get(position).getHouseID();
//        holder.house_Name.setText(houseID);
        String num = userTenant.get(position).getNumber();
        holder.tenant_phone.setText(num);
        holder.tenant_name.setText(userTenant.get(position).getName());
        //holder.teant_profileImg.setImageIcon();
        Bundle bundle = new Bundle();

        // Clicking the house will bring it to house view, where user can see rooms inside the house.

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), HouseActivityTab.class);
//
//                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userTenant.size();
    }

//    @Override
//    public int getItemCount() {
//        //return houses.size();
//    }


    public class TenantViewHolder extends  RecyclerView.ViewHolder{

        public TextView tenant_name,tenant_phone;
        public CardView cardView;
        public ImageView teant_profileImg;

        public TenantViewHolder(@NonNull View itemView) {
            super(itemView);
            tenant_name = (TextView) itemView.findViewById(R.id.tenant_name_Textview);
            teant_profileImg = (ImageView) itemView.findViewById(R.id.TenantimageView2);
            tenant_phone = (TextView) itemView.findViewById(R.id.tenant_phone_TextView);
            cardView = (CardView) itemView.findViewById(R.id.tenant_cardview);

        }
    }
}
