package com.example.inpeace.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inpeace.R;
import com.example.inpeace.main;

public class HomePage extends AppCompatActivity {

    private Button signin;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                SQLiteDatabase database = new SQLiteDatabase(HomePage.this);
//                if(database.checkUserIsSignedIn()){
//                    Toast.makeText(HomePage.this,"user is signed in",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(HomePage.this, main.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(HomePage.this,"not signed in",Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this , signin.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this , PhoneAuth.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
//        SQLiteDatabase database = new SQLiteDatabase(this);
//        if(database.checkUserIsSignedIn()){
//            Toast.makeText(this,"user is signed in",Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(HomePage.this, main.class);
//            startActivity(intent);
//        }else {
//            Toast.makeText(this,"not signed in",Toast.LENGTH_LONG).show();
//        }
    }
}