package com.example.inpeace.music.newLayout.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.music.newLayout.Model.CategoryModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapter extends FirebaseRecyclerAdapter<CategoryModel,MainAdapter.Viewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<CategoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MainAdapter.Viewholder holder, int position, @NonNull CategoryModel model) {

    }

    @NonNull
    @Override
    public MainAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView categoryName ;
        private RecyclerView musicRV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.newMusic_CategoryName);
            musicRV = itemView.findViewById(R.id.newMusic_Music_RV);

        }
    }
}
