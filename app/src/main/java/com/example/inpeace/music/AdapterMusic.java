package com.example.inpeace.music;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterMusic  extends FirebaseRecyclerAdapter<ModelMusic , AdapterMusic.Viewholder> {

    private OnItemClickListener mListener;

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
//        holder.URLofsong.
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_musicplayer, parent, false);
        return new AdapterMusic.Viewholder(view,mListener);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView songName;
        private String URLofsong = "";

        public Viewholder(@NonNull View itemView , OnItemClickListener listener) {
            super(itemView);
            songName = itemView.findViewById(R.id.songNameMusicCardView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
