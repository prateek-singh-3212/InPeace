package com.example.inpeace.audiobooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.inpeace.R;
import com.example.inpeace.audiobooks.Adapter.Adapter_Chapter;
import com.example.inpeace.audiobooks.Model.Model_Details;
import com.example.inpeace.audiobooks.Model.Model_chapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class audiobooks_chapter extends AppCompatActivity {


    private RecyclerView audiobookRV;
    private Adapter_Chapter adapter;
    private ImageView background;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiobooks_chapter);

        String category = getIntent().getStringExtra("Bookcategory");
        String bookName = getIntent().getStringExtra("bookname");
        Log.d("abc",bookName);
        Log.d("abc",category);




        background = findViewById(R.id.chapterBackImage);
        audiobookRV = findViewById(R.id.audioBooks_chapter_RV);

        Picasso.get().load(getIntent().getStringExtra("imgUrl")).into(background);


        FirebaseRecyclerOptions<Model_chapter> options = new FirebaseRecyclerOptions.Builder<Model_chapter>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("newaudiobooks")
                        .child(category)
                        .child(bookName)
                .child("chapters"),Model_chapter.class)
                .build();



        audiobookRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter_Chapter(options,bookName,getIntent().getStringExtra("imgUrl"));
        audiobookRV.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}