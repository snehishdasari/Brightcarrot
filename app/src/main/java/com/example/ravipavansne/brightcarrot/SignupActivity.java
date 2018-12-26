package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {


    private Spinner day ;
    private Spinner month ;
    private Spinner year ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        day=(Spinner) findViewById(R.id.spinnerday) ;
        month = (Spinner) findViewById(R.id.spinnermonth) ;
        year = (Spinner) findViewById(R.id.spinneryear) ;

        ArrayList<String> days = new ArrayList<>() ;
        for(int i=1;i<32;i++)
        {
            String k = String.valueOf(i) ;
            days.add(k) ;
        }

        ArrayList<String> months = new ArrayList<>() ;
        months.add("January") ;
        months.add("February") ;
        months.add("March") ;
        months.add("April") ;
        months.add("May") ;
        months.add("June") ;
        months.add("July") ;
        months.add("August") ;
        months.add("September") ;months.add("October") ;
        months.add("November") ;
        months.add("December") ;

        ArrayList<String> years = new ArrayList<>() ;
        for(int i=2000;i>1949;i--)
        {
            String k = String.valueOf(i) ;
            years.add(k) ;
        }


        ArrayAdapter<String> dayadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,days) ;
        ArrayAdapter<String> monthadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,months) ;
        ArrayAdapter<String> yearadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,years) ;


        day.setAdapter(dayadapter);
        month.setAdapter(monthadapter);
        year.setAdapter(yearadapter) ;

        Button butt = (Button)findViewById(R.id.next);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Home2Activity.class));

            }
        });








    }
}
