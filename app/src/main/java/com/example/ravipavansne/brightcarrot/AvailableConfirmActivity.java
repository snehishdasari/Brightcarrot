package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AvailableConfirmActivity extends AppCompatActivity {

    private TextInputLayout pricetil ;
    private Spinner shour ;
    private Spinner smin ;
    private Spinner sdate ;
    private Spinner smonth ;
    private Spinner syear ;
    private Spinner ehour ;
    private Spinner emin ;
    private Spinner edate ;
    private Spinner emonth ;
    private Spinner eyear ;
    private TextInputLayout addrtil ;
    private TextInputLayout phonetil ;
    private Button cnfm ;
    private ArrayList<String> months;
    private ArrayList<String> days;
    private ArrayList<String> years;
    private ArrayAdapter<String> dayadapter;
    private ArrayAdapter<String> monthadapter;
    private ArrayAdapter<String> yearadapter;
    private ArrayAdapter<String> dayadapter1;
    private ArrayAdapter<String> monthadapter1;
    private ArrayAdapter<String> yearadapter1;
    private ArrayList<String> hours ;
    private ArrayList<String> mins;
    private ArrayAdapter<String> houradapter ;
    private ArrayAdapter<String> minadapter ;
    private DatabaseReference databaseReference ;
    private FirebaseUser firebaseUser ;
    private ArrayList<VehicleDetails> list ;
    private VehicleDetails u ;
    private int  f ;
    private String p ;
    private String sday ;
    private String eday ;
    private String adrs ;
    private String contact ;
    private String stime ;
    private String etime ;
    private String ownername ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_confirm);
        Intent intent=getIntent();
         f = intent.getIntExtra("item",-1);

        pricetil = (TextInputLayout) findViewById(R.id.priceconfirm) ;
        shour= (Spinner) findViewById(R.id.spinnershour) ;
        smin= (Spinner) findViewById(R.id.spinnersmin) ;
        sdate= (Spinner) findViewById(R.id.spinnersdate) ;
        smonth= (Spinner) findViewById(R.id.spinnersmonth) ;
        syear= (Spinner) findViewById(R.id.spinnersyear) ;
        ehour= (Spinner) findViewById(R.id.spinnerehour) ;
        emin = (Spinner) findViewById(R.id.spinneremin) ;
        edate= (Spinner) findViewById(R.id.spinneredate) ;
        emonth= (Spinner) findViewById(R.id.spinneremonth) ;
        eyear= (Spinner) findViewById(R.id.spinnereyear) ;
        addrtil = (TextInputLayout)findViewById(R.id.addrconfirm);
        phonetil = (TextInputLayout)findViewById(R.id.phoneconfirm);
        list = new ArrayList<>() ;
        cnfm = (Button) findViewById(R.id.confirm) ;


        days = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            String k = String.valueOf(i);
            days.add(k);
        }


        mins = new ArrayList<>() ;
        for(int i=0;i<61;i+=5)
        {
            String k = String.valueOf(i) ;
            mins.add(k) ;
        }

        hours = new ArrayList<>() ;
        for(int i=1;i<25;i++)
        {
            String k = String.valueOf(i) ;
            hours.add(k) ;
        }

        months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");


        years = new ArrayList<>();
        for (int i = 2019; i > 1949; i--) {
            String k = String.valueOf(i);
            years.add(k);
        }


        dayadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        monthadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, months);
        yearadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);

        dayadapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        monthadapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, months);
        yearadapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);

        houradapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,hours) ;
        minadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,mins) ;

        sdate.setAdapter(dayadapter);
        edate.setAdapter(dayadapter1);
        smonth.setAdapter(monthadapter);
        emonth.setAdapter(monthadapter1);
        syear.setAdapter(yearadapter);
        eyear.setAdapter(yearadapter1);

        shour.setAdapter(houradapter);
        ehour.setAdapter(houradapter) ;
        smin.setAdapter(minadapter);
        emin.setAdapter(minadapter);





        cnfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                p = pricetil.getEditText().getText().toString() ;
                stime = shour.getSelectedItem().toString() + ":" + smin.getSelectedItem().toString() ;
                etime = ehour.getSelectedItem().toString() + ":" + emin.getSelectedItem().toString() ;
                sday = sdate.getSelectedItem().toString() + "/" + smonth.getSelectedItem().toString() + "/" + syear.getSelectedItem().toString() ;
                eday = edate.getSelectedItem().toString() + "/" + emonth.getSelectedItem().toString() + "/" + eyear.getSelectedItem().toString() ;
                adrs= addrtil.getEditText().getText().toString() ;
                contact = phonetil.getEditText().getText().toString() ;
                u = new VehicleDetails() ;


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
                        u = list.get(f) ;
                        u.setPrice(p);
                        u.setStartday(sday);
                        u.setStarttime(stime);
                        u.setEndday(eday);
                        u.setEndtime(etime);
                        u.setContactaddress(adrs);
                        u.setContactphone(contact);
                        u.setAvailability("true");
                        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference().child("Users").child(u.getOwnerid()).child("Account Details");
                        d1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                ownername = dataSnapshot.child("firstname").getValue().toString()  ;
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        u.setOwnername(ownername);

                        final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Available Vehicles").child(u.getVehicleid()) ;
                        databaseReference1.setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(AvailableConfirmActivity.this,"Done",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(AvailableConfirmActivity.this,"Not Done",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });




                        databaseReference1.child("ownername").setValue(ownername) ;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });






    }
}
