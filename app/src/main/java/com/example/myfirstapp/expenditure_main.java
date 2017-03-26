package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class expenditure_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_main);
    }
    public void goToBudgetCalculator (View view){
        Intent intent = new Intent (this, budget_calculator);
        startActivity(intent);
    }


    private void launchActivity() {

        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
    }
}




    thirdmonth = int getMonth();

            Calendar.get(Calendar.MONTH)
            float secondmonth = Calendar.get(Calendar.MONTH)
            = date.add(Calendar.MONTH, 1);
            float onemonth = Calendar.get(Calendar.MONTH)