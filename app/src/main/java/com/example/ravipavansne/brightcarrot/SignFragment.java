package com.example.ravipavansne.brightcarrot;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignFragment extends Fragment {

    private TextInputEditText email;
    private TextInputEditText pass;
    private TextInputEditText cpass;
    private Button butt;
    private DatabaseReference databaseReference ;
    private FirebaseAuth.AuthStateListener mAuthListener ;
    private FirebaseAuth firebaseAuth;
    private userdetails u1;
    private String name ;
    private TextView textView;
    private LoginButton loginButton ;
    private CallbackManager callbackManager ;
    private static final String EMAIL = "email";


    public SignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign, container, false);
        email = (TextInputEditText) v.findViewById(R.id.email);
        pass = (TextInputEditText) v.findViewById(R.id.pass);
        cpass = (TextInputEditText) v.findViewById(R.id.cpass);
        butt = (Button) v.findViewById(R.id.butt);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        callbackManager = CallbackManager.Factory.create();
        textView = (TextView) v.findViewById(R.id.useralready);
        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });










        mAuthListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    name = user.getDisplayName();
                    Toast.makeText(getContext(), "" + user.getDisplayName(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_LONG).show();
                }


            }
        };


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext = email.getText().toString();
                final String passtext = pass.getText().toString();
                String cpasstext = cpass.getText().toString();

                if (passtext.equals(cpasstext)) {
                    firebaseAuth.createUserWithEmailAndPassword(emailtext, passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Signed up successfully and please verify your email", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                u1 = new userdetails();
                                u1.setFlag(false);
                                u1.setPassword(passtext);
                                u1.setLoggedby("brightcarrot");
                                databaseReference.child("Users").child(user.getUid()).child("Account details").setValue(u1);
                                BankDetails bankDetails = new BankDetails();
                                bankDetails.setFlag("false");
                                databaseReference.child("Users").child(user.getUid()).child("Bank Details").setValue(bankDetails);
                                user.sendEmailVerification();

                            } else {

                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "bull shit password", Toast.LENGTH_SHORT).show();
                    pass.setText("");
                    cpass.setText("");
                }

            }
        });

        return v ;
    }




    }


