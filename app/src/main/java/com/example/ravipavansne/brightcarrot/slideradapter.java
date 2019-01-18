package com.example.ravipavansne.brightcarrot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class slideradapter extends PagerAdapter {

    Context context ;
    LayoutInflater layoutInflater ;
    private int[] layouts ;
    /**
     * Return the number of views available.
     */

    public slideradapter (int layouts[],Context context)
    {
        this.layouts = layouts ;
        this.context = context ;

    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==  o;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE) ;
        View view = layoutInflater.inflate(layouts[position],container,false) ;




        container.addView(view);
        return view ;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View v = (View) object ;
        container.removeView(v);
    }
}
