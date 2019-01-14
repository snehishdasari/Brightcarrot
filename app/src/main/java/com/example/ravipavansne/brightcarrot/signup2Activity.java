package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class signup2Activity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        email=(TextInputEditText)findViewById(R.id.email);
        pass=(TextInputEditText)findViewById(R.id.pass);
        cpass=(TextInputEditText)findViewById(R.id.cpass);
        butt=(Button)findViewById(R.id.butt);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference() ;
        callbackManager = CallbackManager.Factory.create() ;
        textView = (TextView) findViewById(R.id.useralready);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(signup2Activity.this, "Token:"+loginResult.getAccessToken(), Toast.LENGTH_SHORT).show();

                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(signup2Activity.this,"Something error",Toast.LENGTH_SHORT).show();

            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener(){


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user!=null){
                    name = user.getDisplayName();
                    Toast.makeText(signup2Activity.this,""+user.getDisplayName(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(signup2Activity.this,"something went wrong",Toast.LENGTH_LONG).show();
                }


            }
        };



        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String emailtext=email.getText().toString();
                 final String passtext=pass.getText().toString();
                 String cpasstext=cpass.getText().toString();

                if(passtext.equals(cpasstext))
                {
                    firebaseAuth.createUserWithEmailAndPassword(emailtext,passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(signup2Activity.this, "Signed up successfully and please verify your email", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                u1=new userdetails();
                                u1.setFlag(false);
                                u1.setPassword(passtext);
                                databaseReference.child("Users").child(user.getUid()).child("Account details").setValue(u1);
                                BankDetails bankDetails = new BankDetails();
                                bankDetails.setFlag("false");
                                databaseReference.child("Users").child(user.getUid()).child("Bank Details").setValue(bankDetails);
                                user.sendEmailVerification();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                }
                             else {

                                Toast.makeText(signup2Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(signup2Activity.this, "bull shit password", Toast.LENGTH_SHORT).show();
                    pass.setText("");
                    cpass.setText("");
                }

            }
        });
    }


    private void handleFacebookAccessToken(AccessToken token) {
        // Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            u1 = new userdetails();
                            u1.setFlag(false);
                            // u1.setPassword(passtext);
                            databaseReference.child("Users").child(user.getUid()).child("Account details").setValue(u1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(signup2Activity.this, "Added account details", Toast.LENGTH_SHORT).show();
                                }
                            });
                            BankDetails bankDetails = new BankDetails();
                            bankDetails.setFlag("false");
                            databaseReference.child("Users").child(user.getUid()).child("Bank Details").setValue(bankDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(signup2Activity.this, "Added Bank Details", Toast.LENGTH_SHORT).show();
                                }
                            });


                                        Intent i = new Intent(signup2Activity.this, Home2Activity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();

                                  
                        }


                            if (!task.isSuccessful()) {
                                Log.w("blaaaaa", "signInWithCredential", task.getException());
                                Toast.makeText(signup2Activity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }


                        }

                    });
                }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data) ;
    }
}

