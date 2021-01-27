package com.example.inpeace.audiobooks;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inpeace.R;
import com.example.inpeace.audiobooks.Adapter.Adapter_All_Fragments;
import com.example.inpeace.audiobooks.Model.Model_All_Fragments;
import com.example.inpeace.motivation.Adapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class audiobook_content extends Fragment {

    private DatabaseReference databaseReference;
    private Adapter_All_Fragments adapter;
    private RecyclerView recyclerView;

//    public audiobook_content(RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_audiobook_content, container, false);

       databaseReference =  FirebaseDatabase.getInstance().getReference().child("audiobooks").child("Love");

        recyclerView = view.findViewById(R.id.audiobooks_content_recyclerview);
        FirebaseRecyclerOptions<Model_All_Fragments> options = new FirebaseRecyclerOptions.Builder<Model_All_Fragments>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("audiobooks").child("Love"),Model_All_Fragments.class)
                .build();


        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new Adapter_All_Fragments(options);
        recyclerView.setAdapter(adapter);

        return view;
    }
}