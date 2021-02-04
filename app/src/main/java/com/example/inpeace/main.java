package com.example.inpeace;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.inpeace.activites.HomePage;
import com.example.inpeace.audiobooks.audiobooks_MainActivity;
import com.example.inpeace.games.games_homePage;
import com.example.inpeace.motivation.mainMotivation;
import com.example.inpeace.motivation.newLayout.motivation_home;
import com.example.inpeace.music.MainMusic;
import com.example.inpeace.music.newLayout.NewMusicLayout;
import com.example.inpeace.slideshow.slideshowAdapter;
import com.google.android.material.navigation.NavigationView;

public class main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ViewPager pager;
    private slideshowAdapter adapter;
    private DrawerLayout drawer;
    private boolean exit = false;

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


        adapter = new slideshowAdapter(this);

        pager = findViewById(R.id.slideShowPager);
        pager.setAdapter(adapter);


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

//        Custom Icon
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_user_svgrepo_com);
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

        if (exit) {
            finish();
        } else {
            Toast.makeText(this, "Press back again to exit",
                    Toast.LENGTH_SHORT).show();
            //
            drawer.closeDrawer(GravityCompat.START);


            exit = true;
            new CountDownTimer(3000,1000) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    exit = false;
                }
            }.start();
        }



    }

    public void openAudioBooks(View view) {
        Intent intent = new Intent(main.this, audiobooks_MainActivity.class);
        startActivity(intent);
    }

    public void openGames(View view) {
        Intent intent = new Intent(main.this, games_homePage.class);
        startActivity(intent);
    }

    public void openVideos(View view) {

    }

    public void openMusic(View view) {
        Intent intent = new Intent(main.this, NewMusicLayout.class);
        startActivity(intent);
    }

    public void openTask(View view) {
        Intent intent = new Intent( main.this , HomePage.class);
                startActivity(intent);
    }

    public void openMotivation(View view) {
        Intent intent = new Intent(main.this, motivation_home.class);
        startActivity(intent);
    }

    public void openConsultation(View view) {
        Toast.makeText(this, "Coming Soon",Toast.LENGTH_SHORT).show();
    }

    public void openGlobalCommunication(View view) {
        Toast.makeText(this, "Coming Soon",Toast.LENGTH_SHORT).show();
    }

}