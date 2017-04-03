package com.example.myfirstapp.ui;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ToggleButton;
import android.app.AlertDialog;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.Profile;

import java.util.Random;

public class CreateProfileActivity extends AppCompatActivity {

    private Button btDone;
    private EditText etEditUsername;
    private EditText etEditPassword;
    private Spinner spLocationList;
    private SeekBar sbHealthSeekBar;
    private ToggleButton tbHealthierChoice;
    private ToggleButton tbHalal;
    private ToggleButton tbVegetarian;
    private ToggleButton tbGluten;
    private static Profile newProfile;
    public static final int MinPassLen = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        btDone = (Button) findViewById(R.id.Done);
        etEditUsername = (EditText) findViewById(R.id.editUserName);
        etEditPassword = (EditText) findViewById(R.id.editPassword);

        spLocationList = (Spinner) findViewById(R.id.locationList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocationList.setAdapter(adapter);

        sbHealthSeekBar = (SeekBar) findViewById(R.id.healthSeekBar);
        tbHealthierChoice = (ToggleButton) findViewById(R.id.healthierChoiceButton);
        tbHalal = (ToggleButton) findViewById(R.id.halalButton);
        tbVegetarian = (ToggleButton) findViewById(R.id.vegetarianButton);
        tbGluten = (ToggleButton) findViewById(R.id.glutenButton);
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setProfile()){
                    launchActivity();
                }
            }
        });
    };

    private boolean setProfile(){
        if(areFieldsEmpty(etEditUsername, etEditPassword)) {
            showDialog(LoginDialog1.newInstance());
            return false;
        }

        else if(shortPassword(etEditPassword)){
            showDialog(LoginDialog2.newInstance());
            return false;
        }
        else {
            String username = etEditUsername.getText().toString();
            String password = etEditPassword.getText().toString();
            int healthEmphasis = sbHealthSeekBar.getProgress();
            String defaultLocation = spLocationList.getSelectedItem().toString();

            boolean HA = false;
            boolean HC = false;
            boolean GF = false;
            boolean VG = false;

            if (tbHalal.getText() == "On") {
                HA = true;
            }
            if (tbHealthierChoice.getText() == "On") {
                HC = true;
            }
            if (tbGluten.getText() == "On") {
                GF = true;
            }
            if (tbVegetarian.getText() == "On") {
                VG = true;
            }

            int a = 0;
            int b = 0;
            int c = 0;
            int d = 0;
            if (HA == true) {
                a = 1;
            }
            if (HC == true) {
                b = 1;
            }
            if (GF == true) {
                c = 1;
            }
            if (VG == true) {
                d = 1;
            }

            int dpId = Integer.valueOf(String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d));

            //make creating a profile id more consistent
            Random rn = new Random();
            int profileID = rn.nextInt();

            newProfile = new Profile(username, password, defaultLocation, dpId, healthEmphasis, profileID);
            return true;
        }
    }
    private boolean areFieldsEmpty(EditText... fields) {
        for(int i = 0; i < fields.length; i++) {
            if(fields[i].getText().toString().matches("")){
                return true;
            }
        }
        return false;
    }
    private boolean shortPassword(EditText passwordField){
        if(passwordField.getText().toString().length() > (MinPassLen-1)){
           return false;
        }
        return true;
    }
    private void launchActivity() {

        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
    public void showDialog(DialogFragment fragment){
        closeDialogs();
        fragment.show(getSupportFragmentManager().beginTransaction(),"dialog");
    }

    public void closeDialogs(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if(prev != null){
            transaction.remove(prev);
        }
        transaction.commit();
    }
};


