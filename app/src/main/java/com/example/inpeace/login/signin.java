package com.example.inpeace.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.logging.StreamHandler;

public class signin extends AppCompatActivity {

    private Button signinButtonBtn ;
    private EditText Password ;
    private EditText Email ;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Linking XML
        signinButtonBtn = findViewById(R.id.signinButtonBtn);
        Email = findViewById(R.id.signinEmail);
        Password =findViewById(R.id.signinPassword);

        // inaltialzing Alert
        builder = new AlertDialog.Builder(this);

        signinButtonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(Email.getText().toString(),Password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    //creating the user table
                                    String user_ID = FirebaseAuth.getInstance().getUid();
                                    SQLiteDatabase database = new SQLiteDatabase(signin.this);

                                    if(database.checkUserWithCurrentId(user_ID)){
                                        Log.d("ABC" , "values inserted");
                                        database.insertValueInLoginTable(user_ID , "true");
                                        Intent intent = new Intent(signin.this, main.class);
                                        startActivity(intent);
                                    }else {
                                        Log.d("ABC" , "user  is already in table");
                                        builder.setTitle("You are Already SignedIn").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                                Intent intent = new Intent(signin.this , HomePage.class);
                                                startActivity(intent);
                                            }
                                        }).create().show();
                                    }

                                }
                                if(!task.isSuccessful()){
                                    FirebaseAuthException exception = (FirebaseAuthException) task.getException();
                                    Toast.makeText(signin.this, exception.getMessage() , Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

    }

//    private void checkUserIsHavingAccount() {
//
//        value = findViewById(R.id.signinEmail);
//        error = findViewById(R.id.signinError);
//        ArrayList<String> list = new ArrayList<>(0);
//
//        String userInput = "+91" + value.getText().toString() ;
//
//        FirebaseDatabase.getInstance().getReference().child("sign in")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                String data = " ";
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                     data = dataSnapshot.child("Phone Number").getValue().toString();
//                     list.add(data);
//
//
////                     if(data.equals(userInput)){
////                         Toast.makeText(signin.this,"Email is registered" ,Toast.LENGTH_LONG).show();
////                         break;
//////                         error.setText("");
////                     }else {
////                         continue;
////                         Toast.makeText(signin.this,"Please sign up first" ,Toast.LENGTH_LONG).show();
//////                         error.setText("");
////                     }
//
//                }
//
//                error.setText(list.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }
}