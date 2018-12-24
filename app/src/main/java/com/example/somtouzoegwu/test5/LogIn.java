package com.example.somtouzoegwu.test5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Somto Uzoegwu on 11/11/2017.
 */

public class LogIn extends AppCompatActivity {

//    Button btnLogin;
//    EditText txtUserName, txtPassword;

    //User Session Manager Class
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //User Session Manager
        session = new UserSessionManager(getApplicationContext());

        //get Email, Password input text
        final EditText txtUserName = (EditText) findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        Toast.makeText(getApplicationContext(), "User Login Status:" + session.isUserLoggedIn(), Toast.LENGTH_LONG).show();

        //User Login button
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v) {
                String user = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                if(user.trim().length()>0 && password.trim().length() >0){
                    String userDetails = session.getString(user + password + "data");
                    if (!userDetails.equals("Username or Password in Incorrect")){
                        session.createUserLoginSession(user, "bamoyk@yahoo.com");

                        //Starting MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        //Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }else{
                        //username/password doesn't match
                        Toast.makeText(getApplicationContext(), "Username/Password is incorrect", Toast.LENGTH_LONG).show();
                    }
                }else{
                    //user didn't entered username or password
                    Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_LONG).show();
                }



            }}

        );

        btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent registerScreen = new Intent(LogIn.this, Registration.class);
                startActivity(registerScreen);
            }
        });

//        btnLogin.setOnClickListener(new OnClickListener(){
//            @Override
//            public void onClick(View arg0) {
//                //Get username, password from EditText
//                String username = txtUserName.getText().toString();
//                String password = txtPassword.getText().toString();
//
//                if(username.trim().length()>0 && password.trim().length() >0){
//                    //Creating user login session
//                    //Statically storing name = "Android Example"
//                    // and email = "androidenample84@gmail.com"
//                    if (username.equals("admin") && password.equals("admin")) {
//                        session.createUserLoginSession("User Session", "bamoyk@yahoo.com");
//
//                        //Starting MainActivity
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                        //Add new Flag to start new Activity
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(i);
//                        finish();
//                    }else{
//                        //username/password doesn't match
//                        Toast.makeText(getApplicationContext(), "Username/Password is incorrect", Toast.LENGTH_LONG).show();
//                    }
//                }else{
//                    //user didn't entered username or password
//                    Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
}

