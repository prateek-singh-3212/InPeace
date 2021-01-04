package com.example.inpeace.games;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class trial2 extends AppCompatActivity {

    private TextView name ;
    private TextView age ;
    private TextView phone ;
    private Button btn;
    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial2);

        name = findViewById(R.id.tryname);
        age =findViewById(R.id.tryage);
        phone = findViewById(R.id.tryphone);
        btn = findViewById(R.id.btnn);

        mdatabase = FirebaseDatabase.getInstance().getReference();
//                .child("user data").child("F0CSIgvuc9epSxAftCFlNyk0dLp2");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                mdatabase.child("user data").child("F0CSIgvuc9epSxAftCFlNyk0dLp2").addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                        post newpost = snapshot.getValue(post.class);
                                        phone.setText(snapshot.getKey());
                                        age.setText(newpost.age);
                                        name.setText(newpost.name);
                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
//                mdatabase.child("user data").child("F0CSIgvuc9epSxAftCFlNyk0dLp2").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        post newpost = snapshot.getValue(post.class);
//                        phone.setText(snapshot.getKey());
//                        age.setText(newpost.age);
//                        name.setText(newpost.name);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(trial2.this , "fAIL" , Toast.LENGTH_LONG).
//                    }
//                });
            }
        });

    }
}