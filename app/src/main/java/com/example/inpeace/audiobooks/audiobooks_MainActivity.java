package com.example.inpeace.audiobooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;

import com.example.inpeace.R;
import com.example.inpeace.audiobooks.Adapter.Adapter_All_Fragments;
import com.example.inpeace.audiobooks.Model.Model_All_Fragments;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class audiobooks_MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private Adapter_All_Fragments adapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiobooks__main);


        databaseReference =  FirebaseDatabase.getInstance().getReference().child("audiobooks").child("Love");

        recyclerView = findViewById(R.id.rcv);
        FirebaseRecyclerOptions<Model_All_Fragments> options = new FirebaseRecyclerOptions.Builder<Model_All_Fragments>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("audiobooks").child("Love"),Model_All_Fragments.class)
                .build();


//        LinearLayoutManager manager = ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter_All_Fragments(options);
        recyclerView.setAdapter(adapter);

//        FirebaseDatabase.getInstance().getReference().child("audiobooks").child("Love")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                String data = " ";
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                     Log.d("ABC",dataSnapshot.child("bookname").getValue().toString());
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("ABC",error.getMessage());
//            }
//        });

//        Fragment fragment = new audiobook_content();
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().addToBackStack(null).replace(R.id.audiobooks_content_fragment,fragment,null).commit();

    }
}