package com.ace_club_application.app;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.aceapp.MESSAGE";

    //TODO: replace with buildConfig in gradle file
    String Appid = "ace_android_application-sqneg";

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String username = null;
        String password = null;
        Boolean login = false;

        if (extras != null)
        {
            username = getIntent().getStringExtra("username");
            password = getIntent().getStringExtra("password");
            login = getIntent().getBooleanExtra("login", true);
        }



        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin(v);
            }
        });

        //Initializes MongoDB realm on application
        Realm.init(this);

        App app = new App(new AppConfiguration.Builder(Appid).build());

        //If we have a valid username and password
        if (username != null && password != null)
        {
            //If we're coming from the login page, login to the app
            if (login)
            {
                Credentials credentials = Credentials.emailPassword(username, password);
                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if (result.isSuccess())
                        {
                            Log.v("User", "logged in via email and password");
                        }
                        else
                        {
                            Log.v("User", "failed to login");
                        }
                    }
                });
            }
            //Otherwise, create a new user with the username and password.
            else
            {
                app.getEmailPassword().registerUserAsync(username, password, it->{
                    if (it.isSuccess()){
                        Log.v("User", "registered with email");
                    }
                    else{
                        Log.v("User", "was unsuccessful in registration");
                    }
                });
            }
        }


    }

    public void openLogin(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}