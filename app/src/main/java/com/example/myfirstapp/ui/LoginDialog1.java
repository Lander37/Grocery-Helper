package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.R;

/**
 * Created by Yan Chao on 2/4/2017.
 */

public class LoginDialog1 extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_login_1, container, false);
        TextView LoginPrompt = (TextView) v.findViewById(R.id.login_dialog1_prompt);
        return v;
    }

    public static LoginDialog1 newInstance() {
        LoginDialog1 loginDialog = new LoginDialog1();
        return loginDialog;
    }

}
