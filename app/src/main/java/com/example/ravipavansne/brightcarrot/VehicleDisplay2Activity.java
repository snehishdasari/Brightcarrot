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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VehicleDisplay2Activity extends AppCompatActivity {

    private int i;
    private String categ ;
    private TextView head ;
    private TextView name ;
    private TextView color ;
    private TextView kms ;
    private TextView dop ;
    private TextView psd ;
    private TextView fuelused ;
    private TextView price;
    private TextView from ;
    private TextView to ;
    private TextView addr ;
    private TextView numb ;
    private TextView owner ;
    private Button booked ;
    private FirebaseUser firebaseUser ;
    private ImageView vehimage ;
    private ImageView rcimage ;

    private DatabaseReference databaseReference ;

    public static List<VehicleDetails> list ;
    public static List<VehicleDetails> list2 ;
    private CircleImageView b ;
    private ProgressBar progressBar;
    private ProgressBar progressBar2 ;
    private  String vid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_display2);
        Intent intent = getIntent() ;
        i = intent.getIntExtra("item",0) ;
        categ = intent.getStringExtra("categ") ;
        b=(CircleImageView)findViewById(R.id.backvehicledisplay2);
        name = (TextView) findViewById(R.id.namevehdisp2) ;
        color = (TextView) findViewById(R.id.colorvehdisp2) ;
        kms = (TextView) findViewById(R.id.kmsvehdisp2) ;
        dop = (TextView) findViewById(R.id.dopvehdisp2) ;
        psd = (TextView) findViewById(R.id.psdvehdisp2) ;
        fuelused = (TextView) findViewById(R.id.fuelvehdisp2) ;
        vehimage = (ImageView) findViewById(R.id.imvehdisp2) ;
        price = (TextView) findViewById(R.id.pricevehdisp2) ;
        from = (TextView) findViewById(R.id.fromvehdisp2) ;
        to = (TextView)findViewById(R.id.tillvehdisp2) ;
        addr = (TextView)findViewById(R.id.adrsvehdisp2) ;
        numb = (TextView)findViewById(R.id.phonevehdisp2) ;
        booked = (Button) findViewById(R.id.bookthisvehicle) ;
        owner = (TextView)findViewById(R.id.ownervehdisp2) ;
        rcimage = (ImageView) findViewById(R.id.rcimagesvehdisp2) ;
        progressBar = (ProgressBar)findViewById(R.id.vehimgprogbar2);
        progressBar2 = (ProgressBar) findViewById(R.id.rcimgpbvehdisp2) ;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        list = new ArrayList<VehicleDetails>() ;
        list2 = new ArrayList<VehicleDetails>() ;
        head = (TextView) findViewById(R.id.vehicledisplay2heading) ;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home2Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Available Vehicles").child(categ) ;
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
                    Glide.with(VehicleDisplay2Activity.this).load(list.get(i).getVehicleimage()).into(vehimage);
                    progressBar.setVisibility(View.GONE);
                    price.setText("Price   :   "+list.get(i).getPrice());
                    from.setText("Available from   :   "+list.get(i).getStartday() +" "+list.get(i).getStarttime());
                    to.setText("Available till   :   "+list.get(i).getEndday() +" "+list.get(i).getEndtime());
                    addr.setText("Available at  : "+list.get(i).getContactaddress());
                    numb.setText("Contact Details   : "+list.get(i).getContactphone());
                    owner.setText("Owner name  :  "+list.get(i).getOwnername()) ;
                    progressBar2.setVisibility(View.VISIBLE);
                    Glide.with(VehicleDisplay2Activity.this).load(list.get(i).getRc()).into(rcimage) ;
                    progressBar2.setVisibility(View.GONE);





                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            booked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list2.clear();
                            for(DataSnapshot d : dataSnapshot.getChildren())
                            {
                                VehicleDetails v = d.getValue(VehicleDetails.class) ;
                                list2.add(v) ;
                            }
                             vid = list2.get(i).getVehicleid() ;
                            VehicleDetails k = list2.get(i) ;
                            k.setBookedby(firebaseUser.getUid());
                            DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Booked Vehicles").child(list2.get(i).getType()).child(vid) ;
                            db.setValue(k).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        startActivity(new Intent(VehicleDisplay2Activity.this,Home2Activity.class));
                                        databaseReference.child(vid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(getApplicationContext(), "bla", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            });

    }
}
