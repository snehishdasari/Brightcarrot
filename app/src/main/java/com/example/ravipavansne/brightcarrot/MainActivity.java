package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager ;
    private LinearLayout linearLayout ;
    private slideradapter slideradapter1 ;
    private Button signbutton ;
    private Button loginbutton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.slide1) ;
        linearLayout = (LinearLayout) findViewById(R.id.sign) ;
        slideradapter1 = new slideradapter(this) ;
        viewPager.setAdapter(slideradapter1) ;

        signbutton = (Button) findViewById(R.id.signbutton) ;
        loginbutton = (Button) findViewById(R.id.loginbutton) ;
        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignupActivity.class));
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }


}
