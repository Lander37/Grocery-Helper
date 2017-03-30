package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.HistoryManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HistoryActivity extends AppCompatActivity {

    private AppCompatSpinner spinner;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private HistoryManager manager;
    private ArrayList<GroceryList> gListArray;
    private ArrayList<String> gListDescriptions;
    private ArrayAdapter<String> gListAdapter;
    private ListView groceryLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.manager = new HistoryManager();
        gListArray = this.manager.getgListArray();
        spinner = (AppCompatSpinner) findViewById(R.id.history_sorting_spinner);
        groceryLists = (ListView) findViewById(R.id.history_main_list);
        gListAdapter = new ArrayAdapter<>(getBaseContext(),R.layout.content_history_activity,gListDescriptions);
        groceryLists.setAdapter(gListAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sortingBy = adapterView.getItemAtPosition(i).toString();
                showHistory(sortingBy);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        groceryLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                gotoSpecList(gListArray.get(i).getGL_ID());
            }
        });

        showHistory("Date");
    }

    /* --- For main history layout ---*/
    //Sorts gListArray by category: Date, quantity, total expenditure
    private void sortGroceryListArray(String sortingBy){

        if(sortingBy == "Date"){
            Collections.sort(gListArray, new Comparator<GroceryList>() {
                @Override
                public int compare(GroceryList gList1, GroceryList gList2)
                {

                    return  gList1.getDate().compareTo(gList2.getDate());
                }
            });
        } else if (sortingBy == "Quantity"){
            Collections.sort(gListArray, new Comparator<GroceryList>() {
                @Override
                public int compare(GroceryList gList1, GroceryList gList2)
                {
                    int gListQty1 = 0;
                    int gListQty2 = 0;

                    for(int i = 0; i < gList1.getArrayProduct().length; i++){
                        gListQty1 += gList1.getArrayProduct()[i][1];
                    }

                    for(int i = 0; i < gList2.getArrayProduct().length; i++){
                        gListQty2 += gList2.getArrayProduct()[i][1];
                    }

                    if(gListQty1 < gListQty2){
                        return -1;
                    } else if (gListQty1 > gListQty2){
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

        } else if (sortingBy == "Price"){
            Collections.sort(gListArray, new Comparator<GroceryList>() {
                @Override
                public int compare(GroceryList gList1, GroceryList gList2)
                {
                    if(gList1.getTotalCost() < gList2.getTotalCost()){
                        return -1;
                    } else if(gList1.getTotalCost() > gList2.getTotalCost()){
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }
    }

    public void showHistory(String sortingBy){
        sortGroceryListArray(sortingBy);

        for(int i = 0; i < gListArray.size(); i++){
            gListDescriptions.set(i,"List: " + gListArray.get(i).getName() + "\n Total spent: "  + gListArray.get(i).getTotalCost());
        }
    }

    public void gotoSpecList(int list_id){
        Intent intent = new Intent(this, HistorySpecListActivity.class);
        intent.putExtra("list_id",list_id);
        startActivity(intent);
    }

}
