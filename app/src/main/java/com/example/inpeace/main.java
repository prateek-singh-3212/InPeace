package com.example.inpeace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inpeace.games.trial;

public class main extends AppCompatActivity {


    private Button game ;
    private Button audiobooks ;
    private Button music ;
    private Button motivation ;
    private Button activites ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = findViewById(R.id.mainGameBtn);
        music = findViewById(R.id.mainMusicBtn);
        motivation = findViewById(R.id.mainMotivationBtn);
        audiobooks = findViewById(R.id.mainAudiobooksBtn);
        activites = findViewById(R.id.mainActivitesBtn);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( main.this , trial.class);
                startActivity(intent);

            }
        });

    }
}