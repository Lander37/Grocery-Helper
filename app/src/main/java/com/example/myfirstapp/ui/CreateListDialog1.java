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
import com.example.myfirstapp.mgr.GroceryManager;

/**
 * CreateListDialog1.java
 * @author Daniel
 */

public class CreateListDialog1 extends DialogFragment {

    private GroceryManager groceryManager;
    private EditText listNameInput;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groceryManager = new GroceryManager(getActivity().getApplicationContext());

    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_create_list_1, container, false);
        listNameInput = (EditText) v.findViewById(R.id.create_list_dialog_1_editText);
        Button listConfirmButton = (Button) v.findViewById(R.id.create_list_dialog_1_confirm_btn);

        listConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String listName = getListName();

                if(groceryManager.createNewList(listName)) {
                    ((NavigationActivity) getActivity()).replaceThis(SelectCategoryFragment.newInstance(0), "Cart");

                    ((NavigationActivity)getActivity()).closeDialogs();
                }
                else {
                    ((NavigationActivity) getActivity()).showDialog(CreateListDialog2.newInstance());
                }

            }
        });

        return v;
    }

    /**
     *
     * @return
     */
    public static CreateListDialog1 newInstance() {
        return new CreateListDialog1();
    }

    /**
     *
     * @return
     */
    private String getListName(){
        return listNameInput.getText().toString();

    }
}

