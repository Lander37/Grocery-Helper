package com.example.myfirstapp.mgr;

import com.example.myfirstapp.ui.HistoryUI;
import com.example.myfirstapp.classes.GroceryList;

import java.util.ArrayList;

/**
 * Created by Daniel on 3/26/2017.
 */

public class HistoryManager {

    private HistoryUI linkedActivity;
    private ArrayList<GroceryList> gListArray;

    public HistoryManager(){

    }

    public void loadGroceryLists(){

    }

    public ArrayList<GroceryList> getgListArray(){
        return this.gListArray;
    }
}
