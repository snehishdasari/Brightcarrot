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

import com.aminography.redirectglide.GlideApp;
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
    private TextView pickup;
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
    private TextView chat ;
    private TextView vehlatlng;
    private String oname ;
    private String myname ;
    private String vid ;
    private DatabaseReference databaseReference ;

    private List<VehicleDetails> list ;
    private List<VehicleDetails> list2 ;
    private CircleImageView b ;
    private ProgressBar progressBar;
    private ProgressBar progressBar2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_display2);
        Intent intent = getIntent() ;
        int index = intent.getIntExtra("item",-1);
        b=(CircleImageView)findViewById(R.id.backvehicledisplay2);
        name = (TextView) findViewById(R.id.namevehdisp2) ;
        color = (TextView) findViewById(R.id.colorvehdisp2) ;
        kms = (TextView) findViewById(R.id.kmsvehdisp2) ;
        dop = (TextView) findViewById(R.id.dopvehdisp2) ;
        psd = (TextView) findViewById(R.id.psdvehdisp2) ;
        vehlatlng = (TextView)findViewById(R.id.vehlalng1);
        pickup = (TextView)findViewById(R.id.pickuplocation);
        fuelused = (TextView) findViewById(R.id.fuelvehdisp2) ;
        vehimage = (ImageView) findViewById(R.id.imvehdisp2) ;
        price = (TextView) findViewById(R.id.pricevehdisp2) ;
        from = (TextView) findViewById(R.id.fromvehdisp2) ;
        to = (TextView)findViewById(R.id.tillvehdisp2) ;
        addr = (TextView)findViewById(R.id.adrsvehdisp2) ;
        numb = (TextView)findViewById(R.id.phonevehdisp2) ;
        booked = (Button) findViewById(R.id.bookthisvehicle) ;
        owner = (TextView)findViewById(R.id.ownervehdisp2) ;
        chat = (TextView) findViewById(R.id.chatvehdisp2) ;
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
        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapBoxActivity.class));
            }
        });
        vehimage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        rcimage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String n = VehicleAdapter.displayvehicle.getVehiclename() ;
        vid = VehicleAdapter.displayvehicle.getVehicleid() ;
        name.setText("Name   :   "+n);
        color.setText("Colour   :   "+VehicleAdapter.displayvehicle.getColorv());
        kms.setText("Kilometers travelled   :   "+VehicleAdapter.displayvehicle.getNokms());
        dop.setText("Date Of Purchase  :  "+VehicleAdapter.displayvehicle.getDop());
        psd.setText("Last Serviced Date   :   "+VehicleAdapter.displayvehicle.getPsd());
        head.setText(VehicleAdapter.displayvehicle.getVehicleno());
        fuelused.setText("Fuel Used   :   "+VehicleAdapter.displayvehicle.getFuel());
        progressBar.setVisibility(View.VISIBLE);
        GlideApp.with(VehicleDisplay2Activity.this).load(VehicleAdapter.displayvehicle.getVehicleimage()).into(vehimage);
        progressBar.setVisibility(View.GONE);
        price.setText("Price   :   "+VehicleAdapter.displayvehicle.getPrice());
        vehlatlng.setText(VehicleAdapter.displayvehicle.getLatitude().toString()+" , "+VehicleAdapter.displayvehicle.getLongitude().toString());
        from.setText("Available from   :   "+VehicleAdapter.displayvehicle.getStartday() +" "+VehicleAdapter.displayvehicle.getStarttime());
        to.setText("Available till   :   "+VehicleAdapter.displayvehicle.getEndday() +" "+VehicleAdapter.displayvehicle.getEndtime());
        addr.setText("Available at  : "+VehicleAdapter.displayvehicle.getContactaddress());
        numb.setText("Contact Details   : "+VehicleAdapter.displayvehicle.getContactphone());
        owner.setText("Owner name  :  "+VehicleAdapter.displayvehicle.getOwnername()) ;
        progressBar2.setVisibility(View.VISIBLE);
        GlideApp.with(VehicleDisplay2Activity.this).load(VehicleAdapter.displayvehicle.getRc()).into(rcimage) ;
        progressBar2.setVisibility(View.GONE);
        if(index==0)
            booked.setVisibility(View.GONE);
        booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(VehicleAdapter.displayvehicle.getOwnerid().equals(firebaseUser.getUid())){
                    Toast.makeText(VehicleDisplay2Activity.this, "You cannot book your own vehicle", Toast.LENGTH_SHORT).show();
                }
                else {
                    String category = VehicleAdapter.displayvehicle.getType();

                    VehicleAdapter.displayvehicle.setBooked("true");
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Available Vehicles").child(category).child(vid).removeValue();
                    VehicleAdapter.displayvehicle.setBookedby(firebaseUser.getUid());
                    databaseReference.child("Users").child(VehicleAdapter.displayvehicle.getOwnerid()).child("Booking Details")
                            .child("My Rentals").child(vid).setValue(VehicleAdapter.displayvehicle);

                    databaseReference.child("Users").child(firebaseUser.getUid()).child("Booking Details").child("My Bookings")
                            .child(vid).setValue(VehicleAdapter.displayvehicle).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(VehicleDisplay2Activity.this, "Vehicle Successfully booked", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(VehicleDisplay2Activity.this,Bookings.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }
                        }
                    });


                }

            }
        });



        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseReference databaseReferenc = FirebaseDatabase.getInstance().getReference().child("Users").child(VehicleAdapter.displayvehicle.getOwnerid()).child("Account details")
                        ;

                databaseReferenc.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        oname  = dataSnapshot.child("firstname").getValue().toString() ;


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }) ;

                DatabaseReference dm  = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("Account details").child("firstname") ;
                dm.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        myname = dataSnapshot.getValue().toString() ;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                Intent chatIntent  = new Intent(VehicleDisplay2Activity.this,ChatActivity.class) ;
                chatIntent.putExtra("ownername",oname) ;
                chatIntent.putExtra("myname",myname) ;

                chatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(chatIntent);
                finish();

                                    }
        });

    }
}
