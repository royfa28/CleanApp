package com.example.cleanapp.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cleanapp.Model.HouseInvitationModel;
import com.example.cleanapp.Model.TaskAssignCardModel;
import com.example.cleanapp.Model.UserModel;
import com.example.cleanapp.R;
import com.example.cleanapp.ViewHolder.HomeViewAdapter;
import com.example.cleanapp.ViewHolder.TenantTaskListViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TenantHomeFragment extends Fragment {

    DatabaseReference getUserDetails, checkInvite, getInvite, addTenant;

    TextView notificationText;
    Button agreeBtn;
    CardView homepageCard;

    private RecyclerView taskListRecyclerView;
    TenantTaskListViewAdapter adapter;

    ArrayList<UserModel> userDetails;
    ArrayList<HouseInvitationModel> invite;
    ArrayList<TaskAssignCardModel> taskArrayList;

    protected UserModel userModel = new UserModel();
    protected HouseInvitationModel inviteModel = new HouseInvitationModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tenant_homepage, container, false);

        String userID = getUserKeyFireAuth();

        notificationText = (TextView) view.findViewById(R.id.notificationTextView);
        agreeBtn = (Button) view.findViewById(R.id.agreeBtn);

        homepageCard = (CardView) view.findViewById(R.id.tenantHomepageCardView);
        homepageCard.setVisibility(view.GONE);

        taskListRecyclerView = (RecyclerView) view.findViewById(R.id.taskListRecyclerView);
        taskListRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        taskListRecyclerView.setVisibility(view.GONE);

        userDetails = new ArrayList<>();
        invite = new ArrayList<>();

        getUserDetails = FirebaseDatabase.getInstance().getReference().child("User").child(userID);
        getUserDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                userDetails.clear();
                userModel = dataSnapshot.getValue(UserModel.class);
                userModel.setUserKey(userID);
                userDetails.add(userModel);
                getInviteData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTenant = FirebaseDatabase.getInstance().getReference().child("House").child(invite.get(0).getIdOwner())
                        .child(invite.get(0).getIdHouse()).child("Tenant").child(userID);
                addTenant.child("name").setValue(userDetails.get(0).getUserFullName());
                addTenant.child("number").setValue(userDetails.get(0).getUserPhone());
                getInvite = FirebaseDatabase.getInstance().getReference().child("Invitation House").child(userModel.getUserPhone());
                getInvite.child("isRead").setValue(true);


                // Refresh the screen
                Fragment frg = getFragmentManager().findFragmentById(R.id.fragment_container);
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.detach(frg);
                ft.attach(frg);
                ft.commit();

            }
        });

        return view;
    }

    void getInviteData(){
        getInvite = FirebaseDatabase.getInstance().getReference().child("Invitation House").child(userModel.getUserPhone());
        getInvite.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                inviteModel = dataSnapshot.getValue(HouseInvitationModel.class);
                invite.add(inviteModel);

                if(inviteModel.getIsRead() == true){
                    Log.d("Read", "True");
                    taskListRecyclerView.setVisibility(getView().VISIBLE);
                    adapter = new TenantTaskListViewAdapter(TenantHomeFragment.this, taskArrayList);
                    taskListRecyclerView.setAdapter(adapter);
                }else{
                    homepageCard.setVisibility(getView().VISIBLE);
                    notificationText.setText(userModel.getUserMail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    protected String getUserKeyFireAuth(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fireAuthUserKey="";

        if (user != null) {
            fireAuthUserKey = user.getUid();
        }

        return fireAuthUserKey;
    }


}
