package com.example.somtouzoegwu.test5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Somto Uzoegwu on 10/13/2018.
 */

public class UserSessionManager {
    //Shared Preferences reference
    SharedPreferences pref;

    //Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    //Context
    Context _context;

    //Shared pref mode
    int PRIVATE_MODE = 0;

    //Sharedpref file name
    private static final String PREFER_NAME = "Pref";

    //All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsLoggedIn";

    //User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    //Constructor
    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void registerUser(String username, String password){
        editor.putString(username + password + "data", username + "/n");
        editor.commit();
    }

    public void createUserLoginSession(String name, String email){
        //Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        //Storing name in pref
        editor.putString(KEY_NAME, name);

        //commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     */

    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
            //user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LogIn.class);

            //Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            //Add mew flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Add new Flag to start new Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }

    /**
     * Get stored session data
     */
    public HashMap<String,String> getUserDetails(){
        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        //user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        //return user
        return user;
    }

    /**
     * Clear session details
     */

    public void logoutUser(){
        //Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        //After logout redirect user to Login Activity
        Intent i = new Intent(_context, LogIn.class);

        //Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Add new Flag t start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Starting Login Activity
        _context.startActivity(i);


    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}

