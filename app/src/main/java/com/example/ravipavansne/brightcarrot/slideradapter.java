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
    /**
     * Return the number of views available.
     */

    public slideradapter (Context context)
    {
        this.context = context ;

    }

    public int slide_images[]= {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
    } ;
    public String slide_headings[]=
            {
                    "CARS","BIKES","HEAVY VEHICLES"
            };
    public String slide_descs[]={
            "For Small trips with family and friends",
            "For  emergencies and Work purposes",
            "For Transferring from one place to other place"
    } ;
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE) ;
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false) ;

        CircleImageView slideimageview = (CircleImageView) view.findViewById(R.id.slideimage) ;
        TextView textview = (TextView) view.findViewById(R.id.textView) ;
        TextView textView2 = (TextView) view.findViewById(R.id.textView2) ;

        slideimageview.setImageResource(slide_images[position]);
        textview.setText(slide_headings[position]);
        textView2.setText(slide_descs[position]);


        container.addView(view);
        return view ;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
