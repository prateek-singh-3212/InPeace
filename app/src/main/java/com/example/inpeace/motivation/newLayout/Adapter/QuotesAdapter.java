package com.example.inpeace.motivation.newLayout.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.motivation.newLayout.Model.QuotesModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class QuotesAdapter extends FirebaseRecyclerAdapter<QuotesModel,QuotesAdapter.Viewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public QuotesAdapter(@NonNull FirebaseRecyclerOptions<QuotesModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull QuotesModel model) {
        Picasso.get().load(model.getImages()).into(holder.imageView);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_quotes,parent,false));
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.quotes_image);

        }
    }
}
