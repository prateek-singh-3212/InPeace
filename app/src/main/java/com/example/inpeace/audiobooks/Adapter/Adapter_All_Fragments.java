package com.example.inpeace.audiobooks.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.audiobooks.Model.Model_All_Fragments;
import com.example.inpeace.audiobooks.audiobooks_chapter;
import com.example.inpeace.motivation.Adapter;
import com.example.inpeace.motivation.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class Adapter_All_Fragments extends FirebaseRecyclerAdapter<Model_All_Fragments, Adapter_All_Fragments.Viewholder > {

    private OnItemClickListener mListener;
    private String imgUrl;
    private String Bookcategory;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }



    public Adapter_All_Fragments(@NonNull FirebaseRecyclerOptions<Model_All_Fragments> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Adapter_All_Fragments.Viewholder holder, int position, @NonNull Model_All_Fragments model) {

        holder.bookname.setText(model.getTitle());
        Picasso.get().load(model.getThumbnail()).into(holder.imageView);
        holder.category.setText(model.getCategory());
        holder.imgURL.setText(model.getThumbnail());
//        holder.author.setText(model.getThumbnail());

    }

    @NonNull
    @Override
    public Adapter_All_Fragments.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_audiobook_bookholder,parent,false);

        return new Viewholder(view , mListener);
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        private TextView bookname,author,imgURL,category;
        private ImageView imageView;

        public Viewholder(@NonNull View itemView , OnItemClickListener mListener) {
            super(itemView);

            bookname = itemView.findViewById(R.id.audiobook_title_cardview);
            author = itemView.findViewById(R.id.author_audiobook_cardview);
            imageView = itemView.findViewById(R.id.audiobook_thumbnail_cardview);
            imgURL = itemView.findViewById(R.id.audiobook_cover_url);
            category = itemView.findViewById(R.id.audiobook_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), audiobooks_chapter.class);
                    intent.putExtra("bookname",bookname.getText().toString());
                    intent.putExtra("imgUrl",imgURL.getText().toString());
                    intent.putExtra("Bookcategory",category.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
}
