package com.example.ravipavansne.brightcarrot;

import android.content.Context;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aminography.redirectglide.GlideApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatterAdapter extends ArrayAdapter<userdetails> {
    public ChatterAdapter( Context context, List<userdetails> messageList) {
        super(context, R.layout.chatter,messageList);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.chatter,parent,false);

        CircleImageView circleImageView = (CircleImageView)v.findViewById(R.id.chatterpp);
        TextView name = (TextView)v.findViewById(R.id.chattername);
        TextView number = (TextView)v.findViewById(R.id.chatternumber);

        userdetails u = getItem(position);

        name.setText(u.getFirstname()+" "+u.getLastname());
        number.setText(u.getPhonenumber());


        return v;
    }
}
