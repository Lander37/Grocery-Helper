package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.myfirstapp.R;

/**
 * Created by Daniel on 3/30/2017.
 */

public class FilterDietaryPrefDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_select_dietary_preference, container, false);
        CheckBox healthierChoice = (CheckBox) v.findViewById(R.id.select_dietP_healthierChoice_checkBox);
        CheckBox halal = (CheckBox) v.findViewById(R.id.select_dietP_halal_checkBox);
        CheckBox vegetarian = (CheckBox) v.findViewById(R.id.select_dietP_vegeterian_checkBox);
        CheckBox glutenFree = (CheckBox) v.findViewById(R.id.select_dietP_glutenFree_checkBox);
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

