package com.developer.r47.carnotmachinetest.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.developer.r47.carnotmachinetest.R;
import com.developer.r47.carnotmachinetest.configs.CarnotMachineTestCallback;
import com.developer.r47.carnotmachinetest.models.CarnotMachineTestError;
import com.developer.r47.carnotmachinetest.utilities.DownloadingUtility;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //get info from server
        getInfoFromServer();
    }


    //fire all the methods simultaneously
    private void getInfoFromServer() {
        getCommentsFromServer();
        getPhotosFromServer();
        getPostsFromServer();
        getTodosFromServer();
    }

    //for getting the comments from server
    private void getCommentsFromServer() {
        DownloadingUtility.getInstance().getCommentsFromServer(new CarnotMachineTestCallback() {
            @Override
            public void onSuccess(Object response) {
                if (response != null) {
                    Toast.makeText(HomeScreenActivity.this, "Network call is successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(CarnotMachineTestError error) {
                Toast.makeText(HomeScreenActivity.this, error.errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //for getting the photos from server
    private void getPhotosFromServer() {
        DownloadingUtility.getInstance().getPhotosFromServer(new CarnotMachineTestCallback() {
            @Override
            public void onSuccess(Object response) {
                if (response != null) {
                    Toast.makeText(HomeScreenActivity.this, "Photos Successfully retrieved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(CarnotMachineTestError error) {
                Toast.makeText(HomeScreenActivity.this, error.errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //for getting Todos from server
    private void getTodosFromServer() {
        DownloadingUtility.getInstance().getTodosFromServer(new CarnotMachineTestCallback() {
            @Override
            public void onSuccess(Object response) {
                if (response != null) {
                    Toast.makeText(HomeScreenActivity.this, "Todos Successfully retrieved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(CarnotMachineTestError error) {
                Toast.makeText(HomeScreenActivity.this, error.errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //for getting posts from server
    private void getPostsFromServer() {
        DownloadingUtility.getInstance().getPostsFromServer(new CarnotMachineTestCallback() {
            @Override
            public void onSuccess(Object response) {
                if (response != null) {
                    Toast.makeText(HomeScreenActivity.this, "Posts successfully retrieved from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(CarnotMachineTestError error) {
                Toast.makeText(HomeScreenActivity.this, error.errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
