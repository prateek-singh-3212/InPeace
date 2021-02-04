package com.example.inpeace.audiobooks.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inpeace.R;
import com.example.inpeace.audiobooks.Adapter.Adapter_All_Fragments;
import com.example.inpeace.audiobooks.Model.Model_All_Fragments;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link humor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class humor extends Fragment {

    private Adapter_All_Fragments adapter ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public humor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment humor.
     */
    // TODO: Rename and change types and number of parameters
    public static humor newInstance(String param1, String param2) {
        humor fragment = new humor();
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

        View view = inflater.inflate(R.layout.fragment_humor, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.humorRV);
        FirebaseRecyclerOptions<Model_All_Fragments> options = new FirebaseRecyclerOptions.Builder<Model_All_Fragments>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("newaudiobooks").child("humor"),Model_All_Fragments.class)
                .build();



        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new Adapter_All_Fragments(options);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}