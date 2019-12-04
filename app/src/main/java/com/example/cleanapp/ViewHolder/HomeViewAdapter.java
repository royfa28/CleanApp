package com.example.cleanapp.ViewHolder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanapp.OwnerHomeFragment;
import com.example.cleanapp.R;

public class HomeViewAdapter extends RecyclerView.Adapter<HomeViewAdapter.HomeViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public HomeViewAdapter(OwnerHomeFragment mContext, Cursor cursor){
        mContext = mContext;
        mCursor = cursor;
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

    }


    @Override
    public int getItemCount() {
        return 0;
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
