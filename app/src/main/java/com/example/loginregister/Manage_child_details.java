package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Manage_child_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_child_details);
    }

    public void onChildins(View view) {
        Intent i= new Intent(this,ChildReg.class);
        startActivity(i);
    }

    public void onChildDel(View view) {
        Intent i= new Intent(this,DeleteChild.class);
        startActivity(i);
    }
    public void onChildupd(View view) {
        Intent i= new Intent(this,Update_child_details.class);
        startActivity(i);
    }
//    public void onChilddisp(View view) {
//        Intent i= new Intent(this,display_child.class);
//        startActivity(i);
//    }
}