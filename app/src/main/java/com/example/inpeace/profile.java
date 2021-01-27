package com.example.inpeace;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        EditText name = view.findViewById(R.id.userProfileNameEdit);
        Button btn = view.findViewById(R.id.resetProfileButton);
        EditText age = view.findViewById(R.id.userProfileAgeEdit);
        getValue(name ,age);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(name.getText().toString(),age.getText().toString());
            }
        });

        return view;
    }

    private void getValue(EditText name , EditText age){
        FirebaseDatabase.getInstance().getReference().child("user data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child(FirebaseAuth.getInstance().getUid()).child("Name").getValue().toString());
                age.setText(snapshot.child(FirebaseAuth.getInstance().getUid()).child("Age").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getView().getContext(),"Fail",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setValue(String name , String Age){
        FirebaseDatabase.getInstance().getReference().child("user data").child(FirebaseAuth.getInstance().getUid().toString()).child("Age").setValue(Age);
        FirebaseDatabase.getInstance().getReference().child("user data").child(FirebaseAuth.getInstance().getUid().toString()).child("Name").setValue(name);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setTitle("Data Updated").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}