package com.example.inpeace.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.example.inpeace.main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PasswordVerification extends AppCompatActivity {

    private Button passwordNextBtn ;
    private EditText passwordPassword ;
    private EditText passwordConfirmPassword ;
    private DatabaseReference mdatabase;
    private String userId ;
    private ProgressDialog loading;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_verification);

        // Linking to XML
        passwordNextBtn = findViewById(R.id.passwordNextBtn);
        passwordPassword = findViewById(R.id.passwordPassword);
        passwordConfirmPassword =findViewById(R.id.passwordConfirmPassword);
        error = findViewById(R.id.passwordError);

        // inializing the database
        mdatabase = FirebaseDatabase.getInstance().getReference();

        //inializing the userid
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //inializing the loading bar
        loading = new ProgressDialog(this);


        passwordNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setTitle("Setting Up Password");
                loading.setMessage("sit back and relax till we set up your password");
                loading.setCanceledOnTouchOutside(false);
                loading.show();

                String str1 =passwordConfirmPassword.getText().toString();
                String str2 = passwordPassword.getText().toString();
                String vv =str1 + " " +str2;
                error.setText(vv);


                if(str1.equals(str2)){
                    mdatabase.child("sign in").child(userId).child("Password").setValue(passwordPassword.getText().toString());
                    createUser(passwordPassword.getText().toString());
                    Toast.makeText(PasswordVerification.this , "Regestration is successful" , Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(PasswordVerification.this , main.class);
                    loading.dismiss();
                    startActivity(intent);
                }else{
                    Toast.makeText(PasswordVerification.this , "Password is not matching" , Toast.LENGTH_LONG).show();
                    loading.dismiss();
                }

            }
        });

    }

    private void createUser(String password) {
        FirebaseAuth mauth ;
        mauth = FirebaseAuth.getInstance();
        mauth.createUserWithEmailAndPassword(getIntent().getStringExtra("dododo") , password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(PasswordVerification.this, "Account Created",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mauth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(PasswordVerification.this, (getIntent().getStringExtra("dododo" )+ " " + password),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}