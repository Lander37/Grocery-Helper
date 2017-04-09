package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import static com.example.myfirstapp.ui.MainActivity.thisUsername;

/**
 * FilterDietaryPrefDialog.java
 * @author Daniel
 */

public class FilterDietaryPrefDialog extends DialogFragment {
    private boolean isCheckedHC = true;
    private boolean isCheckedHA = true;
    private boolean isCheckedVG = true;
    private boolean isCheckedGF = true;
    private int healthPref;
    private DatabaseAccess databaseAccess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.databaseAccess= DatabaseAccess.getInstance(getContext());
        View v = inflater.inflate(R.layout.dialog_select_dietary_preference, container, false);
        databaseAccess.open();
        healthPref = databaseAccess.getDpId(thisUsername);
        databaseAccess.close();

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

        CheckBox healthierChoice = (CheckBox) v.findViewById(R.id.select_dietP_healthierChoice_checkBox);
        healthierChoice.setChecked(isCheckedHC);
        CheckBox halal = (CheckBox) v.findViewById(R.id.select_dietP_halal_checkBox);
        halal.setChecked(isCheckedHA);
        CheckBox vegetarian = (CheckBox) v.findViewById(R.id.select_dietP_vegeterian_checkBox);
        vegetarian.setChecked(isCheckedVG);
        CheckBox glutenFree = (CheckBox) v.findViewById(R.id.select_dietP_glutenFree_checkBox);
        glutenFree.setChecked(isCheckedGF);
        Button okButton = (Button) v.findViewById(R.id.select_dietP_ok_btn);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).closeDialogs();
            }
        });

        return v;
    }

    public static FilterDietaryPrefDialog newInstance() {
        FilterDietaryPrefDialog filterDPDialog = new FilterDietaryPrefDialog();
        return filterDPDialog;
    }
}

