package com.example.inpeace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.inpeace.login.HomePage;

public class MainActivity extends AppCompatActivity {
    private static int handler =3000;
//    private AlertDialog.Builder asd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                Intent i=new Intent(
                        MainActivity.this
                        , HomePage.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, handler);
    }


}