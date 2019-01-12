package com.example.ravipavansne.brightcarrot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aminography.redirectglide.GlideApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>
{
    private Context context ;
    private List<VehicleDetails> list ;

    public VehicleAdapter(Context context, List<VehicleDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.carslist,null) ;
        VehicleViewHolder vehicleViewHolder = new VehicleViewHolder(v) ;

        return vehicleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder viewHolder, int i) {
        VehicleDetails vehicles = list.get(i) ;
        GlideApp.with(context).load(vehicles.getVehicleimage()).into(viewHolder.circleImageView);
        viewHolder.namev.setText(vehicles.getVehiclename());
        viewHolder.descv.setText(vehicles.getOwnername());
        String a[] = vehicles.getStartday().split("/");
        String b[] = vehicles.getEndday().split("/");
        viewHolder.pricev.setText("Rs."+vehicles.getPrice()+"/-  Per hour") ;
        viewHolder.datev.setText(a[0]+"/"+a[1]+" - "+b[0]+"/"+b[1]);
        viewHolder.ratev.setText(vehicles.getRating()+"/5");
        viewHolder.vehinumber.setText(vehicles.getVehicleno());
        if(vehicles.getType().equals("Bike"))
            viewHolder.vehinumber.setBackgroundResource(R.drawable.roundbuttonblue);
        else if(vehicles.getType().equals("HeavyVehicle"))
            viewHolder.vehinumber.setBackgroundResource(R.drawable.roundbuttongreen);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder {


        public CircleImageView circleImageView ;
        public TextView namev,descv,pricev,datev,ratev,vehinumber ;

        public VehicleViewHolder(@NonNull View itemView) {

            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.dpid) ;
            namev = (TextView) itemView.findViewById(R.id.nameid) ;
            descv = (TextView) itemView.findViewById(R.id.descid) ;
            pricev = (TextView) itemView.findViewById(R.id.priceid) ;
            datev  = (TextView) itemView.findViewById(R.id.dateid);
            ratev = (TextView) itemView.findViewById(R.id.ratingid) ;
            vehinumber = (TextView) itemView.findViewById(R.id.vehinumber);


        }
    }
}
