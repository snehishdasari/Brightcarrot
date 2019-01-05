package com.example.ravipavansne.brightcarrot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyVehicleAdapter extends RecyclerView.Adapter<MyVehicleAdapter.MyvehicleViewHolder> {

    private Context context;
    private ArrayList<VehicleDetails> list;

    public MyVehicleAdapter (Context context , ArrayList<VehicleDetails> list)
    {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyVehicleAdapter.MyvehicleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.myvehiclecard,null);
        MyvehicleViewHolder myvehicleViewHolder = new MyvehicleViewHolder(v);
        return myvehicleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyVehicleAdapter.MyvehicleViewHolder myvehicleViewHolder, int i) {

        VehicleDetails vehicleDetails = list.get(i);
        myvehicleViewHolder.circleImageView.setImageResource(R.drawable.background);
        myvehicleViewHolder.name.setText(vehicleDetails.getVehiclename());
        myvehicleViewHolder.type.setText(vehicleDetails.getType());
        myvehicleViewHolder.number.setText(vehicleDetails.getVehicleNo());
        myvehicleViewHolder.rating.setText("5/5");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyvehicleViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView circleImageView;
        public TextView type,name,number,rating;

        public MyvehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = (CircleImageView)itemView.findViewById(R.id.myvehcircimage);
            type = (TextView)itemView.findViewById(R.id.myvehtype);
            name = (TextView)itemView.findViewById(R.id.myvehname);
            number = (TextView)itemView.findViewById(R.id.myvehnum);
            rating= (TextView)itemView.findViewById(R.id.myvehrating);

        }
    }
}
