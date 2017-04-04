package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapp.R;

/**
 * Created by admin on 4/4/2017.
 */

public class InvalidLoginDialog extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_invalid_login_dialog, container, false);
        TextView LoginPrompt = (TextView) v.findViewById(R.id.invalid_login_dialog_prompt);
        return v;
    }

    public static InvalidLoginDialog newInstance() {
        InvalidLoginDialog invalidDialog = new InvalidLoginDialog();
        return invalidDialog;
    }
}
