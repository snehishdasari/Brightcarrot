package com.example.ravipavansne.brightcarrot;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {


    private FirebaseUser firebaseUser ;
    private ScrollView scrollView ;
    private DatabaseReference dr1,dr2 ;
    private String mess ;
    private String uid ;
    private String myname ;
    private EditText typemessage;
    private ImageView delete;
    private ImageView send;
    private ImageView back;
    private List<Message> messageList;
    private MessageAdapter messageAdapter;
    private TextView chatname;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        typemessage = (EditText)findViewById(R.id.typemessage);
        send = (ImageView)findViewById(R.id.sendmessage);
        delete = (ImageView)findViewById(R.id.deletechat);

        listView = (ListView)findViewById(R.id.chatlistview);
        chatname = (TextView)findViewById(R.id.chatname);
        chatname.setText(VehicleAdapter.displayvehicle.getOwnername());
        back = (ImageView)findViewById(R.id.chatback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home2Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(this,messageList);
        listView.setAdapter(messageAdapter);
        dr1 = FirebaseDatabase.getInstance().getReference().child("Users").child(VehicleAdapter.displayvehicle.getOwnerid()).child("Chats").child(firebaseUser.getUid());
        dr2 = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("Chats").child(VehicleAdapter.displayvehicle.getOwnerid());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                builder.setMessage("Are you sure you want to delete the chat ??");
                builder.setTitle("DELETE CHAT");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dr1.removeValue();
                        messageAdapter.clear();
                        Toast.makeText(ChatActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ChatActivity.this, "Okay", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a;
                if(typemessage.getText()!=null)
                {
                    a = typemessage.getText().toString();
                    dr1.push().setValue(new Message(a,firebaseUser.getUid()));
                    dr2.push().setValue(new Message(a,firebaseUser.getUid()));
                    typemessage.setText("");
                }
                else{
                    Toast.makeText(ChatActivity.this, "Please Type a Message", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dr1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message m1 = dataSnapshot.getValue(Message.class) ;
                messageAdapter.add(m1);
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


}
