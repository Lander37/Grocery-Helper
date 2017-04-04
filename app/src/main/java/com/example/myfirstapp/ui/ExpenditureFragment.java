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
import com.example.myfirstapp.dbHelpers.DatabaseAccess;
import static com.example.myfirstapp.ui.MainActivity.thisUsername;


public class ExpenditureFragment extends Fragment {
    private DatabaseAccess databaseAccess;
    private TextView txt;
    private TextView tvMonthlyExpenditure;
    private Button btInputBudget;
    private String totalMonthlyExpenditure;

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
        this.databaseAccess= DatabaseAccess.getInstance(getContext());
        String months [] = {
                "January","February","March","April",
                "May","June","July","August",
                "September","October","November","December"};
        Calendar gCalendar = GregorianCalendar.getInstance();


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

        databaseAccess.open();
        String month = "0";
        if (gCalendar.get(Calendar.MONTH)<10){
            month = (currentYear + "-" + "0" + Integer.toString(gCalendar.get(Calendar.MONTH)+1));
        }
        else{
            month = (currentYear + "-" + Integer.toString(gCalendar.get(Calendar.MONTH)+1));
        }
        System.out.println(month);
        totalMonthlyExpenditure = String.valueOf(databaseAccess.getMonthlyExpenditure(month));
        tvMonthlyExpenditure = (TextView) view.findViewById(R.id.expenditure);
        tvMonthlyExpenditure.setText("$" + totalMonthlyExpenditure);
        databaseAccess.close();

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