package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText email;
    private TextInputEditText pass;
    private Button butt;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference ;
    private userdetails u1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(TextInputEditText)findViewById(R.id.loginemail);
        pass=(TextInputEditText)findViewById(R.id.loginpass);
        butt=(Button)findViewById(R.id.loginbutt);

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
                                Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show()
                                ;
                                String id = firebaseAuth.getCurrentUser().getUid() ;
                                databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(id);
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String flag = dataSnapshot.child("flag").getValue().toString() ;
                                        if(flag=="true")
                                        {
                                            startActivity(new Intent(LoginActivity.this,Home2Activity.class)) ;

                                        }
                                        else
                                        {
                                            startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });



                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "please verify your email address", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        });
    }
    }

