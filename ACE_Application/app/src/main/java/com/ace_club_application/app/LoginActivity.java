package com.ace_club_application.app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    String email;
    String password;

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
//                            User user = app.currentUser();
//                            MongoClient mongoClient =
//                                    user.getMongoClient("mongodb-atlas"); // service for MongoDB Atlas cluster containing custom user data
//                            MongoDatabase mongoDatabase =
//                                    mongoClient.getDatabase("ACEsite");
//                            MongoCollection<Document> mongoCollection =
//                                    mongoDatabase.getCollection("users");
//                            String name = "name";
//                            String major = "major";
//                            int year = 1;
//                            String phoneNumber = "123-456-7890";
//                            mongoCollection.insertOne(
//                                    new Document("userid", user.getId())
//                                            .append("name", name)
//                                            .append("email", email)
//                                            .append("phoneNumber", phoneNumber)
//                                            .append("major", major)
//                                            .append("year", year)
//                                            .append("score", 0)
//                                            .append("tier", "Standard"))
//                                    .getAsync(result -> {
//                                        if (result.isSuccess()) {
//                                            Log.v("EXAMPLE", "Inserted custom user data document. _id of inserted document: "
//                                                    + result.get().getInsertedId());
//                                        } else {
//                                            Log.e("EXAMPLE", "Unable to insert custom user data. Error: " + result.getError());
//                                        }
//                                    });
                        }
                        else
                        {
                            Log.v("User", "failed to login");
                        }
                    }
                });

                loginUser(v);
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
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("login", true);
        startActivity(intent);
    }

    public void openRegistration(View v) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
