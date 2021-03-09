package com.example.inpeace.music.newLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.inpeace.R;
import com.example.inpeace.music.MusicPlayerActivity;
import com.example.inpeace.music.newLayout.Adapter.CategoryAdapter;
import com.example.inpeace.music.newLayout.Adapter.CategoryAdapterNew;
import com.example.inpeace.music.newLayout.Adapter.MusicAdapter;
import com.example.inpeace.music.newLayout.Model.CategoryModel;
import com.example.inpeace.music.newLayout.Model.MusicModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewMusicLayout extends AppCompatActivity {

    private RecyclerView category_RV;
    private List<CategoryModel> categoryModel;
    private MusicAdapter adapter;
    private CategoryAdapterNew categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_main_layout_new);

        category_RV = findViewById(R.id.newMusic_category_RV);

//        categoryModel = new ArrayList<>();
//        categoryModel.add(new CategoryModel("Most Liked"));
//        categoryModel.add(new CategoryModel("Meditation"));
//        categoryModel.add(new CategoryModel("Soft Wispers"));
//        categoryModel.add(new CategoryModel("Motivation"));
//

        //CATEGORY RECIVER
        FirebaseRecyclerOptions<CategoryModel> categoryOptions = new FirebaseRecyclerOptions.Builder<CategoryModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("newmusic"), CategoryModel.class)
                .build();


//        FirebaseRecyclerOptions<MusicModel> options = new FirebaseRecyclerOptions.Builder<MusicModel>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("music"), MusicModel.class)
//                .build();

      //   adapter = new MusicAdapter(options);
        category_RV.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapterNew(categoryOptions,this,categoryModel);
        category_RV.setAdapter(categoryAdapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        categoryAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryAdapter.stopListening();
    }
}