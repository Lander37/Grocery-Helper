package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myfirstapp.R;

public class BudgetFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_budget_calculator, container, false);
        Button btCalculate = (Button) view.findViewById(R.id.budgetconfirm);
        Button btBack = (Button) view.findViewById(R.id.back);

        btCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
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

    public static BudgetFragment newInstance() {
        return new BudgetFragment();
    }



}