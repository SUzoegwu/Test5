package com.example.somtouzoegwu.test5;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Html;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //User Session Manager Class
    UserSessionManager session;

    //Button Logout
    Button btnLogout;

    //Alert Dialog Manager (**new**)
//    AlertDialogManger alert = new AlertDialogManager


    int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
//    private LocationManager locationManager;
//    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        };
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
//                return;
//            }
//        }ELSE{
//
//        }
//        locationManager.requestLocationUpdates("gps", 5000, 10, locationListener);

        session = new UserSessionManager(getApplicationContext());
        TextView lblName = (TextView) findViewById(R.id.lblName);

        //Button logout
        btnLogout = (Button) findViewById(R.id.btnLogout);

        //Check user login
        //If User is not logged in, This willp redirect user to Login
        if (session.checkLogin()){
            finish();}
        else{
            Intent i = new Intent(getApplicationContext(), LogIn.class);
        }

        //get user data from session
        HashMap<String, String> user = session.getUserDetails();

        //get name
        String name = user.get(UserSessionManager.KEY_NAME);

        //Show user data on activity
        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                session.logoutUser();
            }


        });
    }

    public void open_Emergency_Contacts(View v){
        Intent intents = new Intent(this, EmergencyContact.class);
        startActivity(intents);
    }

    public void open_LogIn(View v){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void btn_SendSMS_OnClick(View v){
        String message = "Hey! This test button works";
        String telNumber = "424-298-0340";

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String []{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        else{
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(telNumber, null, message, null, null);
        }

    }
}
