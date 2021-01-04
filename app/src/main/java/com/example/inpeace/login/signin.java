package com.example.inpeace.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.logging.StreamHandler;

public class signin extends AppCompatActivity {

    private Button signinButtonBtn ;
    private EditText value ;
    private EditText pass ;
    private TextView error;
    private DatabaseReference mdatabase;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Linking XML
        signinButtonBtn = findViewById(R.id.signinButtonBtn);
        value = findViewById(R.id.signinEmail);
        pass =findViewById(R.id.signinPassword);


        signinButtonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserIsHavingAccount();

            }
        });

    }

    private void checkUserIsHavingAccount() {

        value = findViewById(R.id.signinEmail);
        error = findViewById(R.id.signinError);
        ArrayList<String> list = new ArrayList<>(0);

        String userInput = "+91" + value.getText().toString() ;

        FirebaseDatabase.getInstance().getReference().child("sign in")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String data = " ";
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                     data = dataSnapshot.child("Phone Number").getValue().toString();
                     list.add(data);


//                     if(data.equals(userInput)){
//                         Toast.makeText(signin.this,"Email is registered" ,Toast.LENGTH_LONG).show();
//                         break;
////                         error.setText("");
//                     }else {
//                         continue;
//                         Toast.makeText(signin.this,"Please sign up first" ,Toast.LENGTH_LONG).show();
////                         error.setText("");
//                     }

                }

                error.setText(list.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}