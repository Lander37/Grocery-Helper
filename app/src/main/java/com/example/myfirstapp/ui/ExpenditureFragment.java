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


        txt = (TextView)view.findViewById(R.id.currentyear);
        String currentYear = String.valueOf((gCalendar.get(Calendar.YEAR)));
        txt.setText(currentYear);

        txt = (TextView)view.findViewById(R.id.threemonthsyear);
        if (gCalendar.get(Calendar.MONTH) >= 2){
            String threeMonthsYear = String.valueOf((gCalendar.get(Calendar.YEAR)));
            txt.setText(threeMonthsYear);
        }
        else {
            String threeMonthsYear = String.valueOf((gCalendar.get(Calendar.YEAR)));
            txt.setText(threeMonthsYear);
        }

        txt = (TextView)view.findViewById(R.id.twomonthsyear);
        if (gCalendar.get(Calendar.MONTH) >= 1){
            String twoMonthsYear = String.valueOf((gCalendar.get(Calendar.YEAR)));
            txt.setText(twoMonthsYear);
        }
        else {
            String twoMonthsYear = String.valueOf((gCalendar.get(Calendar.YEAR)));
            txt.setText(twoMonthsYear);
        }

        txt = (TextView)view.findViewById(R.id.lastmonthyear);
        if (gCalendar.get(Calendar.MONTH) >= 0){
            String lastMonthYear = String.valueOf((gCalendar.get(Calendar.YEAR)));
            txt.setText(lastMonthYear);
        }
        else {
            String lastMonthYear = String.valueOf((gCalendar.get(Calendar.YEAR)));
            txt.setText(lastMonthYear);
        }

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