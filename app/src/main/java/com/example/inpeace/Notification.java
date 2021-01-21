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
                NotificationManager.IMPORTANCE_HIGH
        );

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
      }
    }
}
