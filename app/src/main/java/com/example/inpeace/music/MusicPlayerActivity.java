package com.example.inpeace.music;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.inpeace.R;
import com.example.inpeace.music.newLayout.NotificationReciver;
import com.squareup.picasso.Picasso;

import static com.example.inpeace.Notification.ACTION_NEXT;
import static com.example.inpeace.Notification.ACTION_PLAY;
import static com.example.inpeace.Notification.ACTION_PREVIOUS;
import static com.example.inpeace.Notification.PLAYER_ID;

public class MusicPlayerActivity extends AppCompatActivity {

    private TextView SongName;

    private TextView currenttimesongTVMusic , totaltimesongTVMusic;
    private ImageButton playpausebuttonMusic ;
    private SeekBar mainPlayerseekbar;
    private MediaPlayer player;
    private ImageView songImage;
    private Handler handler = new Handler();
    private CardView miniPlayer;
    private ProgressBar progressBar;
    private NotificationManagerCompat manager;
    private MediaSessionCompat mediaSessionCompat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        songImage = findViewById(R.id.SongImage);
        SongName = findViewById(R.id.songplayingnameTVMusic);
        SongName.setText(getIntent().getStringExtra("songName"));
        currenttimesongTVMusic = findViewById(R.id.currenttimesongTVMusic);
        totaltimesongTVMusic = findViewById(R.id.totaltimesongTVMusic);
        playpausebuttonMusic = findViewById(R.id.playpausebuttonMusic);
        mainPlayerseekbar = findViewById(R.id.mainPlayerseekbar);
        player = new MediaPlayer();
        miniPlayer = findViewById(R.id.miniMusicPlayer);
        manager = NotificationManagerCompat.from(this);

        mediaSessionCompat = new MediaSessionCompat(this, "MEDIA SESSION");
        progressBar = findViewById(R.id.musicPlayerLoading);


        mainPlayerseekbar.setMax(100);

//         notificationMusic();

//        NotificationImage image = new NotificationImage(getIntent().getStringExtra("image"));
//        image.doInBackground();

        //Set song Image
        Picasso.get().load(getIntent().getStringExtra("image")).into(songImage);

        preparemediaplayer(getIntent().getStringExtra("URL"));


        //Play Pause button of Music
        playpausebuttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    handler.removeCallbacks(updater);
                    player.pause();
                    playpausebuttonMusic.setImageResource(R.drawable.ic_play);
//                    notificationMusic(R.drawable.ic_play);
                }else {
                    player.start();
                    playpausebuttonMusic.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
//                    notificationMusic(R.drawable.ic_pause);
                }
            }
        });


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

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("ABCD", millisecondtosecond(mp.getDuration()));
                totaltimesongTVMusic.setText(millisecondtosecond(mp.getDuration()));
                progressBar.setVisibility(View.INVISIBLE);
                playpausebuttonMusic.setVisibility(View.VISIBLE);
                mp.start();
                if (player.isPlaying()) {
//                    player.pause();
                    updateSeekBar();
                    playpausebuttonMusic.setImageResource(R.drawable.ic_pause);
//                    notificationMusic(R.drawable.ic_play);
                } else {
//                    player.start();
                    playpausebuttonMusic.setImageResource(R.drawable.ic_play);
                    handler.removeCallbacks(updater);

//                    notificationMusic(R.drawable.ic_pause);
                }
                Toast.makeText(MusicPlayerActivity.this, "prepared", Toast.LENGTH_SHORT).show();
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

    private void progressBar(){
        if(player.isPlaying()){
            progressBar.setVisibility(View.INVISIBLE);
        }else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }


    private void preparemediaplayer(String URL){
        try {
            player.setDataSource(URL);
            player.prepareAsync();
            progressBar.setVisibility(View.VISIBLE);
            playpausebuttonMusic.setVisibility(View.INVISIBLE);


        }catch (Exception e){
           // Toast.makeText(MusicPlayerActivity.this , e.getMessage().toString() , Toast.LENGTH_LONG).show();
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
        miniPlayer = findViewById(R.id.miniMusicPlayer);
        if (keyCode == event.KEYCODE_BACK) {
            player.stop();
//            miniPlayer.setVisibility(View.VISIBLE);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void notificationMusic() {


        Intent intent = new Intent(this, MusicPlayerActivity.class);
        PendingIntent onClick = PendingIntent.getBroadcast(this, 0, intent, 0);

        Intent playIntent = new Intent(this, NotificationReciver.class).setAction(ACTION_PLAY);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 0, playIntent, 0);

        Intent previousIntent = new Intent(this, NotificationReciver.class).setAction(ACTION_PREVIOUS);
        PendingIntent previousPendingIntent = PendingIntent.getBroadcast(this, 0, previousIntent, 0);

            Intent nextIntent = new Intent(this, NotificationReciver.class).setAction(ACTION_NEXT);
            PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 0, nextIntent, 0);


            Notification notification = new NotificationCompat.Builder(MusicPlayerActivity.this, PLAYER_ID)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setSmallIcon(R.drawable.logo_splash)
                    .addAction(R.drawable.ic_skip_previous, "PREVIOUS", previousPendingIntent)
                    .addAction(R.drawable.logo_splash, "PLAY", playPendingIntent)
                    .addAction(R.drawable.ic_skip_next, "NEXT", nextPendingIntent)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setMediaSession(mediaSessionCompat.getSessionToken()))
                    .setContentTitle(getIntent().getStringExtra("songName"))
                    //.setContentText(getIntent().getStringExtra(""))
                    .setOngoing(true)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setLargeIcon(new NotificationImage(getIntent().getStringExtra("image")).doInBackground())
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setContentIntent(onClick)
                    .setOnlyAlertOnce(true)
                    .build();

        manager.notify(1, notification);

    }


    public void addToLibrary(View view) {
        Toast.makeText(this, "Added to Library", Toast.LENGTH_SHORT).show();

    }


}
