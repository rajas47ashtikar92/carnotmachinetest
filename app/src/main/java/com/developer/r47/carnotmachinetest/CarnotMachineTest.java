package com.developer.r47.carnotmachinetest;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.developer.r47.carnotmachinetest.configs.RealmConfigurations;

/**
 * Created by r47 on 29/10/17.
 */

public class CarnotMachineTest extends MultiDexApplication {

    private static CarnotMachineTest instance;

    //for maintaining a singleton instance of the application class
    public static CarnotMachineTest getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize the singleton instance of the application class
        instance = this;
        //initialize realm configuration
        RealmConfigurations.initializeRealm(this);
    }
}
