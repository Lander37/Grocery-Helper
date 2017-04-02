package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myfirstapp.R;

/**
 * Created by Daniel on 3/30/2017.
 */

public class ChooseLocationDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_choose_location, container, false);
        Spinner locationSpinner = (Spinner) v.findViewById(R.id.choose_location_spinner);
        Button okButton = (Button) v.findViewById(R.id.choose_location_ok_btn);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).closeDialogs();
            }
        });

        return v;
    }

    public static ChooseLocationDialog newInstance() {
        ChooseLocationDialog chooseLocaDialog = new ChooseLocationDialog();
        return chooseLocaDialog;
    }
}

