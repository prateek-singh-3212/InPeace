package com.example.inpeace.music.newLayout.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.music.ModelMusic;
import com.example.inpeace.music.newLayout.Model.CategoryModel;
import com.example.inpeace.music.newLayout.Model.MusicModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private Context context;
    private List<CategoryModel> categoryModel;
    private FirebaseRecyclerOptions<ModelMusic> musicModel;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModel,FirebaseRecyclerOptions<ModelMusic> musicModels) {
        this.context = context;
        this.categoryModel = categoryModel;
        this.musicModel= musicModels;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_music_category_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categoryModel.get(position).getCategory());
        setCatItemRecycleView(holder.musicRV,musicModel);
    }

    @Override
    public int getItemCount() {
        return categoryModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryName ;
        private RecyclerView musicRV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.newMusic_CategoryName);
            musicRV = itemView.findViewById(R.id.newMusic_Music_RV);
        }
    }

    private void setCatItemRecycleView (RecyclerView recycleView , FirebaseRecyclerOptions<ModelMusic> musicModel){

//        FirebaseRecyclerOptions<MusicModel> options = new FirebaseRecyclerOptions.Builder<MusicModel>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("music"), MusicModel.class)
//                .build();

        Log.d("ABCDEF","options");
        MusicAdapter adapter = new MusicAdapter(musicModel);
        adapter.startListening();
        recycleView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recycleView.setAdapter(adapter);
        Log.d("ABCDEF","adapter");


    }

}
