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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity {
    String appId = "testapp-mftvr";
    // ACE APP ID = "ace_android_application-sqneg"

    private Button button;
    private EditText email;
    private EditText password;
    private TextView textView;

    String emailString = "email";
    String passwordString = "password";

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Displays main page and initializes MongoDB realm on application
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String email = null;
        String password = null;
        Boolean login = false;

        if (extras != null)
        {
            email = getIntent().getStringExtra("email");
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
        App app = new App(new AppConfiguration.Builder(appId).build());

        //Get EditText boxes from main and button
        email = (EditText) findViewById(R.id.editMainEmailAddress);
        password = (EditText) findViewById(R.id.editMainPassword);
        button = (Button) findViewById(R.id.mainLoginButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailString = email.getText().toString();
                passwordString = password.getText().toString();
                Credentials credentials = Credentials.emailPassword(emailString, passwordString);
                
                //Logs a user in to the realm, tells users if successful or not
                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if (result.isSuccess()) {
                            Log.v("User", "Logged in anonymously");
                        } else {
                            Log.v("User", "failed to log in");
                        }
                    }
                });
                openConfirmation();
            }
        });





//        app.getEmailPassword().registerUserAsync(emailString, passwordString, it->{
//            if (it.isSuccess()) {
//                Log.v("User", "new user registered");
//            }
//            else {
//                Log.v("User", "failed to log in");
//            }
//
//        });

        App app = new App(new AppConfiguration.Builder(Appid).build());

        //If we have a valid email and password
        if (email != null && password != null)
        {
            //If we're coming from the login page, login to the app
            if (login)
            {
                Credentials credentials = Credentials.emailPassword(email, password);
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
            //Otherwise, create a new user with the email and password.
            else
            {
                app.getEmailPassword().registerUserAsync(email, password, it->{
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