package com.example.ravipavansne.brightcarrot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyvehiclesActivity extends AppCompatActivity {

    private VehicleDetails vehicleDetails ;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private ArrayList<VehicleDetails> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvehicles);

    recyclerView = (RecyclerView)findViewById(R.id.vehiclesrecycler);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(MyvehiclesActivity.this));
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Searching your vehicles");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        arrayList = new ArrayList<VehicleDetails>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("vehicles");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
            for(DataSnapshot d: dataSnapshot.getChildren())
            {

                VehicleDetails v = d.getValue(VehicleDetails.class);
                arrayList.add(v);
                }

                TextView tv = (TextView)findViewById(R.id.myvehno);
                if(arrayList.size()==0)
                    tv.setText("NO VEHICLES ADDED");
                progressDialog.dismiss();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    MyVehicleAdapter myVehicleAdapter = new MyVehicleAdapter(this,arrayList);
    recyclerView.setAdapter(myVehicleAdapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),VehicleFillingActivity.class));
            }
        });

    }

}
