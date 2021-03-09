package com.example.inpeace.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.xml.sax.helpers.XMLReaderAdapter;

public class signup extends AppCompatActivity {

    //Declearations
    private Button signupNextBtn ;
    private DatabaseReference mdatabase;
    private EditText signupName;
    private EditText signupEmail ;
    private EditText signupAge ;
    private RadioGroup signupGender ;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Connections
        signupNextBtn = findViewById(R.id.signupNextBtn);
        signupName= findViewById(R.id.signupName);
        signupEmail = findViewById(R.id.signupEmail);
        signupAge = findViewById(R.id.signupAge);
        signupGender = findViewById(R.id.signupGender);

        // Database Connection
        mdatabase = FirebaseDatabase.getInstance().getReference();

        //  User Id
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // Radio Button
        signupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
            }
        });


        signupNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Radio button
                int setId = signupGender.getCheckedRadioButtonId();
                String genderValue = "";
                if(setId == -1){
                    genderValue = "";
                }else {
                    RadioButton radioGender = (RadioButton)findViewById(setId);
                    genderValue = radioGender.getText().toString();
                    Toast.makeText(signup.this,radioGender.getText() , Toast.LENGTH_LONG);
                }




                if(signupName.getText().toString().equals("") ||
                    signupAge.getText().toString().equals("")||
                    signupEmail.getText().toString().equals("")||
                    genderValue.equals("")){
                    Toast.makeText(signup.this, "Please fill all the feilds", Toast.LENGTH_SHORT).show();
                }else {

                    if(signupEmail.getText().toString().contains("@")) {
                        //Inserting data in user data
                        mdatabase.child("user data").child(userId).child("Name").setValue(signupName.getText().toString());
                        mdatabase.child("user data").child(userId).child("Age").setValue(signupAge.getText().toString());
                        mdatabase.child("user data").child(userId).child("Email").setValue(signupEmail.getText().toString());
                        mdatabase.child("user data").child(userId).child("Gender").setValue(genderValue);
                        mdatabase.child("user data").child(userId).child("Phone Number").setValue(getIntent().getStringExtra("Phone Number"));

                        //Inserting data in signin
                        mdatabase.child("sign in").child(userId).child("Email").setValue(signupEmail.getText().toString());
                        mdatabase.child("sign in").child(userId).child("Phone Number").setValue(getIntent().getStringExtra("Phone Number"));



                        // Opening new activity password verification
                        Intent intent = new Intent(signup.this, PasswordVerification.class);
                        intent.putExtra("Email",signupEmail.getText().toString());
                        startActivity(intent);
                    }else {
                        Toast.makeText(signup.this , "Check email id" , Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}