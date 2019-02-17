package com.example.ravipavansne.brightcarrot;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextInputEditText email;
    private TextInputEditText pass;
    private Button butt;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference ;
    private userdetails u1 ;
    private TextView textView;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        email=(TextInputEditText)v.findViewById(R.id.loginemail);
        pass=(TextInputEditText)v.findViewById(R.id.loginpass);
        butt=(Button)v.findViewById(R.id.loginbutt);
        textView = (TextView)v.findViewById(R.id.notuser);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext = email.getText().toString();
                String passtext = pass.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(emailtext,passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            if(firebaseAuth.getCurrentUser().isEmailVerified())
                            {
                                Toast.makeText(getContext(), "login successful", Toast.LENGTH_SHORT).show()
                                ;
                                String id = firebaseAuth.getCurrentUser().getUid() ;
                                databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(id).child("Account details");
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String flag =  dataSnapshot.child("flag").getValue().toString();
                                        if(flag.equals("true"))
                                        {
                                            Intent i = new Intent(getContext(),Home2Activity.class) ;
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                            //finish();

                                        }
                                        else
                                        {
                                            Intent i = new Intent(getContext(),SignupActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                            //finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });



                            }
                            else
                            {
                                Toast.makeText(getContext(), "please verify your email address", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return v ;
    }

}
