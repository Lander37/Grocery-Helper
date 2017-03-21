package com.example.myfirstapp.mgr;

import com.example.myfirstapp.classes.GroceryList;
import com.example.myfirstapp.classes.Product;
import com.example.myfirstapp.classes.Supermarket;
import com.example.myfirstapp.ui.GroceryUI;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by XY on 8/3/2017.
 */

public class GroceryManager {

    private int currentListID;
    private int latestListID;
    private ArrayList<GroceryList> gListArray;
    //private GroceryList[] gListArray;

    public int getcurrentListID() {

        return this.currentListID;
    }

    /**
     *
     * @param list_ID
     */
    public void setcurrentListID(int list_ID) {

        this.currentListID = list_ID;
    }

    /**
     *
     * @param listName - name of new list
     */
    public void createNewList(String listName) {
        //Check if list with name == listName exists
        for(int i = 0; i < gListArray.size(); i++){
            if(gListArray.get(i).getName() == listName){
                //Invalid name, name already in use!
                return;
            }
        }

        int newId = latestListID + 1;
        GroceryList newGroceryList = new GroceryList(listName, newId);
        this.addGroceryList(newGroceryList);
        this.gListArray.add(newGroceryList);
        this.currentListID = newId;
    }

    /**
     *
     * @param tobeAdded
     */
    public void addGroceryList(GroceryList tobeAdded) {
        // TODO - implement GroceryManager.addGroceryList

    }

    /**
     *
     * @param startDate - start of search range
     * @param endDate - end of search range
     */
    public void getGroceryLists(Date startDate, Date endDate){
        //this.gListArray;
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void addProduct(int prod_ID, int QTY) {
        // TODO - implement GroceryManager.addProduct
        for (int i = 0; i < gListArray.size(); i++){
            if (currentListID == i){
                gListArray.get(i).addProdtoList(prod_ID , QTY);
                break;
            }
        }
    }

    /**
     *
     * @param prod_ID
     * @param QTY
     */
    public void removeProduct(String prod_ID, int QTY) {
        // TODO - implement GroceryManager.removeProduct
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod_ID
     */
    public int updateQTY(String prod_ID) {
        // TODO - implement GroceryManager.updateQTY
        throw new UnsupportedOperationException();
    }

    public void loadGroceryUI(){
        //this.getGroceryLists(new Date(), new Date());
        //GroceryUI.displayGroceryLists(gListArray.toArray(new GroceryList[gListArray.size()]));
    }

//    public void updateGroceryUI(){
//
//    }

}
