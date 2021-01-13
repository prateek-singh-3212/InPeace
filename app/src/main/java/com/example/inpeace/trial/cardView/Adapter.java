package com.example.inpeace.trial.cardView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inpeace.R;
import com.example.inpeace.music.AdapterMusic;
import com.example.inpeace.music.MusicPlayerActivity;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    private Context context;
    private ArrayList<Model>  modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trial_cardview_main,parent ,false);
        return new Viewholder(view , mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Model model = modelArrayList.get(position);
        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class Viewholder  extends RecyclerView.ViewHolder {
       private TextView name  ,age;

        public Viewholder(@NonNull View itemView , OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.nametrialcard);
            age = itemView.findViewById(R.id.agetrialcard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   v.getContext().startActivity(new Intent(v.getContext() , MusicPlayerActivity.class));

//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onItemClick(position);
//                        }
//                    }
                }
            });
        }
    }
}
