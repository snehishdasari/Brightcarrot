package com.example.ravipavansne.brightcarrot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>
{
    private Context context ;
    private List<Vehicles> list ;

    public VehicleAdapter(Context context, List<Vehicles> list) {
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
        Vehicles vehicles = list.get(i) ;
        viewHolder.circleImageView.setImageResource(vehicles.getImage());
        viewHolder.namev.setText(vehicles.getName());
        viewHolder.descv.setText(vehicles.getDesc());
        viewHolder.pricev.setText(vehicles.getPrice()+"/- Per hour") ;
        viewHolder.datev.setText(vehicles.getStartdate()+" - "+vehicles.getEnddate());
        viewHolder.ratev.setText(vehicles.getRating()+"/5");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder {


        public CircleImageView circleImageView ;
        public TextView namev,descv,pricev,datev,ratev ;

        public VehicleViewHolder(@NonNull View itemView) {

            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.dpid) ;
            namev = (TextView) itemView.findViewById(R.id.nameid) ;
            descv = (TextView) itemView.findViewById(R.id.descid) ;
            pricev = (TextView) itemView.findViewById(R.id.priceid) ;
            datev  = (TextView) itemView.findViewById(R.id.dateid);
            ratev = (TextView) itemView.findViewById(R.id.ratingid) ;


        }
    }
}
