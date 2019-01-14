package com.example.ravipavansne.brightcarrot;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup2Activity extends AppCompatActivity {

    private TextInputEditText email;
    private TextInputEditText pass;
    private TextInputEditText cpass;
    private Button butt;
    private FirebaseAuth firebaseAuth;
    private userdetails u1;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        email=(TextInputEditText)findViewById(R.id.email);
        pass=(TextInputEditText)findViewById(R.id.pass);
        cpass=(TextInputEditText)findViewById(R.id.cpass);
        butt=(Button)findViewById(R.id.butt);
        firebaseAuth=FirebaseAuth.getInstance();
        textView = (TextView) findViewById(R.id.useralready);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

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
}
