package com.example.inpeace.motivation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.example.inpeace.games.trial;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MotivationHome extends AppCompatActivity {


    private RecyclerView view;
    private Button btn ;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation_home);

        btn = findViewById(R.id.youtubePlayerPlayBtn);
        youTubePlayerView = findViewById(R.id.youtubePlayerPlayer);
        view = findViewById(R.id.listofvedio);


        view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("ZYxdhMAF4F0");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MotivationHome.this , "FAIL" , Toast.LENGTH_LONG).show();
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize("AIzaSyCgsGihrjTcLCgfER_aVeyiOdds4WPKmYA" , onInitializedListener);
            }
        });
    }

//    class myViewHolder extends RecyclerView.ViewHolder {
//
//        TextView phonee;
//        TextView namee;
//        Button calll ;
//        TextView agee;
//
//        public myViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            phonee = (TextView)itemView.findViewById(R.id.youtubePlayerThumbnail);
//
//        }
//    }
}