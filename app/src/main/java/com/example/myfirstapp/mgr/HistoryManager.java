package com.example.myfirstapp.mgr;

import android.content.Context;

import com.example.myfirstapp.classes.GroceryList;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Daniel on 3/26/2017.
 */

public class HistoryManager {

    private ArrayList<GroceryList> gListArray;
    private GroceryManager groceryManager;

    public HistoryManager(Context context){
        groceryManager = new GroceryManager(context);
        loadGroceryLists();
    }

    public void loadGroceryLists(){
        gListArray = new ArrayList<GroceryList>(0);
        GroceryList gList1 = new GroceryList("testList",1);
        GroceryList gList2 = new GroceryList("testList2",2);
        gListArray.add(gList1);
        gListArray.add(gList2);
    }

    public GroceryList getGroceryList(int list_id){
        for(int i = 0; i < gListArray.size(); i++){
            if(gListArray.get(i).getGL_ID() == list_id){
                return gListArray.get(i);
            }
        }
        return null;
    }

    public ArrayList<GroceryList> getgListArray(){
        return this.gListArray;
    }
}
