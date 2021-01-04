package com.example.inpeace.trial.cardView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inpeace.R;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private ArrayList<Model> model123 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recycleView123);

        model123 = new ArrayList<>();
        model123.add(new Model("name123" , "22"));
        model123.add(new Model("dsad" , "78"));
        model123.add(new Model("dfhdg" , "45"));
        model123.add(new Model("hjk" , "23"));


        Adapter adapter = new Adapter(this , model123);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                model123.get(position).changeText("Clicked");
                adapter.notifyItemChanged(position);
            }
        });
    }
}