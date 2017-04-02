package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

/**
 * Created by Daniel on 3/30/2017.
 */

public class CreateListDialog1 extends DialogFragment {

    private DatabaseAccess databaseAccess;
    private EditText listNameInput;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());

        View v = inflater.inflate(R.layout.dialog_create_list_1, container, false);
        listNameInput = (EditText) v.findViewById(R.id.create_list_dialog_1_editText);
        Button listConfirmButton = (Button) v.findViewById(R.id.create_list_dialog_1_confirm_btn);

        listConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String listName = getListName();
                if(databaseAccess.listNameValidity(listName)) {

                    createGList(listName);

                    ((NavigationActivity) getActivity()).replaceThis(SelectCategoryFragment.newInstance(), "Cart");
                    ((NavigationActivity)getActivity()).closeDialogs();
                }
                else {
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

    private String getListName(){
        return listNameInput.getText().toString();

    }

    private void createGList(String listName){
        databaseAccess.open();
        databaseAccess.createGList(listName);
        databaseAccess.close();
    }

}

