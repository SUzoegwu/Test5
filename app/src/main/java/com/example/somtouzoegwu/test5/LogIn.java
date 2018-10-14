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

    Button btnLogin;
    EditText txtUserName, txtPassword;

    //User Session Manager Class
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //User Session Manager
        session = new UserSessionManager(getApplicationContext());

        //get Email, Password input text
        txtUserName = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        Toast.makeText(getApplicationContext(), "User Login Status:" + session.isUserLoggedIn(), Toast.LENGTH_LONG).show();

        //User Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0) {
                //Get username, password from EditText
                String username = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();

                if(username.trim().length()>0 && password.trim().length() >0){
                    //Creating user login session
                    //Statically storing name = "Android Example"
                    // and email = "androidenample84@gmail.com"
                    if (username.equals("admin") && password.equals("admin")) {
                        session.createUserLoginSession("User Session", "bamoyk@yahoo.com");

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
            }
        });
    }
}

