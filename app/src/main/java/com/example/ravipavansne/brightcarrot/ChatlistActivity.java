package com.example.ravipavansne.brightcarrot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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

public class ChatlistActivity extends AppCompatActivity {


    private List<userdetails> users;
    private ListView listView;
    private ImageView back;
    private ChatterAdapter chatterAdapter;
    private userdetails u;
    private DatabaseReference db;
    private FirebaseUser firebaseUser;

    public static userdetails currentchatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);
        users = new ArrayList<>();
        listView = (ListView)findViewById(R.id.chatlistlistview);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        u = new userdetails();
        chatterAdapter = new ChatterAdapter(getApplicationContext(),users);
        /*ProgressDialog pd = new ProgressDialog(this);
                pd.setTitle("Loading Chats");
                pd.setMessage("Please wait....");
                pd.setCanceledOnTouchOutside(false);
                pd.show();*/

        db = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("Chats");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatterAdapter.clear();
                for(DataSnapshot d: dataSnapshot.getChildren())
                {
                    String s = d.getKey();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(s).child("Account details");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            u = new userdetails();
                            u.setFirstname(dataSnapshot.child("firstname").getValue().toString());
                            u.setLastname(dataSnapshot.child("lastname").getValue().toString());
                            u.setPhonenumber(dataSnapshot.child("phonenumber").getValue().toString());
                            //u.setFirstname(dataSnapshot.child("firstname").getValue().toString());
                            u.setId(dataSnapshot.child("id").getValue().toString());
                            chatterAdapter.add(u);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }



                listView.setAdapter(chatterAdapter);
              //  pd.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back = (ImageView)findViewById(R.id.chatlistback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Home2Activity.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                currentchatter= users.get(position);
                Intent i = new Intent(getApplicationContext(),ChatActivity2.class);

                startActivity(i);


            }
        });

    }
}
