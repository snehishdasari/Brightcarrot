package com.example.ravipavansne.brightcarrot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class VehicleFillingActivity extends AppCompatActivity {

    private TextInputLayout nametil;
    private TextInputLayout notil;
    private TextInputLayout coltil;
    private Spinner category;
    private Spinner FuelUsed;
    private Spinner monthdop;
    private Spinner yeardop;
    private Spinner daydop;
    private TextInputLayout kmstil;
    private Spinner monthpsd;
    private Spinner yearpsd;
    private Spinner daypsd;
    private ImageView vehimagbtn;
    private ImageView rcimagbtn;
    private ArrayList<String> months;
    private ArrayList<String> days;
    private ArrayList<String> years;
    private ArrayAdapter<String> dayadapter;
    private ArrayAdapter<String> monthadapter;
    private ArrayAdapter<String> yearadapter;
    private ArrayAdapter<String> dayadapter1;
    private ArrayAdapter<String> monthadapter1;
    private ArrayAdapter<String> yearadapter1;
    private ArrayList<String> categories;
    private ArrayList<String> fuels;
    private ArrayAdapter<String> categoryadapter;
    private ArrayAdapter<String> fueladapter;
    private android.widget.TextView savebtn;
    private static final int GALLERY_PICK = 1;
    private static final int GALLERY_PICK1 = 1 ;
    private StorageReference vehicleImage;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser ;
    private VehicleDetails vehicleDetails ;
    private String imgveh ;
    private String imgrc ;
    private ProgressDialog pd;
    private int flag;
    private CircleImageView back;
    private String vid;
    private boolean switchimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_filling);
        nametil = (TextInputLayout) findViewById(R.id.namevehfill);
        notil = (TextInputLayout) findViewById(R.id.novehfill);
        coltil = (TextInputLayout) findViewById(R.id.colvehfill);
        kmstil = (TextInputLayout) findViewById(R.id.kmsvehfill);
        vehimagbtn = (ImageView) findViewById(R.id.vehimages);
        rcimagbtn = (ImageView) findViewById(R.id.rcimages);
        category = (Spinner) findViewById(R.id.spinnercategory);
        FuelUsed = (Spinner) findViewById(R.id.spinnerfuel);
        monthdop = (Spinner) findViewById(R.id.spinnermonthdop);
        yeardop = (Spinner) findViewById(R.id.spinneryeardop);
        daydop = (Spinner) findViewById(R.id.spinnerdaydop);
        monthpsd = (Spinner) findViewById(R.id.spinnermonthpsd);
        yearpsd = (Spinner) findViewById(R.id.spinneryearpsd);
        daypsd = (Spinner) findViewById(R.id.spinnerdaypsd);
        savebtn = (android.widget.TextView) findViewById(R.id.savevehfill);
        vehicleImage = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        back = (CircleImageView)findViewById(R.id.backvehiclefilling);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyvehiclesActivity.class));
            }
        });
        days = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            String k = String.valueOf(i);
            days.add(k);
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


        daypsd.setAdapter(dayadapter);
        monthpsd.setAdapter(monthadapter);
        yearpsd.setAdapter(yearadapter);
        daydop.setAdapter(dayadapter1);
        monthdop.setAdapter(monthadapter1);
        yeardop.setAdapter(yearadapter1);


        categories = new ArrayList<>();
        categories.add("Type");
        categories.add("Car");
        categories.add("Bike");
        categories.add("HeavyVehicle");

        fuels = new ArrayList<>();
        fuels.add("Fuel");
        fuels.add("Petrol");
        fuels.add("Diesel");
        fuels.add("CNG");
        fuels.add("LPG");
        fuels.add("Electricity");


        categoryadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        fueladapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fuels);

        category.setAdapter(categoryadapter);
        FuelUsed.setAdapter(fueladapter);

        Intent intent = getIntent();
         flag = intent.getIntExtra("item",-1);
        if(flag!=-1)
        {
            pd = new ProgressDialog(getApplicationContext());
            pd.setTitle("Adding vehicle details ");
            pd.setMessage("Please wait");
            pd.setCanceledOnTouchOutside(false);
            DatabaseReference databaseReference2 =  databaseReference.child("Users").child(firebaseUser.getUid()).child("Vehicle Details").child(VehicleDisplayActivity.list.get(flag).getVehicleid());
            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    nametil.getEditText().setText(dataSnapshot.child("vehiclename").getValue().toString());
                    notil.getEditText().setText(dataSnapshot.child("vehicleno").getValue().toString());
                    coltil.getEditText().setText(dataSnapshot.child("colorv").getValue().toString());
                    kmstil.getEditText().setText(dataSnapshot.child("vehiclename").getValue().toString());
                    category.setSelection(categoryadapter.getPosition(dataSnapshot.child("type").getValue().toString()));
                    FuelUsed.setSelection(fueladapter.getPosition(dataSnapshot.child("fuel").getValue().toString()));
                    String dop[] = (dataSnapshot.child("dop").getValue().toString()).split("/");
                    monthdop.setSelection(monthadapter1.getPosition(dop[1]));
                    yeardop.setSelection(yearadapter1.getPosition(dop[2]));
                    daydop.setSelection(dayadapter1.getPosition(dop[0]));
                    String psd[] = (dataSnapshot.child("psd").getValue().toString()).split("/");
                    monthpsd.setSelection(monthadapter.getPosition(psd[1]));
                    daypsd.setSelection(dayadapter.getPosition(psd[0]));
                    yearpsd.setSelection(yearadapter.getPosition(psd[2]));
                    String a = VehicleDisplayActivity.list.get(flag).getVehicleimage();
                    String b = VehicleDisplayActivity.list.get(flag).getRc();
                    Picasso.with(VehicleFillingActivity.this).load(a).into(vehimagbtn);
                    Picasso.with(VehicleFillingActivity.this).load(b).into(rcimagbtn);
                    imgveh = a;
                    imgrc = b;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            pd = new ProgressDialog(getApplicationContext());
            pd.setTitle("Editing vehicle details ");
            pd.setMessage("Please wait");
            pd.setCanceledOnTouchOutside(false);
        }
        vehimagbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);
                switchimage = true;
            }
        });

        rcimagbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK1);
                switchimage = false;

            }
        });


         vid = "";
        if(flag==-1){
            DatabaseReference databaseReference1 =  databaseReference.child("Users").child(firebaseUser.getUid()).child("Vehicle Details").push() ;
            vid = databaseReference1.getKey() ;}
        else{
            vid = VehicleDisplayActivity.list.get(flag).getVehicleid();
        }


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();
                String nameov = nametil.getEditText().getText().toString();
                String nov = notil.getEditText().getText().toString();
                String cov = coltil.getEditText().getText().toString();
                String categ = category.getSelectedItem().toString();
                String fu = FuelUsed.getSelectedItem().toString();
                String ddop = daydop.getSelectedItem().toString();
                String mdop = monthdop.getSelectedItem().toString();
                String ydop = yeardop.getSelectedItem().toString();
                String kms = kmstil.getEditText().getText().toString();
                String dpsd = daypsd.getSelectedItem().toString();
                String mpsd = monthpsd.getSelectedItem().toString();
                String ypsd = yearpsd.getSelectedItem().toString();
                String id = firebaseUser.getUid() ;


                    vehicleDetails = new VehicleDetails(nameov,imgveh,nov,vid,id,imgrc,ddop+"/"+mdop+"/"+ydop,categ,kms,dpsd+"/"+mpsd+"/"+ypsd,cov,fu,"5") ;

                    databaseReference.child("Users").child(id).child("Vehicle Details").child(vid).setValue(vehicleDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {

                                Toast.makeText(VehicleFillingActivity.this,"Save Successful",Toast.LENGTH_LONG).show() ;
                                Intent i = new Intent(getApplicationContext(),MyvehiclesActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                                pd.dismiss();

                            }
                            else
                            {
                                pd.hide();
                                Toast.makeText(VehicleFillingActivity.this," not Successful",Toast.LENGTH_LONG).show() ;
                            }
                        }
                    });

                }
               /* if(categ=="Bike"){

                    DatabaseReference databaseReference1 = databaseReference.child("Vehicles").child("Bikes").push() ;
                    String vid = databaseReference1.getKey() ;
                    vehicleDetails = new VehicleDetails(nameov,"s",nov,vid,id,"s",ddop+"/"+mdop+"/"+ydop,kms,dpsd+"/"+mpsd+"/"+ypsd,cov,fu,"5") ;
                    databaseReference.child("Vehicles").child("Bikes").child(vid).setValue(vehicleDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(VehicleFillingActivity.this,"Successful",Toast.LENGTH_LONG).show() ;
                            }
                        }
                    });
                }
                if(categ=="HeavyVehicle"){

                    databaseReference.child("Vehicles").child("HeavyVehicles").push() ;
                    String vid = databaseReference.getKey() ;
                    vehicleDetails = new VehicleDetails(nameov,"s",nov,vid,id,"s",ddop+"/"+mdop+"/"+ydop,kms,dpsd+"/"+mpsd+"/"+ypsd,cov,fu,"5") ;
                    databaseReference.child(vid).setValue(vehicleDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(VehicleFillingActivity.this,"Successful",Toast.LENGTH_LONG).show() ;
                            }
                        }
                    });
                }
            }*/
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();



                if(switchimage){
                String id = firebaseUser.getUid();

                StorageReference filePath = vehicleImage.child("Vehicle_images").child(id).child(vid + "vehicleimage.jpg");
                filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Picasso.with(VehicleFillingActivity.this).load(imageUri.toString()).into(vehimagbtn);
                        Toast.makeText(VehicleFillingActivity.this,"Success veh",Toast.LENGTH_LONG).show();

                    }
                });

                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imgveh = uri.toString() ;
                    }
                });

                   // Picasso.with(VehicleFillingActivity.this).load(imgveh).into(vehimagbtn);

                }
                else{

                    String id = firebaseUser.getUid();
                    StorageReference filePath1 = vehicleImage.child("RCimages").child(id).child(vid + "rcimage.jpg");
                    filePath1.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Picasso.with(VehicleFillingActivity.this).load(imageUri.toString()).into(rcimagbtn);
                            Toast.makeText(VehicleFillingActivity.this,"Success rc",Toast.LENGTH_LONG).show();

                        }
                    });

                    filePath1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imgrc = uri.toString() ;
                        }
                    });
                    Picasso.with(VehicleFillingActivity.this).load(imgrc).into(rcimagbtn);
                }

            }

        }

    public static String random()
    {
        Random gen = new Random() ;
        StringBuilder randomStringBuilder = new StringBuilder() ;
        int randomLength = gen.nextInt(10) ;
        char tempChar ;
        for(int i=0;i<randomLength;i++)
        {
            tempChar = (char) (gen.nextInt(96)+32) ;
            randomStringBuilder.append(tempChar) ;
        }
        return randomStringBuilder.toString() ;
    }

    }
