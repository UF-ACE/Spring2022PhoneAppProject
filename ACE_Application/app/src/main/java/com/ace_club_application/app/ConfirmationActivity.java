package com.ace_club_application.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

public class ConfirmationActivity extends AppCompatActivity {

    Button confirmCodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        confirmCodeButton = (Button) findViewById(R.id.confirmCodeButton);
        confirmCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCode(v);
            }
        });
    }

    public void confirmCode(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
