package com.example.inpeace.motivation;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.music.AdapterMusic;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adapter extends FirebaseRecyclerAdapter<Model, Adapter.Viewholder > {

    private OnItemClickListener mListener;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    @Override
    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull Model model) {
        holder.Title.setText(model.getTitle());
        holder.Code.setText(model.getCode());
        holder.MiniURL.setText(model.getMiniURL());
        Uri uri = Uri.parse(model.getMiniURL());
        holder.ThumbnailVideo.setVideoURI(uri);
        holder.ThumbnailVideo.start();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_motivation, parent, false);
        return new Viewholder(view,mListener );
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView MiniURL ;
        private TextView Code;
        private TextView Title;
        private VideoView ThumbnailVideo;

        public Viewholder(@NonNull View itemView , OnItemClickListener listener) {
            super(itemView);

            MiniURL = itemView.findViewById(R.id.MiniURL);
            Code = itemView.findViewById(R.id.Code);
            Title = itemView.findViewById(R.id.Title);
            ThumbnailVideo = itemView.findViewById((R.id.motivationThumbnail));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext() , MainVideoPlayer.class);
                    intent.putExtra("Code",Code.getText().toString());
                    intent.putExtra("MiniURL",MiniURL.getText().toString());
                    intent.putExtra("Title" , Title.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });

        }
    }
}
