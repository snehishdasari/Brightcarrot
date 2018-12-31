package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyaccountActivity extends AppCompatActivity {

    private CircleImageView backaarrow;
    private TextView first ;
    private TextView last ;
    private TextView phone ;
    private TextView dob ;
    private TextView address ;
    private FirebaseUser firebaseUser ;
    private DatabaseReference databaseReference ;
    private Button edit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

    backaarrow = (CircleImageView) findViewById(R.id.backmyaccount);
    backaarrow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MyaccountActivity.this,Home2Activity.class));
        }
    });


    first = (TextView) findViewById(R.id.firstid) ;
    phone =(TextView) findViewById(R.id.phoneid ) ;
    dob = (TextView) findViewById(R.id.dobid) ;
    address= (TextView) findViewById(R.id.addrssid) ;
    edit = (Button) findViewById(R.id.edit ) ;
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()) ;
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String fname = dataSnapshot.child("firstname").getValue().toString() ;
            String lname = dataSnapshot.child("lastname").getValue().toString() ;
            String phoneno = dataSnapshot.child("phonenumber").getValue().toString() ;
            String date = dataSnapshot.child("birthday").getValue().toString() ;
            String month = dataSnapshot.child("birthmonth").getValue().toString() ;
            String year = dataSnapshot.child("birthyear").getValue().toString() ;
            String qaddress = dataSnapshot.child("address").getValue().toString() ;
            String state = dataSnapshot.child("state").getValue().toString() ;
            String city = dataSnapshot.child("city").getValue().toString() ;

            first.setText(fname+" "+lname);
            phone.setText(phoneno);
            dob.setText(date+"/"+month+"/"+year);
            address.setText(qaddress+","+city+"-"+state);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    edit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            startActivity(new Intent(MyaccountActivity.this,SignupActivity.class));
        }
    });




    }
}
