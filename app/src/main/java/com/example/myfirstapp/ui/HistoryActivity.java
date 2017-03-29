package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.HistoryManager;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private AppCompatSpinner spinner;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private HistoryManager manager;
    private ArrayList<GroceryList> gListArray;
    private ListView groceryLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.manager = new HistoryManager();
        gListArray = this.manager.getgListArray();
        spinner = (AppCompatSpinner) findViewById(R.id.history_sorting_spinner);
        groceryLists = (ListView) findViewById(R.id.history_main_list);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sortingBy = adapterView.getItemAtPosition(i).toString();
                //showHistory(sortingBy);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        showHistory("Date");
        groceryLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                gotoSpecList(gListArray.get(i).getGL_ID());
            }
        });
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
            list.setText("List: " + sortedArray[i].getName() + "\n Total spent: "  + sortedArray[i].getTotalCost());
            groceryLists.addView(list);
        }
    }

    public void gotoSpecList(int list_id){
        Intent intent = new Intent(this, HistorySpecListActivity.class);
        intent.putExtra("list_id",list_id);
        startActivity(intent);
    }

}
