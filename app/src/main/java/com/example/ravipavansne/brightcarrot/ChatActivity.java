package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout linearLayout ;
    private ImageView sendBtn ;
    private EditText messageArea ;
    private FirebaseUser firebaseUser ;
    private ScrollView scrollView ;
    private DatabaseReference dr1,dr2 ;
    private String oname ;
    private String uid ;
    private String myname ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent i = getIntent() ;
        oname = i.getStringExtra("ownername") ;
        myname = i.getStringExtra("myname") ;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;



        linearLayout = (LinearLayout)findViewById(R.id.layout1);
        sendBtn  = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);





        dr1 = FirebaseDatabase.getInstance().getReference().child("Messages").child(VehicleAdapter.displayvehicle.getOwnerid()+"_"+VehicleAdapter.displayvehicle.getBookedby()) ;
        dr2 = FirebaseDatabase.getInstance().getReference().child("Messages").child(VehicleAdapter.displayvehicle.getBookedby()+"_"+VehicleAdapter.displayvehicle.getOwnerid()) ;
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = messageArea.getText().toString() ;
                Message m = new Message(msg,firebaseUser.getUid()) ;
                dr1.push().setValue(m) ;
                dr2.push().setValue(m) ;
            }
        });

        dr2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message m1 = dataSnapshot.getValue(Message.class) ;
                String msg1 = m1.getMessage() ;
                String user1 = m1.getUser() ;

                if(user1==firebaseUser.getUid())
                {
                    addMessageBox("You:-\n" + msg1, 1);
                }
                else
                {
                    addMessageBox("Other:-\n" + msg1, 2);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void addMessageBox(String message, int type){
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        textView.setLayoutParams(lp);

        if(type == 1) {
            textView.setBackgroundResource(R.drawable.rounded_corner1);
        }
        else{
            textView.setBackgroundResource(R.drawable.rounded_corner2);
        }

        linearLayout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
