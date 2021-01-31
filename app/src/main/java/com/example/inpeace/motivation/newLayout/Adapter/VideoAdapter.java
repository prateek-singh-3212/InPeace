package com.example.inpeace.motivation.newLayout.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.motivation.newLayout.Model.VideoModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class VideoAdapter extends FirebaseRecyclerAdapter<VideoModel,VideoAdapter.Viewholder> {

    public VideoAdapter(@NonNull FirebaseRecyclerOptions<VideoModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull VideoModel model) {

        holder.title.setText(model.getTitle());
        Picasso.get().load(model.getThumbnail()).into(holder.image);

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoAdapter.Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_motivation,parent,false));
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.motivation_title);
            image = itemView.findViewById(R.id.motivation_image);
        }
    }
}
