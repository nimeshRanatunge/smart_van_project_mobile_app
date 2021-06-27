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

public class SignUp extends AppCompatActivity {
    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword, textInputEditTextEmail, textInputEditTextAddress, textInputEditTextMobile; //initialize inputs
    Button buttonSignUp; //initialize button
    TextView textViewLogin; //initialize textview
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextAddress = findViewById(R.id.address);
        textInputEditTextMobile = findViewById(R.id.mobile);
        textInputEditTextEmail = findViewById(R.id.email);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String fullname, username, password, email, address, mobile;
                fullname = String.valueOf(textInputEditTextFullname.getText()); //get value from edittext
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                address = String.valueOf(textInputEditTextAddress.getText());
                mobile = String.valueOf(textInputEditTextMobile.getText());
                email = String.valueOf(textInputEditTextEmail.getText());

                if(!((fullname.equals("") || username.equals("") || password.equals("") || email.equals("") || address.equals("") || mobile.equals("")))){

                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[6];
                            field[0] = "fullname"; // post or get to php
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            field[4] = "address";
                            field[5] = "mobile";
                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = fullname; // get from inputs
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            data[4] = address;
                            data[5] = mobile;
                            PutData putData = new PutData("http://192.168.8.103/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult(); //result
                                   if(result.equals("Sign Up Success")){
                                       Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(getApplicationContext(), Login.class);
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