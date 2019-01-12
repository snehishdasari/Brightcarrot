package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class VehicleDisplayActivity extends AppCompatActivity {

    private TextView head ;
    private TextView name ;
    private TextView color ;
    private TextView kms ;
    private TextView dop ;
    private TextView psd ;
    private TextView fuelused ;
    private TextView edit;
    private ImageView vehimage ;
    private DatabaseReference databaseReference ;
    private FirebaseUser firebaseUser ;
    public static List<VehicleDetails> list ;
    private CircleImageView b;
    private int i;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_display);
        Intent intent = getIntent() ;
        i = intent.getIntExtra("item",0) ;
        b=(CircleImageView)findViewById(R.id.backvehicledisplay);
        name = (TextView) findViewById(R.id.namevehdisp) ;
        color = (TextView) findViewById(R.id.colorvehdisp) ;
        kms = (TextView) findViewById(R.id.kmsvehdisp) ;
        dop = (TextView) findViewById(R.id.dopvehdisp) ;
        psd = (TextView) findViewById(R.id.psdvehdisp) ;
        fuelused = (TextView) findViewById(R.id.fuelvehdisp) ;
        vehimage = (ImageView) findViewById(R.id.imvehdisp) ;
        edit = (TextView)findViewById(R.id.editvehfill);
        progressBar = (ProgressBar)findViewById(R.id.vehimgprogbar);
        list = new ArrayList<VehicleDetails>() ;
        head = (TextView) findViewById(R.id.vehicledisplayheading) ;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MyvehiclesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
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
                String n = list.get(i).getVehiclename() ;
                name.setText("Name   :   "+n);
                color.setText("Colour   :   "+list.get(i).getColorv());
                kms.setText("Kilometers travelled   :   "+list.get(i).getNokms());
                dop.setText("Date Of Purchase  :  "+list.get(i).getDop());
                psd.setText("Last Serviced Date   :   "+list.get(i).getPsd());
                head.setText(list.get(i).getVehicleno());
                fuelused.setText("Fuel Used   :   "+list.get(i).getFuel());
                progressBar.setVisibility(View.VISIBLE);
                Glide.with(VehicleDisplayActivity.this).load(list.get(i).getVehicleimage()).into(vehimage);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),VehicleFillingActivity.class) ;
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("item",i) ;
                startActivity(intent);
            }
        });





    }
}