package com.example.inpeace.motivation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inpeace.R;
import com.example.inpeace.music.AdapterMusic;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mainMotivation extends AppCompatActivity {

    private DatabaseReference mdatabase;
    private RecyclerView motivationView;
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_motivation);

        mdatabase = FirebaseDatabase.getInstance().getReference();
        motivationView = findViewById(R.id.motivationRecyclerView);

        motivationView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(mdatabase.child("motivation"),Model.class).build();

        adapter = new Adapter(options);
        motivationView.setAdapter(adapter);

    }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}