package com.example.inpeace;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.inpeace.login.HomePage;
import com.example.inpeace.login.SQLiteDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class logout extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        ProgressDialog loading = new ProgressDialog(view.getContext());
        loading.setTitle("Login Out");
        loading.show();


        SQLiteDatabase database = new SQLiteDatabase(view.getContext());
        database.logOut_user(FirebaseAuth.getInstance().getUid());
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(view.getContext(), HomePage.class);
        startActivity(intent);

        loading.dismiss();
        AlertDialog.Builder buider = new AlertDialog.Builder(view.getContext());
        buider.setTitle("Logout Successful").setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        return view;
    }
}