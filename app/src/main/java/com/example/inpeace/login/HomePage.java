package com.example.inpeace.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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
        SQLiteDatabase database = new SQLiteDatabase(this);
        if(database.checkUserIsSignedIn()){
            Toast.makeText(this,"user is signed in",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(HomePage.this, main.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"not signed in",Toast.LENGTH_LONG).show();
        }
    }
}