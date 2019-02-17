package com.example.ravipavansne.brightcarrot;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class StartActivity extends AppCompatActivity {
    private TabLayout tabLayout ;
    private ViewPager viewPager ;
    private SectionPagerAdapter3 sectionPagerAdapter3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sltoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BrightCarrot");

        tabLayout = (TabLayout)findViewById(R.id.sl_tabs);
        viewPager = (ViewPager)findViewById(R.id.sl_pager);
        sectionPagerAdapter3  = new SectionPagerAdapter3(getSupportFragmentManager());
        viewPager.setAdapter(sectionPagerAdapter3);
        tabLayout.setupWithViewPager(viewPager);

    }
}
