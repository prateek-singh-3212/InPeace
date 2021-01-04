package com.example.inpeace.music;


import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlayer {
    private TextView currentTime , totalDuration;
    private SeekBar seekBar;
    private ImageView PlayPauseButton;
    private MediaPlayer player;
    private android.os.Handler handler = new Handler();



    private void preparemediaplayer(String URL){
        try {
            player.setDataSource(URL);
            player.prepare();
            totalDuration.setText(player.getDuration());
        }catch (Exception e){
          //  Toast.makeText(com.example.inpeace.music.MusicPlayer. , "ye wala" , Toast.LENGTH_LONG).show();
        }
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentduration = player.getCurrentPosition();
            currentTime.setText(millisecondtosecond(currentduration));
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
