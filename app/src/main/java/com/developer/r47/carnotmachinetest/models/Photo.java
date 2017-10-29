package com.developer.r47.carnotmachinetest.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by r47 on 29/10/17.
 * This class is the skeleton for the object that is sent by the server when the photos are asked
 * from the server.
 */

public class Photo extends RealmObject {
    public int albumId;
    @PrimaryKey
    public int id;
    public String title;
    public String url;
    public String thumbnailUrl;
}
