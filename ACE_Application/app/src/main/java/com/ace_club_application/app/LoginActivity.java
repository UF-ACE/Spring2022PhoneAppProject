package com.ace_club_application.app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        confirmLoginButton = (Button) findViewById(R.id.confirmLoginButton);
        registrationButton = (Button) findViewById(R.id.registrationButton);

        emailInput = (EditText) findViewById(R.id.emailAddressText);
        passwordInput = (EditText) findViewById(R.id.passwordText);

        confirmLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
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
