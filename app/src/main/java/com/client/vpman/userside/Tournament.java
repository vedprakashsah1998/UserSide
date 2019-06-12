package com.client.vpman.userside;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

public class Tournament extends Fragment {
    View view;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private List<TournamentDetail> tournamentDetails = new ArrayList<>();
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ChildEventListener valueEventListener;
    List<String> key=new ArrayList<>();


    private static final String Channel_ID="ChannelID";
    private static final String Chanel_Name="ChanelName";
    private static final String Channel_Description="Chanel_Desc";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tournament, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //layoutManager.setReverseLayout(true);
        layoutManager = new LinearLayoutManager(getActivity());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Tournament_detail");
        adapter = new Adapter(tournamentDetails, getActivity().getApplicationContext(),key);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        attachDatabaseReadListener();




        return view;

    }


    private void attachDatabaseReadListener() {
        if (valueEventListener == null) {
            valueEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    System.out.println("Hello" + String.valueOf(dataSnapshot.child("nameHolder1").getValue()));
                    TournamentDetail commentMessage = new TournamentDetail(
                            String.valueOf(dataSnapshot.child("nameHolder1").getValue()),
                            String.valueOf(dataSnapshot.child("descHold1").getValue()),
                            String.valueOf(dataSnapshot.child("dateHold1").getValue()),
                            String.valueOf(dataSnapshot.child("timeHold1").getValue()),
                            String.valueOf(dataSnapshot.child("moneyHold1").getValue()),
                            String.valueOf(dataSnapshot.child("image").getValue()));
                        commentMessage.setmKey(dataSnapshot.getKey());

                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                    {
                        NotificationChannel channel=new NotificationChannel(Channel_ID,Chanel_Name, NotificationManager.IMPORTANCE_DEFAULT);
                        channel.setDescription(Channel_Description);
                        NotificationManager manager= getActivity().getSystemService(NotificationManager.class);
                        manager.createNotificationChannel(channel);
                    }

                    NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(getActivity(),Channel_ID)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("New Event")
                            .setContentText("Enter in the contest")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getActivity());
                    notificationManagerCompat.notify(1,mBuilder.build());

                    initListener();


                    tournamentDetails.add(commentMessage);
                    key.add(dataSnapshot.getKey());
                    Toast.makeText(getActivity(),dataSnapshot.getKey(),Toast.LENGTH_LONG).show();
                  //  Toast.makeText(getActivity(), "hey " + tournamentDetails.size(), Toast.LENGTH_LONG).show();
                    //mMessageAdapter.add(commentMessage);
                   /* adapter = new Adapter(tournamentDetails, getActivity().getApplicationContext());
                    recyclerView.setAdapter(adapter);*/

                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            databaseReference.addChildEventListener(valueEventListener);
        }
    }

    public static Tournament newInstance(String text) {
        Tournament f = new Tournament();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    private void initListener()
    {
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView imageView=view.findViewById(R.id.img);
                Intent intent=new Intent(getActivity(),Main2Activity.class);
                TournamentDetail tournamentDetail=tournamentDetails.get(position);
                intent.putExtra("nameHolder1",tournamentDetail.getNameHolder1());
                intent.putExtra("descHold1",tournamentDetail.getDescHold1());
                intent.putExtra("dateHold1",tournamentDetail.getDateHold1());
                intent.putExtra("timeHold1",tournamentDetail.getTimeHold1());
                intent.putExtra("moneyHold1",tournamentDetail.getMoneyHold1());
                intent.putExtra("image",tournamentDetail.getImage());

                Pair<View, String> pair = Pair.create((View)imageView, ViewCompat.getTransitionName(imageView));
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),pair
                );


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(intent, optionsCompat.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });
    }

}
