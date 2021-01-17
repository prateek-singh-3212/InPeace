package com.example.inpeace.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Scanner;

public class HomePage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textView;
    public final String tableName = "htrhrt";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        button = findViewById(R.id.Button);
        textView = findViewById(R.id.time1);

        recyclerView = findViewById(R.id.activityRecycleView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainWorking();
            }
        });
    }

    public void mainWorking(){

        Database database = new Database(HomePage.this);

        FirebaseDatabase.getInstance().getReference().child("activity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Database database = new Database(HomePage.this);
                database.create_our_activity_table();
                textView.setText(database.current_time());
                database.getReadableDatabase();
                for (DataSnapshot snap : snapshot.getChildren()){
                    Log.d("ABCDE",snap.child("Task").getValue().toString().trim());
                    boolean bool = database.insert_Value_In_OurActivity(snap.child("Task").getValue().toString().trim() ,snap.child("Code").getValue().toString().trim());
//
//                    if(bool == true)
//                        Toast.makeText(HomePage.this , "Done" , Toast.LENGTH_LONG).show();
//                    else
//                        Toast.makeText(HomePage.this , "Failed" , Toast.LENGTH_LONG).show();
                }

                database.close();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePage.this , "Connection Failed!" , Toast.LENGTH_LONG).show();
            }
        });
        database.create_user_table(tableName);
        print_tasks();

        database.close();
    }


    public void print_tasks() {                                     //for printing the random tasks
        Database database = new Database(HomePage.this);
        String[] time_diff_arr = {"00", "00", "00"}   ;
        String taskCode,task;
        boolean b = true ; // abhi ke liye true  //define???

        int poolCount = database.Total_Tasks_OurActivity();
        int userActivityCount = database.user_activity_number(tableName);
        String currentTime = database.current_time();

        if(userActivityCount>0){
            // Taking max Time frm user table
            int maxSerialNO = database.max_serial_number_of_user_table(tableName);
            String storedTimeInMaxSerialNO = database.latest_date_user_activity(tableName,maxSerialNO);
            String Time_Difference = database.time_difference(currentTime,storedTimeInMaxSerialNO);
            time_diff_arr = Time_Difference.split(":");
        }
        if(poolCount== userActivityCount){
            database.empty_user_table(tableName);
        }

        if(userActivityCount == 0 || (poolCount != userActivityCount && Integer.parseInt(time_diff_arr[2]) >= Integer.parseInt("5"))){

            int row = database.row_at_given_time_user_activity(currentTime,tableName);
            for(int i = row; i<3 ; ){
                // code randeom
                String randomCodeFromOurActivity = database.random_code_from_ourActivity();
                // no of tasks from user table where code== randeom code
                if(database.user_activity_codes(tableName,randomCodeFromOurActivity)){
                    String activity_from_code = database.selecting_tasks_from_ourActivity(randomCodeFromOurActivity);
                    database.insert_Value_In_UserActivity(tableName,activity_from_code,randomCodeFromOurActivity,currentTime);
                    i++;
                }else {
                    i= database.row_at_given_time_user_activity(currentTime,tableName);
                }
                // if up == 0 true . take out task where code == c
                // add task in user table
            }
        }

        ActivityAdapter adapter = new ActivityAdapter(this,database.getDataFromUserTableToModelClass(tableName) );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        database.close();

    }
}