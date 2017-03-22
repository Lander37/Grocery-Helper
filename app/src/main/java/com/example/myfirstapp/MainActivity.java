package com.example.myfirstapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btCreateProfile;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCreateProfile = (Button) findViewById(R.id.createProfile);

        btCreateProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                launchActivity();
            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
    }

}
