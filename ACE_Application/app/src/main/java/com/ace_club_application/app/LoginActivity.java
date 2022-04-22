package com.ace_club_application.app;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
=======
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
                    public void onResult(App.Result<User> result) {
                        if (result.isSuccess())
                        {
                            Log.v("User", "logged in via email and password");
                            User user = app.currentUser();
                            mongoClient = user.getMongoClient("mongodb-atlas");
                            mongoDatabase = mongoClient.getDatabase("ACEsite");
                            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("events");
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
>>>>>>> Stashed changes
}
