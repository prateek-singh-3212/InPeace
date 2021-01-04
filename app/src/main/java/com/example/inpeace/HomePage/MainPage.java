package com.example.inpeace.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inpeace.R;

public class MainPage extends AppCompatActivity {

    private Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btn =(Button) findViewById(R.id.homeCVGameBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this , "GAMES" , Toast.LENGTH_LONG).show();
            }
        });

    }
}