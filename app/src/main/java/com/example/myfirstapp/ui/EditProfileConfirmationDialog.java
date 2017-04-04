package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapp.R;


/**
 * Created by admin on 3/4/2017.
 */

public class EditProfileConfirmationDialog extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_edit_profile, container, false);
        TextView LoginPrompt = (TextView) v.findViewById(R.id.edit_profile_cfm_dialog);
        return v;
    }

    public static EditProfileConfirmationDialog newInstance() {
        EditProfileConfirmationDialog editProfileDialog = new EditProfileConfirmationDialog();

        return editProfileDialog;
    }
}
