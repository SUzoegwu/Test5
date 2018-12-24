package com.example.somtouzoegwu.test5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new UserSessionManager(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final EditText userName = (EditText) findViewById(R.id.editText5);
        final EditText password = (EditText) findViewById(R.id.editText8);
        Button btnRegister = findViewById(R.id.button10);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                String newUser = userName.getText().toString();
                String newPassword = password.getText().toString();

//                SharedPreferences.Editor editor = preferences.edit();

                session.registerUser(newUser, newPassword);

//                editor.putString(newUser + newPassword + "data", newUser + "/n");
//                editor.commit();

                Intent loginScreen = new Intent(Registration.this, LogIn.class);

                startActivity(loginScreen);
            }

        });


    }
}
