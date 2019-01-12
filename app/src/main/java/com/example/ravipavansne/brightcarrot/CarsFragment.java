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
public class CarsFragment extends Fragment {


    public CarsFragment() {
        // Required empty public constructor
    }

    private List<VehicleDetails> listcars;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cars, container, false) ;

         recyclerView = (RecyclerView)  v.findViewById(R.id.recycler_cars) ;
        recyclerView.setHasFixedSize(true );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listcars = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Available Vehicles").child("Car");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listcars.clear();
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    listcars.add(d.getValue(VehicleDetails.class));
                }
                VehicleAdapter vehicleAdapter = new VehicleAdapter(getContext(),listcars) ;

                recyclerView.setAdapter(vehicleAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v ;



    }

}
