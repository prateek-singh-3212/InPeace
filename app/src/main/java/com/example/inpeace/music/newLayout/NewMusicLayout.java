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
import com.example.inpeace.music.newLayout.Model.CategoryModel;
import com.example.inpeace.music.newLayout.Model.MusicModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewMusicLayout extends AppCompatActivity {

    private RecyclerView category_RV;
    private List<CategoryModel> categoryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_main_layout_new);

        category_RV = findViewById(R.id.newMusic_category_RV);

        categoryModel = new ArrayList<>();
        categoryModel.add(new CategoryModel("Most Liked"));
        categoryModel.add(new CategoryModel("Meditation"));
        categoryModel.add(new CategoryModel("Soft Wispers"));
        categoryModel.add(new CategoryModel("Motivation"));

        FirebaseRecyclerOptions<MusicModel> options = new FirebaseRecyclerOptions.Builder<MusicModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("music"), MusicModel.class)
                .build();


        category_RV.setLayoutManager(new LinearLayoutManager(this));
        CategoryAdapter categoryAdapter = new CategoryAdapter(this,categoryModel,options);
        category_RV.setAdapter(categoryAdapter);



    }
}