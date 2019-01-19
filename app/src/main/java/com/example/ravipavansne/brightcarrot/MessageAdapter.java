package com.example.ravipavansne.brightcarrot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context, List<Message> messagelist) {
        super(context,R.layout.singlemessage,messagelist );
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.singlemessage,parent,false);

        Message message = getItem(position);
        String typedmessage = message.getMessage();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(calendar.getTime());
        TextView mymessage = (TextView)v.findViewById(R.id.chatmessage);
        TextView mytime = (TextView)v.findViewById(R.id.messagetime);
        mymessage.setText(typedmessage);
        mytime.setText(time);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)mymessage.getLayoutParams();
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams)mytime.getLayoutParams();
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(message.getUser()))
        {
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mymessage.setLayoutParams(lp);
            mymessage.setBackgroundResource(R.drawable.roundbuttonlightblue);
            lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mytime.setLayoutParams(lp2);
        }
        else{
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mymessage.setLayoutParams(lp);
            mymessage.setBackgroundResource(R.drawable.roundbuttonycoffee);
            lp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mytime.setLayoutParams(lp2);
        }

        return v;
    }

}

