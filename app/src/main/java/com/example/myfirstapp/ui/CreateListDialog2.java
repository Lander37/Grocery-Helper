package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstapp.R;

/**
 * CreateListDialog2.java
 * @author Daniel
 */

public class CreateListDialog2 extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_create_list_2, container, false);
        Button okButton = (Button) v.findViewById(R.id.create_list_dialog_2_ok_btn);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).showDialog(CreateListDialog1.newInstance());
            }
        });

        return v;
    }

    /**
     * Creates and reflect that the list has been created
     * @return  the dialog showing that the list has been created
     */
    public static CreateListDialog2 newInstance() {
        CreateListDialog2 createListDialog = new CreateListDialog2();
        return createListDialog;
    }
}

