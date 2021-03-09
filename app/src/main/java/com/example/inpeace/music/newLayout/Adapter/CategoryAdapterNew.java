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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CategoryAdapterNew extends FirebaseRecyclerAdapter<CategoryModel,CategoryAdapterNew.Viewholder> {

    private static final String TAG = "ABC";
    private Context context;
    private List<CategoryModel> categoryModel;
    private FirebaseRecyclerOptions<ModelMusic> musicModel;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoryAdapterNew(@NonNull FirebaseRecyclerOptions<CategoryModel> options, Context context, List<CategoryModel> categoryModel) {
        super(options);this.context = context;
        this.categoryModel = categoryModel;
//        this.musicModel= musicModels;

    }


    @Override
    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull CategoryModel model) {
        holder.categoryName.setText(model.getCategory());
        setCatItemRecycleView(holder.musicRV,holder.categoryName.getText().toString());
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryAdapterNew.Viewholder(LayoutInflater.from(context).inflate(R.layout.cardview_music_category_holder,parent,false));
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

    private void setCatItemRecycleView (RecyclerView recycleView ,String category){

//        FirebaseRecyclerOptions<MusicModel> options = new FirebaseRecyclerOptions.Builder<MusicModel>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("music"), MusicModel.class)
//                .build();

        Log.d(TAG, "setCatItemRecycleView: "+category);

        FirebaseRecyclerOptions<ModelMusic> options = new FirebaseRecyclerOptions.Builder<ModelMusic>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("music"), ModelMusic.class)
                .build();

        Log.d("ABCDEF","options");
        MusicAdapter adapterM= new MusicAdapter(options);
        adapterM.startListening();
        recycleView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recycleView.setAdapter(adapterM);
        Log.d("ABCDEF","adapter");


    }

}
