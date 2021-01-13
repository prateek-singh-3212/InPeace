package com.example.inpeace.music;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.inpeace.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainMusic extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private AdapterMusic adapterMusic;
    private TextView songName;
   // private  FirebaseRecyclerOptions<ModelMusic> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_music);

        songName = findViewById(R.id.songNameMusicCardView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycleviewMusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<ModelMusic>  options = new FirebaseRecyclerOptions.Builder<ModelMusic>()
                .setQuery(mDatabase.child("music"), ModelMusic.class)
                .build();

        adapterMusic = new AdapterMusic(options);
        recyclerView.setAdapter(adapterMusic);

//        adapterMusic.setOnClickListener(new AdapterMusic.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                ModelMusic music = new ModelMusic();
//
//            }
//        });

    }
    @Override protected void onStart()
    {
        super.onStart();
        adapterMusic.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        adapterMusic.stopListening();
    }
}