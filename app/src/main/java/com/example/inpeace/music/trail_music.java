package com.example.inpeace.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

import java.net.URI;

public class trail_music extends AppCompatActivity {

    private TextView currenttime , asas;
    private ImageButton playpause ;
    private SeekBar seekBar;
    private MediaPlayer player;
    private Handler  handler = new Handler();
    private ImageView view ;
//    private FirebaseStorage storage;
    private StorageReference storageRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_music);

        currenttime = findViewById(R.id.currenttime);
        asas = findViewById(R.id.musictime);
        view =findViewById(R.id.testbtn);
        playpause = findViewById(R.id.playpausebutton);
        seekBar = findViewById(R.id.seekbar);
        player = new MediaPlayer();
        storageRef = FirebaseStorage.getInstance().getReference();

        seekBar.setMax(100);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(trail_music.this ,"a", Toast.LENGTH_LONG).show();
                //url();
            }
        });

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(trail_music.this ,player.getDuration(),Toast.LENGTH_LONG).show();
//                Toast.makeText(trail_music.this , "cl" , Toast.LENGTH_LONG).show();
                if(player.isPlaying()){
                    handler.removeCallbacks(updater);
                    player.pause();
                    playpause.setImageResource(R.drawable.ic_play);
                }else {
                    player.start();
                    playpause.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
                }
            }
        });

        url();

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar bar = (SeekBar) v;
                int playPosition =(player.getDuration()/100)*bar.getProgress();
                player.seekTo(playPosition);
                currenttime.setText(millisecondtosecond(player.getCurrentPosition()));
                return false;
            }
        });

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);
            }
        });

    }

    private void url(){
          String str ="";
        storageRef.child("abc/Chidiya_320(PaglaSongs).mp3").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                preparemediaplayer(uri.toString());
                Log.d("ABC" , uri.toString());

                //Toast.makeText(trail_music.this , " "+ str + " " ,Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
               Toast.makeText(trail_music.this , "error url" ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void preparemediaplayer(String URL){
        try {
            player.setDataSource(URL);
            player.prepare();
            asas.setText(player.getDuration());
        }catch (Exception e){
            Toast.makeText(trail_music.this , "ye wala" , Toast.LENGTH_LONG).show();
        }
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentduration = player.getCurrentPosition();
            currenttime.setText(millisecondtosecond(currentduration));
        }
    };

    private void updateSeekBar(){
        if(player.isPlaying()){
            seekBar.setProgress((int) (((float)player.getCurrentPosition()/player.getDuration())*100));
            handler.postDelayed(updater,1000);
        }
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
}