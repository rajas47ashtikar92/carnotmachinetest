package com.developer.r47.carnotmachinetest.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by r47 on 29/10/17.
 * This class is the skeleton class for the object that is sent by the server when a todos are requested
 * from the server
 */

public class Todo extends RealmObject{
    public int userId;
    @PrimaryKey
    public int id;
    public String title;
    public boolean completed;
}
