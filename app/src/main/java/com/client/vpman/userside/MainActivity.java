package com.client.vpman.userside;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;

import com.firebase.client.Firebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    Firebase firebase;


    DatabaseReference databaseReference;
    public static final String Database_Path = "Tournament_detail";
    public static final String Firebase_Server_URL = "https://insertdata-android-examples.firebaseio.com/";

    private static final String Channel_ID="ChannelID";
    private static final String Chanel_Name="ChanelName";
    private static final String Channel_Description="Chanel_Desc";


    private DemoFragmentStateAdapter adapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.pager);
        adapter = new DemoFragmentStateAdapter(getSupportFragmentManager());
        BottomNavigationView bottomNav = findViewById(R.id.bottombar);
        mViewPager.setAdapter(adapter);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.create:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.tournament:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.register:
                        mViewPager.setCurrentItem(2);
                        break;
                    default:
                        mViewPager.setCurrentItem(0);
                        break;
                }
                return true;
            }
        });

      /*  if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel(Channel_ID,Chanel_Name,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(Channel_Description);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this,Channel_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New Event")
                .setContentText("Enter in the contest")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());*/


        Firebase.setAndroidContext(MainActivity.this);



        firebase = new Firebase(Firebase_Server_URL);

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            Intent intent=new Intent(this,Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
