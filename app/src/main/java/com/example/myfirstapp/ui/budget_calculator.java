package com.example.myfirstapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myfirstapp.R;

import android.view.View;

public class budget_calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_calculator);

    }

    public void buttonOnCalculatorConfirm() {
        Intent intent = new Intent(this, expenditure_main.class);
        startActivity(intent);
    }


    private void launchActivity() {

        Intent intent = new Intent(this, expenditure_main.class);
        startActivity(intent);
    }
}