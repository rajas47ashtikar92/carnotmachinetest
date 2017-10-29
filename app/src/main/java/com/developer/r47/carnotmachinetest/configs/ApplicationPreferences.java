package com.developer.r47.carnotmachinetest.configs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by r47 on 29/10/17.
 * This class is a wrapper to save the data to the shared preferences of the db
 */

public class ApplicationPreferences {
    private static SharedPreferences preferences;
    private static ApplicationPreferences mInstance;


    //initialize the shared preferences for the application in private mode
    private ApplicationPreferences(Context context) {
        String APPLICATION_PREFERENCE = "APPLICATION_PREFERENCE";
        preferences = context.getSharedPreferences(APPLICATION_PREFERENCE, Context.MODE_PRIVATE);
    }

    //ensuring a singleton of the application preferences
    public static void init(Application application) {
        if (mInstance == null) {
            mInstance = new ApplicationPreferences(application);
        }
    }

    //for storing a POJO to the shared preferences
    public static <T> void putObject(T t, String name) {
        if (t != null) {
            Gson gson = new Gson();
            String json = gson.toJson(t);
            preferences.edit().putString(name, json).apply();
        }
    }

    //for retrieving the POJO from SharedPreferences
    public static <T> T returnObject(String name, String defaultValue, Class classz) {
        if (name != null && preferences.contains(name) && classz != null) {
            Gson gson = new Gson();
            return (T) gson.fromJson(preferences.getString(name, defaultValue), classz);
        } else {
            return null;
        }
    }

    //to check if the value is present in the sharedpreferences
    public static boolean validate(String name) {
        return preferences.contains(name);
    }

}
