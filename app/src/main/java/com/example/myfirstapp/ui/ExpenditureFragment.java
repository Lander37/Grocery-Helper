package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstapp.R;

public class ExpenditureFragment extends Fragment {

    private Button btInputBudget;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_expenditure_main, container, false);
        btInputBudget = (Button) view.findViewById(R.id.inputBudget);
        btInputBudget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                launchActivity();
            }
        });
        return view;
    }
    public void launchActivity() {
        Intent intent = new Intent (getActivity(), budget_calculator.class);
        startActivity(intent);
    }
    public static ExpenditureFragment newInstance() {
        ExpenditureFragment fragment = new ExpenditureFragment();
        return fragment;
    }


}