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

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class RegistrationActivity extends AppCompatActivity {

    Button confirmRegistrationButton;
    EditText nameInput;
    EditText majorInput;
    EditText yearInput;
    EditText phoneInput;
    EditText emailInput;
    EditText passwordInput;
    EditText confirmPasswordInput;

    String name;
    String major;
    String year;
    String phoneNumber;
    String email;
    String password;
    String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        App app = new App(new AppConfiguration.Builder(BuildConfig.REALM_APP_ID).build());

        confirmRegistrationButton = (Button) findViewById(R.id.confirmRegistrationButton);

        nameInput = (EditText) findViewById(R.id.nameText);
        majorInput = (EditText) findViewById(R.id.majorText);
        yearInput = (EditText) findViewById(R.id.yearText);
        phoneInput = (EditText) findViewById(R.id.phoneText);
        emailInput = (EditText) findViewById(R.id.emailAddressText);
        passwordInput = (EditText) findViewById(R.id.passwordText);
        confirmPasswordInput = (EditText) findViewById(R.id.confirmPasswordText);

        confirmRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                major = majorInput.getText().toString();
                year = yearInput.getText().toString();
                phoneNumber = phoneInput.getText().toString();
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                confirmPassword = confirmPasswordInput.getText().toString();

                if (!password.equals(confirmPassword))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Passwords do not match";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    app.getEmailPassword().registerUserAsync(email, password, it->{
                        if (it.isSuccess()){
                            Log.v("User", "registered with email");
                            User user = app.currentUser();
                            MongoClient mongoClient =
                                    user.getMongoClient("mongodb-atlas"); // service for MongoDB Atlas cluster containing custom user data
                            MongoDatabase mongoDatabase =
                                    mongoClient.getDatabase("ACEsite");
                            MongoCollection<Document> mongoCollection =
                                    mongoDatabase.getCollection("users");
                            mongoCollection.insertOne(
                                    new Document("userid", user.getId())
                                            .append("name", name)
                                            .append("email", email)
                                            .append("phoneNumber", phoneNumber)
                                            .append("major", major)
                                            .append("year", year)
                                            .append("score", 0)
                                            .append("tier", "Standard"))
                                    .getAsync(result -> {
                                        if (result.isSuccess()) {
                                            Log.v("EXAMPLE", "Inserted custom user data document. _id of inserted document: "
                                                    + result.get().getInsertedId());
                                        } else {
                                            Log.e("EXAMPLE", "Unable to insert custom user data. Error: " + result.getError());
                                        }
                                    });
                        }
                        else{
                            Log.v("User", "was unsuccessful in registration");
                        }
                    });
                    confirmRegistration(v);
                }
            }
        });

    }

    public void confirmRegistration(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("major", major);
        intent.putExtra("year", year);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("login", false);
        startActivity(intent);
    }
}