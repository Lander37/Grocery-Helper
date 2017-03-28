package com.example.myfirstapp.mgr;

import android.content.Intent;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.ui.CreateProfileActivity;
import com.example.myfirstapp.ui.HistorySpecListActivity;

import java.util.ArrayList;

/**
 * Created by Daniel on 3/26/2017.
 */

public class HistoryManager {

    private ArrayList<GroceryList> gListArray;

    public HistoryManager(){
    }

    public void loadGroceryLists(){

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
