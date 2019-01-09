package com.example.ravipavansne.brightcarrot;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyVehicleAdapter extends RecyclerView.Adapter<MyVehicleAdapter.MyvehicleViewHolder> {

    private Context context;
    private List<VehicleDetails> list;
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    } ;

    public MyVehicleAdapter (Context context , List<VehicleDetails> list)
    {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyVehicleAdapter.MyvehicleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.myvehiclecard,null);
        v.setOnClickListener(onClickListener);
        MyvehicleViewHolder myvehicleViewHolder = new MyvehicleViewHolder(v);
        return myvehicleViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyVehicleAdapter.MyvehicleViewHolder myvehicleViewHolder, int i) {
        final int k = i ;
        VehicleDetails vehicleDetails = list.get(i);
        myvehicleViewHolder.circleImageView.setImageResource(R.drawable.background);
        myvehicleViewHolder.name.setText(vehicleDetails.getVehiclename());
        myvehicleViewHolder.type.setText(vehicleDetails.getType());
        myvehicleViewHolder.number.setText(vehicleDetails.getVehicleno());
        myvehicleViewHolder.rating.setText("5/5");
        myvehicleViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,VehicleDisplayActivity.class) ;
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("item",k) ;
                context.startActivity(intent);
            }
        });
        myvehicleViewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                    Intent intent = new Intent(context,AvailableConfirmActivity.class) ;
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("item",k) ;
                                    context.startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(context,"Its ok",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
             //   builder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
                builder.setMessage("Are you sure You want to Add your Vehicle?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyvehicleViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView circleImageView;
        public TextView type,name,number,rating;

        public LinearLayout linearLayout ;
        public MyvehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = (CircleImageView)itemView.findViewById(R.id.myvehcircimage);
            type = (TextView)itemView.findViewById(R.id.myvehtype);
            name = (TextView)itemView.findViewById(R.id.myvehname);
            number = (TextView)itemView.findViewById(R.id.myvehnum);
            rating= (TextView)itemView.findViewById(R.id.myvehrating);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.parent) ;
        }
    }
}
