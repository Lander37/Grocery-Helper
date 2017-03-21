package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.myfirstapp.classes.DietaryPreference;
import com.example.myfirstapp.classes.Profile;

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
    Profile newProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        btDone = (Button) findViewById(R.id.Done);
        etEditUsername = (EditText) findViewById(R.id.editUserName);
        etEditPassword = (EditText) findViewById(R.id.editPassword);

        spLocationList = (Spinner) findViewById(R.id.locationList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.location_array, android.R.layout.simple_spinner_item);
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
                setProfile();
                launchActivity();
            }
        });
    };

    private void setProfile(){
        String username = etEditUsername.getText().toString();
        String password = etEditPassword.getText().toString();
        int healthEmphasis = sbHealthSeekBar.getProgress();
        String defaultLocation = spLocationList.getSelectedItem().toString();
        boolean HC = false;
        boolean HA = false;
        boolean VG = false;
        boolean GF = false;
        if(tbHealthierChoice.getText()=="On"){HC = true;}
        if(tbHalal.getText()=="On"){HA = true;}
        if(tbVegetarian.getText()=="On"){VG = true;}
        if(tbGluten.getText()=="On"){GF = true;}

        //need changes to DietaryPreference
        DietaryPreference healthPreference = new DietaryPreference(0000,HC,HA,VG,GF);
        Profile newProfile= new Profile(username, password, defaultLocation, healthPreference, healthEmphasis);
    }
    private void launchActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
};




