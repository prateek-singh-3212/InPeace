package com.example.inpeace.games;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inpeace.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class card_game extends AppCompatActivity {

    ImageView user_1;
    ImageView user_2;
    ImageView user_3;

    ImageView userUp_1;
    ImageView userUp_2;
    ImageView userUp_3;
    ImageView pile_img;
    ImageView comp_1;
    ImageView comp_2;
    ImageView comp_3;

    TextView comp_points;
    TextView player_points;

    AlertDialog.Builder alertBox;
    int pointsUser = 5;
    int pointsComp = pointsUser;

    List suits = new ArrayList(Arrays.asList("d","h","c","s"));
    List cards = new ArrayList(Arrays.asList("2","3","4","5","6","7","8","9","10","j","q","k","a"));
    List deck = new ArrayList(Arrays.asList("sa", "sk", "sq", "sj", "s10", "s9", "s8", "s7", "s6", "s5", "s4", "s3", "s2", "ca", "ck", "cq", "cj", "c10", "c9", "c8", "c7", "c6", "c5", "c4", "c3", "c2", "ha", "hk", "hq", "hj", "h10", "h9", "h8", "h7", "h6", "h5", "h4", "h3", "h2", "da", "dk", "dq", "dj", "d10", "d9", "d8", "d7", "d6", "d5", "d4", "d3", "d2"));
    List pile = new ArrayList(52);
    List trash = new ArrayList(52);
    List player = new ArrayList(52);
    List computer = new ArrayList(52);
    List face_Up_User = new ArrayList(3);
    List face_Up_Up_User = new ArrayList(Arrays.asList(0));
    List back_Comp = new ArrayList(3);

    List ace = new ArrayList(Arrays.asList("ha","da","ca","sa"));
    List king = new ArrayList(Arrays.asList("hk","dk","ck","sk"));
    List queen = new ArrayList(Arrays.asList("hq","dq","cq","sq"));
    List jack = new ArrayList(Arrays.asList("hj","dj","cj","sj"));
    List ten = new ArrayList(Arrays.asList("h10","d10","c10","s10"));
    List nine = new ArrayList(Arrays.asList("h9","d9","c9","s9"));
    List eight = new ArrayList(Arrays.asList("h8","d8","c8","s8"));
    List seven = new ArrayList(Arrays.asList("h7","d7","c7","s7"));
    List six = new ArrayList(Arrays.asList("h6","d6","c6","s6"));
    List five = new ArrayList(Arrays.asList("h5","d5","c5","s5"));
    List four = new ArrayList(Arrays.asList("h4","d4","c4","s4"));
    List three = new ArrayList(Arrays.asList("h3","d3","c3","s3"));
    List two = new ArrayList(Arrays.asList("h2","d2","c2","s2"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);

        alertBox = new AlertDialog.Builder(this);

        alertBox.setTitle("\t\t\t\t\t\t\t\t\tWELCOME").setMessage("Rules:\n\n1.One up the Computer's Card on the \n    pile.\n\n2.Tap on the card you can play and then\n    tap on the pile to play it.\n\n3.You have "+pointsUser+" points.\n\n4.If none of Your or the Computer's \n   card can \"One Up\" the pile's card you \n   lose one point.\n\n5.Whoever reaches 0 points. \n    It's Game Over for them.").setNeutralButton("Ready!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
        inCreate();
    }

    public void inCreate(){
        user_1 = findViewById(R.id.user1);
        user_2 = findViewById(R.id.user2);
        user_3 = findViewById(R.id.user3);

        userUp_1 = findViewById(R.id.upCard1);
        userUp_2 = findViewById(R.id.up_Card2);
        userUp_3 = findViewById(R.id.up_Card3);
        pile_img = findViewById(R.id.pile);
        comp_1 = findViewById(R.id.comp1);
        comp_2 = findViewById(R.id.comp2);
        comp_3 = findViewById(R.id.comp3);

        comp_points = findViewById(R.id.compPoints);
        player_points = findViewById(R.id.userPoints);

        comp_points.setText("Computer: "+pointsComp);
        player_points.setText("User: "+pointsUser);

        //add animation for shuffling if possible
        Collections.shuffle(deck);

        for(int k=0; k<26; k++){
            player.add(0,deck.get(0).toString());
            deck.remove(0);
            computer.add(0,deck.get(0).toString());
            deck.remove(0);
        }

        //adding to user face up cards and removing from player list the same card
        face_Up_User.add(0,player.get(0).toString());   //card 1
        setImg(user_1,player.get(0).toString());
        player.remove(0);
        face_Up_User.add(1,player.get(0).toString());           //card 2
        setImg(user_2,player.get(0).toString());
        player.remove(0);
        face_Up_User.add(2,player.get(0).toString());   //card 3
        setImg(user_3,player.get(0).toString());
        player.remove(0);

        //adding to computer cards and removing from deck the same card
        back_Comp.add(0,computer.get(0).toString());
        computer.remove(0);
        back_Comp.add(computer.get(0).toString());
        computer.remove(0);
        back_Comp.add(0,computer.get(0).toString());
        computer.remove(0);
        setImg(comp_1,"back");
        setImg(comp_2,"back");
        setImg(comp_3,"back");

        //add card from 3 computer cards to pile -> remove from the 3 crds -> remove from comp3 and add to cmp3 from comp deck
        pile.add(0,back_Comp.get(0).toString());
        setImg(pile_img, back_Comp.get(0).toString());
        back_Comp.remove(0);
        back_Comp.add(0,computer.get(0).toString());
        computer.remove(0);

    }

    public boolean tapUserCard1(View view) {

        int pile_check = 0;

        if(pile.size() != 0) {
            pile_check = getIntForCard(pile.get(0).toString());
        }
        int card1 = getIntForCard(face_Up_User.get(0).toString());
        int card2 = getIntForCard(face_Up_User.get(1).toString());
        int card3 = getIntForCard(face_Up_User.get(2).toString());

        //check if tapped card is grater than pile[0] card
        if(card1>=pile_check){
//            user_1.setTranslationY(1000f);
            setImg(userUp_1,face_Up_User.get(0).toString());
            face_Up_Up_User.remove(0);
            face_Up_Up_User.add(0,1);
            user_1.setImageResource(0);
            //userUp_1.animate().translationY(1000f).setDuration(200l);//.setTranslationY(1000f);
            return true;
        }
        else if(card1<pile_check && card2<pile_check && card3<pile_check){//pile -> player
            //reshuffle and redistribute
            int n = pile.size();
            for(int l=0; l<n; l++){
                player.add(0, pile.get(0).toString());
                pile.remove(0);
            }

            player.add(0,face_Up_User.get(0).toString());
            player.add(0,face_Up_User.get(1).toString());
            player.add(0,face_Up_User.get(2).toString());
            user_1.setImageResource(0);
            user_2.setImageResource(0);
            user_3.setImageResource(0);
            pile_img.setImageResource(0);

            face_Up_User.remove(0);
            face_Up_User.remove(0);
            face_Up_User.remove(0);

            face_Up_Up_User.remove(0);
            face_Up_Up_User.add(0,0);

            Collections.shuffle(player);

            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);
            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);
            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);

            setImg(user_1,face_Up_User.get(0).toString());
            setImg(user_2,face_Up_User.get(1).toString());
            setImg(user_3,face_Up_User.get(2).toString());
            pointsUser--;
            comp_points.setText("Computer: "+pointsComp);
            player_points.setText("User: "+pointsUser);
            if(pointsUser == 0 || (player.size() == 52 && computer.isEmpty())){
                alertBox = new AlertDialog.Builder(this);

                alertBox.setTitle("\t\t\t\t\t\t\t\t\tGame Over!").setMessage("Computer Wins!!\nDON'T GIVE UP GIVE HIM HELL NEXT TIME!!").setNeutralButton("reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset();
                        dialog.dismiss();
                    }
                }).create().show();
            }
            return false;
        }

        else{
            return false;
        }
    }
    public boolean returnCard1(View view){
//        userUp_1.animate().translationY(1000f).setDuration(200l);//                 setTranslationY(3000f);
        setImg(user_1,face_Up_User.get(0).toString());
        userUp_1.setImageResource(0);
        return true;
    }


    public boolean tapUserCard2(View view){

        int pile_check = 0;

        if(pile.size() != 0) {
            pile_check = getIntForCard(pile.get(0).toString());
        }
        int card1 = getIntForCard(face_Up_User.get(0).toString());
        int card2 = getIntForCard(face_Up_User.get(1).toString());
        int card3 = getIntForCard(face_Up_User.get(2).toString());
        //check if tapped card is grater than pile[0] card
        if(card2>=pile_check) {
            //user_2.animate().translationY(-1000f).setDuration(200l);//                 setTranslationY(3000f);
            setImg(userUp_2,face_Up_User.get(1).toString());
            face_Up_Up_User.remove(0);
            face_Up_Up_User.add(0,2);
            user_2.setImageResource(0);
            //userUp_2.animate().translationY(1000f).setDuration(200l);//.setTranslationY(3000f);

            return true;
        }

        else if(card1<pile_check && card2<pile_check && card3<pile_check){//pile -> player
            //reshuffle and redistribute
            int n = pile.size();
            for(int l=0; l<n; l++){
                player.add(0, pile.get(0).toString());
                pile.remove(0);
            }

            player.add(0,face_Up_User.get(0).toString());
            player.add(0,face_Up_User.get(1).toString());
            player.add(0,face_Up_User.get(2).toString());
            user_1.setImageResource(0);
            user_2.setImageResource(0);
            user_3.setImageResource(0);
            pile_img.setImageResource(0);

            face_Up_Up_User.remove(0);
            face_Up_Up_User.add(0,0);

            face_Up_User.remove(0);
            face_Up_User.remove(0);
            face_Up_User.remove(0);

            Collections.shuffle(player);

            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);
            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);
            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);

            setImg(user_1,face_Up_User.get(0).toString());
            setImg(user_2,face_Up_User.get(1).toString());
            setImg(user_3,face_Up_User.get(2).toString());
            pointsUser--;
            comp_points.setText("Computer: "+pointsComp);
            player_points.setText("User: "+pointsUser);
            if(pointsUser == 0 || (player.size() == 52 && computer.isEmpty())){
                alertBox = new AlertDialog.Builder(this);

                alertBox.setTitle("\t\t\t\t\t\t\t\t\tGame Over!").setMessage("Computer Wins!!\nDON'T GIVE UP GIVE HIM HELL NEXT TIME!!").setNeutralButton("reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset();//////////////////method create and computer resetand stuff
                        dialog.dismiss();
                    }
                }).create().show();
            }
            return false;
        }

        else {
            return false;
        }
    }

    public boolean returnCard2(View view){
//        userUp_2.animate().translationY(1000f).setDuration(200l);//                 setTranslationY(3000f);
        setImg(user_2,face_Up_User.get(1).toString());
        userUp_2.setImageResource(0);
        return true;
    }

    public boolean tapUserCard3(View view){

        int pile_check = 0;

        if(pile.size() != 0) {
            pile_check = getIntForCard(pile.get(0).toString());
        }
        int card1 = getIntForCard(face_Up_User.get(0).toString());
        int card2 = getIntForCard(face_Up_User.get(1).toString());
        int card3 = getIntForCard(face_Up_User.get(2).toString());
        //check if tapped card is grater than pile[0] card
        if(card3>=pile_check) {
            //user_3.animate().translationY(-1000f).setDuration(200l);//.setTranslationY(1000f);
            setImg(userUp_3,face_Up_User.get(2).toString());
            face_Up_Up_User.remove(0);
            face_Up_Up_User.add(0,3);
            user_3.setImageResource(0);
            //userUp_3.animate().translationY(1000f).setDuration(200l);//.setTranslationY(1000f);

            return true;
        }

        else if(card1<pile_check && card2<pile_check && card3<pile_check){//pile -> player
            //reshuffle and redistribute
            int n = pile.size();
            for(int l=0; l<n; l++){
                player.add(0, pile.get(0).toString());
                pile.remove(0);
            }

            player.add(0,face_Up_User.get(0).toString());
            player.add(0,face_Up_User.get(1).toString());
            player.add(0,face_Up_User.get(2).toString());
            user_1.setImageResource(0);
            user_2.setImageResource(0);
            user_3.setImageResource(0);
            pile_img.setImageResource(0);

            face_Up_Up_User.remove(0);
            face_Up_Up_User.add(0,0);

            face_Up_User.remove(0);
            face_Up_User.remove(0);
            face_Up_User.remove(0);

            Collections.shuffle(player);

            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);
            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);
            face_Up_User.add(0,player.get(0).toString());
            player.remove(0);

            setImg(user_1,face_Up_User.get(0).toString());
            setImg(user_2,face_Up_User.get(1).toString());
            setImg(user_3,face_Up_User.get(2).toString());
            pointsUser--;
            comp_points.setText("Computer: "+pointsComp);
            player_points.setText("User: "+pointsUser);
            if(pointsUser == 0 || (player.size() == 52 && computer.isEmpty())){
                alertBox = new AlertDialog.Builder(this);

                alertBox.setTitle("\t\t\t\t\t\t\t\t\tGame Over!").setMessage("Computer Wins!!\nDON'T GIVE UP GIVE HIM HELL NEXT TIME!!").setNeutralButton("reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset();//////////////////method create and computer reset and stuff
                        dialog.dismiss();
                    }
                }).create().show();
            }
            return false;
        }

        else{
            return false;
        }
    }
    public boolean returnCard3(View view){
        //userUp_3.animate().translationY(1000f).setDuration(200l);//                 setTranslationY(3000f);
        setImg(user_3,face_Up_User.get(2).toString());
        userUp_3.setImageResource(0);
        return true;
    }

    public void gameReset(){
        user_1.setImageResource(0);
        user_2.setImageResource(0);
        user_3.setImageResource(0);

        userUp_1.setImageResource(0);
        userUp_2.setImageResource(0);
        userUp_3.setImageResource(0);
        pile_img.setImageResource(0);
        comp_1.setImageResource(0);
        comp_2.setImageResource(0);
        comp_3.setImageResource(0);

        pointsUser = 5;
        pointsComp = pointsUser;

        suits = new ArrayList(Arrays.asList("d","h","c","s"));
        cards = new ArrayList(Arrays.asList("2","3","4","5","6","7","8","9","10","j","q","k","a"));
        deck = new ArrayList(Arrays.asList("sa", "sk", "sq", "sj", "s10", "s9", "s8", "s7", "s6", "s5", "s4", "s3", "s2", "ca", "ck", "cq", "cj", "c10", "c9", "c8", "c7", "c6", "c5", "c4", "c3", "c2", "ha", "hk", "hq", "hj", "h10", "h9", "h8", "h7", "h6", "h5", "h4", "h3", "h2", "da", "dk", "dq", "dj", "d10", "d9", "d8", "d7", "d6", "d5", "d4", "d3", "d2"));
        pile = new ArrayList(52);
        trash = new ArrayList(52);
        player = new ArrayList(52);
        computer = new ArrayList(52);
        face_Up_User = new ArrayList(3);
        face_Up_Up_User = new ArrayList(Arrays.asList(0));
        back_Comp = new ArrayList(3);

        ace = new ArrayList(Arrays.asList("ha","da","ca","sa"));
        king = new ArrayList(Arrays.asList("hk","dk","ck","sk"));
        queen = new ArrayList(Arrays.asList("hq","dq","cq","sq"));
        jack = new ArrayList(Arrays.asList("hj","dj","cj","sj"));
        ten = new ArrayList(Arrays.asList("h10","d10","c10","s10"));
        nine = new ArrayList(Arrays.asList("h9","d9","c9","s9"));
        eight = new ArrayList(Arrays.asList("h8","d8","c8","s8"));
        seven = new ArrayList(Arrays.asList("h7","d7","c7","s7"));
        six = new ArrayList(Arrays.asList("h6","d6","c6","s6"));
        five = new ArrayList(Arrays.asList("h5","d5","c5","s5"));
        four = new ArrayList(Arrays.asList("h4","d4","c4","s4"));
        three = new ArrayList(Arrays.asList("h3","d3","c3","s3"));
        two = new ArrayList(Arrays.asList("h2","d2","c2","s2"));
        inCreate();
    }

    public void computer_turn(){
        int pile_check = 0;

        if(pile.size() != 0) {
            pile_check = getIntForCard(pile.get(0).toString());
        }
        int comp1 = getIntForCard(back_Comp.get(0).toString());
        int comp2 = getIntForCard(back_Comp.get(1).toString());
        int comp3 = getIntForCard(back_Comp.get(2).toString());
        if(comp1>=pile_check){
            pile.add(0,back_Comp.get(0).toString());
            setImg(pile_img,back_Comp.get(0).toString());
            back_Comp.remove(0);
            back_Comp.add(0,computer.get(0).toString());
        }
        else if(comp2>=pile_check){
            pile.add(0,back_Comp.get(1).toString());
            setImg(pile_img,back_Comp.get(1).toString());
            back_Comp.remove(1);
            back_Comp.add(1,computer.get(0).toString());
        }
        else if(comp3>=pile_check){
            pile.add(0,back_Comp.get(2).toString());
            setImg(pile_img,back_Comp.get(2).toString());
            back_Comp.remove(2);
            back_Comp.add(2,computer.get(0).toString());
        }
        else if(comp1<pile_check && comp2<pile_check && comp3<pile_check){

            int n = pile.size();
            for(int l=0; l<n; l++) {
                computer.add(0, pile.get(0).toString());
                pile.remove(0);
            }
            computer.add(0,back_Comp.get(0).toString());
            computer.add(0,back_Comp.get(1).toString());
            computer.add(0,back_Comp.get(2).toString());
            back_Comp.remove(0);
            back_Comp.remove(0);
            back_Comp.remove(0);

            pile_img.setImageResource(0);
            comp_1.setImageResource(0);
            comp_2.setImageResource(0);
            comp_3.setImageResource(0);


            Collections.shuffle(computer);
            back_Comp.add(0, computer.get(0).toString());
            computer.remove(0);
            back_Comp.add(0, computer.get(0).toString());
            computer.remove(0);
            back_Comp.add(0, computer.get(0).toString());
            computer.remove(0);
            setImg(comp_1,"back");
            setImg(comp_2,"back");
            setImg(comp_3,"back");

            pile.add(0,back_Comp.get(0).toString());
            setImg(pile_img, back_Comp.get(0).toString());
            back_Comp.remove(0);
            back_Comp.add(0,computer.get(0).toString());
            computer.remove(0);
            pointsComp--;
            comp_points.setText("Computer: "+pointsComp);
            player_points.setText("User: "+pointsUser);
            if(pointsComp == 0 || (player.size() == 52 && computer.isEmpty())){
                alertBox = new AlertDialog.Builder(this);

                alertBox.setTitle("\t\t\t\t\t\t\t\tCongratulations!").setMessage("\t\t\t\t\t\t\t\t\tYou Win!!").setNeutralButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset();//////////////////method create and computer resetand stuff
                        dialog.dismiss();
                    }
                }).create().show();

            }

        }
    }

    public boolean tapOnPile(View view){
        int pile_check = 0;

        if(pile.size() != 0) {
            pile_check = getIntForCard(pile.get(0).toString());
        }
        int card1 = getIntForCard(face_Up_User.get(0).toString());
        int card2 = getIntForCard(face_Up_User.get(1).toString());
        int card3 = getIntForCard(face_Up_User.get(2).toString());
        if(card1>=pile_check && face_Up_Up_User.contains(1)){
            pile.add(0, face_Up_User.get(0).toString());
            setImg(pile_img, face_Up_User.get(0).toString());
            face_Up_User.remove(0);
            userUp_1.setImageResource(0);
            face_Up_User.add(0, player.get(0).toString());
            setImg(user_1, player.get(0).toString());
            player.remove(0);
            computer_turn();  //systematic flow of computers turn
        }
        else if(card2>=pile_check && face_Up_Up_User.contains(2)){
            pile.add(0, face_Up_User.get(1).toString());
            setImg(pile_img, face_Up_User.get(1).toString());
            face_Up_User.remove(1);
            userUp_2.setImageResource(0);
            face_Up_User.add(1, player.get(0).toString());
            setImg(user_2, player.get(0).toString());
            player.remove(0);
            computer_turn();
        }
        else if(card3>=pile_check && face_Up_Up_User.contains(3)){
            pile.add(0, face_Up_User.get(2).toString());
            setImg(pile_img, face_Up_User.get(2).toString());
            face_Up_User.remove(2);
            userUp_3.setImageResource(0);
            face_Up_User.add(2, player.get(0).toString());
            setImg(user_3, player.get(0).toString());
            player.remove(0);
            computer_turn();
        }
        else {}
        return false;
    }

    public int getIntForCard(String card){
        if(ace.contains(card)){
            return 13;
        }
        else if(king.contains(card)){
            return 12;
        }
        else if (queen.contains(card)){
            return 11;
        }
        else if (jack.contains(card)){
            return 10;
        }
        else if (ten.contains(card)){
            return 9;
        }
        else if (nine.contains(card)){
            return 8;
        }
        else if (eight.contains(card)){
            return 7;
        }
        else if (seven.contains(card)){
            return 6;
        }
        else if (six.contains(card)){
            return 5;
        }
        else if (five.contains(card)){
            return 4;
        }
        else if (four.contains(card)){
            return 3;
        }
        else if (three.contains(card)){
            return 2;
        }
        else if (two.contains(card)){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void setImg(ImageView view,String card) {
        switch (card) {
//1
            case "c2":
                view.setImageResource(R.drawable.c2);
                break;
//2
            case "c3":
                view.setImageResource(R.drawable.c3);
                break;
//3
            case "c4":
                view.setImageResource(R.drawable.c4);
                break;
//4
            case "c5":
                view.setImageResource(R.drawable.c5);
                break;
//5
            case "c6":
                view.setImageResource(R.drawable.c6);
                break;
//6
            case "c7":
                view.setImageResource(R.drawable.c7);
                break;
//7
            case "c8":
                view.setImageResource(R.drawable.c8);
                break;
//8
            case "c9":
                view.setImageResource(R.drawable.c9);
                break;
//9
            case "c10":
                view.setImageResource(R.drawable.c10);
                break;
//10
            case "cj":
                view.setImageResource(R.drawable.cj);
                break;
//11
            case "cq":
                view.setImageResource(R.drawable.cq);
                break;
//12
            case "ck":
                view.setImageResource(R.drawable.ck);
                break;
//13
            case "ca":
                view.setImageResource(R.drawable.ca);
                break;
//14
            case "d2":
                view.setImageResource(R.drawable.d2);
                break;
//15
            case "d3":
                view.setImageResource(R.drawable.d3);
                break;
//16
            case "d4":
                view.setImageResource(R.drawable.d4);
                break;
//17
            case "d5":
                view.setImageResource(R.drawable.d5);
                break;
//18
            case "d6":
                view.setImageResource(R.drawable.d6);
                break;
//19
            case "d7":
                view.setImageResource(R.drawable.d7);
                break;
//20
            case "d8":
                view.setImageResource(R.drawable.d8);
                break;
//21
            case "d9":
                view.setImageResource(R.drawable.d9);
                break;
//22
            case "d10":
                view.setImageResource(R.drawable.d10);
                break;
//23
            case "dj":
                view.setImageResource(R.drawable.dj);
                break;
//24
            case "dq":
                view.setImageResource(R.drawable.dq);
                break;
//25
            case "dk":
                view.setImageResource(R.drawable.dk);
                break;
//26
            case "da":
                view.setImageResource(R.drawable.da);
                break;
//27
            case "s2":
                view.setImageResource(R.drawable.s2);
                break;
//28
            case "s3":
                view.setImageResource(R.drawable.s3);
                break;
//29
            case "s4":
                view.setImageResource(R.drawable.s4);
                break;
//30
            case "s5":
                view.setImageResource(R.drawable.s5);
                break;
//31
            case "s6":
                view.setImageResource(R.drawable.s6);
                break;
//32
            case "s7":
                view.setImageResource(R.drawable.s7);
                break;
//33
            case "s8":
                view.setImageResource(R.drawable.s8);
                break;
//34
            case "s9":
                view.setImageResource(R.drawable.s9);
                break;
//35
            case "s10":
                view.setImageResource(R.drawable.s10);
                break;
//36
            case "sj":
                view.setImageResource(R.drawable.sj);
                break;
//37
            case "sq":
                view.setImageResource(R.drawable.sq);
                break;
//38
            case "sk":
                view.setImageResource(R.drawable.sk);
                break;
//39
            case "sa":
                view.setImageResource(R.drawable.sa);
                break;
//40
            case "h2":
                view.setImageResource(R.drawable.h2);
                break;
//41
            case "h3":
                view.setImageResource(R.drawable.h3);
                break;
//42
            case "h4":
                view.setImageResource(R.drawable.h4);
                break;
//43
            case "h5":
                view.setImageResource(R.drawable.h5);
                break;
//44
            case "h6":
                view.setImageResource(R.drawable.h6);
                break;
//45
            case "h7":
                view.setImageResource(R.drawable.h7);
                break;
//46
            case "h8":
                view.setImageResource(R.drawable.h8);
                break;
//47
            case "h9":
                view.setImageResource(R.drawable.h9);
                break;
//48
            case "h10":
                view.setImageResource(R.drawable.h10);
                break;
//49
            case "hj":
                view.setImageResource(R.drawable.hj);
                break;
//50
            case "hq":
                view.setImageResource(R.drawable.hq);
                break;
//51
            case "hk":
                view.setImageResource(R.drawable.hk);
                break;
//52
            case "ha":
                view.setImageResource(R.drawable.ha);
                break;
//53
            case "back":
                view.setImageResource(R.drawable.back);
                break;

            default:
                view.setImageResource(0);
                break;
        }
    }

}