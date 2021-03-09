package com.example.inpeace.music.newLayout.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.music.AdapterMusic;
import com.example.inpeace.music.ModelMusic;
import com.example.inpeace.music.MusicPlayerActivity;
import com.example.inpeace.music.newLayout.Model.MusicModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MusicAdapter extends FirebaseRecyclerAdapter<ModelMusic,MusicAdapter.Viewholder> {

    private OnItemClickListener mListener;


    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public MusicAdapter(@NonNull FirebaseRecyclerOptions<ModelMusic> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull ModelMusic model) {

        holder.newMusic_url.setText(model.getSongURL());
        holder.newMusic_image.setText(model.getSongImage());
        holder.newMusic_SongName.setText(model.getSongname());
        Picasso.get().load(model.getSongImage()).into(holder.newMusic_miniImage);

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_mostlike_music, parent, false);

        return new MusicAdapter.Viewholder(view);
    }

    public class Viewholder extends RecyclerView.ViewHolder  {

        private TextView newMusic_SongName;
        private TextView newMusic_url;
        private TextView newMusic_image;
        private ImageView newMusic_miniImage ;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            newMusic_SongName = itemView.findViewById(R.id.newMusic_SongName);
            newMusic_image = itemView.findViewById(R.id.newMusic_image);
            newMusic_miniImage = itemView.findViewById(R.id.newMusic_miniImage);
            newMusic_url= itemView.findViewById(R.id.newMusic_url);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext() , MusicPlayerActivity.class);
                    intent.putExtra("songName",newMusic_SongName.getText().toString());
                    intent.putExtra("URL",newMusic_url.getText().toString());
                    intent.putExtra("image" ,newMusic_image.getText().toString());
                    v.getContext().startActivity(intent);

                }
            } );

        }
    }
}
