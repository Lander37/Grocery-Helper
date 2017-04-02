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
        String months [] = {
                "January","February","March","April",
                "May","June","July","August",
                "September","October","November","December"};
        GregorianCalendar gCalendar = new GregorianCalendar();

        View view = inflater.inflate(R.layout.activity_expenditure_main, container, false);
        btInputBudget = (Button) view.findViewById(R.id.inputBudget);

        btInputBudget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((NavigationActivity)getActivity()).replaceThis(BudgetFragment.newInstance(),"Expenditure");
            }
        });

        txt = (TextView)view.findViewById(R.id.currentmonth);
        String currentMonth = String.valueOf(months[gCalendar.get(Calendar.MONTH)]);
        txt.setText(currentMonth);

        txt = (TextView)view.findViewById(R.id.threemonths);
        String threeMonths = String.valueOf((months[(((gCalendar.get(Calendar.MONTH)-3)+12)%12)]));
        txt.setText(threeMonths);

        txt = (TextView)view.findViewById(R.id.twomonths);
        String twoMonths = String.valueOf((months[(((gCalendar.get(Calendar.MONTH)-2)+12)%12)]));
        txt.setText(twoMonths);

        txt = (TextView)view.findViewById(R.id.lastmonth);
        String lastMonth = String.valueOf((months[(((gCalendar.get(Calendar.MONTH)-1)+12)%12)]));
        txt.setText(lastMonth);





        return view;
    }

    public static ExpenditureFragment newInstance() {
        return new ExpenditureFragment();
    }


}