package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Bookings extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionsPagerAdapter2 sectionsPagerAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        tabLayout = (TabLayout)findViewById(R.id.booking_tabs);
        viewPager = (ViewPager)findViewById(R.id.booking_pager);
        sectionsPagerAdapter2  = new SectionsPagerAdapter2(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter2);
        tabLayout.setupWithViewPager(viewPager);
        ImageView img = (ImageView)findViewById(R.id.bookingback);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home2Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}
