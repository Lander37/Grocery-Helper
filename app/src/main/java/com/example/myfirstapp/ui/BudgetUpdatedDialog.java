package com.example.myfirstapp.ui;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstapp.R;

/**
 * BudgetUpdatedDialog.java
 * @author Daniel
 */


public class BudgetUpdatedDialog extends DialogFragment {
    /**
     *Bundle has been passed on to onCreate.
     * @param savedInstanceState UI state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_budget_update, container, false);
        Button okButton = (Button) v.findViewById(R.id.budget_update_ok_btn);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).closeDialogs();
            }
        });

        return v;
    }

    /**
     * Creates a new instance of the dialog indicating that budget has been updated
     * @return pop up that diaog has been updated.
     */
    public static BudgetUpdatedDialog newInstance() {
        BudgetUpdatedDialog budgetUpdatedDialog = new BudgetUpdatedDialog();
        return budgetUpdatedDialog;
    }
}

