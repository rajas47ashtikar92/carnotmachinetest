package com.developer.r47.carnotmachinetest.utilities;

import com.developer.r47.carnotmachinetest.configs.CarnotMachineTestCallback;
import com.developer.r47.carnotmachinetest.configs.RetrofitConfigurations;
import com.developer.r47.carnotmachinetest.models.CarnotMachineTestError;
import com.developer.r47.carnotmachinetest.models.Comment;
import com.developer.r47.carnotmachinetest.models.Photo;
import com.developer.r47.carnotmachinetest.models.Post;
import com.developer.r47.carnotmachinetest.models.Todo;

import java.util.List;

import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by r47 on 29/10/17.
 * This class implements the methods that DownloadingApi class defines.
 * This class receives the data from the server and will pass on the data to the activity
 */

public class DownloadingUtility {

    private static DownloadingUtility instance;

    //since this is the utility called for every download that needs to be done, it is made singleton
    public static DownloadingUtility getInstance() {
        if (instance == null) {
            instance = new DownloadingUtility();
        }
        return instance;
    }

    //for getting the comments from the server
    public void getCommentsFromServer(final CarnotMachineTestCallback carnotMachineTestCallback) {
        DownloadingApi api = RetrofitConfigurations.getRetrofitBuilderInstance().create(DownloadingApi.class);
        Call<List<Comment>> call = api.getCommentsFromServer();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    carnotMachineTestCallback.onSuccess(response.body());
                } else {
                    if (response.errorBody() != null) {
                        carnotMachineTestCallback.onError(new CarnotMachineTestError(response.code(), response.errorBody().toString()));
                    } else {
                        carnotMachineTestCallback.onError(new CarnotMachineTestError(response.code(), RetrofitConfigurations.INTERNAL_ERROR_MESSAGE));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                //if the error is not from server but caused due to other reasons like no network connection
                carnotMachineTestCallback.onError(new CarnotMachineTestError(RetrofitConfigurations.INTERNAL_ERROR_CODE, RetrofitConfigurations.INTERNAL_ERROR_MESSAGE));
            }
        });

    }

    //for getting the photos from the server
    public void getPhotosFromServer(final CarnotMachineTestCallback carnotMachineTestCallback) {
        DownloadingApi api = RetrofitConfigurations.getRetrofitBuilderInstance().create(DownloadingApi.class);
        Call<List<Photo>> call = api.getPhotosFromServer();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    carnotMachineTestCallback.onSuccess(response.body());
                } else {
                    if (response.errorBody() != null) {
                        carnotMachineTestCallback.onError(new CarnotMachineTestError(response.code(), response.errorBody().toString()));
                    } else {
                        carnotMachineTestCallback.onError(new CarnotMachineTestError(response.code(), RetrofitConfigurations.INTERNAL_ERROR_MESSAGE));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                //if the error is not from server but caused due to other reasons like no network connection
                carnotMachineTestCallback.onError(new CarnotMachineTestError(RetrofitConfigurations.INTERNAL_ERROR_CODE, RetrofitConfigurations.INTERNAL_ERROR_MESSAGE));
            }
        });
    }

    //for getting the todos from the server
    public void getTodosFromServer(final CarnotMachineTestCallback carnotMachineTestCallback) {
        DownloadingApi api = RetrofitConfigurations.getRetrofitBuilderInstance().create(DownloadingApi.class);
        Call<List<Todo>> call = api.getTodosFromServer();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful()) {
                    carnotMachineTestCallback.onSuccess(response.body());
                } else {
                    if (response.errorBody() != null) {
                        carnotMachineTestCallback.onError(new CarnotMachineTestError(response.code(), response.errorBody().toString()));
                    } else {
                        carnotMachineTestCallback.onError(new CarnotMachineTestError(response.code(), RetrofitConfigurations.INTERNAL_ERROR_MESSAGE));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                //if the error is not from server but caused due to other reasons like no network connection
                carnotMachineTestCallback.onError(new CarnotMachineTestError(RetrofitConfigurations.INTERNAL_ERROR_CODE, RetrofitConfigurations.INTERNAL_ERROR_MESSAGE));
            }
        });
    }

    //for getting the posts from the server
    public void getPostsFromServer(final CarnotMachineTestCallback carnotMachineTestCallback) {
        DownloadingApi api = RetrofitConfigurations.getRetrofitBuilderInstance().create(DownloadingApi.class);
        Call<List<Post>> call = api.getPostsFromServer();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    carnotMachineTestCallback.onSuccess(response.body());
                } else {
                    if (response.errorBody() != null) {
                        carnotMachineTestCallback.onError(new CarnotMachineTestError(response.code(), response.errorBody().toString()));
                    } else {
                        carnotMachineTestCallback.onError(new CarnotMachineTestError(response.code(), RetrofitConfigurations.INTERNAL_ERROR_MESSAGE));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //if the error is not from server but caused due to other reasons like no network connection
                carnotMachineTestCallback.onError(new CarnotMachineTestError(RetrofitConfigurations.INTERNAL_ERROR_CODE, RetrofitConfigurations.INTERNAL_ERROR_MESSAGE));
            }
        });
    }
}
