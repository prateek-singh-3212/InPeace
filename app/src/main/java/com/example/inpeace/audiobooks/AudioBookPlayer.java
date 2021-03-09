package com.example.inpeace.audiobooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;

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

import com.example.inpeace.R;
import com.example.inpeace.audiobooks.Model.Model_Details;
import com.example.inpeace.music.MusicPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AudioBookPlayer extends AppCompatActivity {

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
        setContentView(R.layout.activity_audio_book_player);


        songImage = findViewById(R.id.SongImage123);
        SongName = findViewById(R.id.songplayingnameTVMusic123);
        currenttimesongTVMusic = findViewById(R.id.currenttimesongTVMusic123);
        totaltimesongTVMusic = findViewById(R.id.totaltimesongTVMusic123);
        playpausebuttonMusic = findViewById(R.id.playpausebuttonMusic123);
        mainPlayerseekbar = findViewById(R.id.mainPlayerseekbar123);
        player = new MediaPlayer();
        miniPlayer = findViewById(R.id.miniMusicPlayer);
        manager = NotificationManagerCompat.from(this);

        mediaSessionCompat = new MediaSessionCompat(this, "MEDIA SESSION");
        progressBar = findViewById(R.id.musicPlayerLoading123);


        mainPlayerseekbar.setMax(100);


        Log.d("ABC", " " + getIntent().getStringExtra("URL"));
//         notificationMusic();

//        NotificationImage image = new NotificationImage(getIntent().getStringExtra("image"));
//        image.doInBackground();

        //Set song Image

        Picasso.get().load(getIntent().getStringExtra("imgURL")).into(songImage);

        SongName.setText(getIntent().getStringExtra("BookName"));

        //PREPARE MEDIA PLAYER
        preparemediaplayer(getIntent().getStringExtra("URL"));


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
                Toast.makeText(AudioBookPlayer.this, "prepared", Toast.LENGTH_SHORT).show();
            }
        });

        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                Log.d("ABC",""+what+" "+extra);

                return true;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        miniPlayer = findViewById(R.id.miniMusicPlayer);
        if (keyCode == event.KEYCODE_BACK) {
            player.stop();
//            player.release();
//            miniPlayer.setVisibility(View.VISIBLE);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void addToLibrary(View view) {
        Toast.makeText(this,"Added To Library",Toast.LENGTH_SHORT).show();
    }

    public void moveForward(View view) {
        int seekForwardTime = 10000;
        int currentPosition = player.getCurrentPosition();
        if (currentPosition + seekForwardTime <= player.getDuration()) {
            player.seekTo(currentPosition + seekForwardTime);
        } else {
            player.seekTo(player.getDuration());
        }
        Toast.makeText(this , "+10 seconds" ,Toast.LENGTH_SHORT).show();
    }

    public void moveBackward(View view) {
        int seekForwardTime = 10000;
        int currentPosition = player.getCurrentPosition();
        if (currentPosition <= seekForwardTime) {
            player.seekTo(0);
        } else {
            player.seekTo(currentPosition-seekForwardTime);
        }
        Toast.makeText(this , "-10 seconds" ,Toast.LENGTH_SHORT).show();

    }
}