package com.example.myfirstapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myfirstapp.R;
import com.example.myfirstapp.ui.CreateProfileActivity;

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

}
