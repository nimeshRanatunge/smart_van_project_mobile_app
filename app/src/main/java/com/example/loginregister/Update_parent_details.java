package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Update_parent_details extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextEmail, textInputEditTextAddress, textInputEditTextMobile; //initialize inputs
    Button buttonUpdatepar; //initialize button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_parent_details);


        textInputEditTextFullname = findViewById(R.id.ParenameUpd);
        textInputEditTextUsername = findViewById(R.id.parentUN);
        textInputEditTextAddress = findViewById(R.id.addressupd);
        textInputEditTextMobile = findViewById(R.id.mobileParentUpd);
        textInputEditTextEmail = findViewById(R.id.PareEmailUpd);
        buttonUpdatepar = findViewById(R.id.buttonparUpdt);

        buttonUpdatepar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String fullname, username,email, address, mobile;
                fullname = String.valueOf(textInputEditTextFullname.getText()); //get value from edittext
                username = String.valueOf(textInputEditTextUsername.getText());
                address = String.valueOf(textInputEditTextAddress.getText());
                mobile = String.valueOf(textInputEditTextMobile.getText());
                email = String.valueOf(textInputEditTextEmail.getText());


                if(!(fullname.equals("") || username.equals("") || email.equals("") || address.equals("") || mobile.equals(""))){


                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "fullname"; // post or get to php
                            field[1] = "username";
                            field[2] = "mobile";
                            field[3] = "email";
                            field[4] = "address";
                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = fullname; // get from inputs
                            data[1] = username;
                            data[2] = mobile;
                            data[3] = email;
                            data[4] = address;

                            PutData putData = new PutData("http://192.168.8.103/LoginRegister/parent_update.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult(); //result
                                    if(result.equals("Succesfully updated!")){
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