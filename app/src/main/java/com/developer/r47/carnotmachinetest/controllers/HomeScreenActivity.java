package com.developer.r47.carnotmachinetest.controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.r47.carnotmachinetest.R;
import com.developer.r47.carnotmachinetest.configs.CarnotMachineTestCallback;
import com.developer.r47.carnotmachinetest.configs.RealmConfigurations;
import com.developer.r47.carnotmachinetest.models.CarnotMachineTestError;
import com.developer.r47.carnotmachinetest.models.CommentResponseTime;
import com.developer.r47.carnotmachinetest.models.PhotoResponseTime;
import com.developer.r47.carnotmachinetest.utilities.DownloadingUtility;

import java.io.File;


import io.realm.Realm;

public class HomeScreenActivity extends AppCompatActivity {

    private TextView exportrealmdb;
    private TextView currentsystemtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        this.exportrealmdb = (TextView) findViewById(R.id.exportrealmdb);
        this.currentsystemtime = (TextView) findViewById(R.id.currentsystemtime);
        this.currentsystemtime.setText(DownloadingUtility.getInstance().getCurrentSystemTime());
        exportrealmdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportRealmDB();
            }
        });

        //get info from server
        getInfoFromServer();
    }


    //fire all the methods simultaneously
    private void getInfoFromServer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCommentsFromServer();
                getPhotosFromServer();
                getPostsFromServer();
                getTodosFromServer();
            }
        }, 5000);
    }

    //for getting the comments from server
    private void getCommentsFromServer() {
        DownloadingUtility.getInstance().getCommentsFromServer(new CarnotMachineTestCallback() {
            @Override
            public void onSuccess(Object response) {
                if (response != null && response instanceof CommentResponseTime) {
                    CommentResponseTime responseTime = (CommentResponseTime) response;
                    Toast.makeText(HomeScreenActivity.this, responseTime.commentPingTimeStart + "\n" + responseTime.commentPingTimeEnd + "\n" + responseTime.commentSaveTimeStart + "\n" + responseTime.commentSaveTimeEnd, Toast.LENGTH_SHORT).show();
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
                if (response != null && response instanceof PhotoResponseTime) {
                    PhotoResponseTime responseTime = (PhotoResponseTime) response;
                    Toast.makeText(HomeScreenActivity.this, responseTime.photoPingTimeStart +"\n"+responseTime.photoPingTimeEnd +"\n" + responseTime.photoSaveTimeStart +"\n" + responseTime.photoSaveTimeEnd , Toast.LENGTH_SHORT).show();
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


    //if the offline database needs to be checked
    private void exportRealmDB() {
        // init realm
        Realm realm = RealmConfigurations.getRealmInstance();

        File exportRealmFile = null;
        try {
            // get or create an "export.realm" file
            exportRealmFile = new File(this.getExternalCacheDir(), "export.realm");
            // if "export.realm" already exists, delete
            exportRealmFile.delete();
            // copy current realm to "export.realm"
            realm.writeCopyTo(exportRealmFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        realm.close();

        // init email intent and add export.realm as attachment
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, "YOUR MAIL");
        intent.putExtra(Intent.EXTRA_SUBJECT, "YOUR SUBJECT");
        intent.putExtra(Intent.EXTRA_TEXT, "YOUR TEXT");
        Uri u = Uri.fromFile(exportRealmFile);
        intent.putExtra(Intent.EXTRA_STREAM, u);

        // start email intent
        startActivity(Intent.createChooser(intent, "YOUR CHOOSER TITLE"));
    }
}
