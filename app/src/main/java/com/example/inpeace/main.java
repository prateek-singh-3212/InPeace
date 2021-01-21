package com.example.inpeace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.inpeace.activites.HomePage;
import com.example.inpeace.games.Tic_Tac_Toe;
import com.example.inpeace.games.trial;
import com.example.inpeace.motivation.MotivationHome;
import com.example.inpeace.music.MainMusic;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity {


    private Button game ;
    private Button audiobooks ;
    private Button music ;
    private Button motivation ;
    private Button activites ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //spashScreen();

        setContentView(R.layout.activity_main);

//        FirebaseMessaging.getInstance().subscribeToTopic("Tests")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(!task.isSuccessful()){
//                            Log.d("ABC","Sub Unscucessful");
//                        }
//                        Log.d("ABC","Sub Sucess");
//                    }
//                });



        game = findViewById(R.id.mainGameBtn);
        music = findViewById(R.id.mainMusicBtn);
        motivation = findViewById(R.id.mainMotivationBtn);
        audiobooks = findViewById(R.id.mainAudiobooksBtn);
        activites = findViewById(R.id.mainActivitesBtn);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( main.this , Tic_Tac_Toe.class);
                startActivity(intent);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( main.this , MainMusic.class);
                startActivity(intent);
            }
        });

//        motivation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent( main.this , MotivationHome.class);
//                startActivity(intent);
//            }
//        });

        audiobooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( main.this , trial.class);
                startActivity(intent);
            }
        });

        activites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( main.this , HomePage.class);
                startActivity(intent);
            }
        });

    }

//    private void spashScreen() {
//        int handler =2000;
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i=new Intent(main.this,
//                        MainActivity.class);
//                //Intent is used to switch from one activity to another.
//
//                startActivity(i);
//                //invoke the SecondActivity.
//
//                finish();
//                //the current activity will get finished.
//            }
//        }, handler);
//    }
}