package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.HistoryManager;

import java.util.ArrayList;

public class HistoryUI extends AppCompatActivity {

    private AppCompatSpinner spinner;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private HistoryManager manager = new HistoryManager(this);
    private ArrayList<GroceryList> gListArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_ui);
        gListArray = this.manager.getgListArray();
        spinner = (AppCompatSpinner) findViewById(R.id.history_sorting_spinner);
        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner.setOnItemSelectedListener(spinnerListener);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


    }

    private GroceryList[] sortedGroceryListArray(String sortingBy){
        GroceryList[] sortedArray = new GroceryList[gListArray.size()];

        if(sortingBy == "Date"){
            for(int i = 0; i <gListArray.size(); i++){
                //if(gListArray.get(i).getDate()){}
            }
        } else if (sortingBy == "Quantity"){
            for(int i = 0; i <gListArray.size(); i++){
                //if(gListArray.get(i).getArrayProduct().length){}
            }
        } else if (sortingBy == "Price"){
            for(int i = 0; i <gListArray.size(); i++){
                //if(gListArray.get(i).getTotalCost()){}
            }
        }

        return sortedArray;
    }

    public void showHistory(String sortingBy){
        GroceryList[] sortedArray = sortedGroceryListArray(sortingBy);

        for(int i = 0; i < sortedArray.length; i++){

        }
    }

}
