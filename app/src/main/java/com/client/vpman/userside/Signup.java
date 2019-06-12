package com.client.vpman.userside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class Signup extends AppCompatActivity {

   private EditText name,pubg_username,mobile,email;
   private Button sign_up;
   public static String name1,pubg_username1,mobile1,email1;
   DatabaseReference databaseReference;
   FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseReference= FirebaseDatabase.getInstance().getReference("User_Details");
        name=findViewById(R.id.name);
        pubg_username=findViewById(R.id.pubg_username);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile_no);
        sign_up=findViewById(R.id.sign_up1);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PubgUser();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                finish();
                startActivity(intent);

            }
        });




    }
    public void PubgUser()
    {
        name1=name.getText().toString().trim();
        pubg_username1=pubg_username.getText().toString().trim();
        email1=email.getText().toString().trim();
        mobile1=mobile.getText().toString().trim();
        if (!TextUtils.isEmpty(name1) && !TextUtils.isEmpty(pubg_username1)&&!TextUtils.isEmpty(email1)&&!TextUtils.isEmpty(mobile1))
        {
            String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
          User_Detail user_detail=new User_Detail(name1,pubg_username1,email1,mobile1);
            mobile1=mAuth.getCurrentUser().getPhoneNumber();
         //   databaseReference.child(mAuth.getCurrentUser().getUid()).setValue()
            databaseReference.child(id).setValue(user_detail);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please Enter Your Details",Toast.LENGTH_LONG).show();
        }
    }
}
