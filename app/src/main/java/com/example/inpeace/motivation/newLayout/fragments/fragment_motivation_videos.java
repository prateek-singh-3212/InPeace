package com.example.inpeace.motivation.newLayout.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inpeace.R;
import com.example.inpeace.motivation.newLayout.Adapter.QuotesAdapter;
import com.example.inpeace.motivation.newLayout.Adapter.VideoAdapter;
import com.example.inpeace.motivation.newLayout.Model.QuotesModel;
import com.example.inpeace.motivation.newLayout.Model.VideoModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class fragment_motivation_videos extends Fragment {

    private RecyclerView recyclerView;
    private VideoAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_motivation_videos() {
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
        View view = inflater.inflate(R.layout.fragment_motivation_videos, container, false);

        FirebaseRecyclerOptions<VideoModel> options = new FirebaseRecyclerOptions.Builder<VideoModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("azurevideo"),VideoModel.class)
                .build();

        recyclerView = view.findViewById(R.id.motivation_videos_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new VideoAdapter(options);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    //    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        adapterMusic.startListening();
//    }
//    @Override
//    protected void onStop()
//    {
//        super.onStop();
//        adapterMusic.stopListening();
//    }
}