package com.example.ravipavansne.brightcarrot;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter2 extends FragmentPagerAdapter {
    public SectionsPagerAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
    switch (i)
    {
        case 0 :
            bookedFragment1 bf1 = new bookedFragment1();
            return bf1;

        case 1 :
            bookedFragment2 bf2 = new bookedFragment2();
            return bf2;
        default:
            return null;

    }
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0 :
                return "My Bookings" ;
            case 1 :
                return "My Rentals" ;
            default:
                return null;
        }
    }
}
