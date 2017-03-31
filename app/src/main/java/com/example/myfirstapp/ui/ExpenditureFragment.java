package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfirstapp.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ExpenditureFragment extends Fragment {
    TextView txt;
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
                ((NavigationActivity)getActivity()).replaceExpenditure();
            }
        });
        txt = (TextView)view.findViewById(R.id.currentmonth);
        String months [] = {
                "January","February","March","April",
                "May","June","July","August",
                "September","October","November","December"};
        GregorianCalendar gCalendar = new GregorianCalendar();
        String Month = String.valueOf(months[gCalendar.get(Calendar.MONTH)]);

        txt.setText(Month);
        return view;
    }

    public static ExpenditureFragment newInstance() {
        return new ExpenditureFragment();
    }


}