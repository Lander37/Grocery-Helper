package com.example.myfirstapp.ui;
/**
 * ExpenditureFragment.jave
 * @author
 */

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

    /**
     *
     * @param savedInstanceState
     */
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
        String months [] = {
                "January","February","March","April",
                "May","June","July","August",
                "September","October","November","December"};
        GregorianCalendar gCalendar = new GregorianCalendar();

        View view = inflater.inflate(R.layout.fragment_expenditure, container, false);
        btInputBudget = (Button) view.findViewById(R.id.inputBudget);

        btInputBudget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(BudgetFragment.newInstance(),"Expenditure");
            }
        });

        txt = (TextView)view.findViewById(R.id.month);
        String currentMonth = String.valueOf(months[gCalendar.get(Calendar.MONTH)]);
        txt.setText(currentMonth);


        txt = (TextView)view.findViewById(R.id.year);
        String currentYear = String.valueOf((gCalendar.get(Calendar.YEAR)));
        txt.setText(currentYear);


        return view;
    }

    /**
     *
     * @return
     */
    public static ExpenditureFragment newInstance() {
        return new ExpenditureFragment();
    }


}