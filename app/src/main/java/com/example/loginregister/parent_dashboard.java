package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class parent_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_dashboard);
    }

    public void onChild(View view) {
        Intent i= new Intent(this,Manage_child_details.class);
        startActivity(i);
    }

    public void onProfile(View view) {
        Intent i= new Intent(this,Update_parent_details.class);
        startActivity(i);
    }

}