package com.client.vpman.userside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Arrays;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText mobile_no;
    Button sign_in;
    private Spinner spinner;
    TextView signup;
    DatabaseReference databaseReference;
    User_Detail user_detail;
    private static final int RC_SIGN_IN = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.PhoneBuilder().build()
                              ))
                        .build(),
                RC_SIGN_IN);

      /*  mAuth=FirebaseAuth.getInstance();
        mobile_no=findViewById(R.id.mobileNo);
        sign_in=findViewById(R.id.sign_in);
        spinner=findViewById(R.id.spinner);
        signup=findViewById(R.id.sign_up);

        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,CountryData.countryNames));
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String number=mobile_no.getText().toString().trim();
                if (number.isEmpty()||number.length()<10)
                {
                    mobile_no.setText("Number is Required");
                    mobile_no.requestFocus();
                    return;
                }
                String phonenumber="+"+code+number;
                Intent intent=new Intent(getApplicationContext(),OTP_Authentication.class);
                intent.putExtra("phonenumber",phonenumber);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Signup.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
       /*if (FirebaseAuth.getInstance().getCurrentUser()!=null)
       {
           Intent intent=new Intent(this,MainActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
           startActivity(intent);
       }*/


    }
  /*  public void checkUser()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("User_Details");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    if (data.child("mobileNo").exists())
                    {
                        //do ur stuff
*//*
                        String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                        String number=mobile_no.getText().toString().trim();
                        if (number.isEmpty()||number.length()<10)
                        {
                            mobile_no.setText("Number is Required");
                            mobile_no.requestFocus();
                            return;
                        }
                        String phonenumber="+"+code+number;
                        Intent intent=new Intent(getApplicationContext(),OTP_Authentication.class);
                        intent.putExtra("phonenumber",phonenumber);
                        startActivity(intent);*//*
                    }
                    else
                        {
                        //do something if not exists

                        String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                        String number=mobile_no.getText().toString().trim();
                        if (number.isEmpty()||number.length()<10)
                        {
                            mobile_no.setText("Number is Required");
                            mobile_no.requestFocus();
                            return;
                        }
                        String phonenumber="+"+code+number;
                        Toast.makeText(getApplicationContext(),"New User",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Login.this,Signup.class);
                        intent.putExtra("phonenumber",phonenumber);
                        startActivity(intent);
                    }
                }
              *//*  if (dataSnapshot.hasChild("mobileNo"))
                {
                    String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                    String number=mobile_no.getText().toString().trim();
                    if (number.isEmpty()||number.length()<10)
                    {
                        mobile_no.setText("Number is Required");
                        mobile_no.requestFocus();
                        return;
                    }
                    String phonenumber="+"+code+number;
                    Intent intent=new Intent(getApplicationContext(),OTP_Authentication.class);
                    intent.putExtra("phonenumber",phonenumber);
                    startActivity(intent);

                }
                else
                {

                    String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                    String number=mobile_no.getText().toString().trim();
                    if (number.isEmpty()||number.length()<10)
                    {
                        mobile_no.setText("Number is Required");
                        mobile_no.requestFocus();
                        return;
                    }
                    String phonenumber="+"+code+number;
                    Toast.makeText(getApplicationContext(),"New User",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Signup.class);
                    intent.putExtra("phonenumber",phonenumber);
                    startActivity(intent);



                }*//*
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("User_Details");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!dataSnapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                        {
                            startActivity(new Intent(Login.this,Signup.class));

                        }
                        else
                        {
                            if (FirebaseAuth.getInstance().getCurrentUser()!=null)
                            {

                                Intent intent=new Intent(Login.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            startActivity(new Intent(Login.this,MainActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Token"+task.getResult().getToken(),Toast.LENGTH_LONG).show();
                            // PubgUser();

                            user_detail=new User_Detail();

                            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user_detail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"Token is saved",Toast.LENGTH_LONG).show();
                            }
                        });
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Token is not Generated",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    return;
                }

            }
        }
    }


}
