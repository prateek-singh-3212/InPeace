package com.example.inpeace;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Notification extends Application {

    public static final String PLAYER_ID = "player";
    public static final String ACTION_PLAY = "Play";
    public static final String ACTION_PREVIOUS ="Previous";
    public static final String ACTION_NEXT = "Next";

    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();
    }

    public void createChannel(){

    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
        NotificationChannel channel = new NotificationChannel(
                PLAYER_ID,
                "Player",
                NotificationManager.IMPORTANCE_LOW
        );

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
      }
    }
}
