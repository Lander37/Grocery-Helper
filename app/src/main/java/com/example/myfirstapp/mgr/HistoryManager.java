package com.example.myfirstapp.mgr;

import android.content.Context;
import android.database.Cursor;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.dbHelpers.DatabaseAccess;

import java.util.ArrayList;
/**
 * Created by Daniel on 3/26/2017.
 */

public class HistoryManager {

    private DatabaseAccess databaseAccess;
    private ArrayList<GroceryList> gListArray;

    public HistoryManager(Context context){
        this.databaseAccess = DatabaseAccess.getInstance(context);
        gListArray = new ArrayList<GroceryList>(0);
        loadGroceryLists();
    }

    public void loadGroceryLists(){
        databaseAccess.open();
        Cursor cursor = databaseAccess.pullGLists();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int addToHistory = cursor.getInt(cursor.getColumnIndex("isHistory"));

                if(addToHistory == 1) {
                    GroceryList gList;

                    // Read columns data
                    int id = (int) cursor.getLong(cursor.getColumnIndex("_id"));
                    String listName = cursor.getString(cursor.getColumnIndex("Name"));
                    float totalCost = (float) cursor.getDouble(cursor.getColumnIndex("TotalCost"));

                    // Make Grocery List
                    gList = new GroceryList(listName, id);
                    gList.setTotalCost(totalCost);
                    gListArray.add(gList);
                }
            }
        }
        databaseAccess.close();

        //For testing purposes, remove when ready to test actual
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
