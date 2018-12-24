package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout ;
    private ActionBarDrawerToggle mToggle ;
    private NavigationView navigationView ;
    private BottomNavigationView bottomNavigationView ;
    private FrameLayout frameLayout ;
    private CarsFragment carsFragment ;
    private  BikesFragment bikesFragment ;
    private HeavyVehiclesFragment heavyVehiclesFragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer) ;
        mToggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.nav_view) ;
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnav) ;
        frameLayout = (FrameLayout) findViewById(R.id.nav_frame) ;
        carsFragment= new CarsFragment() ;
        bikesFragment = new BikesFragment() ;
        heavyVehiclesFragment = new HeavyVehiclesFragment() ;
        setFragment(carsFragment) ;

       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.cars :
                        setFragment(carsFragment);
                        return true ;
                    case R.id.bikes :
                        setFragment(bikesFragment);
                        return true ;
                    case R.id.heavyvehicles :
                        setFragment(heavyVehiclesFragment);
                        return true ;
                    default:
                        return false ;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction() ;
        fragmentTransaction.replace(R.id.nav_frame,fragment) ;
        fragmentTransaction.commit() ;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true ;
        }
        return super.onOptionsItemSelected(item) ;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch(menuItem.getItemId())
        {
            case R.id.myaccount :
                startActivity(new Intent(getBaseContext(),MyaccountActivity.class));
            case R.id.previousrides :
                startActivity(new Intent(getApplication(),PreviousridesActivity.class));
            case R.id.upcomingrides :
                startActivity(new Intent(getApplicationContext(),UpcomingridesActivity.class));
            case R.id.myvehicles :
                startActivity(new Intent(HomeActivity.this,MyvehiclesActivity.class));
            case R.id.logout :
                startActivity(new Intent(this,SignupActivity.class));


        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer) ;
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}
