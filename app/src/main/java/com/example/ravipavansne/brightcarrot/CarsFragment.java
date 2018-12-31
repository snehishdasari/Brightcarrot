package com.example.ravipavansne.brightcarrot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarsFragment extends Fragment {


    public CarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cars, container, false) ;

        RecyclerView recyclerView = (RecyclerView)  v.findViewById(R.id.recycler_cars) ;
        recyclerView.setHasFixedSize(true );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));






        // Inflate the layout for this fragment
        List<Vehicles> list = new ArrayList<Vehicles>() ;
        list.add(new Vehicles("name of the vehicle","Bla bla bla vla bla bla bla bla bla bla ",1000,R.drawable.background,"27jan","31jan",4));
        list.add(new Vehicles("name of the vehicle","Bla bla bla vla bla bla bla bla bla bla ablbal",1000,R.drawable.background,"27jan","31jan",4));
        list.add(new Vehicles("name of the vehicle","Bla bla bla vla bla bla bla bla bla bla ablbal",1000,R.drawable.background,"27jan","31jan",4));
        VehicleAdapter vehicleAdapter = new VehicleAdapter(getContext(),list) ;

        recyclerView.setAdapter(vehicleAdapter);

        return v ;



    }

}
