package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.HistoryManager;
import com.example.myfirstapp.ui.HistorySpecListActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private AppCompatSpinner spinner;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private HistoryManager manager;
    private ArrayList<GroceryList> gListArray;
    private NestedScrollView groceryLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_ui);
        this.manager = new HistoryManager();
        gListArray = this.manager.getgListArray();
        spinner = (AppCompatSpinner) findViewById(R.id.history_sorting_spinner);
        groceryLists = (NestedScrollView) findViewById(R.id.history_main_scrollView);
        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sortingBy = adapterView.getItemAtPosition(i).toString();
                //showHistory(sortingBy);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner.setOnItemSelectedListener(spinnerListener);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


    }

    /* --- For main history layout ---*/
    //Return array of GroceryLists sorted by category
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
            TextView list = new TextView(getBaseContext());
            list.setHeight(20);
            list.setText("List: " + sortedArray[i].getName());
            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView text = (TextView) view;
                    gotoSpecList(text.getText().toString());
                }
            });
            groceryLists.addView(list);
        }
    }

    public void gotoSpecList(String list_id){
        Intent intent = new Intent(this, HistorySpecListActivity.class);
        intent.putExtra("list_id",list_id);
        startActivity(intent);
    }

}
