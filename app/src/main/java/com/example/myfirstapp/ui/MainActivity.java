package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.HealthierChoiceAPIHandler;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    HealthierChoiceAPIHandler healthierChoiceAPIHandler;
    Button btCreateProfile;
    Button btLogin;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static String thisUsername;
    public static DecimalFormat df = new DecimalFormat("0.00");

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

        btLogin = (Button) findViewById(R.id.login);
        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                launchLogin();
            }
        });

        healthierChoiceAPIHandler = new HealthierChoiceAPIHandler(getApplicationContext());
        df.setRoundingMode(RoundingMode.HALF_UP);
    }

    private void launchActivity() {

        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);

    }

    /**
     * Initates the transition into the login activity class
     */

    private void launchLogin() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void setThisUsername(String username){
        thisUsername = username;
    }
}
