package com.example.inpeace.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inpeace.R;

public class games_homePage extends AppCompatActivity {

    private Button tictac , cardGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_home_page);

        tictac = findViewById(R.id.tictac);
        cardGame = findViewById(R.id.cardGame);

        tictac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(games_homePage.this,Tic_Tac_Toe.class);
                startActivity(intent);
            }
        });

        cardGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(games_homePage.this,card_game.class);
                startActivity(intent);
            }
        });

    }
}