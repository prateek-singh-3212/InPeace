package com.example.inpeace.motivation;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.inpeace.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainVideoPlayer extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    private VideoView view;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video_player);

        view = findViewById(R.id.ytvideoplayer);
//        youTubePlayerView = findViewById(R.id.ytvideoplayer);
        title = findViewById(R.id.Title);
        title.setText(getIntent().getStringExtra("Title"));

        Uri uri = Uri.parse(getIntent().getStringExtra("MiniURL"));
        MediaController controller = new MediaController(this);
        controller.show(3);
        view.setVideoURI(uri);
        view.start();

//        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo(getIntent().getStringExtra("Code"));
//               // youTubePlayerView.initialize("AIzaSyCgsGihrjTcLCgfER_aVeyiOdds4WPKmYA" , onInitializedListener);
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(MainVideoPlayer.this , "FAIL" , Toast.LENGTH_LONG).show();
//            }
//        };

    }
}