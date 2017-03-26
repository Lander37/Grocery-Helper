package com.example.myfirstapp.mgr;

import android.content.Intent;

import com.example.myfirstapp.HistoryActivity;
import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.ui.CreateProfileActivity;
import com.example.myfirstapp.ui.HistorySpecListActivity;

import java.util.ArrayList;

/**
 * Created by Daniel on 3/26/2017.
 */

public class HistoryManager {

    private HistoryActivity linkedActivity;
    private ArrayList<GroceryList> gListArray;

    public HistoryManager(HistoryActivity linkedActivity){
        this.linkedActivity = linkedActivity;
    }

    public void loadGroceryLists(){

    }

    public ArrayList<GroceryList> getgListArray(){
        return this.gListArray;
    }
}
