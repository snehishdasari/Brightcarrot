package com.example.ravipavansne.brightcarrot;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.Signature ;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Connection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotlayout;
    private slideradapter slideradapter1;
    private Button skipbutton;
    private TextView []dots ;
    private int []layouts ;
    private Button nextbutton;
    private FirebaseUser fuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (fuser != null) {
            {
                Intent i = new Intent(MainActivity.this, Home2Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }

        }


        viewPager = (ViewPager) findViewById(R.id.pager);
        dotlayout = (LinearLayout) findViewById(R.id.dotLayout);
        layouts = new int[]{R.layout.slider_cars,R.layout.slider_bikes,R.layout.slider_hv} ;
        slideradapter1 = new slideradapter(layouts,getApplicationContext());
        viewPager.setAdapter(slideradapter1);

       // printhashkey();
        skipbutton = (Button) findViewById(R.id.skipbutton);
        nextbutton = (Button) findViewById(R.id.nextbutton);


        setStatusTransparent();
        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, signup2Activity.class));
            }
        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        setDotStatus(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==layouts.length-1)
                {
                    nextbutton.setText("start");
                    skipbutton.setVisibility(View.GONE);
                }
                else
                {
                    nextbutton.setText("next");
                    skipbutton.setVisibility(View.VISIBLE);
                }
                setDotStatus(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    private void setDotStatus(int page)
    {
        dotlayout.removeAllViews();
        dots  = new TextView[layouts.length] ;
        for(int i=0;i<dots.length;i++)
        {
            dots[i]= new TextView(this) ;
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30) ;
            dots[i].setTextColor(Color.parseColor("#a9b4bb"));
            dotlayout.addView(dots[i]);
        }
        if(dots.length>0)
        {
            dots[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }




    private void setStatusTransparent()
    {
        if(Build.VERSION.SDK_INT >=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow() ;
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) ;
            window.setStatusBarColor(Color.TRANSPARENT);

        }
    }

}