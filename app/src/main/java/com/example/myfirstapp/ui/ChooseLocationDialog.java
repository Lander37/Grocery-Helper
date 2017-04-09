package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import static com.example.myfirstapp.ui.MainActivity.thisUsername;

/**
 * ChooseLocationDialog.java
 * @author Daniel
 */

public class ChooseLocationDialog extends DialogFragment {
    private String defaultLocation;
    private DatabaseAccess databaseAccess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.databaseAccess= DatabaseAccess.getInstance(getContext());
        databaseAccess.open();
        defaultLocation = databaseAccess.getDefaultLocation(thisUsername);
        databaseAccess.close();
        View v = inflater.inflate(R.layout.dialog_choose_location, container, false);
        Spinner locationSpinner = (Spinner) v.findViewById(R.id.choose_location_spinner);
        locationSpinner.setSelection(getIndex(locationSpinner, defaultLocation));

        Button okButton = (Button) v.findViewById(R.id.choose_location_ok_btn);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).closeDialogs();
            }
        });

        return v;
    }

    /**
     * Creates a new instance of the location Dialog
     * @return  a new location dialog
     */
    public static ChooseLocationDialog newInstance() {
        ChooseLocationDialog chooseLocaDialog = new ChooseLocationDialog();
        return chooseLocaDialog;
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
}

