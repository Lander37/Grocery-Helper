package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.myfirstapp.dbHelpers.DatabaseAccess;
import com.example.myfirstapp.R;

import static com.example.myfirstapp.ui.CreateProfileActivity.thisUsername;

public class ProfileFragment extends Fragment {

    private Button btLogOut;
    private Button btCloseApp;
    private Button btSaveChanges;
    private EditText etEditUsername;
    private EditText etEditPassword;
    private Spinner spLocationList;
    private SeekBar sbHealthSeekBar;
    private CheckBox tbHealthierChoice;
    private CheckBox tbHalal;
    private CheckBox tbVegetarian;
    private CheckBox tbGluten;
    private boolean isCheckedHC = true;
    private boolean isCheckedHA = true;
    private boolean isCheckedVG = true;
    private boolean isCheckedGF = true;
    private DatabaseAccess databaseAccess;
    private int healthPref;
    private int healthEmphasis;
    private String defaultLocation;
    private static final int MinPassLen = 6;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.databaseAccess= DatabaseAccess.getInstance(getContext());

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btSaveChanges = (Button) view.findViewById(R.id.saveChanges);
        btSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editProfile()){
                    showDialog(EditProfileConfirmationDialog.newInstance());
                }
            }
        });
        btLogOut = (Button) view.findViewById(R.id.logOut);
        btLogOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).setBtLogOut();
            }
        });

        etEditUsername = (EditText) view.findViewById(R.id.editUserName);
        etEditPassword = (EditText) view.findViewById(R.id.editPassword);

        databaseAccess.open();
        healthPref = databaseAccess.getDpId(thisUsername);
        healthEmphasis = databaseAccess.getHealthEmphasis(thisUsername);
        defaultLocation = databaseAccess.getDefaultLocation(thisUsername);
        databaseAccess.close();

        spLocationList = (Spinner) view.findViewById(R.id.locationList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocationList.setAdapter(adapter);
        spLocationList.setSelection(getIndex(spLocationList, defaultLocation));



        if ((healthPref % 2)==0){
            isCheckedGF = false;
        }
        if (((healthPref/2) % 2)==0){
            isCheckedHC = false;
        }
        if (((healthPref/4) % 2)==0){
            isCheckedVG = false;
        }
        if (((healthPref/8) % 2)==0){
            isCheckedHA = false;
        }

        sbHealthSeekBar = (SeekBar) view.findViewById(R.id.healthSeekBar);
        sbHealthSeekBar.setMax(4);
        sbHealthSeekBar.setProgress(healthEmphasis-1);
        tbHealthierChoice = (CheckBox) view.findViewById(R.id.healthierChoiceButton);
        tbHealthierChoice.setChecked(isCheckedHC);
        tbHalal = (CheckBox) view.findViewById(R.id.halalButton);
        tbHalal.setChecked(isCheckedHA);
        tbVegetarian = (CheckBox) view.findViewById(R.id.vegetarianButton);
        tbVegetarian.setChecked(isCheckedVG);
        tbGluten = (CheckBox) view.findViewById(R.id.glutenButton);
        tbGluten.setChecked(isCheckedGF);

        return view;
    }


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean editProfile(){
        if(shortPassword(etEditPassword)){
            showDialog(LoginDialog2.newInstance());
            return false;
        }
        else {
            String username = etEditUsername.getText().toString();
            thisUsername =  username;
            String password = etEditPassword.getText().toString();
            int healthEmphasis = sbHealthSeekBar.getProgress() + 1;
            String defaultLocation = spLocationList.getSelectedItem().toString();

            boolean HA = false;
            boolean HC = false;
            boolean GF = false;
            boolean VG = false;

            if (tbHalal.isChecked()) {
                HA = true;
            }
            if (tbHealthierChoice.isChecked()) {
                HC = true;
            }
            if (tbGluten.isChecked()) {
                GF = true;
            }
            if (tbVegetarian.isChecked()) {
                VG = true;
            }

            int a = 0;
            int b = 0;
            int c = 0;
            int d = 0;

            if(tbHalal.isChecked()){
                a = 1;
            }
            if(tbVegetarian.isChecked()){
                b = 1;
            }
            if(tbHealthierChoice.isChecked()){
                c = 1;
            }
            if(tbGluten.isChecked()){
                d = 1;
            }

            int dpId = 8*a + 4*b + 2*c + d;

            databaseAccess.open();
            databaseAccess.editProfile(username, password, healthEmphasis, defaultLocation, dpId);
            databaseAccess.close();
        }
        return true;
    }
    private boolean shortPassword(EditText passwordField) {
        return passwordField.getText().toString().length() <= (MinPassLen - 1);
    }

    public void showDialog(DialogFragment fragment){
        closeDialogs();
        fragment.show(getFragmentManager().beginTransaction(),"dialog");
    }

    public void closeDialogs(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if(prev != null){
            transaction.remove(prev);
        }
        transaction.commit();
    }
}
