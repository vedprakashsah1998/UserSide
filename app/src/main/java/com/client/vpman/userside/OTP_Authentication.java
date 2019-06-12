package com.client.vpman.userside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class OTP_Authentication extends AppCompatActivity {

    private String verificationId;
    private Button buttonSignIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;
    String phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__authentication);
        buttonSignIn=findViewById(R.id.buttonSignIn);
        phonenumber=getIntent().getStringExtra("phonenumber");
        progressBar=findViewById(R.id.progressbar);
        editText=findViewById(R.id.editTextCode);
        sendVerificationCode(phonenumber);
        mAuth=FirebaseAuth.getInstance();
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=editText.getText().toString().trim();
                if (code.isEmpty()||code.length()<6)
                {
                    editText.setError("Enter Code...");
                    editText.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

      /*  String existedUser=mAuth.getCurrentUser().getPhoneNumber();
        Toast.makeText(this, existedUser, Toast.LENGTH_SHORT).show();*/
     /*   String existedUser=mAuth.getCurrentUser().getPhoneNumber();
      if (existedUser==null)
      {
          Intent intent=new Intent(OTP_Authentication.this,Signup.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
          startActivity(intent);
      }
      else
      {
          Toast.makeText(OTP_Authentication.this,"New User",Toast.LENGTH_LONG).show();
          Intent intent=new Intent(OTP_Authentication.this,MainActivity.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
          startActivity(intent);
      }*/


    }
    private void verifyCode(String code)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
        signinWithCredential(credential);
    }
    private void signinWithCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){


                   /* FirebaseUser user = task.getResult().getUser();
                    if (user!=null)*/
                   // {

                   // checkUser();
                    String existedUser=mAuth.getCurrentUser().getPhoneNumber();
                    Toast.makeText(OTP_Authentication.this, existedUser, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(OTP_Authentication.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                  /*  }
                    else
                    {
                        Intent intent=new Intent(OTP_Authentication.this,Signup.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }*/

                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void sendVerificationCode(String number)
    {
        progressBar.setVisibility(View.VISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,mCallBack);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if (code!=null)
            {
                editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
    public void checkUser()
    {
        final String existedUser=mAuth.getCurrentUser().getPhoneNumber();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("User_Details");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                String passc = dataSnapshot.child("mobileNo").getValue().toString();

                if (existedUser.equals(passc))
                {
                    Toast.makeText(getApplicationContext(),"New User",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(OTP_Authentication.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                  //  Toast.makeText(getApplicationContext(),"New User",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(OTP_Authentication.this,Signup.class);
                    startActivity(intent);
                }

                /*    if (dataSnapshot.getValue()!=null)
                    {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                           *//* UserbasicInfo user = userSnapshot.getValue(UserbasicInfo.class);
                            Toast.makeText(UserInfoActivity.this, "User already exist...!", Toast.LENGTH_SHORT).show();*//*
                            //do ur stuff

                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }

                    }
                    else
                    {
                        //do something if not exists

                        Toast.makeText(getApplicationContext(),"New User",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(OTP_Authentication.this,Signup.class);
                        startActivity(intent);
                    }*/
                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
