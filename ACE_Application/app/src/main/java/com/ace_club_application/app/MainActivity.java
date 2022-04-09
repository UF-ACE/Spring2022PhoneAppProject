package com.ace_club_application.app;

import com.ace_club_application.app.BuildConfig;

import io.realm.Realm;
import io.realm.RealmCollection;
import io.realm.RealmConfiguration;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.aceapp.MESSAGE";

    //TODO: replace with buildConfig in gradle file
    String Appid = "ace_android_application-sqneg";

    Realm testRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes MongoDB realm on application
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder().name(Appid).build();

        testRealm = Realm.getInstance(config);

    }

    public void confirmCode(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

}