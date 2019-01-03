package com.example.ravipavansne.brightcarrot;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class VehicleFillingActivity extends AppCompatActivity {

    private TextInputLayout nametil ;
    private TextInputLayout notil ;
    private TextInputLayout coltil ;
    private Spinner category ;
    private Spinner FuelUsed ;
    private Spinner monthdop ;
    private Spinner yeardop ;
    private Spinner daydop ;
    private TextInputLayout kmstil ;
    private Spinner monthpsd ;
    private Spinner yearpsd ;
    private Spinner daypsd ;
    private Button vehimagbtn ;
    private Button rcimagbtn ;
    private ArrayList<String> months;
    private ArrayList<String> days;
    private ArrayList<String> years;
    private ArrayAdapter<String> dayadapter;
    private ArrayAdapter<String> monthadapter;
    private ArrayAdapter<String> yearadapter;
    private ArrayList<String> categories ;
    private ArrayList<String> fuels ;
    private ArrayAdapter<String> categoryadapter ;
    private ArrayAdapter<String> fueladapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_filling);
        nametil = (TextInputLayout) findViewById(R.id.namevehfill) ;
        notil = (TextInputLayout) findViewById(R.id.novehfill) ;
        coltil = (TextInputLayout) findViewById(R.id.colvehfill) ;
        kmstil = (TextInputLayout) findViewById(R.id.kmsvehfill) ;
        vehimagbtn =  (Button) findViewById(R.id.vehimages) ;
        rcimagbtn = (Button) findViewById(R.id.rcimages) ;
        category = (Spinner) findViewById(R.id.spinnercategory) ;
        FuelUsed = (Spinner) findViewById(R.id.spinnerfuel) ;
        monthdop = (Spinner) findViewById(R.id.spinnermonthdop) ;
        yeardop = (Spinner) findViewById(R.id.spinneryeardop) ;
        daydop = (Spinner) findViewById(R.id.spinnerdaydop) ;
        monthpsd = (Spinner) findViewById(R.id.spinnermonthpsd) ;
        yearpsd = (Spinner)findViewById(R.id.spinneryearpsd) ;
        daypsd = (Spinner)findViewById(R.id.spinnerdaypsd) ;



        days = new ArrayList<>() ;
        for(int i=1;i<32;i++)
        {
            String k = String.valueOf(i) ;
            days.add(k) ;
        }

        months = new ArrayList<>() ;
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



        years = new ArrayList<>() ;
        for(int i=2000;i>1949;i--)
        {
            String k = String.valueOf(i) ;
            years.add(k) ;
        }


        dayadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,days) ;
        monthadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,months) ;
        yearadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,years) ;


        daypsd.setAdapter(dayadapter);
        monthpsd.setAdapter(monthadapter);
        yearpsd.setAdapter(yearadapter) ;
        daydop.setAdapter(dayadapter);
        monthdop.setAdapter(monthadapter);
        yeardop.setAdapter(yearadapter) ;


        categories = new ArrayList<>() ;
        categories.add("Type") ;
        categories.add("Car") ;
        categories.add("Bike") ;
        categories.add("HeavyVehicle") ;

        fuels = new ArrayList<>() ;
        fuels.add("Fuel") ;
        fuels.add("Petrol") ;
        fuels.add("Diesel") ;
        fuels.add("CNG") ;
        fuels.add("LPG") ;
        fuels.add("Electricity") ;


        categoryadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,categories) ;
        fueladapter  = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,fuels) ;

        category.setAdapter(categoryadapter);
        FuelUsed.setAdapter(fueladapter);


    }
}
