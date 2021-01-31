package com.example.inpeace.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inpeace.R;

public class games_homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_home_page);

    }

    public void openTicTakToe(View view) {
        Intent intent = new Intent(games_homePage.this,Tic_Tac_Toe.class);
        startActivity(intent);
    }

    public void openCardGame(View view) {
        Intent intent = new Intent(games_homePage.this,card_game.class);
        startActivity(intent);
    }

    public void openWouldYouRather(View view) {
        Toast.makeText(this,"Comming Soon",Toast.LENGTH_SHORT).show();
    }

    public void openJigSaw(View view) {
        Toast.makeText(this,"Comming Soon",Toast.LENGTH_SHORT).show();
    }
}