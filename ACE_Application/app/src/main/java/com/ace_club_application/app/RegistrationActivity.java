package com.ace_club_application.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                confirmRegistration(v);
            }
        });

    }

    public void confirmRegistration(View v) {
        Intent intent = new Intent(this, MainActivity.class);
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