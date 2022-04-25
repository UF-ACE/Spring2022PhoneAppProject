package com.ace_club_application.app;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.bson.Document;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Displays main page and initializes MongoDB realm on application
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes MongoDB realm on application
        Realm.init(this);
        App app = new App(new AppConfiguration.Builder(BuildConfig.REALM_APP_ID).build());

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        Button loginButton;
        TextView scoreDisplay;
        TextView tierDisplay;


        String name = null;
        String email = null;
        String phoneNumber = null;
        String major = null;
        int year = 0;
        int score = 0;
        String tier = null;
        Boolean login = false;

        if (extras != null)
        {
            name = getIntent().getStringExtra("name");
            email = getIntent().getStringExtra("email");
            phoneNumber = getIntent().getStringExtra("phoneNumber");
            major = getIntent().getStringExtra("major");
            year = getIntent().getIntExtra("year", 0);
            score = getIntent().getIntExtra("score", 0);
            tier = getIntent().getStringExtra("tier");
            login = getIntent().getBooleanExtra("login", true);
        }

        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                User user = app.currentUser();
//                if (user != null)
//                {
//                    String userId = user.getId();
//                    Log.v("ID: ",userId);
//                    user.logOut();
//                }
                openLogin(v);
            }
        });

        //If we have a valid email
        if (email != null)
        {
            //If we're coming from the login page, load user data
            if (login)
            {
                scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);
                tierDisplay = (TextView) findViewById(R.id.tierDisplay);

                String scoreString = score + " Points";
                String logout = "Logout";

                scoreDisplay.setText(scoreString);
                tierDisplay.setText(tier);
                loginButton.setText(logout);
            }
            //Otherwise, bring the user to the login page
            else
            {
                Intent newIntent = new Intent(this, LoginActivity.class);
                startActivity(newIntent);
//                app.getEmailPassword().registerUserAsync(email, password, it->{
//                    if (it.isSuccess()){
//                        Log.v("User", "registered with email");
//                    }
//                    else{
//                        Log.v("User", "was unsuccessful in registration");
//                    }
//                });
            }
        }


    }

    public void openLogin(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}