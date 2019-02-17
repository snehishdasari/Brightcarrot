package com.example.ravipavansne.brightcarrot;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionPagerAdapter3 extends FragmentPagerAdapter {
    public SectionPagerAdapter3(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0 :
                SignFragment signFragment = new SignFragment() ;
                return signFragment ;

            case 1 :
                LoginFragment loginFragment = new LoginFragment() ;
                return loginFragment ;

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
        switch (position) {
            case 0:
                return "SIGNUP";
            case 1:
                return "LOGIN";
            default:
                return null;
        }

    }
}
