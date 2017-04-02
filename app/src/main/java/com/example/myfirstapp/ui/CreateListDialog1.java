package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfirstapp.R;

/**
 * Created by Daniel on 3/30/2017.
 */

public class CreateListDialog1 extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_create_list_1, container, false);
        EditText listNameInput = (EditText) v.findViewById(R.id.create_list_dialog_1_editText);
        Button listConfirmButton = (Button) v.findViewById(R.id.create_list_dialog_1_confirm_btn);

        listConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true) {
                    ((NavigationActivity) getActivity()).replaceThis(SelectCategoryFragment.newInstance(), "Cart");
                    ((NavigationActivity)getActivity()).closeDialogs();
                } else {
                    ((NavigationActivity) getActivity()).showDialog(CreateListDialog2.newInstance());
                }
            }
        });

        return v;
    }

    public static CreateListDialog1 newInstance() {
        CreateListDialog1 createListDialog = new CreateListDialog1();
        return createListDialog;
    }
}

