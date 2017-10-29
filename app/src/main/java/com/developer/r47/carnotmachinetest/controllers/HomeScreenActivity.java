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

        DownloadingUtility utility = new DownloadingUtility();
        utility.getCommentsFromServer(new CarnotMachineTestCallback() {
            @Override
            public void onSuccess(Object response) {
                if (response != null) {
                    Toast.makeText(HomeScreenActivity.this, "Network call is successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(CarnotMachineTestError error) {
                Toast.makeText(HomeScreenActivity.this, "Kuch to gadbad hua hain", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
