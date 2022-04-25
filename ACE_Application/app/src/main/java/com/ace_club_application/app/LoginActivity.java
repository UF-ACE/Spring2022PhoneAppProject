package com.ace_club_application.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.bson.Document;

import org.bson.Document;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class LoginActivity extends AppCompatActivity {


    Button confirmLoginButton;
    Button registrationButton;
    EditText emailInput;
    EditText passwordInput;

    String name = "";
    String email = "";
    String phoneNumber = "";
    String major = "";
    int year = 0;
    int score = 0;
    String tier = "";
    String password;

    MongoDatabase mongoDatabase;
    MongoClient mongoClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        App app = new App(new AppConfiguration.Builder(BuildConfig.REALM_APP_ID).build());

        confirmLoginButton = (Button) findViewById(R.id.confirmLoginButton);
        registrationButton = (Button) findViewById(R.id.registrationButton);

        emailInput = (EditText) findViewById(R.id.emailAddressText);
        passwordInput = (EditText) findViewById(R.id.passwordText);

        confirmLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();


                Credentials credentials = Credentials.emailPassword(email, password);
                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> it) {
                        if (it.isSuccess())
                        {
                            Log.v("User", "logged in via email and password");
                            User user = app.currentUser();
                            Document customUserData = user.getCustomData();
                            try
                            {
                                name = (String) customUserData.get("name");
                                phoneNumber = (String) customUserData.get("phoneNumber");
                                major = (String) customUserData.get("major");
                                tier = (String) customUserData.get("tier");
                                year = (Integer) customUserData.get("year");
                                score = (Integer) customUserData.get("score");
                            }
                            catch (NullPointerException ex) {

                            }
                            loginUser(v);
                            mongoClient = user.getMongoClient("mongodb-atlas");
                            mongoDatabase = mongoClient.getDatabase("ACEsite");
                            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("events");
                        }
                        else
                        {
                            Log.v("User", "failed to login");
                            Context context = getApplicationContext();
                            CharSequence text = "Invalid username or password";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                });
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistration(v);
            }
        });

    }

    public void loginUser(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("major", major);
        intent.putExtra("year", year);
        intent.putExtra("score", score);
        intent.putExtra("tier", tier);
        intent.putExtra("login", true);
        startActivity(intent);
    }

    public void openRegistration(View v) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
