package com.example.inpeace.audiobooks.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.audiobooks.Model.Model_All_Fragments;
import com.example.inpeace.motivation.Adapter;
import com.example.inpeace.motivation.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adapter_All_Fragments extends FirebaseRecyclerAdapter<Model_All_Fragments, Adapter_All_Fragments.Viewholder > {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter_All_Fragments(@NonNull FirebaseRecyclerOptions<Model_All_Fragments> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Adapter_All_Fragments.Viewholder holder, int position, @NonNull Model_All_Fragments model) {

        holder.bookname.setText(model.getThumbnail());
//        holder.author.setText(model.getThumbnail());

    }

    @NonNull
    @Override
    public Adapter_All_Fragments.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_audiobook_bookholder,parent,false);

        return new Viewholder(view);
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        private TextView bookname,author;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            bookname = itemView.findViewById(R.id.audiobook_title_cardview);
            author = itemView.findViewById(R.id.author_audiobook_cardview);
        }
    }
}
