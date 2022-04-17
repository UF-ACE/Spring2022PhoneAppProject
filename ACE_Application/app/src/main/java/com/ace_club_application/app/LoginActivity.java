package com.ace_club_application.app;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button confirmLoginButton;
    EditText emailInput;
    EditText passwordInput;

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        confirmLoginButton = (Button) findViewById(R.id.confirmLoginButton);
        emailInput = (EditText) findViewById(R.id.emailAddressText);
        passwordInput = (EditText) findViewById(R.id.passwordText);

        confirmLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                openConfirmation(v);
            }
        });

    }

    public void openConfirmation(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("login", true);
        startActivity(intent);
    }
}
