package com.example.inpeace.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import static com.example.inpeace.Notification.PLAYER_ID;

public class MusicPlayerActivity extends AppCompatActivity {

    private TextView SongName;
    private TextView urlholder;

    private TextView currenttimesongTVMusic , totaltimesongTVMusic;
    private ImageButton playpausebuttonMusic ;
    private SeekBar mainPlayerseekbar;
    private MediaPlayer player;
    private ImageView songImage;
    private Handler handler = new Handler();
    private CardView miniPlayer;
    private ProgressBar progressBar;
    private NotificationManagerCompat manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        urlholder = findViewById(R.id.URL_HOLDER);

        songImage = findViewById(R.id.SongImage);
        SongName = findViewById(R.id.songplayingnameTVMusic);
        SongName.setText(getIntent().getStringExtra("songName"));
        urlholder.setText(getIntent().getStringExtra("URL"));
        currenttimesongTVMusic = findViewById(R.id.currenttimesongTVMusic);
        totaltimesongTVMusic = findViewById(R.id.totaltimesongTVMusic);
        playpausebuttonMusic = findViewById(R.id.playpausebuttonMusic);
        mainPlayerseekbar = findViewById(R.id.mainPlayerseekbar);
        player = new MediaPlayer();
        progressBar = findViewById(R.id.musicPlayerLoading);
        manager = NotificationManagerCompat.from(this);

        notificationMusic();
        progressBar = new ProgressBar(this);

        mainPlayerseekbar.setMax(100);

        Picasso.get().load(getIntent().getStringExtra("image")).into(songImage);
        playpausebuttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });


                preparemediaplayer(getIntent().getStringExtra("URL"));
//                totaltimesongTVMusic.setText(player.getDuration());
                if(player.isPlaying()){
                    handler.removeCallbacks(updater);
                    player.pause();
                    playpausebuttonMusic.setImageResource(R.drawable.ic_play);
                }else {
                    player.start();
                    playpausebuttonMusic.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
                }
            }
        });

//        preparemediaplayer(getIntent().getStringExtra("URL"));

        mainPlayerseekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar bar = (SeekBar) v;
                int playPosition =(player.getDuration()/100)*bar.getProgress();
                player.seekTo(playPosition);
                currenttimesongTVMusic.setText(millisecondtosecond(player.getCurrentPosition()));
                return false;
            }
        });

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                mainPlayerseekbar.setSecondaryProgress(percent);
            }
        });

    }


    public String  millisecondtosecond(long millisecond){
        String timerstring = "";
        String secondstring ;

        int hours = (int) (millisecond/(1000*60*60));
        int minutes = (int) (millisecond%(1000*60*60))/(1000*60);
        int seconds = (int) ((millisecond%(1000*60*60))%(1000*60)/1000);

        if(hours>0){
            timerstring = hours + ":";
        }

        if(seconds<10){
            secondstring = "0" + seconds;
        }else {
            secondstring ="" +seconds;
        }

        timerstring = timerstring + minutes + ":" + secondstring;
        return timerstring;

    }

    private void updateSeekBar(){
        if(player.isPlaying()){
            mainPlayerseekbar.setProgress((int) (((float)player.getCurrentPosition()/player.getDuration())*100));
            handler.postDelayed(updater,1000);
        }
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentduration = player.getCurrentPosition();
            currenttimesongTVMusic.setText(millisecondtosecond(currentduration));
        }
    };

    private void preparemediaplayer(String URL){
        try {
            player.setDataSource(URL);
            player.prepare();
            totaltimesongTVMusic.setText(player.getDuration());
        }catch (Exception e){
            Toast.makeText(MusicPlayerActivity.this , e.toString() , Toast.LENGTH_LONG).show();
        }
    }

//    private void url(){
//        String str ="";
//        storageRef.child("abc/Chidiya_320(PaglaSongs).mp3").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                preparemediaplayer(uri.toString());
//                Log.d("ABC" , uri.toString());
//
//                //Toast.makeText(trail_music.this , " "+ str + " " ,Toast.LENGTH_LONG).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(MusicPlayerActivity.this , "error url" ,Toast.LENGTH_LONG).show();
//            }
//        });
//    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        miniPlayer= findViewById(R.id.miniMusicPlayer);
        if(keyCode == event.KEYCODE_BACK){
            miniPlayer.setVisibility(View.VISIBLE);
        }
       // player.stop();
        return super.onKeyDown(keyCode, event);
    }

    public void notificationMusic(){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.logo_splash);
//
//        PendingIntent intent = new PendingIntent(this,trail_music.class);

        Notification notification = new NotificationCompat.Builder(MusicPlayerActivity.this,PLAYER_ID)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.logo_splash)
//                .addAction(R.drawable.ic_new_pause,"Pause",intent)
//                .setStyle(new Notification.MediaStyle()
//                        .setShowActionsInCompactView(1 /* #1: pause button */)
//                        .setMediaSession(player.getSessionToken()))

                .setContentTitle("NAME")
                .setContentText("Title")

                .setLargeIcon(bitmap)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();

        manager.notify(1,notification);

    }

}
