package com.developer.r47.carnotmachinetest.configs;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by r47 on 29/10/17.
 * This class is for maintaining a singleton instance of Realm.
 * This class contains the housekeeping methods of Realm
 */

public class RealmConfigurations {

    public static Realm realmInstance;

    public static void initializeRealm(Context context) {
        //initialize realm
        Realm.init(context);
        //set the default configuration for realm
        Realm.setDefaultConfiguration(setRealmConfigurations());
    }

    //customize realm best suited to the applications requirements
    private static RealmConfiguration setRealmConfigurations() {
        /* The current realm configuration is to delete all the data locally cached if a
        migration is required.  */
        return new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
    }

    //to maintain a singleton instance of Realm, makes it easier to close and destroy the instance of realm if required
    public static Realm getRealmInstance() {
        if (realmInstance == null) {
            realmInstance = Realm.getDefaultInstance();
        }
        return realmInstance;
    }

    //since the instance of the realm is static, it will have to made null after realm has been destroyed
    public static void setRealmInstance(Realm realmInstance) {
        RealmConfigurations.realmInstance = realmInstance;
    }

    //save objects to the realm db
    public static void saveDataToLocalDB(List objects) {
        Realm realmInstance = getRealmInstance();
        try {
            realmInstance.beginTransaction();
            realmInstance.copyToRealmOrUpdate(objects);
            realmInstance.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
