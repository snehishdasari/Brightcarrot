package com.example.ravipavansne.brightcarrot;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Home2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SectionsPagerAdapter sectionsPagerAdapter ;
    private ViewPager viewPager ;
    private TabLayout tabLayout ;
    private FirebaseUser fuser;
    private userdetails u1;
    private String oldpass;
    String flag;
    String nameuser ;
    String emailuser ;
    int x ;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private ProgressDialog pd;
    private BankDetails bankDetails;
    private TextView hellouser ;
    private TextView email ;
    private TextInputLayout t1;
    private TextInputLayout t3;
    private TextInputLayout t2;
    private TextInputLayout t4;
    private Button banksave;
    private Button bankedit;
    public boolean isServicesOk(){

        int availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Home2Activity.this);
        if(availability==ConnectionResult.SUCCESS)
            return true;
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(availability))
        {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Home2Activity.this,availability,9001);

        }
        else
        {
            Toast.makeText(this, "unable to make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(fuser.getUid()).child("Account details");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home2Activity.this,MyvehiclesActivity.class));
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         viewPager = (ViewPager) findViewById(R.id.tab_pager);;
        sectionsPagerAdapter= new SectionsPagerAdapter(getSupportFragmentManager()) ;
        viewPager.setAdapter(sectionsPagerAdapter) ;

        tabLayout = (TabLayout) findViewById(R.id.main_tabs) ;
        tabLayout.setupWithViewPager(viewPager);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0) ;
        hellouser = (TextView) headerview.findViewById(R.id.hellouser) ;
        email = (TextView) headerview.findViewById(R.id.emailshow) ;

        pd = new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.setTitle("Checking user details");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                flag = dataSnapshot.child("flag").getValue().toString();
                if(!(flag.equals("true")))
                {
                    Intent i = new Intent(getApplicationContext(),SignupActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
                else {
                    nameuser = dataSnapshot.child("firstname").getValue().toString() ;
                    emailuser = dataSnapshot.child("email").getValue().toString() ;
                    hellouser.setText(nameuser);
                    email.setText(emailuser);
                }

                pd.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /*DatabaseReference dr1 = FirebaseDatabase.getInstance().getReference().child("Users").child(fuser.getUid()).child("Account details") ;
        dr1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                nameuser = dataSnapshot.child("firstname").getValue().toString() ;
                emailuser = dataSnapshot.child("email").getValue().toString() ;
                hellouser.setText(nameuser);
                email.setText(emailuser);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sortbyavailabilty) {
            return true;
        }
        else if(id== R.id.sortbyloc)
        {
            return true ;
        }
        else if(id==R.id.sortbyprice)
        {
            return true ;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.myaccount_nav) {
            startActivity(new Intent(Home2Activity.this,MyaccountActivity.class)) ;
        }
        else if(id == R.id.changepass_nav){

            AlertDialog.Builder mbuilder = new AlertDialog.Builder(Home2Activity.this);
            View mview = getLayoutInflater().inflate(R.layout.changepassword,null);
            final TextInputLayout t1 = (TextInputLayout) mview.findViewById(R.id.currpass);
            final TextInputLayout t2 = (TextInputLayout) mview.findViewById(R.id.newpass);
            final TextInputLayout t3 = (TextInputLayout) mview.findViewById(R.id.conpass);
            ImageView imageView = (ImageView) mview.findViewById(R.id.closechangepass);

            Button change = (Button) mview.findViewById(R.id.change);

             databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(fuser.getUid()).child("Account details");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    oldpass = dataSnapshot.child("password").getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  String currpass = t1.getEditText().getText().toString();
                    final String newpass = t2.getEditText().getText().toString();
                    String conpass = t3.getEditText().getText().toString();
                    progressDialog = new ProgressDialog(Home2Activity.this);
                    progressDialog.setTitle("Changing Password");
                    progressDialog.setMessage("Please wait while we change your password");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                if(currpass.isEmpty() || newpass.isEmpty() || conpass.isEmpty())
                {
                    progressDialog.hide();
                    Toast.makeText(Home2Activity.this, "Fields empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(currpass.equals(oldpass))){
                    progressDialog.hide();
                        Toast.makeText(Home2Activity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                else if(!(newpass.equals(conpass)))
                {
                    progressDialog.hide();
                    Toast.makeText(Home2Activity.this, "passwords don't nmatch", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    fuser.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    databaseReference.child("password").setValue(newpass);
                                    progressDialog.dismiss();
                                }

                        }
                    });
                }
                }

                });
                mbuilder.setView(mview);
                final AlertDialog dialog = mbuilder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            }
            else if(id==R.id.currbookings_nav){

            startActivity(new Intent(Home2Activity.this,Bookings.class));

        }
        else if(id== R.id.previousrides_nav)
        {
            startActivity(new Intent(Home2Activity.this,PreviousridesActivity.class));
        }
        else if(id== R.id.myvehicles_nav)
        {
            startActivity(new Intent(Home2Activity.this,MyvehiclesActivity.class));
        }
        else if(id==R.id.bank_nav){

        startActivity(new Intent(Home2Activity.this,BankDetails2.class));


        }
        else if(id== R.id.logout_nav)
        {

            FirebaseAuth.getInstance().signOut();
           Intent i = new Intent(Home2Activity.this,MainActivity.class);
           i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(i);
           finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
