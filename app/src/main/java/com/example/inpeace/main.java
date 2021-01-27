package com.example.inpeace;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.inpeace.activites.HomePage;
import com.example.inpeace.audiobooks.audiobooks_MainActivity;
import com.example.inpeace.games.Tic_Tac_Toe;
import com.example.inpeace.music.MainMusic;
import com.google.android.material.navigation.NavigationView;

public class main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Button game ;
    private Button audiobooks ;
    private Button music ;
    private Button motivation ;
    private Button activites ;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setNavigationPannel();


//        FirebaseMessaging.getInstance().subscribeToTopic("Tests")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(!task.isSuccessful()){
//                            Log.d("ABC","Sub Unscucessful");
//                        }
//                        Log.d("ABC","Sub Sucess");
//                    }
//                });



        game = findViewById(R.id.mainGameBtn);
        music = findViewById(R.id.mainMusicBtn);
        motivation = findViewById(R.id.mainMotivationBtn);
        audiobooks = findViewById(R.id.mainAudiobooksBtn);
        activites = findViewById(R.id.mainActivitesBtn);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( main.this , Tic_Tac_Toe.class);
                startActivity(intent);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( main.this , MainMusic.class);
                startActivity(intent);
            }
        });

//        motivation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent( main.this , MotivationHome.class);
//                startActivity(intent);
//            }
//        });

        audiobooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main.this, audiobooks_MainActivity.class);
                startActivity(intent);
            }
        });

        activites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( main.this , HomePage.class);
                startActivity(intent);
            }
        });

    }

    private void setNavigationPannel(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.draw_res,R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Custom Icon
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_5g);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.profile: {
                drawer.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.navFragmentContainer, new profile())
                        .commit();
            }
            break;

            case R.id.settings: {
                drawer.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.navFragmentContainer, new settings())
                        .commit();
            }

            break;

            case R.id.logout: {
                drawer.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.navFragmentContainer, new logout())
                        .commit();
            }

            break;

            case R.id.aboutus: {
                drawer.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.navFragmentContainer, new aboutus())
                        .commit();
            }

        }

        return true;
    }


    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


//    public void profileFragment(MenuItem item) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.navFragmentContainer,new profile())
//                .commit();
//    }
}