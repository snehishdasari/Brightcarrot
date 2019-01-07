package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VehicleDisplayActivity extends AppCompatActivity {


    private TextView name ;
    private TextView color ;
    private TextView kms ;
    private TextView dop ;
    private TextView psd ;
    private TextView fuelused ;
    private ImageView vehimage ;
    private DatabaseReference databaseReference ;
    private FirebaseUser firebaseUser ;
    private List<VehicleDetails> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_display);
        Intent intent = getIntent() ;
        int i = intent.getIntExtra("item",0) ;

        name = (TextView) findViewById(R.id.namevehdisp) ;
        color = (TextView) findViewById(R.id.colorvehdisp) ;
        kms = (TextView) findViewById(R.id.kmsvehdisp) ;
        dop = (TextView) findViewById(R.id.dopvehdisp) ;
        psd = (TextView) findViewById(R.id.psdvehdisp) ;
        fuelused = (TextView) findViewById(R.id.fuelvehdisp) ;
        vehimage = (ImageView) findViewById(R.id.imvehdisp) ;
        list = new ArrayList<VehicleDetails>() ;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("Vehicle Details") ;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot d : dataSnapshot.getChildren())
                {
                    VehicleDetails v = d.getValue(VehicleDetails.class) ;
                    list.add(v) ;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String n = list.get(i).getVehiclename() ;
        name.setText(n);
        color.setText(list.get(i).getColorv());
        kms.setText(list.get(i).getNokms());
        dop.setText(list.get(i).getDop());
        psd.setText(list.get(i).getPsd());
        fuelused.setText(list.get(i).getFuel());
        Picasso.with(VehicleDisplayActivity.this).load(list.get(i).getVehicleimage()).into(vehimage);



    }
}
