package com.example.myfirstapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.mgr.HistoryManager;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private AppCompatSpinner spinner;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private HistoryManager manager = new HistoryManager();
    private ArrayList<GroceryList> gListArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history_ui, container, false);
        gListArray = this.manager.getgListArray();
        spinner = (AppCompatSpinner) view.findViewById(R.id.history_sorting_spinner);
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
        return view;
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
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

}
