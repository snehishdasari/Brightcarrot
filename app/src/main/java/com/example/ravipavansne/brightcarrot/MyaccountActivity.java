package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyaccountActivity extends AppCompatActivity {

    private Toolbar toolbar ;
    private CircleImageView backaarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

    backaarrow = (CircleImageView) findViewById(R.id.backmyaccount);
    backaarrow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MyaccountActivity.this,Home2Activity.class));
        }
    });

    }
}
