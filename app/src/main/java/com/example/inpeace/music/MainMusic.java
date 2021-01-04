package com.example.inpeace.music;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
   // private  FirebaseRecyclerOptions<ModelMusic> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_music);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycleviewMusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<ModelMusic>  options = new FirebaseRecyclerOptions.Builder<ModelMusic>()
                .setQuery(mDatabase.child("music"), ModelMusic.class)
                .build();

        adapterMusic = new AdapterMusic(options);
        recyclerView.setAdapter(adapterMusic);

        adapterMusic.setOnClickListener(new AdapterMusic.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String done = "Music"+(position + 1);
                FirebaseDatabase.getInstance().getReference().child(done).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        ClickedMusicModel post = snapshot.getValue(ClickedMusicModel.class);
                        post.changeTEXT(done);
                        adapterMusic.notifyItemChanged(position);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

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