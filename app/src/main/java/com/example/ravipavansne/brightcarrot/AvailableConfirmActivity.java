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
import android.widget.TextView;
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
    private ArrayAdapter<String> houradapter1 ;
    private ArrayAdapter<String> minadapter1 ;
    private DatabaseReference databaseReference ;
    private FirebaseUser firebaseUser ;
    private ArrayList<VehicleDetails> list ;
    public static VehicleDetails u ;
    private int  f ;
    private String p ;
    private String sday ;
    private String eday ;
    private String adrs ;
    private String contact ;
    private String stime ;
    private String etime ;
    private String ownername ;
    private DatabaseReference databaseReference1;
    public static boolean mapflag;
    public static Vehicles dummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_confirm);

        mapflag = false;
        final Intent intent = getIntent();
        f = intent.getIntExtra("item", -1);

        pricetil = (TextInputLayout) findViewById(R.id.priceconfirm);
        shour = (Spinner) findViewById(R.id.spinnershour);
        smin = (Spinner) findViewById(R.id.spinnersmin);
        sdate = (Spinner) findViewById(R.id.spinnersdate);
        smonth = (Spinner) findViewById(R.id.spinnersmonth);
        syear = (Spinner) findViewById(R.id.spinnersyear);
        ehour = (Spinner) findViewById(R.id.spinnerehour);
        emin = (Spinner) findViewById(R.id.spinneremin);
        edate = (Spinner) findViewById(R.id.spinneredate);
        emonth = (Spinner) findViewById(R.id.spinneremonth);
        eyear = (Spinner) findViewById(R.id.spinnereyear);
        phonetil = (TextInputLayout) findViewById(R.id.phoneconfirm);
        list = new ArrayList<>();
        cnfm = (Button) findViewById(R.id.confirm);


        days = new ArrayList<>();
        days.add("day");
        for (int i = 1; i < 32; i++) {
            String k = String.valueOf(i);
            days.add(k);
        }


        mins = new ArrayList<>();
        mins.add("min");
        for (int i = 0; i < 61; i += 5) {
            String k = String.valueOf(i);
            mins.add(k);
        }

        hours = new ArrayList<>();
        hours.add("hour");
        for (int i = 1; i < 25; i++) {
            String k = String.valueOf(i);
            hours.add(k);
        }

        months = new ArrayList<>();
        months.add("month");
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");


        years = new ArrayList<>();
        years.add("year");
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

        houradapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hours);
        minadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mins);

        houradapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hours);
        minadapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mins);

        sdate.setAdapter(dayadapter);
        edate.setAdapter(dayadapter1);
        smonth.setAdapter(monthadapter);
        emonth.setAdapter(monthadapter1);
        syear.setAdapter(yearadapter);
        eyear.setAdapter(yearadapter1);

        shour.setAdapter(houradapter);
        ehour.setAdapter(houradapter1);
        smin.setAdapter(minadapter);
        emin.setAdapter(minadapter1);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference().child("Users").child(MyVehicleAdapter.currvehicle.getOwnerid()).child("Account details");

        d1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ownername = dataSnapshot.child("firstname").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        cnfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                p = pricetil.getEditText().getText().toString();
                stime = shour.getSelectedItem().toString() + ":" + smin.getSelectedItem().toString();
                etime = ehour.getSelectedItem().toString() + ":" + emin.getSelectedItem().toString();
                sday = sdate.getSelectedItem().toString() + "/" + smonth.getSelectedItem().toString() + "/" + syear.getSelectedItem().toString();
                eday = edate.getSelectedItem().toString() + "/" + emonth.getSelectedItem().toString() + "/" + eyear.getSelectedItem().toString();
                //       adrs= addrtil.getEditText().getText().toString() ;

                contact = phonetil.getEditText().getText().toString();
                MyVehicleAdapter.currvehicle.setPrice(p);
                MyVehicleAdapter.currvehicle.setStartday(sday);
                MyVehicleAdapter.currvehicle.setStarttime(stime);
                MyVehicleAdapter.currvehicle.setEndday(eday);
                MyVehicleAdapter.currvehicle.setEndtime(etime);
                MyVehicleAdapter.currvehicle.setContactaddress(adrs);
                MyVehicleAdapter.currvehicle.setContactphone(contact);
                MyVehicleAdapter.currvehicle.setAvailability("true");
                MyVehicleAdapter.currvehicle.setOwnername(ownername);
                MyVehicleAdapter.currvehicle.setBooked("false");
                MyVehicleAdapter.currvehicle.setContactaddress("shiy");
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));

               /* MyVehicleAdapter.currvehicle.setContactaddress(vehaddress);
                MyVehicleAdapter.currvehicle.setLatitude(vehlat);
                MyVehicleAdapter.currvehicle.setLongitude(vehlong);*/

            }
        });




    }

}
