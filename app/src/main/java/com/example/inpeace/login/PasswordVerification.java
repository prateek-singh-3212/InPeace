package com.example.inpeace.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuthException;
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
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_verification);

        // Linking to XML
        passwordNextBtn = findViewById(R.id.passwordNextBtn);
        passwordPassword = findViewById(R.id.passwordPassword);
        passwordConfirmPassword =findViewById(R.id.passwordConfirmPassword);

        // inializing the database
        mdatabase = FirebaseDatabase.getInstance().getReference();

        //inializing the userid
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //inializing the loading bar
        loading = new ProgressDialog(this);

        //inializing the Alert Dialog
        builder = new AlertDialog.Builder(this);


        passwordNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setTitle("Setting Up Password");
                loading.setMessage("sit back and relax till we set up your password");
                loading.setCanceledOnTouchOutside(false);
                loading.show();

                String str1 =passwordConfirmPassword.getText().toString();
                String str2 = passwordPassword.getText().toString();

                if(str1.equals(str2) && str1.length()>=8){
                    mdatabase.child("sign in").child(userId).child("Password").setValue(passwordPassword.getText().toString());
                    //createUser(passwordPassword.getText().toString());

                    //CreateUserWithSignUP
                    Log.d("AbC",getIntent().getStringExtra("Email"));
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(getIntent().getStringExtra("Email"),str1)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //creating the user table
                                        String user_ID = FirebaseAuth.getInstance().getUid();
                                        SQLiteDatabase database = new SQLiteDatabase(PasswordVerification.this);
                                        database.insertValueInLoginTable(user_ID , "true");

                                        Intent intent =new Intent(PasswordVerification.this , main.class);
                                        loading.dismiss();
                                        startActivity(intent);

                                        Log.d("ABC","UserCreated");
                                        Toast.makeText(PasswordVerification.this , "UserCreated" ,Toast.LENGTH_SHORT).show();
                                        Toast.makeText(PasswordVerification.this , "Registration is successful" , Toast.LENGTH_LONG).show();
                                    }
                                    if(!task.isSuccessful()){
                                        Log.d("ABC","UserNotCreated");
                                        Toast.makeText(PasswordVerification.this , "UserNotCreated" ,Toast.LENGTH_SHORT).show();
                                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                        Toast.makeText(PasswordVerification.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("ABC",e.getMessage());
                                        Log.d("ABC",e.getErrorCode());

                                        //Resolving Error in the Email Already In use
                                        if(e.getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")){
                                            loading.dismiss();
                                            builder.setTitle("EMAIL ALREADY IN USE").setMessage("The email address is already in use by another account.")
                                                    .setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                    Intent intent = new Intent(PasswordVerification.this , HomePage.class);
                                                    startActivity(intent);
                                                }
                                            }).create().show();
                                        }
                                    }
                                }
                            });
                }else{
                    Toast.makeText(PasswordVerification.this , "Password Should Be Of 8 digits" , Toast.LENGTH_LONG).show();
                    loading.dismiss();
                }

            }
        });

    }


}