package com.example.inpeace.motivation.newLayout.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inpeace.R;
import com.example.inpeace.activites.Database;
import com.example.inpeace.motivation.newLayout.Adapter.QuotesAdapter;
import com.example.inpeace.motivation.newLayout.Model.QuotesModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_motivation_quotes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_motivation_quotes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_motivation_videos.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_motivation_videos newInstance(String param1, String param2) {
        fragment_motivation_videos fragment = new fragment_motivation_videos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_motivation_quotes, container, false);

        FirebaseDatabase.getInstance().getReference().child("newquotes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Log.d("ALER"," "+snap.child("image").getValue());
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseRecyclerOptions<QuotesModel> options = new FirebaseRecyclerOptions.Builder<QuotesModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("newquotes"),QuotesModel.class)
                .build();

        RecyclerView recyclerView = view.findViewById(R.id.motivation_quotes_RV);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        QuotesAdapter adapter = new QuotesAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
    }
}