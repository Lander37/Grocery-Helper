package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.mgr.GroceryManager;

/**
 * Created by Daniel on 3/30/2017.
 */

public class LogoutDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_logout, container, false);
        FloatingActionButton okButton = (FloatingActionButton) v.findViewById(R.id.logout_ok_btn);
        FloatingActionButton cancelButton = (FloatingActionButton) v.findViewById(R.id.logout_cancel_btn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).closeDialogs();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).closeDialogs();
            }
        });

        return v;
    }

    public static LogoutDialog newInstance() {
        LogoutDialog logoutDialog = new LogoutDialog();

        return logoutDialog;
    }
}

