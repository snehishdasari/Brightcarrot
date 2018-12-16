package com.example.ravipavansne.brightcarrot;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager ;
    private LinearLayout linearLayout ;
    private slideradapter slideradapter1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.slide1) ;
        linearLayout = (LinearLayout) findViewById(R.id.sign) ;
        slideradapter1 = new slideradapter(this) ;
        viewPager.setAdapter(slideradapter1) ;

    }
}
