package com.example.inpeace.games;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inpeace.R;

public class Tic_Tac_Toe extends AppCompatActivity {
    //103
    AlertDialog.Builder alertBox;

    boolean gameActive = true;
    boolean winner = false;
    // Player representation
    // 0 - X
    // 1 - O

    int activePlayer,score_x,score_o = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null

    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    int count = 0;

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        TextView xscore = findViewById(R.id.x_score);
        TextView oscore = findViewById(R.id.o_score);

        if(gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
            count++;
        }

        // Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                // Somebody has won! - Find out who!
                gameActive = false;
                String winnerStr = "";
                if(gameState[winPosition[0]] == 0){
                    score_x++;
                    alertBox = new AlertDialog.Builder(this);
                    alertBox.setTitle("\t\t\t\t\t\t\t\t\tSTATUS").setMessage("\nPlayer X: "+score_x+"\nPlayer O: "+score_o).setNeutralButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                    alertBox = new AlertDialog.Builder(this);
                    alertBox.setTitle("\t\t\t\t\t\t\t\t\tOoo X has Won!!").setMessage("\nCongrats to the Player X.\nPlayer O my Condolences. But, as they say \"Never Give Up!!\"").setNeutralButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gameReset(view);
                            dialog.dismiss();
                        }
                    }).create().show();

                    winner = true;

                    xscore.setText("X Score: "+score_x);
                }
                else{
                    score_o++;
                    alertBox = new AlertDialog.Builder(this);
                    alertBox.setTitle("\t\t\t\t\t\t\t\t\tSTATUS").setMessage("\nPlayer X: "+score_x+"\nPlayer O: "+score_o).setNeutralButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                    alertBox = new AlertDialog.Builder(this);
                    alertBox.setTitle("\t\t\t\t\t\t\t\t\tOooh O has Won!!").setMessage("\nCongrats to the Player O.\nPlayer X my Condolences. But, as they say \"Never Give Up!!\"").setNeutralButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gameReset(view);
                            dialog.dismiss();
                        }
                    }).create().show();

                    winner = true;

                    oscore.setText("O Score: "+score_o);
                }

            }
        }


        if(count == 9 && winner == false) {
            TextView status = findViewById(R.id.status);
            status.setText("Tie");
            alertBox = new AlertDialog.Builder(this);
            alertBox.setTitle("\t\t\t\t\t\t\t\t\tAhhhhh Tie!").setMessage("\t\t\tAww It Was A Tie!\n\t\tGive it anoyher go...PLAY AGAIN??").setNeutralButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gameReset(view);
                    dialog.dismiss();
                }
            }).create().show();
            alertBox = new AlertDialog.Builder(this);
            alertBox.setTitle("\t\t\t\t\t\t\t\t\tSTATUS").setMessage("\nPlayer X: "+score_x+"\nPlayer O: "+score_o).setNeutralButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

    public void gameReset(View view) {

        gameActive = true;
        winner = false;

        count = 0;
        activePlayer = 0;

        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic__tac__toe);
        alertBox = new AlertDialog.Builder(this);

        alertBox.setTitle("\t\t\t\t\t\t\t\t\tWELCOME").setMessage("You are well aware of this GAME :)!\n\t\t\tSo, go ahead play and enjoy").setNeutralButton("Start", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView xscore = findViewById(R.id.x_score);
        TextView oscore = findViewById(R.id.o_score);
        xscore.setText("X Score: "+score_x);
        oscore.setText("O Score: "+score_o);
    }
}