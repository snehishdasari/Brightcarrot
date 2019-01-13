package com.example.ravipavansne.brightcarrot;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BikesFragment extends Fragment {


    private List<VehicleDetails> listbikes;
    private RecyclerView recyclerView;
    private View v;
    public BikesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_bikes, container, false) ;

         recyclerView = (RecyclerView)  v.findViewById(R.id.recycler_bikes) ;
        recyclerView.setHasFixedSize(true );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));






        // Inflate the layout for this fragment
        listbikes = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Available Vehicles").child("Bike");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listbikes.clear();
                for(DataSnapshot d:dataSnapshot.getChildren()){

                    VehicleDetails v = d.getValue(VehicleDetails.class) ;
                    if(v.getAvailability() == "true")
                    listbikes.add(v);
                }

                VehicleAdapter vehicleAdapter = new VehicleAdapter(getContext(),listbikes) ;

                recyclerView.setAdapter(vehicleAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }


}
