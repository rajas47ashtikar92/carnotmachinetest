package com.developer.r47.carnotmachinetest.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by r47 on 29/10/17.
 * This class is the skeleton for the comment object that will be sent by the server.
 * Getter and setters are not used as they increase the processing due to reflection of control,
 * and that is not required for the given situation.
 */

public class Comment extends RealmObject {
    public int postId;
    @PrimaryKey
    public int id;
    public String name;
    public String email;
    public String body;
}
