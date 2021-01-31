package com.example.inpeace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.inpeace.games.Tic_Tac_Toe;
import com.example.inpeace.games.games_homePage;
import com.example.inpeace.login.HomePage;
import com.example.inpeace.login.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {
    private static int handler =2000;
//    private AlertDialog.Builder asd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

//        asd = new AlertDialog.Builder(this);
//        asd.setTitle("sdad").setMessage("sddsad").setNeutralButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        }).create().show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SQLiteDatabase database = new SQLiteDatabase(MainActivity.this);
                if(database.checkUserIsSignedIn()){
                    Toast.makeText(MainActivity.this,"user is signed in",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, main.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent i=new Intent(
                            MainActivity.this
                            , HomePage.class);
                    startActivity(i);
                    Toast.makeText(MainActivity.this,"not signed in",Toast.LENGTH_LONG).show();
                    finish();

                }
                finish();
            }
        }, handler);
    }

    public void addToLibrary(View view) {
        Toast.makeText(this,"Added to Library",Toast.LENGTH_SHORT).show();
    }

}