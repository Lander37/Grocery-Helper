package com.example.myfirstapp.ui;

import android.content.Intent;
import java.util.Calendar ;
import java.util.Date;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import android.widget.TextView;
import android.view.View;
import android.R.id;
import com.example.myfirstapp.R;




public class expenditure_main extends AppCompatActivity {
TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_main);

        txt = (TextView)findViewById(R.id.currentmonth);
        String months [] = {
                "January","February","March","April",
                "May","June","July","August",
                "September","October","November","December"};
        GregorianCalendar gcalendar = new GregorianCalendar();
        String Month = String.valueOf(months[gcalendar.get(Calendar.MONTH)]);

        txt.setText(Month);
        }


    public void goToBudgetCalculator() {
        Intent intent = new Intent(this, budget_calculator.class);
        startActivity(intent);
    }

    private void launchActivity() {

        Intent intent = new Intent(this, expenditure_main.class);
        startActivity(intent);
    }


}