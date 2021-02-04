package com.example.inpeace.motivation.newLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.inpeace.R;

public class VideoPlayer extends AppCompatActivity {

    private VideoView view;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        view = findViewById(R.id.ytvideoplayer);
//        youTubePlayerView = findViewById(R.id.ytvideoplayer);
        title = findViewById(R.id.Title);
        title.setText(getIntent().getStringExtra("Title"));



        Uri uri = Uri.parse(getIntent().getStringExtra("MiniURL"));
        MediaController controller = new MediaController(this);
        controller.show(3);
        view.setVideoURI(uri);
        view.start();

    }
}