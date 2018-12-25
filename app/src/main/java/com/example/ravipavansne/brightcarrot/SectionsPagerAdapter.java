package com.example.ravipavansne.brightcarrot;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
    switch (i)
    {
        case 0 :
            CarsFragment carsFragment = new CarsFragment() ;
            return carsFragment ;
        case 1 :
            BikesFragment bikesFragment = new BikesFragment() ;
            return bikesFragment ;
        case 2 :
            HeavyVehiclesFragment heavyVehiclesFragment = new HeavyVehiclesFragment() ;
            return heavyVehiclesFragment ;
        default:
            return null ;
    }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0 :
                return "CARS" ;
            case 1 :
                return  "BIKES" ;
            case 2 :
                return  "HEAVY VEHICLES" ;
            default: return null ;
        }
    }
}
