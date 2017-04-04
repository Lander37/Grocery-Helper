package com.example.myfirstapp.ui;
/**
 * BudgetFragment.java
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myfirstapp.R;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import static com.example.myfirstapp.ui.MainActivity.thisUsername;

public class BudgetFragment extends Fragment {
    private DatabaseAccess databaseAccess;
    private Button btCalculate;
    private Button btBack;
    private EditText etEnterBudget;
    private TextView tvNewBudget;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.databaseAccess= DatabaseAccess.getInstance(getContext());
        View view = inflater.inflate(R.layout.activity_budget_calculator, container, false);
        etEnterBudget = (EditText) view.findViewById(R.id.editText);
        tvNewBudget = (TextView) view.findViewById(R.id.textView6);
        btCalculate = (Button) view.findViewById(R.id.budgetconfirm);
        btBack = (Button) view.findViewById(R.id.back);

        btCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                float budget = Float.parseFloat(etEnterBudget.getText().toString());
                databaseAccess.open();
                databaseAccess.updateBudget(budget,thisUsername);
                databaseAccess.close();
                ((NavigationActivity)getActivity()).showDialog(BudgetUpdatedDialog.newInstance());
                ((NavigationActivity)getActivity()).replaceThis(ExpenditureFragment.newInstance(),"Expenditure");
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(ExpenditureFragment.newInstance(),"Expenditure");

            }
        });
        return view;
    }

    /**
     *
     * @return
     */
    public static BudgetFragment newInstance() {
        return new BudgetFragment();
    }



}