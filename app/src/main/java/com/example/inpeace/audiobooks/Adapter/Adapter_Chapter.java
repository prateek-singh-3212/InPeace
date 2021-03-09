package com.example.inpeace.audiobooks.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.audiobooks.AudioBookPlayer;
import com.example.inpeace.audiobooks.Model.Model_chapter;
import com.example.inpeace.music.MusicPlayerActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adapter_Chapter  extends FirebaseRecyclerAdapter<Model_chapter,Adapter_Chapter.Viewholder> {

    private OnItemClickListener mListener;
    private String bookname,URL;
    private String TAG = "ABC";

    interface OnItemClickListener{
        void OnItemClick(int position);
    }

    private void setOnClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter_Chapter(@NonNull FirebaseRecyclerOptions<Model_chapter> options, String bookname,String URL) {
        super(options);
        this.bookname = bookname;
        this.URL = URL;
    }

    @Override
    protected void onBindViewHolder(@NonNull Adapter_Chapter.Viewholder holder, int position, @NonNull Model_chapter model) {
        holder.ChapAudio.setText(model.getAudio());
//        holder.imgURL.setText(ge);
//        URL = model.getAudio();
    }

    @NonNull
    @Override
    public Adapter_Chapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_chapter,parent,false),mListener);
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView imgURL , ChapAudio;


        public Viewholder(@NonNull View itemView, OnItemClickListener listener ){
            super(itemView);

            imgURL = itemView.findViewById(R.id.audiobook_chapter_cover_url);
            ChapAudio = itemView.findViewById(R.id.audiobook_chapter_url);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AudioBookPlayer.class);
                    intent.putExtra("URL",ChapAudio.getText().toString());
                    Log.d("ABC", "Viewholder: "+bookname);
                    Log.d(TAG, "onClick: "+URL);
                    intent.putExtra("BookName",bookname);
                    intent.putExtra("imgURL",URL);
//                    intent.putExtra("songName",songName);
                    v.getContext().startActivity(intent);
                }
            });

        }
    }
}
