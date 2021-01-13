package com.example.inpeace.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

//    private RecyclerView recyclerView;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        textView = findViewById(R.id.time1);
//        recyclerView = recyclerView.findViewById(R.id.activityRecycleView);


        FirebaseDatabase.getInstance().getReference().child("activity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Database database = new Database(HomePage.this);
                database.create_our_activity_table();
                textView.setText(database.current_time());
                database.getReadableDatabase();
                for (DataSnapshot snap : snapshot.getChildren()){
                    Log.d("ABC",snap.child("Task").getValue().toString().trim());
                    boolean bool = database.insert_Value_In_OurActivity(snap.child("Task").getValue().toString().trim() ,snap.child("Code").getValue().toString().trim());

                    if(bool == true)
                        Toast.makeText(HomePage.this , "Done" , Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(HomePage.this , "Failed" , Toast.LENGTH_LONG).show();
                }

                database.create_user_table("ABC");
                database.close();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePage.this , "Connection Failed!" , Toast.LENGTH_LONG).show();
            }
        });
//            print_tasks();
    }

    public void print_tasks() {                                     //for printing the random tasks
        Database database = new Database(HomePage.this);
        String[] time_diff = {"00", "00", "00"};
        String taskCode,task;
        boolean b = true ; // abhi ke liye true  //define???

        while (b) {

            //User Login Check {b} ?

            String cur_time = database.current_time();

            int pcount = database.Total_Tasks_OurActivity();


            int dcount = database.user_activity_number("ABC");

            if (dcount != 0) {////dcount == 0 initialise sare variables
                int max_sno = database.max_serial_number_of_user_table("ABC");
                String stored_time = database.latest_date_user_activity("ABC" , max_sno);
                String time_difference = database.time_difference(stored_time);
                time_diff = time_difference.split(":");
            }

            if (dcount == pcount) {
                database.empty_user_table("ABC");
//                Scanner scanner = new Scanner(System.in);
                System.out.println("eNTer__________________________________________________________________________________________________");
//               int i =   scanner.nextInt();
////                if(i==0){
////                    break;
////                }
            }

            if ((dcount == 0) || (dcount != pcount && Integer.parseInt(time_diff[2]) >= Integer.parseInt("10"))) {             //add concept of "submit button"

                int check_row = database.row_at_given_time_user_activity(cur_time , "ABC");

                while (check_row <= 2) {
                    taskCode = database.random_code_from_ourActivity();

                    int task_ch = database.count_rows_code_user_activity(taskCode ,"ABC");

                    if (task_ch == 0) {
                        dcount += 1;
                        task = database.selecting_tasks_from_ourActivity(taskCode);

                        Log.d("time " , cur_time);
                        Log.d("task", task);
                        Log.d("code",taskCode);
                        database.insert_Value_In_UserActivity(task , taskCode ,cur_time);

                        //for adding task in done table

//                            System.out.println("Task " + (check_row + 1) + ": " + t);         //displaying task
//                            System.out.println("--------------------------------------");
//                            check_row++;
//
//                            Scanner s = new Scanner(System.in);
//                            System.out.println("Submit(0/1): ");
//                            int submit = s.nextInt();

//                            point_add(submit); POINT ADD
                    }
                }
            }
        }
        database.close();
    }
}