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
import com.developer.r47.carnotmachinetest.models.PostResponseTime;
import com.developer.r47.carnotmachinetest.models.TodoResponseTime;
import com.developer.r47.carnotmachinetest.utilities.DownloadingUtility;

import java.io.File;


import io.realm.Realm;

public class HomeScreenActivity extends AppCompatActivity {

    private TextView exportrealmdb;
    private TextView currentsystemtime;
    private TextView commentstats;
    private TextView photostats;
    private TextView todostats;
    private TextView postsstats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //initializing the textviews for displaying the data
        this.exportrealmdb = (TextView) findViewById(R.id.exportrealmdb);
        this.currentsystemtime = (TextView) findViewById(R.id.currentsystemtime);
        this.currentsystemtime.setText(DownloadingUtility.getInstance().getCurrentSystemTime());
        this.commentstats = (TextView) findViewById(R.id.commentstats);
        this.photostats = (TextView) findViewById(R.id.photostats);
        this.todostats = (TextView) findViewById(R.id.todostats);
        this.postsstats = (TextView) findViewById(R.id.postsstats);


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
                Toast.makeText(HomeScreenActivity.this, "Downloading data please wait till the stats appear", Toast.LENGTH_SHORT).show();
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
                    commentstats.setText("Start: "+responseTime.commentPingTimeStart+"\nEnd: "+responseTime.commentPingTimeEnd+"\nStart Save: "+responseTime.commentSaveTimeStart+"\nEnd Save: "+responseTime.commentSaveTimeEnd);
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
                    photostats.setText("Start: "+responseTime.photoPingTimeStart+"\nEnd: "+responseTime.photoPingTimeEnd+"\nStart Save: "+responseTime.photoSaveTimeStart+"\nEnd Save: "+responseTime.photoSaveTimeEnd);
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
                if (response != null && response instanceof TodoResponseTime) {
                    TodoResponseTime responseTime = (TodoResponseTime) response;
                    todostats.setText("Start: "+responseTime.todoPingTimeStart+"\nEnd: "+responseTime.todoPingTimeEnd+"\nStart Save: "+responseTime.todaSaveTimeStart+"\nEnd Save: "+responseTime.todoSaveTimeEnd);
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
                if (response != null && response instanceof PostResponseTime) {
                    PostResponseTime responseTime = (PostResponseTime) response;
                    postsstats.setText("Start: "+responseTime.postPingTimeStart+"\nEnd: "+responseTime.postPingTimeEnd+"\nStart Save: "+responseTime.postSaveTimeStart+"\nEnd Save: "+responseTime.postSaveTimeEnd);
                }
            }

            @Override
            public void onError(CarnotMachineTestError error) {
                Toast.makeText(HomeScreenActivity.this, error.errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


    //if the offline database needs to be checked, this can only be viewed using a realm browser.
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
