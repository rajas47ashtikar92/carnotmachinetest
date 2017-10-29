package com.developer.r47.carnotmachinetest.utilities;

import com.developer.r47.carnotmachinetest.models.Comment;
import com.developer.r47.carnotmachinetest.models.Photo;
import com.developer.r47.carnotmachinetest.models.Post;
import com.developer.r47.carnotmachinetest.models.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by r47 on 29/10/17.
 * This interface contains the endpoints that the application will hit, in order to get the required
 * data from the server
 */

public interface DownloadingApi {
    //for getting comments
    @GET("comments")
    Call<List<Comment>> getCommentsFromServer();

    //for getting photos
    @GET("photos")
    Call<List<Photo>> getPhotosFromServer();

    //for getting todos
    @GET("todos")
    Call<List<Todo>> getTodosFromServer();

    //for getting posts
    @GET("posts")
    Call<List<Post>> getPostsFromServer();
}
