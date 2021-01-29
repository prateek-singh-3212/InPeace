package com.example.inpeace.music;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import android.os.Handler;

public class AdapterMusic  extends FirebaseRecyclerAdapter<ModelMusic , AdapterMusic.Viewholder> {

    private OnItemClickListener mListener;
//    private Handler handler = new Handler(Looper.getMainLooper());
//    private MediaPlayer player= new MediaPlayer();

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public AdapterMusic(@NonNull FirebaseRecyclerOptions<ModelMusic> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull ModelMusic model) {
       holder.songName.setText(model.getSongname());
       holder.url.setText(model.getSongURL());
       holder.image.setText(model.getSongImage());
       Picasso.get().load(model.getSongImage()).into(holder.miniImageSong);

//        holder.URLofsong.
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_musicplayer, parent, false);
        return new AdapterMusic.Viewholder(view,mListener );
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private CardView cv;
        private TextView songName;
        private TextView url;
        private TextView image;
        private ImageView miniImageSong ;
        private String URLofsong = "";

        public Viewholder(@NonNull View itemView  ,OnItemClickListener listener) {
            super(itemView);
            songName = itemView.findViewById(R.id.songNameMusicCardView);
            url = itemView.findViewById(R.id.URL_holder);
            image = itemView.findViewById(R.id.IMAGE_holder);
            miniImageSong = itemView.findViewById(R.id.miniImageSong);



            // cv.findViewById(R.id.musicCV);
            //cv.setId(getAdapterPosition());

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext() , MusicPlayerActivity.class);
                    intent.putExtra("songName",songName.getText().toString());
                    intent.putExtra("URL",url.getText().toString());
                    intent.putExtra("image" ,image.getText().toString());
                    v.getContext().startActivity(intent);

                }
            } );
        }
    }

//    public class Viewholder extends RecyclerView.ViewHolder implements OnClickListener{
//
//        private TextView songName;
//        private String URLofsong = "";
//
//        public Viewholder(@NonNull View itemView) {
//            super(itemView);
//            songName = itemView.findViewById(R.id.songNameMusicCardView);
//            itemView.setOnClickListener(this);
//
//
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
//    }

}
