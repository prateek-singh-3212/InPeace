package com.example.inpeace.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MusicPlayerActivity extends AppCompatActivity {

    private TextView SongName;
    private TextView urlholder;

    private TextView currenttimesongTVMusic , totaltimesongTVMusic;
    private ImageButton playpausebuttonMusic ;
    private SeekBar mainPlayerseekbar;
    private MediaPlayer player;
    private ImageView songImage;
    private Handler handler = new Handler();


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

        mainPlayerseekbar.setMax(100);

        Picasso.get().load(getIntent().getStringExtra("image")).into(songImage);
        playpausebuttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        preparemediaplayer(getIntent().getStringExtra("URL"));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            player.stop();
        }
        return super.onKeyDown(keyCode, event);
    }
}
