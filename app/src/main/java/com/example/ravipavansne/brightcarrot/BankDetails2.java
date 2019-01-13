package com.example.ravipavansne.brightcarrot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BankDetails2 extends AppCompatActivity {


    private ProgressDialog progressDialog;
    private BankDetails bankDetails;
    private TextInputLayout t1;
    private TextInputLayout t3;
    private TextInputLayout t2;
    private TextInputLayout t4;
    private Button banksave;
    private Button bankedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details2);



        FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        t1 = (TextInputLayout)findViewById(R.id.accno);
        t2 = (TextInputLayout)findViewById(R.id.ifsc);
        t3 = (TextInputLayout)findViewById(R.id.bankname);
        t4 = (TextInputLayout)findViewById(R.id.bankbranch);
        banksave = (Button)findViewById(R.id.banksave);
        bankedit = (Button)findViewById(R.id.bankedit);
        ImageView img  = (ImageView)findViewById(R.id.bankclose);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home2Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });


        final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users").child(fuser.getUid()).child("Bank Details");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("flag").getValue().toString().equals("true")){
                    String a = dataSnapshot.child("accountnumber").getValue().toString();
                    String b = dataSnapshot.child("ifsccode").getValue().toString();
                    String c = dataSnapshot.child("bankname").getValue().toString();
                    String d = dataSnapshot.child("accountnumber").getValue().toString();
                    t1.getEditText().setText(a);
                    t2.getEditText().setText(b);
                    t3.getEditText().setText(c);
                    t4.getEditText().setText(d);
                    bankDetails = new BankDetails(a,b,c,d);
                    banksave.setEnabled(false);
                    t1.setEnabled(false);
                    t2.setEnabled(false);
                    t3.setEnabled(false);
                    t4.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        bankedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setEnabled(true);
                t2.setEnabled(true);
                t3.setEnabled(true);
                t4.setEnabled(true);
                banksave.setEnabled(true);

            }
        });
        banksave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(getApplicationContext());
                progressDialog.setTitle("Adding Bank Details");
                progressDialog.setMessage("Please wait while we add your bank details");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                if(t1.getEditText().getText().toString().isEmpty()||
                        t2.getEditText().getText().toString().isEmpty()||
                        t3.getEditText().getText().toString().isEmpty()||
                        t4.getEditText().getText().toString().isEmpty()){
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), "Fields Empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    bankDetails = new BankDetails(t1.getEditText().getText().toString(),
                            t2.getEditText().getText().toString(),
                            t3.getEditText().getText().toString(),
                            t4.getEditText().getText().toString());
                    bankDetails.setFlag("true");
                    db.setValue(bankDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Bank Details Added", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent i = new Intent(getApplicationContext(),Home2Activity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }
                            else
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });




    }
}
