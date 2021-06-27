package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ChildReg extends AppCompatActivity {
    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextGender, textInputEditTextSchool, textInputEditTextdob, textInputEditTextdor; //initialize inputs
    Button buttonChildreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_reg);

        textInputEditTextFullname = findViewById(R.id.childFN);
        textInputEditTextUsername = findViewById(R.id.childUN);
        textInputEditTextGender = findViewById(R.id.Childgen);
        textInputEditTextSchool = findViewById(R.id.childScl);
        textInputEditTextdob = findViewById(R.id.childDob);
        textInputEditTextdor = findViewById(R.id.childRegd);
        buttonChildreg = findViewById(R.id.buttonchildreg);


        buttonChildreg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String fullname, username, gender, schoolcode, dob, doreg;
                fullname = String.valueOf(textInputEditTextFullname.getText()); //get value from edittext
                username = String.valueOf(textInputEditTextUsername.getText());
                gender = String.valueOf(textInputEditTextGender.getText());
                schoolcode = String.valueOf(textInputEditTextSchool.getText());
                dob = String.valueOf(textInputEditTextdob.getText());
                doreg = String.valueOf(textInputEditTextdor.getText());

                if(!((fullname.equals("") || username.equals("") || schoolcode.equals("") || dob.equals("")|| gender.equals("") || doreg.equals("")))){

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[6];
                            field[0] = "fullname"; // post or get to php
                            field[1] = "username";
                            field[2] = "gender";
                            field[3] = "schoolcode";
                            field[4] = "dob";
                            field[5] = "doreg";
                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = fullname; // get from inputs
                            data[1] = username;
                            data[2] = gender;
                            data[3] = schoolcode;
                            data[4] = dob;
                            data[5] = doreg;
                            PutData putData = new PutData("http://192.168.8.103/LoginRegister/childreg.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult(); //result
                                    if(result.equals("Registration Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), parent_dashboard.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else {

                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}