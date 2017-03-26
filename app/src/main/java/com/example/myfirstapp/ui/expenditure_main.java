package com.example.myfirstapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myfirstapp.R;

public class expenditure_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_main);
    }
    public void goToBudgetCalculator (View view){
        Intent intent = new Intent (this, budget_calculator.class);
        startActivity(intent);
    }


    private void launchActivity() {

        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
    }
}