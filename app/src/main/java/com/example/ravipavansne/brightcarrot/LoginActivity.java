package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(TextInputEditText)findViewById(R.id.loginemail);
        pass=(TextInputEditText)findViewById(R.id.loginpass);
        butt=(Button)findViewById(R.id.loginbutt);
        textView = (TextView)findViewById(R.id.notuser);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
                                Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show()
                                ;
                                String id = firebaseAuth.getCurrentUser().getUid() ;
                                databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(id).child("Account details");
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String flag =  dataSnapshot.child("flag").getValue().toString();
                                        if(flag.equals("true"))
                                        {
                                            Intent i = new Intent(LoginActivity.this,Home2Activity.class) ;
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                            finish();

                                        }
                                        else
                                        {
                                            Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                            finish();
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
                        else
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    }

